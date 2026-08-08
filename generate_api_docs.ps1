[CmdletBinding()]
param(
    [string]$PageUrl = "https://remanga.org/card",
    [string]$OutputDirectory = ""
)

$ErrorActionPreference = "Stop"
$ProgressPreference = "Continue"
[Net.ServicePointManager]::SecurityProtocol = [Net.SecurityProtocolType]::Tls12
Add-Type -AssemblyName System.Net.Http

$Utf8NoBom = New-Object Text.UTF8Encoding($false)
if ([string]::IsNullOrWhiteSpace($OutputDirectory)) { $OutputDirectory = $PSScriptRoot }

function Write-Utf8Text {
    param([string]$Path, [string]$Text)
    [IO.File]::WriteAllText($Path, $Text, $script:Utf8NoBom)
}

function Get-ApiGroup {
    param([string]$Path)
    $parts = @($Path.Trim('/') -split '/')
    if ($parts.Count -lt 2) { return "other" }
    $index = 1
    if ($parts[$index] -match '^v\d+$') { $index++ }
    if ($index -ge $parts.Count) { return "other" }
    return $parts[$index]
}

function Get-PathParameters {
    param([string]$Path)
    return @([regex]::Matches($Path, '\{([^}]+)\}') | ForEach-Object { $_.Groups[1].Value } | Select-Object -Unique)
}

New-Item -ItemType Directory -Force -Path $OutputDirectory | Out-Null

$client = New-Object System.Net.Http.HttpClient
$client.Timeout = [TimeSpan]::FromSeconds(90)
$client.DefaultRequestHeaders.UserAgent.ParseAdd("Mozilla/5.0 (Windows NT 10.0; Win64; x64) ReMangaApiDocGenerator/1.0")

Write-Host "Чтение $PageUrl ..."
$html = $client.GetStringAsync($PageUrl).GetAwaiter().GetResult()
$pageUri = [Uri]$PageUrl
$siteOrigin = $pageUri.GetLeftPart([UriPartial]::Authority)

$releaseMatch = [regex]::Match($html, '\\"GIT_HASH\\":\\"([^\\"]+)\\"')
$releaseId = if ($releaseMatch.Success) { $releaseMatch.Groups[1].Value } else { "unknown" }
$scriptPaths = @([regex]::Matches($html, '/_next/static/[^"'' ]+\.js') | ForEach-Object Value | Sort-Object -Unique)
if ($scriptPaths.Count -eq 0) { throw "На странице $PageUrl не найдены JavaScript-сборки" }

Write-Host "Версия frontend: $releaseId; JavaScript-сборок: $($scriptPaths.Count)"
$downloads = New-Object Collections.Generic.List[object]
foreach ($path in $scriptPaths) {
    $downloads.Add([PSCustomObject]@{
        Path = $path
        Task = $client.GetStringAsync("$siteOrigin$path")
    })
}

$allTasks = [System.Threading.Tasks.Task[]]@($downloads | ForEach-Object { $_.Task })
[System.Threading.Tasks.Task]::WaitAll($allTasks, 90000) | Out-Null

$endpointMatches = New-Object Collections.Generic.List[object]
$urlRegex = New-Object regex('url:"(?<path>/api/[^"?#]+)"')
$methodRegex = New-Object regex('\.(?<method>get|post|put|patch|delete)\(')

$bundleIndex = 0
foreach ($download in $downloads) {
    $bundleIndex++
    Write-Progress -Id 1 -Activity "Извлечение маршрутов API" -Status "$bundleIndex / $($downloads.Count)" -PercentComplete ([Math]::Floor($bundleIndex * 100 / $downloads.Count))
    if ($download.Task.Status -ne [System.Threading.Tasks.TaskStatus]::RanToCompletion) {
        Write-Warning "Не удалось обработать сборку: $($download.Path)"
        continue
    }

    $javascript = $download.Task.Result
    foreach ($urlMatch in $urlRegex.Matches($javascript)) {
        $contextStart = [Math]::Max(0, $urlMatch.Index - 900)
        $contextLength = $urlMatch.Index - $contextStart
        $prefix = $javascript.Substring($contextStart, $contextLength)
        $methodMatches = $methodRegex.Matches($prefix)
        if ($methodMatches.Count -eq 0) { continue }

        $method = $methodMatches[$methodMatches.Count - 1].Groups['method'].Value.ToUpperInvariant()
        $afterLength = [Math]::Min(450, $javascript.Length - $urlMatch.Index)
        $snippet = $javascript.Substring($contextStart, $contextLength + $afterLength)
        $contentTypeMatch = [regex]::Match($snippet, 'Content-Type":"([^"]+)"')
        $contentType = if ($method -in @('POST', 'PUT', 'PATCH') -and $contentTypeMatch.Success) { $contentTypeMatch.Groups[1].Value } else { $null }
        $path = $urlMatch.Groups['path'].Value

        $endpointMatches.Add([PSCustomObject]@{
            method = $method
            path = $path
            group = Get-ApiGroup -Path $path
            path_parameters = @(Get-PathParameters -Path $path)
            bearer_capable = ($snippet -match 'scheme:"bearer"')
            content_type = $contentType
            source_bundle = [IO.Path]::GetFileName($download.Path)
        })
    }
}
$client.Dispose()
Write-Progress -Id 1 -Activity "Извлечение маршрутов API" -Completed

$deduplicated = New-Object Collections.Generic.List[object]
foreach ($set in @($endpointMatches | Group-Object method, path)) {
    $first = $set.Group | Select-Object -First 1
    $sources = @($set.Group.source_bundle | Sort-Object -Unique)
    $deduplicated.Add([PSCustomObject]@{
        method = $first.method
        path = $first.path
        group = $first.group
        path_parameters = @($first.path_parameters)
        bearer_capable = [bool]$first.bearer_capable
        content_type = $first.content_type
        source_bundles = $sources
    })
}
$endpoints = @($deduplicated | Sort-Object group, path, method)
if ($endpoints.Count -eq 0) { throw "Не удалось извлечь ни одного маршрута API." }

$generatedMomentUtc = (Get-Date).ToUniversalTime()
$generatedAt = $generatedMomentUtc.ToString("o")
$moscowTimeZone = [TimeZoneInfo]::FindSystemTimeZoneById("Russian Standard Time")
$generatedAtDisplay = [TimeZoneInfo]::ConvertTimeFromUtc($generatedMomentUtc, $moscowTimeZone).ToString("HH:mm dd.MM.yyyy", [Globalization.CultureInfo]::InvariantCulture)
$snapshot = [PSCustomObject]@{
    generated_at_utc = $generatedAt
    generated_at = $generatedAtDisplay
    source_page = $PageUrl
    frontend_release = $releaseId
    api_base_url = "https://api.remanga.org"
    media_base_url = "https://remanga.org"
    endpoint_count = $endpoints.Count
    endpoints = $endpoints
}

$jsonPath = Join-Path $OutputDirectory "endpoints.json"
$csvPath = Join-Path $OutputDirectory "endpoints.csv"
$markdownPath = Join-Path $OutputDirectory "API_REFERENCE.md"
$snapshotPath = Join-Path $OutputDirectory "snapshot-summary.json"

Write-Utf8Text -Path $jsonPath -Text (($snapshot | ConvertTo-Json -Depth 8) + [Environment]::NewLine)
$endpoints | Select-Object method, path, group, @{N='path_parameters';E={$_.path_parameters -join ','}}, bearer_capable, content_type, @{N='source_bundles';E={$_.source_bundles -join ','}} | Export-Csv -LiteralPath $csvPath -NoTypeInformation -Encoding UTF8
Write-Utf8Text -Path $snapshotPath -Text (([PSCustomObject]@{
    generated_at_utc = $generatedAt
    generated_at = $generatedAtDisplay
    source_page = $PageUrl
    frontend_release = $releaseId
    endpoint_count = $endpoints.Count
    groups = @($endpoints | Group-Object group | Sort-Object Count -Descending | ForEach-Object { [PSCustomObject]@{ name = $_.Name; count = $_.Count } })
    methods = @($endpoints | Group-Object method | Sort-Object Name | ForEach-Object { [PSCustomObject]@{ name = $_.Name; count = $_.Count } })
} | ConvertTo-Json -Depth 6) + [Environment]::NewLine)

$md = New-Object Text.StringBuilder
[void]$md.AppendLine("# Неофициальный справочник маршрутов ReManga API")
[void]$md.AppendLine("")
[void]$md.AppendLine("Справочник автоматически сформирован из публичных JavaScript-сборок сайта [ReManga](https://remanga.org).")
[void]$md.AppendLine("")
[void]$md.AppendLine("- Дата генерации: ``$generatedAtDisplay``")
[void]$md.AppendLine("- Версия frontend: ``$releaseId``")
[void]$md.AppendLine("- Базовый адрес API: ``https://api.remanga.org``")
[void]$md.AppendLine("- Базовый адрес медиа: ``https://remanga.org``")
[void]$md.AppendLine("- Извлечено маршрутов: **$($endpoints.Count)**")
[void]$md.AppendLine("")
[void]$md.AppendLine("> Это не официальная документация ReManga. Маршруты могут измениться без предупреждения. Клиент сайта помечает маршруты как совместимые с Bearer-токеном, но это не доказывает обязательность авторизации. Полные схемы запросов и ответов отсутствуют в production-сборке, поэтому справочник их не выдумывает.")
[void]$md.AppendLine("")
[void]$md.AppendLine("## Безопасность и правила использования")
[void]$md.AppendLine("")
[void]$md.AppendLine("Не проверяйте изменяющие методы на реальных аккаунтах. Маршруты POST, PUT, PATCH и DELETE перечислены только для полноты. Соблюдайте условия ReManga, ограничения частоты запросов, авторские права и robots.txt. Никогда не собирайте пароли пользователей или cookies браузера.")
[void]$md.AppendLine("")
[void]$md.AppendLine("## Сводка по HTTP-методам")
[void]$md.AppendLine("")
[void]$md.AppendLine("| Метод | Количество |")
[void]$md.AppendLine("|---|---:|")
foreach ($methodGroup in @($endpoints | Group-Object method | Sort-Object Name)) {
    [void]$md.AppendLine("| $($methodGroup.Name) | $($methodGroup.Count) |")
}
[void]$md.AppendLine("")
[void]$md.AppendLine("## Проверенные полезные маршруты только для чтения")
[void]$md.AppendLine("")
[void]$md.AppendLine("На момент генерации эти маршруты отдельно проверены и возвращали публичные данные без токена доступа:")
[void]$md.AppendLine("")
[void]$md.AppendLine("| Назначение | Маршрут |")
[void]$md.AppendLine("|---|---|")
[void]$md.AppendLine('| Каталог карточек | `GET /api/inventory/catalog/` |')
[void]$md.AppendLine('| Публичный профиль пользователя | `GET /api/v2/users/{user_id}/` |')
[void]$md.AppendLine('| Инвентарь карточек с группировкой | `GET /api/v3/inventory/items/cards/{user_id}/` |')
[void]$md.AppendLine('| Отдельные экземпляры карточек | `GET /api/v2/inventory/items/cards/{user_id}/` |')
[void]$md.AppendLine("")
[void]$md.AppendLine("В веб-клиенте обнаружены стандартные параметры каталога: ``count``, ``page``, ``rank`` и ``ordering``. Значения рангов: ``rank_a``, ``rank_s``, ``rank_re`` и ``rank_ev``.")

$groupNames = @{
    activity = "Активность"
    battlepass = "Боевой пропуск"
    billing = "Платежи"
    clubs = "Клубы"
    dashboard = "Панель управления"
    entries = "Записи"
    events = "События"
    forms = "Формы"
    forum = "Форум"
    functions = "Служебные функции"
    inventory = "Инвентарь"
    publishers = "Издатели"
    quizzes = "Викторины"
    search = "Поиск"
    shop = "Магазин"
    subscription = "Подписка"
    titles = "Тайтлы"
    users = "Пользователи"
}

foreach ($group in @($endpoints | Group-Object group | Sort-Object Name)) {
    $groupTitle = if ($groupNames.ContainsKey($group.Name)) { $groupNames[$group.Name] } else { $group.Name }
    [void]$md.AppendLine("")
    [void]$md.AppendLine("## $groupTitle - ``$($group.Name)`` ($($group.Count))")
    [void]$md.AppendLine("")
    [void]$md.AppendLine("| Метод | Путь | Параметры пути | Bearer | Тип содержимого |")
    [void]$md.AppendLine("|---|---|---|:---:|---|")
    foreach ($endpoint in @($group.Group | Sort-Object path, method)) {
        $parameters = if ($endpoint.path_parameters.Count) { ($endpoint.path_parameters -join ', ') } else { '' }
        $bearer = if ($endpoint.bearer_capable) { 'да' } else { 'не обнаружен' }
        $contentType = if ($endpoint.content_type) { $endpoint.content_type } else { '' }
        [void]$md.AppendLine("| $($endpoint.method) | ``$($endpoint.path)`` | $parameters | $bearer | $contentType |")
    }
}

Write-Utf8Text -Path $markdownPath -Text $md.ToString()

Write-Host ""
Write-Host "Готово. Извлечено уникальных маршрутов API: $($endpoints.Count)."
Write-Host "Markdown: $markdownPath"
Write-Host "JSON: $jsonPath"
Write-Host "CSV: $csvPath"

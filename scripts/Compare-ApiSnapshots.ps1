[CmdletBinding()]
param(
    [Parameter(Mandatory = $true)][string]$PreviousPath,
    [Parameter(Mandatory = $true)][string]$CurrentPath,
    [Parameter(Mandatory = $true)][string]$MarkdownPath,
    [Parameter(Mandatory = $true)][string]$JsonPath
)

$ErrorActionPreference = 'Stop'

function Get-EndpointKey($endpoint) {
    return "$($endpoint.method.ToUpperInvariant()) $($endpoint.path)"
}

function Get-ComparableValue($endpoint, [string]$property) {
    $value = $endpoint.$property
    if ($property -eq 'path_parameters') {
        return (@($value) | Sort-Object) -join ', '
    }
    if ($null -eq $value -or $value -eq '') { return 'нет' }
    if ($value -is [bool]) { return $(if ($value) { 'да' } else { 'нет' }) }
    return [string]$value
}

function New-EndpointMap($snapshot) {
    $map = @{}
    foreach ($endpoint in @($snapshot.endpoints)) {
        $map[(Get-EndpointKey $endpoint)] = $endpoint
    }
    return $map
}

function Write-Utf8Bom([string]$path, [string]$content) {
    $directory = Split-Path -Parent $path
    if ($directory) { New-Item -ItemType Directory -Path $directory -Force | Out-Null }
    $encoding = New-Object System.Text.UTF8Encoding($true)
    [System.IO.File]::WriteAllText($path, $content, $encoding)
}

$previous = Get-Content -Raw -Encoding UTF8 -LiteralPath $PreviousPath | ConvertFrom-Json
$current = Get-Content -Raw -Encoding UTF8 -LiteralPath $CurrentPath | ConvertFrom-Json
$previousMap = New-EndpointMap $previous
$currentMap = New-EndpointMap $current

$added = @(
    foreach ($key in $currentMap.Keys | Sort-Object) {
        if (-not $previousMap.ContainsKey($key)) {
            $endpoint = $currentMap[$key]
            [ordered]@{ method = $endpoint.method; path = $endpoint.path }
        }
    }
)

$removed = @(
    foreach ($key in $previousMap.Keys | Sort-Object) {
        if (-not $currentMap.ContainsKey($key)) {
            $endpoint = $previousMap[$key]
            [ordered]@{ method = $endpoint.method; path = $endpoint.path }
        }
    }
)

$labels = [ordered]@{
    group = 'группа'
    path_parameters = 'параметры пути'
    bearer_capable = 'Bearer-токен'
    content_type = 'Content-Type'
}

$changed = @(
    foreach ($key in $currentMap.Keys | Sort-Object) {
        if (-not $previousMap.ContainsKey($key)) { continue }
        $before = $previousMap[$key]
        $after = $currentMap[$key]
        $details = @(
            foreach ($property in $labels.Keys) {
                $beforeValue = Get-ComparableValue $before $property
                $afterValue = Get-ComparableValue $after $property
                if ($beforeValue -ne $afterValue) {
                    "$($labels[$property]): $beforeValue -> $afterValue"
                }
            }
        )
        if ($details.Count -gt 0) {
            [ordered]@{ method = $after.method; path = $after.path; details = $details }
        }
    }
)

$report = [ordered]@{
    previous_frontend_release = $previous.frontend_release
    frontend_release = $current.frontend_release
    generated_at = $current.generated_at
    endpoint_count = [int]$current.endpoint_count
    added_count = $added.Count
    changed_count = $changed.Count
    removed_count = $removed.Count
    added = $added
    changed = $changed
    removed = $removed
}

$json = $report | ConvertTo-Json -Depth 20
Write-Utf8Bom $JsonPath (($json -replace "`r?`n", "`r`n") + "`r`n")

$lines = New-Object System.Collections.Generic.List[string]
$lines.Add("# Изменения ReManga API")
$lines.Add('')
$lines.Add("Frontend: ``$($previous.frontend_release)`` -> ``$($current.frontend_release)``")
$lines.Add('')
$lines.Add("Сформировано: $($current.generated_at)")
$lines.Add('')
$lines.Add("Всего маршрутов: **$($current.endpoint_count)**. Добавлено: **$($added.Count)**, изменено: **$($changed.Count)**, удалено: **$($removed.Count)**.")

foreach ($section in @(
    @{ title = 'Добавленные методы'; rows = $added; type = 'simple' },
    @{ title = 'Изменённые методы'; rows = $changed; type = 'changed' },
    @{ title = 'Удалённые методы'; rows = $removed; type = 'simple' }
)) {
    $lines.Add('')
    $lines.Add("## $($section.title) ($($section.rows.Count))")
    $lines.Add('')
    if ($section.rows.Count -eq 0) {
        $lines.Add('Нет изменений.')
        continue
    }
    foreach ($row in $section.rows) {
        $suffix = if ($section.type -eq 'changed') { ' - ' + (@($row.details) -join '; ') } else { '' }
        $lines.Add("- ``$($row.method) $($row.path)``$suffix")
    }
}

Write-Utf8Bom $MarkdownPath ((($lines -join "`r`n").TrimEnd()) + "`r`n")
Write-Host "Добавлено: $($added.Count), изменено: $($changed.Count), удалено: $($removed.Count)"

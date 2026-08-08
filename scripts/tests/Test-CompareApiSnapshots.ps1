$ErrorActionPreference = 'Stop'
$root = Join-Path $env:TEMP "remanga-api-diff-$([guid]::NewGuid().ToString('N'))"
New-Item -ItemType Directory -Path $root | Out-Null

try {
    $before = @{
        frontend_release = 'old12345'
        endpoints = @(
            @{ method = 'GET'; path = '/api/old/'; group = 'old'; path_parameters = @(); bearer_capable = $false; content_type = $null },
            @{ method = 'POST'; path = '/api/change/'; group = 'users'; path_parameters = @('id'); bearer_capable = $true; content_type = 'application/json' }
        )
    } | ConvertTo-Json -Depth 10
    $after = @{
        frontend_release = 'new67890'
        generated_at = '10:30 01.08.2026'
        endpoint_count = 2
        endpoints = @(
            @{ method = 'POST'; path = '/api/change/'; group = 'users'; path_parameters = @('id'); bearer_capable = $false; content_type = 'application/json' },
            @{ method = 'PUT'; path = '/api/new/'; group = 'users'; path_parameters = @(); bearer_capable = $true; content_type = 'application/json' }
        )
    } | ConvertTo-Json -Depth 10

    $beforePath = Join-Path $root 'before.json'
    $afterPath = Join-Path $root 'after.json'
    $markdownPath = Join-Path $root 'changes.md'
    $jsonPath = Join-Path $root 'changes.json'
    [IO.File]::WriteAllText($beforePath, $before, (New-Object Text.UTF8Encoding($true)))
    [IO.File]::WriteAllText($afterPath, $after, (New-Object Text.UTF8Encoding($true)))

    & (Join-Path $PSScriptRoot '..\Compare-ApiSnapshots.ps1') `
        -PreviousPath $beforePath -CurrentPath $afterPath `
        -MarkdownPath $markdownPath -JsonPath $jsonPath

    $result = Get-Content -Raw -Encoding UTF8 $jsonPath | ConvertFrom-Json
    if ($result.added_count -ne 1) { throw 'Ожидался один добавленный метод' }
    if ($result.changed_count -ne 1) { throw 'Ожидался один изменённый метод' }
    if ($result.removed_count -ne 1) { throw 'Ожидался один удалённый метод' }
    if ((Get-Content -Raw -Encoding UTF8 $markdownPath) -notmatch 'POST /api/change/') { throw 'Изменённый метод отсутствует в Markdown' }
    Write-Host 'Тест сравнения снимков пройден'
}
finally {
    Remove-Item -LiteralPath $root -Recurse -Force -ErrorAction SilentlyContinue
}

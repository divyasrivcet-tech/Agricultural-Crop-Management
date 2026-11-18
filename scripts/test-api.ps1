$ErrorActionPreference = "Stop"
$base = "http://localhost:8080"
$created = @{ farmer = $null; field = $null; crop = $null; season = $null; yield = $null }

function Invoke-Api($Method, $Path, $Body = $null) {
    if ($null -eq $Body) {
        return Invoke-RestMethod -Method $Method -Uri "$base$Path"
    }
    $json = $Body | ConvertTo-Json -Depth 5
    return Invoke-RestMethod -Method $Method -Uri "$base$Path" -ContentType "application/json" -Body $json
}

try {
    Write-Host "Checking database health..."
    $health = Invoke-Api "GET" "/api/health"
    if ($health.status -ne "Connected") { throw "Database health check did not return Connected." }
    Write-Host "PASS: health check"

    $tag = Get-Date -Format "yyyyMMddHHmmss"
    $created.farmer = Invoke-Api "POST" "/api/farmers" @{
        farmerName = "API Test $tag"; phone = "9000000000"; village = "Test Village"; district = "Erode"
    }
    Write-Host "PASS: create farmer"

    $created.field = Invoke-Api "POST" "/api/fields" @{
        farmerId = [int]$created.farmer.farmer_id; fieldName = "Test Field $tag"; areaAcres = 1.25
    }
    Write-Host "PASS: create field"

    $created.crop = Invoke-Api "POST" "/api/crops" @{
        cropName = "Test Crop $tag"; cropType = "Test"
    }
    Write-Host "PASS: create crop"

    $created.season = Invoke-Api "POST" "/api/seasons" @{
        seasonName = "Test Season $tag"; startDate = "2026-01-01"; endDate = "2026-04-30"
    }
    Write-Host "PASS: create season"

    $created.yield = Invoke-Api "POST" "/api/yield-records" @{
        fieldId = [int]$created.field.field_id
        cropId = [int]$created.crop.crop_id
        seasonId = [int]$created.season.season_id
        yieldKg = 123.45
    }
    Write-Host "PASS: create yield record"

    Invoke-Api "GET" "/api/yield-records/$($created.yield.yield_id)" | Out-Null
    Write-Host "PASS: retrieve yield record by ID"
    Write-Host "All smoke-test creation and retrieval checks passed."
}
finally {
    # Delete in reverse foreign-key order. Cleanup errors are displayed without hiding the test result.
    foreach ($item in @(
        @{ key = "yield"; path = "/api/yield-records"; id = "yield_id" },
        @{ key = "field"; path = "/api/fields"; id = "field_id" },
        @{ key = "crop"; path = "/api/crops"; id = "crop_id" },
        @{ key = "season"; path = "/api/seasons"; id = "season_id" },
        @{ key = "farmer"; path = "/api/farmers"; id = "farmer_id" }
    )) {
        $record = $created[$item.key]
        if ($null -ne $record -and $null -ne $record.($item.id)) {
            try {
                Invoke-Api "DELETE" "$($item.path)/$($record.($item.id))" | Out-Null
                Write-Host "Cleaned up $($item.key) test record."
            } catch {
                Write-Warning "Could not clean up $($item.key) test record: $($_.Exception.Message)"
            }
        }
    }
}

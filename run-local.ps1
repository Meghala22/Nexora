$projectRoot = Split-Path -Parent $MyInvocation.MyCommand.Path

Start-Process powershell -ArgumentList @(
  '-NoExit',
  '-Command',
  "cd '$projectRoot\backend'; `$env:SPRING_PROFILES_ACTIVE='dev'; mvn spring-boot:run"
)

Start-Process powershell -ArgumentList @(
  '-NoExit',
  '-Command',
  "cd '$projectRoot\frontend'; npm start"
)

Write-Host 'Starting Nexora locally...'
Write-Host 'Backend:  http://localhost:8080'
Write-Host 'Frontend: http://localhost:4200'

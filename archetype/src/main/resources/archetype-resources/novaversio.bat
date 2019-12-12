#set( $symbol_pount = '#' )
#set( $symbol_escape = '\' )
#set( $symbol_dollar = '$' )

@echo off 

IF "%~1" == "" GOTO SENSEPARAMS

@echo on
cmd /C mvn -DgroupId=${package} -DartifactId=* versions:set -DnewVersion=%*

@echo off
SETLOCAL EnableDelayedExpansion
for /F "tokens=1,2 delims=${symbol_pound}" %%a in ('"prompt ${symbol_pound}${symbol_dollar}H${symbol_pound}${symbol_dollar}E${symbol_pound} & echo on & for %%b in (1) do rem"') do (
  set "DEL=%%a"
)

@echo. 
@echo.
@echo off

GOTO EXIT

:ColorText
@echo off
<nul set /p ".=%DEL%" > "%~2"
findstr /v /a:%1 /R "^${symbol_dollar}" "%~2" nul
del "%~2" > nul 2>&1
goto :eof


:SENSEPARAMS

@echo.
@echo.
echo Falta versio
@echo.
@echo.


:EXIT

@echo off

echo optional parameters -Dcaib -Psqlgen

cmd /C mvn -DskipTests %* install

if %errorlevel% EQU 0 (

	@echo off
	IF DEFINED PROJECTEBASEARCHETYPE_DEPLOY_DIR (
      for /f "tokens=* delims=" %%x in (versio.txt) do set PROJECTEBASEARCHETYPE_VERSIO=%%x
	  @echo on
	  echo --------- COPIANT EAR %PROJECTEBASEARCHETYPE_VERSIO% ---------

	  xcopy /Y ear\target\projectebasearchetype.ear %PROJECTEBASEARCHETYPE_DEPLOY_DIR%

	) ELSE (
	  echo  =================================================================
	  echo    Definex la variable d'entorn PROJECTEBASEARCHETYPE_DEPLOY_DIR apuntant al
	  echo    directori de deploy del JBOSS  i automaticament s'hi copiara
	  echo    l'ear generat.
	  echo  =================================================================
	) 

)
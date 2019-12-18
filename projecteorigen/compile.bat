@echo off

type help.txt

cmd /C mvn -DskipTests %* install

if %errorlevel% EQU 0 (

	@echo off
	IF DEFINED PROJECTEBASE_DEPLOY_DIR (
	  @echo on
	  echo --------- COPIANT EAR ---------

	  xcopy /Y projectebase-ear\target\projectebase.ear %PROJECTEBASE_DEPLOY_DIR%

	) ELSE (
	  echo  =================================================================
	  echo    Definex la variable d'entorn PROJECTEBASE_DEPLOY_DIR 
	  echo    apuntant al directori de deploy del JBOSS  i automaticament 
	  echo    s'hi copiara l'ear generat.
	  echo  =================================================================
	) 

)
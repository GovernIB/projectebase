#set( $symbol_pount = '#' )
#set( $symbol_escape = '\' )
#set( $symbol_dollar = '$' )
@echo off

type help.txt

cmd /C mvn -DskipTests %* install

if %errorlevel% EQU 0 (

	@echo off
	IF DEFINED ${projectnameuppercase}_DEPLOY_DIR (
	  @echo on
	  echo --------- COPIANT EAR ---------

	  xcopy /Y ${artifactId}-ear${symbol_escape}target${symbol_escape}${artifactId}.ear %${projectnameuppercase}_DEPLOY_DIR%

	) ELSE (
	  echo  =================================================================
	  echo    Definex la variable d'entorn ${projectnameuppercase}_DEPLOY_DIR 
	  echo    apuntant al directori de deploy del JBOSS  i automaticament 
	  echo    s'hi copiara l'ear generat.
	  echo  =================================================================
	) 

)
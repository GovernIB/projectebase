#set( $symbol_pount = '#' )
#set( $symbol_escape = '\' )
#set( $symbol_dollar = '$' )
${symbol_pound}!/bin/bash

echo off
cat help.txt

env mvn -DskipTests ${symbol_dollar}@ install 

if [ ${symbol_dollar}? == 0 ]; then
  if [ "${projectnameuppercase}_DEPLOY_DIR" == "" ];  then

    echo  =================================================================
    echo    Definex la variable d${symbol_escape}'entorn ${projectnameuppercase}_DEPLOY_DIR 
    echo    apuntant al directori de deploy del JBOSS  i automaticament 
    echo    s${symbol_escape}'hi copiara l${symbol_escape}'ear generat.
    echo  =================================================================
  
  else
  
    echo on
    echo --------- COPIANT EAR ---------
    cp ./ear/target/${artifactId}.ear ${projectnameuppercase}_DEPLOY_DIR

  fi
fi



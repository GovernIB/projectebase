#!/bin/bash

echo off
cat help.txt

env mvn -DskipTests $@ install 

if [ $? == 0 ]; then
  if [ "PROJECTEBASE_DEPLOY_DIR" == "" ];  then

    echo  =================================================================
    echo    Definex la variable d\'entorn PROJECTEBASE_DEPLOY_DIR 
    echo    apuntant al directori de deploy del JBOSS  i automaticament 
    echo    s\'hi copiara l\'ear generat.
    echo  =================================================================
  
  else
  
    echo on
    echo --------- COPIANT EAR ---------
    cp ./ear/target/projectebase.ear PROJECTEBASE_DEPLOY_DIR

  fi
fi



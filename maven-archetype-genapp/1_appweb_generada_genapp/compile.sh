#!/bin/bash

echo off
cat help.txt

env mvn -DskipTests $@ install 

if [ $? == 0 ]; then
  if [ "PROJECTEBASEARCHETYPE_DEPLOY_DIR" == "" ];  then

    echo  =================================================================
    echo    Definex la variable d\'entorn PROJECTEBASEARCHETYPE_DEPLOY_DIR apuntant al
    echo    directori de deploy del JBOSS  i automaticament s\'hi copiara
    echo    l\'ear generat.
    echo  =================================================================
  
  else
  
    echo on
    echo --------- COPIANT EAR ---------
    cp ./ear/target/projectebasearchetype.ear $PROJECTEBASEARCHETYPE_DEPLOY_DIR

  fi
fi



#!/bin/bash

env mvn -DgroupId=es.caib.projectebase -DartifactId=* versions:set -DnewVersion=$@  


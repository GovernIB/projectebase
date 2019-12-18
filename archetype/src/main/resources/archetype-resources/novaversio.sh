#!/bin/bash

env mvn -DgroupId=${package} -DartifactId=* versions:set -DnewVersion=$@  


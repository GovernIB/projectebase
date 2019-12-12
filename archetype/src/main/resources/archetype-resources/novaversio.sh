#set( $symbol_pount = '#' )
#set( $symbol_escape = '\' )
#set( $symbol_dollar = '$' )
${symbol_pound}!/bin/bash

env mvn -DgroupId=${package} -DartifactId=* versions:set -DnewVersion=${symbol_dollar}@  


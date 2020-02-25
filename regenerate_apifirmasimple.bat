ECHO OFF

ECHO ----------------
ECHO "Esborrar <module>projectebase</module> de projecteorigen/pom.xml"
ECHO ----------------
pause


ECHO Unpacking from apifirmasimple_web_example ...

call mvn install -Papifirmasimple


ECHO Unpacked from apifirmasimple_web_example:OK

cd tmp
cd apifirmasimpleoriginal

call mvn clean

ECHO Generate Artifact from apifirmasimple_web_example unpacked

call mvn org.apache.maven.plugins:maven-archetype-plugin:3.1.2:create-from-project -Darchetype.properties=..\..\archetype.properties

echo ERROR = %ERRORLEVEL%

IF /I "%ERRORLEVEL%" EQU "0" (

  ECHO Artifact creation: OK
  
  ECHO Generate projectebase-apifirmasimple from artifact
  
  cd ./target/generated-sources/archetype/
  
  call mvn clean install
  
  cd ../../../../..
  
  cd projecteorigen
  
  echo
  echo ESBORRANT projectebase-apifirmasimple  si existeix
  echo

  rmdir /Q /S projectebase-apifirmasimple 
  
  echo
  echo Executant generacio de Archetype
  echo
  
  set MAVEN_OPTS="-Dfile.encoding=UTF-8"
  call mvn org.apache.maven.plugins:maven-archetype-plugin:3.1.2:generate -B -DarchetypeGroupId=es.caib.apisib.apifirmasimple -DarchetypeArtifactId=apifirmasimple-example-web-archetype -DarchetypeVersion=1.0.0 -Dpackage=es.caib.projectebase.apifirmasimple.example.web -DgroupId=es.caib.projectebase -DartifactId=projectebase-apifirmasimple -Dversion=1.0.0
  
  
  cd ..
  REM Això neteja d'espais el pom.xml
  call mvn org.codehaus.gmaven:groovy-maven-plugin:2.1.1:execute  -Dsource=post_regenerate_apifirmasimple.groovy

  
) ELSE (
  ECHO execution failed
)

rmdir /s /q .\tmp 


cd projecteorigen

call mvn clean

call mvn org.apache.maven.plugins:maven-archetype-plugin:3.1.2:create-from-project -Darchetype.properties=..\archetype.properties

IF /I "%ERRORLEVEL%" EQU "0" (
  cd ..
  ECHO Execution OK. Copying to artifact ...
  
  robocopy .\projecteorigen\target\generated-sources\archetype\src\main\resources\archetype-resources\  .\archetype\src\main\resources\archetype-resources\ /MIR /E
  
  call mvn org.codehaus.gmaven:groovy-maven-plugin:2.1.1:execute  -Dsource=post_create_artifact_from_projecteorigen.groovy

  
) ELSE (
  ECHO execution failed
)

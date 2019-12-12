
cd projecteorigen

call mvn clean

call mvn archetype:create-from-project -Darchetype.properties=..\archetype.properties

IF /I "%ERRORLEVEL%" EQU "0" (
  cd ..
  ECHO Execution OK. Copying to artifact ...
  xcopy .\projecteorigen\target\generated-sources\archetype\src\main\resources\archetype-resources\*.*  .\archetype\src\main\resources\archetype-resources\  /e /y /d
  
  call mvn org.codehaus.gmaven:groovy-maven-plugin:2.1.1:execute  -Dsource=post_create_from_project.groovy

  
) ELSE (
  ECHO execution failed
)

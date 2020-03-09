
cd projecteorigen

mvn clean
mvn org.apache.maven.plugins:maven-archetype-plugin:3.1.2:create-from-project -Darchetype.properties=../archetype.properties

retVal=$?

if [ $retVal -eq 0 ]; then
  cd ..
  echo "Execution OK. Copying to artifact ..."
 
  rsync -a --delete projecteorigen/target/generated-sources/archetype/src/main/resources/archetype-resources/ archetype/src/main/resources/archetype-resources

  mvn org.codehaus.gmaven:groovy-maven-plugin:2.1.1:execute  -Dsource=post_create_artifact_from_projecteorigen.groovy

else
  echo "Execution failed"
fi

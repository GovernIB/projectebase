
echo "----------------"
echo "Esborrar <module>projectebase-apifirmasimple</module> de projecteorigen/pom.xml"
echo "----------------"

read -n1 -r -p "Press any key to continue..."

echo "Unpacking from apifirmasimple_web_example ..."

mvn install -Papifirmasimple

echo "Unpacked from apifirmasimple_web_example:OK"

cd tmp
cd apifirmasimpleoriginal

mvn clean

echo "Generate Artifact from apifirmasimple_web_example unpacked"

mvn org.apache.maven.plugins:maven-archetype-plugin:3.1.2:create-from-project -Darchetype.properties=../../archetype.properties

retVal=$?

if [ $retVal -eq 0 ]; then

  echo "Artifact creation: OK"
  
  echo "Generate projectebase-apifirmasimple from artifact"
  
  cd ./target/generated-sources/archetype/
  
  mvn clean install
  
  cd ../../../../..
  
  cd projecteorigen
  
  echo
  echo "ESBORRANT projectebase-apifirmasimple  si existeix"
  echo

  rm -fr projectebase-apifirmasimple 
  
  echo
  echo "Executant generacio de Archetype"
  echo
  
  export MAVEN_OPTS="-Dfile.encoding=UTF-8"
  
  mvn org.apache.maven.plugins:maven-archetype-plugin:3.1.2:generate -B -DarchetypeGroupId=es.caib.apisib.apifirmasimple -DarchetypeArtifactId=apifirmasimple-example-web-archetype -DarchetypeVersion=1.0.0 -Dpackage=es.caib.projectebase.apifirmasimple.example.web -DgroupId=es.caib.projectebase -DartifactId=projectebase-apifirmasimple -Dversion=1.0.0
  
  cd ..
  
  # Això neteja d'espais el pom.xml
  mvn org.codehaus.gmaven:groovy-maven-plugin:2.1.1:execute  -Dsource=post_regenerate_apifirmasimple.groovy

else

  echo "execution failed"
  
fi

rm -fr ./tmp

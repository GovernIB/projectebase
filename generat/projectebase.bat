
rmdir /Q /S projectebase

set MAVEN_OPTS="-Dfile.encoding=UTF-8" && mvn org.apache.maven.plugins:maven-archetype-plugin:3.2.0:generate -B -DarchetypeGroupId=es.caib.projectebase -DarchetypeArtifactId=projectebase-archetype -DarchetypeVersion=1.0.7 -Dpackage=es.caib.projectebase -Dpackagepath=es/caib/projectebase -Dinversepackage=projectebase.caib.es -DgroupId=es.caib.projectebase -DartifactId=projectebase -Dversion=1.0.0 -Dprojectname=ProjecteBase -Dprojectnameuppercase=PROJECTEBASE -Dprefix=pbs -Dprefixuppercase=PBS -DperfilBack=true -DperfilFront=true -DperfilApiFirmaSimple=true -DperfilArxiu=true -DperfilRegistre=true -DperfilNotib=true -DperfilDir3Caib=true -DperfilDistribucio=true -DperfilSistra2=true -DperfilBatSh=true -DperfilWS=true

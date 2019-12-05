
Requisits compilació:
 - Maven 3.6
 - JDK 11

Requisits execució:
 - JBoss 7.2 / Wildfly 13 o superior
 - Definir dins el servidor el Datasource amb nom "jdbc/projectebaseDS" cap a una BDD buida
 - Definir variable entorn JBOSS_HOME

Compilació:
 mvn clean install
L'aplicació generada es troba dins projectebase-ear/target/projectebase.ear

Deploy/redeploy/undeploy aplicació sobre el servidor:
 mvn cargo:deploy / cargo:redeploy / cargo:undeploy

El JBoss/Wildfly es pot arrancar manualment o fer servir la
comandes:
 mvn cargo:run

Exemple:
 Compilar l'aplicació i arrancar el JBoss amb l'aplicació deployada:
   mvn clean install cargo:run

 Recompilar l'aplicació i redeployar sobre un JBoss ja arrancat:
   mvn install cargo:redeploy

Provar backoffice:
 - http://localhost:8080/projectebaseback/

Provar api REST
 Llistar les unitats orgàniques:
   - curl http://localhost:8080/projectebase/api/services/unitatorganica/all
 Obtenir la unitat orànica amb ideficador 1:
   - curl http://localhost:8080/projectebase/api/services/unitatorganica/1
 Crear una nova unitat orgànica:
   - curl -i -X POST -H "Content-Type: application/json" -d "{\"codiDir3\":\"A99999999\",\"dataCreacio\":\"2019-12-01\",\"nom\":\"Unitat XXX\"}" http://localhost:8080/projectebase/api/services/unitatorganica
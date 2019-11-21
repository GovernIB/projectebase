
Per crear un archetype des d'un projecte existent:



(1) mvn archetype:create-from-project   => Això genera un archetype a partir d'un projecte existent. El archetype el trobareu dins target/generated-sources/archetype.

PROBLEMES: pot ser demanani que no troba el fitxer [HOME]/.m2/settings.xml. si és el cas creau el fitxer en aquesta ruta i l'ompliu amb:

<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
      xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
      xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0
                          https://maven.apache.org/xsd/settings-1.0.0.xsd">
  <localRepository/>
  <interactiveMode/>
  <offline/>
  <pluginGroups/>
  <servers/>
  <mirrors/>
  <proxies/>
  <profiles/>
  <activeProfiles/>
</settings>

Verificar que [HOME]\.m2\repository\org\apache\maven\plugins\maven-archetype-plugin teniu activa la versio 3.1.2. Si no és així l'activau, i tornau a repetir les passes d'aquest punt.


(2) EL primer a fer és donar-li nom a l'archetype ja que dur el nom original del projecte afegint-li archetype al final. Per això retocarem el fitxer pom.xml. Un exemple de nous valors seria:


  <groupId>es.caib.projectebase.archetype</groupId>
  <artifactId>projectebase-archetype</artifactId>
  <version>1.0.0</version>
  <packaging>maven-archetype</packaging>
  <name>ProjecteBase - Archetype</name>



(3) El contingut de target/generated-sources/archetype el noveu a un directori independent. Com hem dit, això és l'artifact del projecte anterior. 

(3.1) Substitució de variables:
   (a)  La llista de variables a utilitzar es troben en el fitxer /src/main/resources/META-INF/maven/archetype-metadata.xml

<requiredProperties>
<requiredProperty key="variable_amb_default">
<defaultValue>value1111</defaultValue>
</requiredProperty>
<requiredProperty key="variable_sense_default">
<defaultValue>value222</defaultValue>
<validationRegex></validationRegex> 	<!-- 	String 	A regular expression used to validate the property. -->
</requiredProperty>
</requiredProperties>

   (b) Les mateixes variables que donem d'alta a archetype-metadata.xml també es molt recomenable donar-les d'alta a 
 \src\test\resources\projects\basic\archetype.properties. Aquestes variables serveixen per executar una simulació de generació cada vegada que es compila el archetype. Aquesta simulació generada la trobarem a target\test-classes\projects\basic\project\basic després d'executar mvn clean install.

   (c) La substitució en contingut dels fitxers de les variables es fa utilitzant el següent format: ${varName}
 
   (d) La substitució en els noms de fitxers i directoris de les variables es fa de la següent forma: __varname__
    

(d) EXEMPLE: Crearem una nova variable anomenada prefixcaib, que es obligatoria i formada per tres caracters en minuscules i l'utilitzarem en un fitxer i en el contingut:
     * Afegirem la variable en /src/main/resources/META-INF/maven/archetype-metadata.xml:

<requiredProperties>
  ...
  <requiredProperty key="prefixcaib">
    <validationRegex>[a-z][a-z][a-z]</validationRegex>
  </requiredProperty>
</requiredProperties>

     * En el mateix fitxer hem de dir al generador que volem que preocessi aquell fitxer i a més volen que realitzi les substitucions (filtered="true"):
     
    <fileSet filtered="true" encoding="UTF-8">
      <directory></directory>
      <includes>
        <include>prefix-es-__prefixcaib__.txt</include>
      </includes>
    </fileSet>


     * Afegirem la variable pel test en el fitxer \src\test\resources\projects\basic\archetype.properties (i de pas modificarem les altres per posar-lisnoms coherents)
     
package=org.fundaciobit.provaartifact
version=1.0.0
groupId=org.fundaciobit.provaartifact
artifactId=provaartifact
#PRova Articfact
prefixcaib=pra
       
     * Crearem un fitxer  prefix-es-__prefixcaib__.txt en src\main\resources\archetype-resources i li afegirem el següent contingut:
     
El package és ${package}
La version és ${version}
El groupId és ${groupId}
El artifactId és ${artifactId}

El prefixcaib és ${prefixcaib}

     * Ara executam "mvn clean install" i dins target\test-classes\projects\basic\project\provaartifact trobarem un fitxer "prefix-es-pra.txt" i si l'editam:
     
El package és org.fundaciobit.provaartifact
La version és 1.0.0
El groupId és org.fundaciobit.provaartifact
El artifactId és provaartifact

El prefixcaib és pra
     
    
(4) Canvis específics i condicionals
   A vegades volem fer coses més fines com afegir lògica al canvi o substitució o fer que s'afegesqui o elimini codi segons els valor de les variables. Per fer això dins rc\main\resources\META-INF poden guardar un fitxer titulat archetype-post-generate.groovy que contengui codi en groovy per fer coses un cop generat el codi a partir de l'archetype:
   
   EXEMPLE 1: 
   
   Crearem el fitxer abans comentat i li afegirem el següent tros de codi:
   
    println " + artifactId: " + request.getArtifactId()
    println " + groupId: " + request.getGroupId()
    println " + version: " + request.getVersion()

    Properties properties = request.properties
    println " + package: " + properties.get("package")
    println " + prefixcaib: " + properties.get("prefixcaib")

    String rootDir = new File(request.getOutputDirectory() + "/" + request.getArtifactId())
    println " + Directori Generació: " + rootDir

   Compilarem amb mvn clean install i la sortida per pantalla serà com:

 + artifactId: provaartifact
 + groupId: org.fundaciobit.provaartifact
 + version: 1.0.0
 + package: org.fundaciobit.provaartifact
 + prefixcaib: pra
 + Directori Generació: D:\dades\dades\CarpetesPersonals\ProgramacioPortaFIB2\projectebase\genapp\2_archetypemaven\target\test-classes\projects\basic\project\provaartifact


EXEMPLE 2: En aquest exemple segons el valor d'una nova propietat llevarem o no llevarem un mòdul del projecte. Com exemple llevarem o no llevarem els WS del projecte que es troben en una carpeta /ws. En aquest cas haurem de modificar tres coses:
      (a) Eliminar l'entrada <module>ws</module> del pom.xml
      (b) Eliminar el directori físic /ws
      (c) Eliminar en el pom.xml de l'ear la inclusió del mòdul EJB de WS:
             <ejbModule>
              <groupId>${groupId}</groupId>
              <artifactId>${rootArtifactId}-ws-server</artifactId>
            </ejbModule>
            
            <<<<ALTROS DE CODI >>>
            
            
 2.1 Afegir propietat que actui com a Profile maven
    Afegirem una altre varable que definirem amb valors boolean i ens indicara si volem o no llevar el projecte de WS.
    
    * Dins /src/main/resources/META-INF/maven/archetype-metadata.xml afegir:
    
    <requiredProperty key="perfilWS">
      <validationRegex>^(true|false)$</validationRegex>
    </requiredProperty>

   * Dins \src\test\resources\projects\basic\archetype.properties afegir:
   
             perfilWS=false

   * Dins el pom.xml de l'ear marcarem les zones de WS que s'han d'eliminar si no definim el perfil emprant els tags 
   <!-- WS START -->", "<!-- WS END --> : Per exemple
   
<!-- WS START -->
            <ejbModule>
              <groupId>${groupId}</groupId>
              <artifactId>${rootArtifactId}-ws-server</artifactId>
            </ejbModule>
<!-- WS END -->
   
   * Modificarem el fitxer archetype-post-generate.groovy com segueix:
   
   
import static groovy.io.FileType.*
import java.nio.file.Path
import java.util.regex.Pattern

def removeModule(module, rootDir) {
    assert new File(rootDir, module).deleteDir()
    def pomFile = new File(rootDir, "pom.xml")
 
    def pattern = Pattern.compile("\\s*<module>" + Pattern.quote(module) + "</module>", Pattern.MULTILINE)
    def pomContent = pomFile.getText("UTF-8")
    pomContent = pomContent.replaceAll(pattern, "")
    pomFile.newWriter("UTF-8").withWriter { w -> w << pomContent }
}

def removeTextBetweenTwoStrings(file, startString, endString) {
    String regex = Pattern.quote(startString) + "([\\s\\S]*?)" + Pattern.quote(endString);
    def pattern = Pattern.compile(regex, Pattern.MULTILINE)
    def pomContent = file.getText("UTF-8")
    pomContent = pomContent.replaceAll(regex, "")
    file.newWriter("UTF-8").withWriter { w -> w << pomContent }
}

println ""
println ""
println " + artifactId: " + request.getArtifactId()
println " + groupId: " + request.getGroupId()
println " + version: " + request.getVersion()

Properties properties = request.properties
println " + package: " + properties.get("package")
println " + prefixcaib: " + properties.get("prefixcaib")
String perfilWS = properties.get("perfilWS")
println " + perfilWS: " + perfilWS

String rootDir = new File(request.getOutputDirectory() + "/" + request.getArtifactId())
println " + Directori Generació: " + rootDir
println ""
println ""

if (perfilWS.equals("false")) {
  // Llevar WS
  removeModule('ws', rootDir)
  // Llevar Ws de EAR pom
  def pomEar = new File(rootDir, "./ear/pom.xml")
  removeTextBetweenTwoStrings(pomEar, "<!-- WS START -->", "<!-- WS END -->");
}
   
   
   * Si provam de posar el valor a true el ws apareixerà. Si el posam a false llavors WS desapareixerà. 





(5) Anar a un directori nou per provar la plantilla i executar: mvn archetype:generate -DarchetypeCatalog=local






//evaluate(new File("./projectebaseutils.groovy"))
import static groovy.io.FileType.*
import java.nio.file.Path
import java.nio.file.Files
import java.util.regex.Pattern
import java.io.*
import javax.xml.transform.*;
import javax.xml.transform.stream.*;

//GroovyShell shell = new GroovyShell()
//def ProjecteBaseUtils = shell.parse(new File("./projectebaseutils.groovy"))
//ProjecteBaseUtils.method()



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

def replaceText(file, search, replace) {    
    def pomContent = file.getText("UTF-8")
    pomContent = pomContent.replaceAll(search, replace)
    file.newWriter("UTF-8").withWriter { w -> w << pomContent }
}

def replaceProperties(file, isRoot) {
  escapeFile(file); 
  onlyReplaceProperties(file, isRoot);
}
  
def onlyReplaceProperties(file, isRoot) {
  String search = "es\\.caib\\.projectebase";
  String replace = "\\\$\\{package\\}";
  replaceText(file, search, replace);
  
  if (isRoot) {
    search = "projectebase";
    replace = "\\\$\\{artifactId\\}";
    replaceText(file, search, replace);
  } else {
    search = "projectebase";
    replace = "\\\$\\{rootArtifactId\\}";
    replaceText(file, search, replace);
  }  
  
  search = "ProjecteBase";
  replace = "\\\$\\{projectname\\}";
  replaceText(file, search, replace);
  
  search = "PROJECTEBASE";
  replace = "\\\$\\{projectnameuppercase\\}";
  replaceText(file, search, replace);

  search = "PBS";
  replace = "\\\$\\{prefixuppercase\\}";
  replaceText(file, search, replace);
  
  search = "pbs";
  replace = "\\\$\\{prefix\\}";
  replaceText(file, search, replace);

  search = "projectebase\\.caib\\.es";
  replace = "\\\$\\{inversepackage\\}";
  replaceText(file, search, replace);
}

def escapeFile(file) {    
    def content = file.getText("UTF-8")
    
    if (content.indexOf("symbol_dollar") != -1
      || content.indexOf("symbol_pound") != -1
      || content.indexOf("symbol_escape") != -1) {
      return;
    }
    
    content = content.replaceAll("\\\$", "\\\$\\{symbol_dollar\\}");
    content = content.replaceAll("\\\\", "\\\$\\{symbol_escape\\}");
    content = content.replaceAll("#",    "\\\$\\{symbol_pound\\}");
    
    content = "#set( \$symbol_dollar = '\$' )" + "\r\n" + content;
    content = "#set( \$symbol_escape = '\\' )" + "\r\n" + content;
    content = "#set( \$symbol_pound = '#' )" + "\r\n" + content;
    
    file.newWriter("UTF-8").withWriter { w -> w << content }
}


def checkProperty(regex, value, property) {    
  if (!Pattern.matches(regex, value)) {
    throw new Exception("\n\nERROR: El valor de la propietat '" + property + "'(" 
         + value + ") no s'ajusta a l'expressió " + regex + "\n\n");
  }
}


println 'Inici'

File rootDir = new File(".")

File baseProject = new File(rootDir, "./archetype/src/main/resources/archetype-resources/");

println " + Directori Generacio: " + baseProject.getAbsolutePath()


// POMS
def moduleFolders = [ "", "__rootArtifactId__-commons", "__rootArtifactId__-api", "__rootArtifactId__-back", "__rootArtifactId__-service",
     "__rootArtifactId__-front", "__rootArtifactId__-apifirmasimple", "__rootArtifactId__-ear", "__rootArtifactId__-ejb", "__rootArtifactId__-persistence",
	 "__rootArtifactId__-ws", "__rootArtifactId__-ws/__rootArtifactId___ws_server", "__rootArtifactId__-arxiu", "__rootArtifactId__-registre",
	 "__rootArtifactId__-notib", "__rootArtifactId__-dir3caib", "__rootArtifactId__-distribucio", "__rootArtifactId__-sistra2", 
	 "__rootArtifactId__-ws/__rootArtifactId___ws_api"];

for(String moduleDir : moduleFolders) {
  File tmp = new File(baseProject, moduleDir);
  onlyReplaceProperties(new File(tmp, "pom.xml"), false);
}

// DIRECTORI ARREL
def rootFiles = [ "compile.sh",  "novaversio.bat", "novaversio.sh", "README.md" ];
for(String rootFile : rootFiles) {
  onlyReplaceProperties(new File(baseProject, rootFile), true);
}
// compile.bat es un cas especial ja que té un \ abans de $
replaceProperties(new File(baseProject, "compile.bat"), true);


// COMMONS - model i utilitats
def commonsFiles = [ 
	"./__rootArtifactId__-commons/src/main/java/commons/utils/Constants.java",
	"./__rootArtifactId__-commons/src/main/java/commons/config/PropertyFileConfigSource.java",
	"./__rootArtifactId__-commons/src/main/resources/META-INF/services/org.eclipse.microprofile.config.spi.ConfigSource"
	];
for(String commonFile : commonsFiles) {
  replaceProperties(new File(baseProject, commonFile), false);
}

// JPA - persistence
def jpaFiles = [ "./__rootArtifactId__-persistence/src/main/resources/META-INF/persistence.xml",
	"./__rootArtifactId__-persistence/src/test/resources/META-INF/persistence.xml",
	"./__rootArtifactId__-persistence/src/test/resources/META-INF/arquillian-persistence.xml",
	"./__rootArtifactId__-persistence/src/test/resources/META-INF/arquillian-ds.xml",
    "./__rootArtifactId__-persistence/src/main/java/persistence/model/Procediment.java",
    "./__rootArtifactId__-persistence/src/main/java/persistence/model/UnitatOrganica.java" 
	];
for(String jpaFile : jpaFiles) {
  replaceProperties(new File(baseProject, jpaFile), false);
}


// Back - web
def backFiles = [
   "./__rootArtifactId__-back/src/main/webapp/WEB-INF/faces-config.xml",
   "./__rootArtifactId__-back/src/main/webapp/WEB-INF/web.xml",
   "./__rootArtifactId__-back/src/main/webapp/editUnitatOrganica.xhtml",
   "./__rootArtifactId__-back/src/main/webapp/listUnitatOrganica.xhtml",
   "./__rootArtifactId__-back/src/main/java/back/PrincipalInfoServlet.java"
  ];
for(String backFile : backFiles) {
  replaceProperties(new File(baseProject, backFile), false);
}

// Front - web
def frontFiles = [
   "./__rootArtifactId__-front/src/main/webapp/WEB-INF/web.xml"
  ];
for(String frontFile : frontFiles) {
  replaceProperties(new File(baseProject, frontFile), false);
}

// APIFirmaSimple - web
String searchAFS = "es\\.caib\\.projectebase";
String replaceAFS = "\\\$\\{package\\}";
replaceText(new File(baseProject,"./__rootArtifactId__-apifirmasimple/src/main/webapp/WEB-INF/web.xml"),
   searchAFS, replaceAFS);

// ARXIU - web
def arxiuFiles = [
        "./__rootArtifactId__-arxiu/src/main/webapp/WEB-INF/web.xml",
        "./__rootArtifactId__-arxiu/readme.txt"
];
for(String arxiuFile : arxiuFiles) {
    replaceProperties(new File(baseProject, arxiuFile), false);
}

// REGISTRE - web
def registreFiles = [
        "./__rootArtifactId__-registre/src/main/webapp/WEB-INF/web.xml",
        "./__rootArtifactId__-registre/readme.txt"
];
for(String registreFile : registreFiles) {
    replaceProperties(new File(baseProject, registreFile), false);
}

// NOTIB - web
def notibFiles = [
        "./__rootArtifactId__-notib/src/main/webapp/WEB-INF/web.xml",
        "./__rootArtifactId__-notib/readme.txt"
];
for(String notibFile : notibFiles) {
    replaceProperties(new File(baseProject, notibFile), false);
}

// DIR3CAIB - web
def dir3caibFiles = [
        "./__rootArtifactId__-dir3caib/src/main/webapp/WEB-INF/web.xml",
        "./__rootArtifactId__-dir3caib/readme.txt"
];
for(String dir3caibFile : dir3caibFiles) {
    replaceProperties(new File(baseProject, dir3caibFile), false);
}

// DISTRIBUCIO - web
def distribucioFiles = [
        "./__rootArtifactId__-distribucio/src/main/webapp/WEB-INF/web.xml",
		"./__rootArtifactId__-distribucio/src/main/webapp/exemple.xhtml",
		"./__rootArtifactId__-distribucio/src/main/java/distribucio/BustiaController.java",
        "./__rootArtifactId__-distribucio/readme.txt"
];
for(String distribucioFile : distribucioFiles) {
    replaceProperties(new File(baseProject, distribucioFile), false);
}

// SISTRA2 - ejb
def sistra2Files = [ 
	"./__rootArtifactId__-sistra2/src/main/resources/handlers/backoffice-handlers.xml",
	"./__rootArtifactId__-sistra2/src/main/resources/META-INF/persistence.xml",
	"./__rootArtifactId__-sistra2/src/test/resources/META-INF/persistence.xml",
    "./__rootArtifactId__-sistra2/src/main/java/sistra2/persistence/AnotacioInbox.java",
	"./__rootArtifactId__-sistra2/readme.txt"
];
for(String sistra2File : sistra2Files) {
  replaceProperties(new File(baseProject, sistra2File), false);
}

// SERVICE
def serviceFiles = [ "./__rootArtifactId__-service/src/main/resources/META-INF/beans.xml" ];
for(String file : serviceFiles) {
  replaceProperties(new File(baseProject, file), false);
}

// EJB   
def ejbFiles = [ 
	"./__rootArtifactId__-ejb/src/main/resources/META-INF/beans.xml",
	"./__rootArtifactId__-ejb/src/main/resources/META-INF/ejb-jar.xml",
	"./__rootArtifactId__-ejb/src/main/java/ejb/facade/ProcedimentServiceFacadeBean.java",
	"./__rootArtifactId__-ejb/src/main/java/ejb/facade/UnitatOrganicaServiceFacadeBean.java",
	"./__rootArtifactId__-ejb/src/test/resources/META-INF/arquillian-ds.xml",
	"./__rootArtifactId__-ejb/src/test/resources/META-INF/arquillian-persistence.xml",
	"./__rootArtifactId__-ejb/src/test/java/ejb/facade/AdminManager.java",
	"./__rootArtifactId__-ejb/src/test/resources/META-INF/sample_data.sql"
	];
for(String ejbFile : ejbFiles) {
  replaceProperties(new File(baseProject, ejbFile), false);
}

// REST
def restFiles = [
   "./__rootArtifactId__-api/src/main/webapp/WEB-INF/web.xml",
   "./__rootArtifactId__-api/src/main/java/api/services/ProcedimentResource.java",
   "./__rootArtifactId__-api/src/main/java/api/services/UnitatOrganicaResource.java",
   "./__rootArtifactId__-api/src/test/resources/web.xml",
   "./__rootArtifactId__-api/src/test/resources/ejb-jar.xml",
   "./__rootArtifactId__-api/src/test/resources/beans.xml",
   "./__rootArtifactId__-api/src/test/resources/arquillian-ds.xml",
   "./__rootArtifactId__-api/src/test/resources/META-INF/arquillian-persistence.xml"
  ];
for(String restFile : restFiles) {
  replaceProperties(new File(baseProject, restFile), false);
}

// WS
def wsFiles = [ 
"./__rootArtifactId__-ws/__rootArtifactId___ws_server/src/main/java/ws/impl/HelloWorldWsImpl.java",
"./__rootArtifactId__-ws/__rootArtifactId___ws_server/src/main/java/ws/impl/HelloWorldWithSecurityWsImpl.java",
"./__rootArtifactId__-ws/__rootArtifactId___ws_server/src/main/java/ws/utils/WsInInterceptor.java",
"./__rootArtifactId__-ws/__rootArtifactId___ws_api/src/main/resources/wsdl/HelloWorld.wsdl",
"./__rootArtifactId__-ws/__rootArtifactId___ws_api/src/main/resources/wsdl/HelloWorldWithSecurity.wsdl",
"./__rootArtifactId__-ws/__rootArtifactId___ws_api/src/main/java/ws/api/WsI18NException.java",
"./__rootArtifactId__-ws/__rootArtifactId___ws_api/src/main/java/ws/api/HelloWorldWsService.java",
"./__rootArtifactId__-ws/__rootArtifactId___ws_api/src/main/java/ws/api/HelloWorldWithSecurityWs.java",
"./__rootArtifactId__-ws/__rootArtifactId___ws_api/src/main/java/ws/api/HelloWorldWithSecurityWsService.java",
"./__rootArtifactId__-ws/__rootArtifactId___ws_api/src/main/java/ws/api/HelloWorldWs.java",
"./__rootArtifactId__-ws/__rootArtifactId___ws_api/src/main/java/ws/api/ObjectFactory.java",
"./__rootArtifactId__-ws/__rootArtifactId___ws_api/src/main/java/ws/api/package-info.java",
"./__rootArtifactId__-ws/__rootArtifactId___ws_api/src/main/java/ws/api/WsI18NError.java",
"./__rootArtifactId__-ws/__rootArtifactId___ws_api/test.properties"
 ];
for(String wsFile : wsFiles) {
  onlyReplaceProperties(new File(baseProject, wsFile), false);
}

// SCRIPTS
def scriptsFiles = [
   "./scripts/configuracio/__artifactId__.properties",
   "./scripts/configuracio/readme.txt",
   "./scripts/datasources/oracle/__artifactId__-ds.xml",
   "./scripts/datasources/postgresql/__artifactId__-ds.xml", 
   "./scripts/bbdd/oracle/01_create_schema.sql",
   "./scripts/bbdd/oracle/02_sample_data.sql",
   "./scripts/bbdd/oracle/drop_schema.sql",
   "./scripts/bbdd/oracle/sistra2_create_schema.sql",
   "./scripts/bbdd/oracle/sistra2_drop_schema.sql",
   "./scripts/bbdd/postgresql/01_create_schema.sql",
   "./scripts/bbdd/postgresql/02_sample_data.sql",
   "./scripts/bbdd/postgresql/drop_schema.sql",
   "./scripts/bbdd/postgresql/sistra2_create_schema.sql",
   "./scripts/bbdd/postgresql/sistra2_drop_schema.sql",
   "./scripts/bbdd/postgresql/readme.txt",
   "./scripts/keycloak/keycloak-subsystem.xml"
  ];
for(String scriptFile : scriptsFiles) {
  replaceProperties(new File(baseProject, scriptFile), false);
}

// .gitignore
File sourceFile = new File(rootDir, "projecteorigen/.gitignore");
Path sourcePath = sourceFile.toPath();
File destFile = new File(baseProject, "gitignore");
Path destPath = destFile.toPath();
Files.copy( sourcePath, destPath );


println 'Final'

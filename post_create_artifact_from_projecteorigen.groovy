//evaluate(new File("./projectebaseutils.groovy"))
import static groovy.io.FileType.*
import java.nio.file.Path
import java.nio.file.Files
import java.util.regex.Pattern

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
    content = "#set( \$symbol_pount = '#' )" + "\r\n" + content;
    
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
def moduleFolders = [ "", "__rootArtifactId__-commons", "__rootArtifactId__-rest", "__rootArtifactId__-back",
     "__rootArtifactId__-ear", "__rootArtifactId__-ejb", "__rootArtifactId__-jpa", "__rootArtifactId__-ws",
     "__rootArtifactId__-ws/__rootArtifactId___ws_server", 
     "__rootArtifactId__-ws/__rootArtifactId___ws_api"];

for(String moduleDir : moduleFolders) {
  File tmp = new File(baseProject, moduleDir);
  onlyReplaceProperties(new File(tmp, "pom.xml"), false);
}

// DIRECTORI ARREL
def rootFiles = [ "compile.sh",  "novaversio.bat", "novaversio.sh" ];
for(String rootFile : rootFiles) {
  onlyReplaceProperties(new File(baseProject, rootFile), true);
}
// compile.bat es un cas especial ja que té un \ abans de $
replaceProperties(new File(baseProject, "compile.bat"), true);

// DIRECTORI DOC
def docFiles = [ "./doc/readme.txt" ];
for(String docFile : docFiles) {
  replaceProperties(new File(baseProject, docFile), false);
}

// COMMONS - model i utilitats
def commonsFiles = [ "./__rootArtifactId__-commons/src/main/java/commons/utils/Constants.java",
       "./__rootArtifactId__-commons/src/main/java/commons/utils/Configuration.java" ];
for(String commonFile : commonsFiles) {
  replaceProperties(new File(baseProject, commonFile), false);
}

// JPA - persistence
def jpaFiles = [ "./__rootArtifactId__-jpa/src/main/resources/META-INF/persistence.xml",
    "./__rootArtifactId__-jpa/src/test/resources/META-INF/persistence.xml",
    "./__rootArtifactId__-jpa/src/main/java/jpa/Procediment.java",
    "./__rootArtifactId__-jpa/src/main/java/jpa/UnitatOrganica.java" ];
for(String jpaFile : jpaFiles) {
  replaceProperties(new File(baseProject, jpaFile), false);
}


// Back - web
def backFiles = [
   "./__rootArtifactId__-back/src/main/webapp/WEB-INF/faces-config.xml",
   "./__rootArtifactId__-back/src/main/webapp/WEB-INF/web.xml"
  ];
for(String backFile : backFiles) {
  replaceProperties(new File(baseProject, backFile), false);
}

// EJB   
File beans = new File(baseProject, "./__rootArtifactId__-ejb/src/main/resources/META-INF/beans.xml");
replaceProperties(beans, false);

// REST
def restFiles = [
   "./__rootArtifactId__-rest/src/main/webapp/WEB-INF/web.xml"
  ];
for(String restFile : restFiles) {
  replaceProperties(new File(baseProject, restFile), false);
}

// WS
def wsFiles = [ "./__rootArtifactId__-ws/__rootArtifactId___ws_server/src/main/java/ws/v1/impl/HelloWorldWsImpl.java",
"./__rootArtifactId__-ws/__rootArtifactId___ws_server/src/main/java/ws/v1/impl/HelloWorldWithSecurityWsImpl.java",
"./__rootArtifactId__-ws/__rootArtifactId___ws_server/src/main/java/ws/utils/I18NLogicUtils.java",
"./__rootArtifactId__-ws/__rootArtifactId___ws_server/src/main/java/ws/utils/WsInInterceptor.java",
"./__rootArtifactId__-ws/__rootArtifactId___ws_server/src/main/java/ws/utils/AuthenticatedBaseWsImpl.java",
"./__rootArtifactId__-ws/__rootArtifactId___ws_api/src/main/java/ws/api/v1/WsValidationErrors.java",
"./__rootArtifactId__-ws/__rootArtifactId___ws_api/src/main/java/ws/api/v1/WsI18NException.java",
"./__rootArtifactId__-ws/__rootArtifactId___ws_api/src/main/java/ws/api/v1/WsFieldValidationError.java",
"./__rootArtifactId__-ws/__rootArtifactId___ws_api/src/main/java/ws/api/v1/HelloWorldWsService.java",
"./__rootArtifactId__-ws/__rootArtifactId___ws_api/src/test/java/ws/v1/test/HelloWorldTest.java",
"./__rootArtifactId__-ws/__rootArtifactId___ws_api/src/main/java/ws/api/v1/HelloWorldWithSecurityWs.java",
"./__rootArtifactId__-ws/__rootArtifactId___ws_api/src/main/java/ws/api/v1/HelloWorldWithSecurityWsService.java",
"./__rootArtifactId__-ws/__rootArtifactId___ws_api/src/main/java/ws/api/v1/HelloWorldWs.java",
"./__rootArtifactId__-ws/__rootArtifactId___ws_api/src/main/java/ws/api/v1/ObjectFactory.java",
"./__rootArtifactId__-ws/__rootArtifactId___ws_api/src/main/java/ws/api/v1/package-info.java",
"./__rootArtifactId__-ws/__rootArtifactId___ws_api/src/main/java/ws/api/v1/WsI18NError.java",
"./__rootArtifactId__-ws/__rootArtifactId___ws_api/src/main/java/ws/api/v1/WsI18NTranslation.java",
"./__rootArtifactId__-ws/__rootArtifactId___ws_api/src/main/java/ws/api/v1/WsValidationException.java",
"./__rootArtifactId__-ws/__rootArtifactId___ws_api/test.properties"
 ];
for(String wsFile : wsFiles) {
  onlyReplaceProperties(new File(baseProject, wsFile), false);
}

// SCRIPTS
def scriptsFiles = [ 
   "./scripts/datasource/oracle.xml",
   "./scripts/datasource/postgresql.xml" 
  ];
for(String scriptFile : scriptsFiles) {
  replaceProperties(new File(baseProject, scriptFile), false);
}

// .gitignore
File sourceFile = new File(rootDir, "projecteorigen/.gitignore");
Path sourcePath = sourceFile.toPath();
File destFile = new File(baseProject, ".gitignore");
Path destPath = destFile.toPath();
Files.copy( sourcePath, destPath );


println 'Final'

//evaluate(new File("./projectebaseutils.groovy"))
import static groovy.io.FileType.*
import java.nio.file.Path
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

def replaceProperties(file) {
  escapeFile(file); 
  onlyReplaceProperties(file);
}
  
def onlyReplaceProperties(file) {
  String search = "es\\.caib\\.projectebase";
  String replace = "\\\$\\{package\\}";
  replaceText(file, search, replace);
  
  search = "projectebase";
  replace = "\\\$\\{artifactId\\}";
  replaceText(file, search, replace);
  
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
def moduleFolders = [ "", "__rootArtifactId__-commons", "__rootArtifactId__-rest", "__rootArtifactId__-back", "__rootArtifactId__-ear", "__rootArtifactId__-ejb", "__rootArtifactId__-jpa", "__rootArtifactId__-ws", "__rootArtifactId__-ws/__rootArtifactId___ws_server", "__rootArtifactId__-ws/__rootArtifactId___ws_api"];

for(String moduleDir : moduleFolders) {
  File tmp = new File(baseProject, moduleDir);
  onlyReplaceProperties(new File(tmp, "pom.xml"));
}

// DIRECTORI ARREL
def rootFiles = [ "compile.bat" , "compile.sh",  "novaversio.bat", "novaversio.sh" ];
for(String rootFile : rootFiles) {
  replaceProperties(new File(baseProject, rootFile));
}

// DIRECTORI DOC
def docFiles = [ "./doc/readme.txt" ];
for(String docFile : docFiles) {
  replaceProperties(new File(baseProject, docFile));
}

// COMMONS - model i utilitats
def commonsFiles = [ "./__rootArtifactId__-commons/src/main/java/commons/utils/Constants.java",
       "./__rootArtifactId__-commons/src/main/java/commons/utils/Configuration.java" ];
for(String commonFile : commonsFiles) {
  replaceProperties(new File(baseProject, commonFile));
}

// JPA - persistence
def jpaFiles = [ "./__rootArtifactId__-jpa/src/main/resources/META-INF/persistence.xml",
    "./__rootArtifactId__-jpa/src/test/resources/META-INF/persistence.xml",
    "./__rootArtifactId__-jpa/src/main/java/jpa/Procediment.java",
    "./__rootArtifactId__-jpa/src/main/java/jpa/UnitatOrganica.java" ];
for(String jpaFile : jpaFiles) {
  replaceProperties(new File(baseProject, jpaFile));
}


// Back - web
def backFiles = [ "./__rootArtifactId__-back/src/main/webapp/WEB-INF/faces-config.xml"
    ];
for(String backFile : backFiles) {
  replaceProperties(new File(baseProject, backFile));
}

// EJB   
File beans = new File(baseProject, "./__rootArtifactId__-ejb/src/main/resources/META-INF/beans.xml");
replaceProperties(beans);


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
  onlyReplaceProperties(new File(baseProject, wsFile));
}


println 'Final'

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

def checkProperty(regex, value, property) {    
  if (!Pattern.matches(regex, value)) {
    throw new Exception("\n\nERROR: El valor de la propietat '" + property + "'(" 
         + value + ") no s'ajusta a l'expressió " + regex + "\n\n");
  }
}


println ""
println ""
println " + artifactId: " + request.getArtifactId()
println " + groupId: " + request.getGroupId()
println " + version: " + request.getVersion()

Properties properties = request.properties
println " + package: " + properties.get("package")
String prefix = properties.get("prefix");
println " + prefix: " + prefix
checkProperty("^[a-z][a-z][a-z]\$", prefix, "prefix");

// prefixuppercase
String prefixuppercase= properties.get("prefixuppercase");
println " + prefixuppercase: " + prefixuppercase
checkProperty("^[A-Z][A-Z][A-Z]\$", prefixuppercase, "prefixuppercase");

// perfilWS
String perfilWS = properties.get("perfilWS");
println " + perfilWS: " + perfilWS;
checkProperty("^(true|false)\$", perfilWS, "perfilWS");

// perfilWS
String perfilBack = properties.get("perfilBack");
println " + perfilBack: " + perfilBack;
checkProperty("^(true|false)\$", perfilBack, "perfilBack");

// projectname
String projectname= properties.get("projectname");
println " + projectname: " + projectname
checkProperty("^([A-Za-z0-9])+\$", projectname, "projectname");

// projectnameuppercase
String projectnameuppercase= properties.get("projectnameuppercase");
println " + projectnameuppercase: " + projectnameuppercase
checkProperty("^([A-Z0-9])+\$", projectnameuppercase, "projectnameuppercase");


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

// TODO Falta per perfilBack



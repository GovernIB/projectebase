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
println " + Directori Generacio: " + rootDir.getAbsolutePath()

File baseProject = new File(rootDir, "./archetype/src/main/resources/archetype-resources/");

// EJB   
File beans = new File(baseProject, "./__rootArtifactId__-ejb/src/main/resources/META-INF/beans.xml");
replaceProperties(beans);

// JPA - persistence
File persistence = new File(baseProject, "./__rootArtifactId__-jpa/src/main/resources/META-INF/persistence.xml");
replaceProperties(persistence);


// DIRECTORI ARREL
def rootFiles = [ "compile.bat" , "compile.sh",  "novaversio.bat", "novaversio.sh" ];
for(String rootFile : rootFiles) {
  replaceProperties(new File(baseProject, rootFile));
}

// POMS
def moduleFolders = [ "", "__rootArtifactId__-api", "__rootArtifactId__-back", "__rootArtifactId__-ear", "__rootArtifactId__-ejb", "__rootArtifactId__-jpa"];

for(String moduleDir : moduleFolders) {
  File tmp = new File(baseProject, moduleDir);
  onlyReplaceProperties(new File(tmp, "pom.xml"));
}

println 'Final'

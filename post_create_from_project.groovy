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

def checkProperty(regex, value, property) {    
  if (!Pattern.matches(regex, value)) {
    throw new Exception("\n\nERROR: El valor de la propietat '" + property + "'(" 
         + value + ") no s'ajusta a l'expressió " + regex + "\n\n");
  }
}



println 'Inici'


File rootDir = new File(".")
println " + Directori Generacio: " + rootDir

File baseProject = new File(rootDir, "./archetype/src/main/resources/archetype-resources/");

// EJB   
File beans = new File(baseProject, "./__rootArtifactId__-ejb/src/main/resources/META-INF/beans.xml");
String search = "es\\.caib\\.projectebase";
String replace = "\\\$\\{package\\}";
replaceText(beans, search, replace);

// JPA
File persistence = new File(baseProject, "./__rootArtifactId__-jpa/src/main/resources/META-INF/persistence.xml");
search = "es\\.caib\\.projectebase";
replace = "\\\$\\{package\\}";
replaceText(persistence, search, replace);
search = "projectebase";
replace = "\\\$\\{artifactId\\}";
replaceText(persistence, search, replace);

// API REST
File apirest = new File(baseProject, "./__rootArtifactId__-api/pom.xml");
search = "es\\.caib\\.projectebase";
replace = "\\\$\\{package\\}";
replaceText(apirest, search, replace);


println 'Final'

import static groovy.io.FileType.*
import groovy.io.FileType
import java.nio.file.Path
import java.nio.file.Files
import java.util.regex.Pattern
import java.io.*
import javax.xml.transform.*;
import javax.xml.transform.stream.*;

boolean WIN = System.lineSeparator() != "\n";

def dos2unix(file) {    
      def content = file.getText("UTF-8")

      FileWriter fileWriter = new FileWriter(file);
			BufferedWriter fwr = new BufferedWriter( fileWriter );
			//FileReader frdr = new FileReader(file);
      StringReader frdr = new StringReader(content);
      
      
			BufferedReader buff = new BufferedReader( frdr );
			int bt = buff.read();
			char c = (char)bt;
			while(bt != -1)
			{
				//System.out.print("Char " + c + " = ");
				//System.out.println((int)c);
				if (bt != 13 )
					fwr.write(c);
				bt = buff.read();
				c = (char)bt;
			}
			//fwr.write(c);
			//if( c != '\n' ) fwr.write('\n');
			fwr.close();
			frdr.close()
}



def unix2dos(file) {    
    def content = file.getText("UTF-8")

    FileWriter fileWriter = new FileWriter(file);
    BufferedWriter fwr = new BufferedWriter( fileWriter );
    StringReader frdr = new StringReader(content);

    BufferedReader buff = new BufferedReader( frdr );
    int bt = buff.read();
    char c = (char)bt;
    int last = c;
    while(bt != -1)
    {
        //System.out.print("Char " + c + " = ");
        //System.out.println((int)c);
        if (bt == 10 ) { // 
          if (last != 13) { // /r
            fwr.write('\r');
          }
        }
        fwr.write(c);         

        last = bt;
        bt = buff.read();        
        c = (char)bt;
    }
    
    fwr.close();
    frdr.close()
}

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


def cleanEmptylines(String pomContent) {

    String LS = "\\r\\n";

    String[] lines = [
       "                                                                ",
       "                                                ",
       "                              ", 
       "                ",
       "              ",
       "            ",
       "          ",
       "        ",
       "      ",
       "    ",
       "  "
    ];
    for (String line : lines) {
        String search=line + LS;
        String replace="";
        pomContent = pomContent.replaceAll(search, replace)
    }
    
    return pomContent;
}


def cleanPom(file) {

    def pomContent = file.getText("UTF-8");

    pomContent = cleanEmptylines(pomContent)
    
    StringReader  reader = new StringReader(pomContent);
    StreamSource xmlInput = new StreamSource(reader);

    StringWriter stringWriter = new StringWriter();
    StreamResult xmlOutput = new StreamResult(stringWriter);
    TransformerFactory transformerFactory = TransformerFactory.newInstance();
    transformerFactory.setAttribute("indent-number", 4);
    Transformer transformer = transformerFactory.newTransformer(); 
    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
    transformer.transform(xmlInput, xmlOutput);
    pomContent = xmlOutput.getWriter().toString();

    //pomContent = cleanEmptylines(pomContent)  

    pomContent = pomContent.replaceAll("(?m)^[ \t]*\r?\n", "");    
    
    pomContent = pomContent.replaceAll("><project ", ">" + System.lineSeparator() + "<project "); 

    file.newWriter("UTF-8").withWriter { w -> w << pomContent }
    
    
}


def copyGitIgnore(rootDir) {


URL url = this.class.classLoader.getResource('/archetype-resources/.gitignore');
println "RESOURCE => " + url; 

/*

   String gitignore = this.getClass().getResource('archetype-resources/.gitignore').text
   
   File file = new File(rootDir, ".gitignore");

   file.newWriter("UTF-8").withWriter { w -> w << gitignore }   
*/
}


println ""
println " -------   POST GENERATE GROOVY -------"
println ""
def artifactId = request.getArtifactId()
println " + artifactId: " + artifactId
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

// perfilBack
String perfilBack = properties.get("perfilBack");
println " + perfilBack: " + perfilBack;
checkProperty("^(true|false)\$", perfilBack, "perfilBack");

// perfilFront
String perfilFront = properties.get("perfilFront");
println " + perfilFront: " + perfilFront;
checkProperty("^(true|false)\$", perfilFront, "perfilFront");

// perfilApiFirmaSimple
String perfilApiFirmaSimple = properties.get("perfilApiFirmaSimple");
println " + perfilApiFirmaSimple: " + perfilApiFirmaSimple;
checkProperty("^(true|false)\$", perfilApiFirmaSimple, "perfilApiFirmaSimple");

// perfilArxiu
String perfilArxiu = properties.get("perfilArxiu");
println " + perfilArxiu: " + perfilArxiu;
checkProperty("^(true|false)\$", perfilArxiu, "perfilArxiu");

// perfilRegistre
String perfilRegistre = properties.get("perfilRegistre");
println " + perfilRegistre: " + perfilRegistre;
checkProperty("^(true|false)\$", perfilRegistre, "perfilRegistre");

// perfilNotib
String perfilNotib = properties.get("perfilNotib");
println " + perfilNotib: " + perfilNotib;
checkProperty("^(true|false)\$", perfilNotib, "perfilNotib");

// perfilBatSh
String perfilBatSh = properties.get("perfilBatSh");
println " + perfilBatSh: " + perfilBatSh;
checkProperty("^(true|false)\$", perfilBatSh, "perfilBatSh");

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

/*
def list = []
def dir = new File(request.getOutputDirectory() + "/" + request.getArtifactId())
dir.eachFileRecurse (FileType.FILES) { file ->
  list << file
}
list.each {
  println "     - " + it.path
}
*/


if (perfilWS.equals("false")) {
  println " + Eliminant modul de WS ..."
  // Llevar WS
  removeModule(artifactId +'-ws', rootDir)
  // Llevar Ws de EAR pom
  def pomEar = new File(rootDir,artifactId +"-ear/pom.xml")
  removeTextBetweenTwoStrings(pomEar, "<!-- WS START -->", "<!-- WS END -->");
} else {
    
  cleanPom(new File(new File(rootDir, artifactId + "-ws"), "pom.xml"));
}

if (perfilBatSh.equals("false")) {
  new File(rootDir, "compile.bat").delete();
  new File(rootDir, "compile.sh").delete();
  new File(rootDir, "novaversio.bat").delete();
  new File(rootDir, "novaversio.sh").delete();
  new File(rootDir, "help.bat").delete();
  new File(rootDir, "help.sh").delete();
  new File(rootDir, "help.txt").delete();
} else {
  
  // DOS TO UNIX
  if (!WIN) {
      def dos2unixFiles = ["compile.sh", "help.sh", "novaversio.sh"];
      for (String dos2unixFile : dos2unixFiles) {
          dos2unix(new File(rootDir, dos2unixFile));
      }
  }
}

// Solucionar Bug de Archetype-maven (Afegeix linies buides al pom.xml arrel #27)
cleanPom(new File(rootDir, "pom.xml"));
if (WIN) {
    unix2dos(new File(rootDir, "pom.xml"));
}

// .gitignore
File sourceFile = new File(rootDir, "gitignore");
Path sourcePath = sourceFile.toPath();
File destFile = new File(rootDir, ".gitignore");
Path destPath = destFile.toPath();
Files.move( sourcePath, destPath );

println ""
println ""

// PerfilBack
if (perfilBack.equals("false")) {
  println " + Eliminant Modul Back ..."
  // Llevar directori
  removeModule(artifactId +'-back', rootDir)
  // Llevar WEB de EAR pom
  def pomEar = new File(rootDir,artifactId +"-ear/pom.xml")
  removeTextBetweenTwoStrings(pomEar, "<!-- BACK START -->", "<!-- BACK END -->");
}

// PerfilFront
if (perfilFront.equals("false")) {
  println " + Eliminant Modul Front ..."
  // Llevar directori
  removeModule(artifactId +'-front', rootDir)
  // Llevar WEB de EAR pom
  def pomEar = new File(rootDir,artifactId +"-ear/pom.xml")
  removeTextBetweenTwoStrings(pomEar, "<!-- FRONT START -->", "<!-- FRONT END -->");
}

// PerfilApiFirmaSimple
if (perfilApiFirmaSimple.equals("false")) {
  println " + Eliminant Modul ApiFirmaSimple ..."
  // Llevar directori
  removeModule(artifactId +'-apifirmasimple', rootDir)
  // Llevar WEB de EAR pom
  def pomEar = new File(rootDir,artifactId +"-ear/pom.xml")
  removeTextBetweenTwoStrings(pomEar, "<!-- APIFIRMASIMPLE START -->", "<!-- APIFIRMASIMPLE END -->");
}

// PerfilArxiu
if (perfilArxiu.equals("false")) {
  println " + Eliminant Modul Arxiu ..."
  // Llevar directori
  removeModule(artifactId +'-arxiu', rootDir)
  // Llevar WEB de EAR pom
  def pomEar = new File(rootDir,artifactId +"-ear/pom.xml")
  removeTextBetweenTwoStrings(pomEar, "<!-- ARXIU START -->", "<!-- ARXIU END -->");
}

// PerfilRegistre
if (perfilRegistre.equals("false")) {
  println " + Eliminant Modul Registre ..."
  // Llevar directori
  removeModule(artifactId +'-registre', rootDir)
  // Llevar WEB de EAR pom
  def pomEar = new File(rootDir,artifactId +"-ear/pom.xml")
  removeTextBetweenTwoStrings(pomEar, "<!-- REGISTRE START -->", "<!-- REGISTRE END -->");
}

// PerfilNotib
if (perfilNotib.equals("false")) {
  println " + Eliminant Modul Notib ..."
  // Llevar directori
  removeModule(artifactId +'-notib', rootDir)
  // Llevar WEB de EAR pom
  def pomEar = new File(rootDir,artifactId +"-ear/pom.xml")
  removeTextBetweenTwoStrings(pomEar, "<!-- NOTIB START -->", "<!-- NOTIB END -->");
}

import javax.xml.transform.OutputKeys
import javax.xml.transform.Transformer
import javax.xml.transform.TransformerFactory
import javax.xml.transform.stream.StreamResult
import javax.xml.transform.stream.StreamSource
import java.nio.file.Files
import java.nio.file.Path
import java.util.regex.Pattern

import static groovy.io.FileType.*

boolean WIN = System.lineSeparator() != "\n";

static def dos2unix(file) {
    def content = file.getText("UTF-8")

    FileWriter fileWriter = new FileWriter(file);
    BufferedWriter fwr = new BufferedWriter(fileWriter);
    StringReader frdr = new StringReader(content);


    BufferedReader buff = new BufferedReader(frdr);
    int bt = buff.read();
    char c = (char) bt;
    while (bt != -1) {
        if (bt != 13)
            fwr.write(c);
        bt = buff.read();
        c = (char) bt;
    }
    fwr.close();
    frdr.close()
}


static def unix2dos(file) {
    def content = file.getText("UTF-8")

    FileWriter fileWriter = new FileWriter(file);
    BufferedWriter fwr = new BufferedWriter(fileWriter);
    StringReader frdr = new StringReader(content);

    BufferedReader buff = new BufferedReader(frdr);
    int bt = buff.read();
    char c = (char) bt;
    int last = c;
    while (bt != -1) {
        if (bt == 10) { //
            if (last != 13) { // /r
                fwr.write('\r');
            }
        }
        fwr.write(c);

        last = bt;
        bt = buff.read();
        c = (char) bt;
    }

    fwr.close();
    frdr.close()
}

//def removePackage(module, , rootDir

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
        String search = line + LS;
        String replace = "";
        pomContent = pomContent.replaceAll(search, replace)
    }

    return pomContent;
}


def cleanPom(file) {

    def pomContent = file.getText("UTF-8");

    pomContent = cleanEmptylines(pomContent)

    StringReader reader = new StringReader(pomContent);
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
checkProperty("^[a-z0-9][a-z0-9][a-z0-9]\$", prefix, "prefix");

// prefixuppercase
String prefixuppercase = properties.get("prefixuppercase");
println " + prefixuppercase: " + prefixuppercase
checkProperty("^[A-Z0-9][A-Z0-9][A-Z0-9]\$", prefixuppercase, "prefixuppercase");

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

// perfilApiInterna
String perfilApiInterna = properties.get("perfilApiInterna");
println " + perfilApiInterna: " + perfilApiInterna;
checkProperty("^(true|false)\$", perfilApiInterna, "perfilApiInterna");

// perfilApiExterna
String perfilApiExterna = properties.get("perfilApiExterna");
println " + perfilApiExterna: " + perfilApiExterna;
checkProperty("^(true|false)\$", perfilApiExterna, "perfilApiExterna");

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

// perfilDir3Caib
String perfilDir3Caib = properties.get("perfilDir3Caib");
println " + perfilDir3Caib: " + perfilDir3Caib;
checkProperty("^(true|false)\$", perfilDir3Caib, "perfilDir3Caib");

// perfilPinbal
String perfilPinbal = properties.get("perfilPinbal");
println " + perfilPinbal: " + perfilPinbal;
checkProperty("^(true|false)\$", perfilPinbal, "perfilPinbal");

// perfilSistra2
String perfilSistra2 = properties.get("perfilSistra2");
println " + perfilSistra2: " + perfilSistra2;
checkProperty("^(true|false)\$", perfilSistra2, "perfilSistra2");

// perfilBatSh
String perfilBatSh = properties.get("perfilBatSh");
println " + perfilBatSh: " + perfilBatSh;
checkProperty("^(true|false)\$", perfilBatSh, "perfilBatSh");

// projectname
String projectname = properties.get("projectname");
println " + projectname: " + projectname
checkProperty("^([A-Za-z0-9])+\$", projectname, "projectname");

// projectnameuppercase
String projectnameuppercase = properties.get("projectnameuppercase");
println " + projectnameuppercase: " + projectnameuppercase
checkProperty("^([A-Z0-9])+\$", projectnameuppercase, "projectnameuppercase");


String rootDir = new File(request.getOutputDirectory() + "/" + request.getArtifactId())
println " + Directori Generació: " + rootDir


def configProperties = new File(rootDir, "scripts/configuracio/" + artifactId + ".properties")
def configSystemProperties = new File(rootDir, "scripts/configuracio/" + artifactId + ".system.properties")

if (perfilWS.equals("false")) {
    println " + Eliminant modul de WS ..."
    // Llevar WS
    removeModule(artifactId + '-ws', rootDir)
    // Llevar Ws de EAR pom
    def pomEar = new File(rootDir, artifactId + "-ear/pom.xml")
    removeTextBetweenTwoStrings(pomEar, "<!-- WS START -->", "<!-- WS END -->")
    removeTextBetweenTwoStrings(configProperties, "## WS START", "## WS END")
    removeTextBetweenTwoStrings(configSystemProperties, "## WS START", "## WS END")
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
Files.move(sourcePath, destPath);

// 



println ""
println ""

// PerfilBack
if (perfilBack.equals("false")) {
    println " + Eliminant Modul Back ..."
    // Llevar directori
    removeModule(artifactId + '-back', rootDir)
    // Llevar WEB de EAR pom
    def pomEar = new File(rootDir, artifactId + "-ear/pom.xml")
    removeTextBetweenTwoStrings(pomEar, "<!-- BACK START -->", "<!-- BACK END -->")
	def keycloak = new File(rootDir, "scripts/keycloak/keycloak-subsystem.xml")
	removeTextBetweenTwoStrings(keycloak, "<!-- BACK START -->", "<!-- BACK END -->")
	
    removeTextBetweenTwoStrings(configProperties, "## BACK START", "## BACK END")
    removeTextBetweenTwoStrings(configSystemProperties, "## BACK START", "## BACK END")
}

// PerfilFront
if (perfilFront.equals("false")) {
    println " + Eliminant Modul Front ..."
    // Llevar directori
    removeModule(artifactId + '-front', rootDir)
    // Llevar WEB de EAR pom
    def pomEar = new File(rootDir, artifactId + "-ear/pom.xml")
    removeTextBetweenTwoStrings(pomEar, "<!-- FRONT START -->", "<!-- FRONT END -->")
    removeTextBetweenTwoStrings(configProperties, "## FRONT START", "## FRONT END")
    removeTextBetweenTwoStrings(configSystemProperties, "## FRONT START", "## FRONT END")
}

// PerfilApiExterna
if (perfilApiExterna.equals("false")) {
    println " + Eliminant Modul Api Externa ..."
    // Llevar directori
    removeModule(artifactId + '-api-externa', rootDir)
    // Llevar WEB de EAR pom
    def pomEar = new File(rootDir, artifactId + "-ear/pom.xml")
    removeTextBetweenTwoStrings(pomEar, "<!-- API EXTERNA START -->", "<!-- API EXTERNA END -->")
	
    removeTextBetweenTwoStrings(configProperties, "## API EXTERNA START", "## API EXTERNA END")
    removeTextBetweenTwoStrings(configSystemProperties, "## API EXTERNA START", "## API EXTERNA END")
}

// PerfilApiInterna
if (perfilApiInterna.equals("false")) {
    println " + Eliminant Modul Api Interna ..."
    // Llevar directori
    removeModule(artifactId + '-api-interna', rootDir)
    // Llevar WEB de EAR pom
    def pomEar = new File(rootDir, artifactId + "-ear/pom.xml")
    removeTextBetweenTwoStrings(pomEar, "<!-- API INTERNA START -->", "<!-- API INTERNA END -->")
	def keycloak = new File(rootDir, "scripts/keycloak/keycloak-subsystem.xml")
	removeTextBetweenTwoStrings(keycloak, "<!-- API INTERNA START -->", "<!-- API INTERNA END -->")
	
    removeTextBetweenTwoStrings(configProperties, "## API INTERNA START", "## API INTERNA END")
    removeTextBetweenTwoStrings(configSystemProperties, "## API INTERNA START", "## API INTERNA END")
}

// PerfilApiFirmaSimple
if (perfilApiFirmaSimple.equals("false")) {
    println " + Eliminant Modul ApiFirmaSimple ..."
    // Llevar directori
    removeModule(artifactId + '-apifirmasimple', rootDir)
    // Llevar WEB de EAR pom
    def pomEar = new File(rootDir, artifactId + "-ear/pom.xml")
    removeTextBetweenTwoStrings(pomEar, "<!-- APIFIRMASIMPLE START -->", "<!-- APIFIRMASIMPLE END -->")
    removeTextBetweenTwoStrings(configProperties, "## APIFIRMASIMPLE START", "## APIFIRMASIMPLE END")
    removeTextBetweenTwoStrings(configSystemProperties, "## APIFIRMASIMPLE START", "## APIFIRMASIMPLE END")
}

// PerfilArxiu
if (perfilArxiu.equals("false")) {
    println " + Eliminant Modul Arxiu ..."
    // Llevar directori
    removeModule(artifactId + '-arxiu', rootDir)
    // Llevar WEB de EAR pom
    def pomEar = new File(rootDir, artifactId + "-ear/pom.xml")
    removeTextBetweenTwoStrings(pomEar, "<!-- ARXIU START -->", "<!-- ARXIU END -->")
    removeTextBetweenTwoStrings(configProperties, "## ARXIU START", "## ARXIU END")
    removeTextBetweenTwoStrings(configSystemProperties, "## ARXIU START", "## ARXIU END")
}

// PerfilRegistre
if (perfilRegistre.equals("false")) {
    println " + Eliminant Modul Registre ..."
    // Llevar directori
    removeModule(artifactId + '-registre', rootDir)
    // Llevar WEB de EAR pom
    def pomEar = new File(rootDir, artifactId + "-ear/pom.xml")
    removeTextBetweenTwoStrings(pomEar, "<!-- REGISTRE START -->", "<!-- REGISTRE END -->")
    removeTextBetweenTwoStrings(configProperties, "## REGISTRE START", "## REGISTRE END")
    removeTextBetweenTwoStrings(configSystemProperties, "## REGISTRE START", "## REGISTRE END")
}

// PerfilNotib
if (perfilNotib.equals("false")) {
    println " + Eliminant Modul Notib ..."
    // Llevar directori
    removeModule(artifactId + '-notib', rootDir)
    // Llevar WEB de EAR pom
    def pomEar = new File(rootDir, artifactId + "-ear/pom.xml")
    removeTextBetweenTwoStrings(pomEar, "<!-- NOTIB START -->", "<!-- NOTIB END -->")
    removeTextBetweenTwoStrings(configProperties, "## NOTIB START", "## NOTIB END")
    removeTextBetweenTwoStrings(configSystemProperties, "## NOTIB START", "## NOTIB END")
}

// PerfilDir3Caib
if (perfilDir3Caib.equals("false")) {
    println " + Eliminant Modul Dir3Caib ..."
    // Llevar directori
    removeModule(artifactId + '-dir3caib', rootDir)
    // Llevar WEB de EAR pom
    def pomEar = new File(rootDir, artifactId + "-ear/pom.xml")
    removeTextBetweenTwoStrings(pomEar, "<!-- DIR3CAIB START -->", "<!-- DIR3CAIB END -->")
    removeTextBetweenTwoStrings(configProperties, "## DIR3CAIB START", "## DIR3CAIB END")
    removeTextBetweenTwoStrings(configSystemProperties, "## DIR3CAIB START", "## DIR3CAIB END")
}



// PerfilPinbal
if (perfilPinbal.equals("false")) {
    println " + Eliminant Modul Pinbal ..."
    // Llevar directori
    removeModule(artifactId + '-pinbal', rootDir)
    // Llevar WEB de EAR pom
    def pomEar = new File(rootDir, artifactId + "-ear/pom.xml")
    removeTextBetweenTwoStrings(pomEar, "<!-- PINBAL START -->", "<!-- PINBAL END -->")
    removeTextBetweenTwoStrings(configProperties, "## PINBAL START", "## PINBAL END")
    removeTextBetweenTwoStrings(configSystemProperties, "## PINBAL START", "## PINBAL END")
}

// PerfilSistra2
if (perfilSistra2.equals("false")) {
    println " + Eliminant Modul Sistra2 ..."
    // Llevar directori
    removeModule(artifactId + '-sistra2', rootDir)
    // Llevar EJB de EAR pom
    def pomEar = new File(rootDir, artifactId + "-ear/pom.xml")
    removeTextBetweenTwoStrings(pomEar, "<!-- SISTRA2 START -->", "<!-- SISTRA2 END -->")
    // Netejar fitxers de properties
    removeTextBetweenTwoStrings(configProperties, "## SISTRA2 START", "## SISTRA2 END")
    removeTextBetweenTwoStrings(configSystemProperties, "## SISTRA2 START", "## SISTRA2 END")

    // Netejar scripts de base de dades
    assert new File(rootDir, "scripts/bbdd/oracle/sistra2_create_schema.sql").delete()
    assert new File(rootDir, "scripts/bbdd/oracle/sistra2_drop_schema.sql").delete()
    assert new File(rootDir, "scripts/bbdd/postgresql/sistra2_create_schema.sql").delete()
    assert new File(rootDir, "scripts/bbdd/postgresql/sistra2_drop_schema.sql").delete()

    // Netejar mòdul API, només si està activat
	if (perfilApiInterna.equals("true")) {
		def pomApi = new File(rootDir, artifactId + "-api-interna/pom.xml")
		removeTextBetweenTwoStrings(pomApi, "<!-- SISTRA2 START -->", "<!-- SISTRA2 END -->")
		packageDir = properties.get("package").replace(".", "/");
		assert new File(rootDir, artifactId + "-api-interna/src/main/java/" + packageDir + "/api/interna/sistra2").deleteDir()
		assert new File(rootDir, artifactId + "-api-interna/src/test/java/" + packageDir + "/api/interna/sistra2").deleteDir()
	}
}

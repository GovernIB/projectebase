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


    pomContent = pomContent.replaceAll("(?m)^[ \t]*\r?\n", "");    
    
    pomContent = pomContent.replaceAll("><project ", ">" + System.lineSeparator() + "<project "); 

    file.newWriter("UTF-8").withWriter { w -> w << pomContent }
    
    
}


// Neteja de linies buides del pom.xml (per si s'ha generat apifirmasimple)
cleanPom(new File("./projecteorigen", "pom.xml"));

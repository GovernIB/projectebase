#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package};

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.ResourceBundle;

/**
 * Bean amb dades de la versió. Serà el mateix per tothom per tant el definim dins l'scope d'aplicació.
 * Les agafa del fitxer Vesion.properties del mateix package.
 *
 * @author areus
 * @author anadal
 */
@Named
@ApplicationScoped
public class Version {

    private String version;
    private String buildTime;
    private String scmRevision;
    private String jdkVersion;
    

    /**
     * Inicialitza el bean amb els valors de Version.properties
     */
    @PostConstruct
    protected void init() {
        /* Agafa fitxer Version.properties amb el mateix package */
        ResourceBundle bundle = ResourceBundle.getBundle(this.getClass().getName());
        version = bundle.getString("project.version");
        buildTime = bundle.getString("project.buildtime");
        scmRevision = bundle.getString("scm.revision");
        jdkVersion = bundle.getString("jdk.version");
    }

    /**
     * Obté la versió del projecte
     */
    public String getVersion() {
        return version;
    }

    /**
     * Obté el moment de compilació del projecte
     */
    public String getBuildTime() {
        return buildTime;
    }

    /**
     * Obté la revisió del sistema de control de versions
     */
    public String getScmRevision() {
        return scmRevision;
    }

    /**
     * Obté el JDK amb el que es va compilar el projecte
     */
    public String getJdkVersion() {
        return jdkVersion;
    }

    /**
     * Obté el nom del projecte
     * @return
     */
    public String getProjectName() {
      // No modificar el nom !!!! 
      return "${symbol_dollar}{projectname}";
    }
    
}
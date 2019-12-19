package es.caib.projectebase.commons.utils;

import java.util.ResourceBundle;

/**
 * Bean amb dades de la versió. Serà el mateix per tothom per tant el definim dins l'scope
 * d'aplicació. Les agafa del fitxer Vesion.properties del mateix package.
 *
 * @author areus
 * @author anadal
 */
public class Version {

  private static Version versionInstance = null;

  private String version;
  private String buildTime;
  private String scmRevision;
  private String jdkVersion;
  private String projectName;

  /**
   * Inicialitza el bean amb els valors de Version.properties
   */
  public Version() {
      /* Agafa fitxer Version.properties amb el mateix package */
    ResourceBundle bundle = ResourceBundle.getBundle("projectebase.version.Version");
    version = bundle.getString("project.version");
    buildTime = bundle.getString("project.buildtime");
    scmRevision = bundle.getString("scm.revision");
    jdkVersion = bundle.getString("jdk.version");
    projectName = bundle.getString("project.name");
  }

  public static Version getVersionInstance() {
    if (versionInstance == null) {
      versionInstance = new Version();
    }
    return versionInstance;
  }

  /**
   * Obté la versió del projecte
   */
  public String getVersion() {
    return this.version;
  }

  /**
   * Obté el moment de compilació del projecte
   */
  public String getBuildTime() {
    return this.buildTime;
  }

  /**
   * Obté la revisió del sistema de control de versions
   */
  public String getScmRevision() {
    if ("${buildNumber}".equals(this.scmRevision)) {
        return "Recompile using profile revisionnumber (-Previsionnumber) to see this property";
    } else {
        return this.scmRevision;
    }
    
  }

  /**
   * Obté el JDK amb el que es va compilar el projecte
   */
  public String getJdkVersion() {
      return this.jdkVersion;
  }

  /**
   * Obté el nom del projecte
   */
  public String getProjectName() {
    return this.projectName;
  }
}
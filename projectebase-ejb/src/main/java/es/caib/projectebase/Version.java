package es.caib.projectebase;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.ResourceBundle;

/**
 * Bean amb dades de la versió.
 * Les agafa del fitxer Vesion.properties del mateix package.
 *
 * Els beans han de definir l'scope.
 * Si no posam nom, el nom serà el nom de la classe amb la primera minúscula.
 */
@Named
@ApplicationScoped
public class Version {

    private String version;
    private String buildTime;
    private String scmRevision;
    private String jdkVersion;

    /*
     * Els Beans sempre han de definir un constructor buid o amb @Inject
     */
    protected Version() {
    }

    @PostConstruct
    protected void init() {
        /* Agafa fitxer Version.properties amb el mateix package */
        ResourceBundle bundle = ResourceBundle.getBundle(this.getClass().getName());
        version = bundle.getString("project.version");
        buildTime = bundle.getString("project.buildtime");
        scmRevision = bundle.getString("scm.revision");
        jdkVersion = bundle.getString("jdk.version");
    }

    public String getVersion() {
        return version;
    }

    public String getBuildTime() {
        return buildTime;
    }

    public String getScmRevision() {
        return scmRevision;
    }

    public String getJdkVersion() {
        return jdkVersion;
    }
}
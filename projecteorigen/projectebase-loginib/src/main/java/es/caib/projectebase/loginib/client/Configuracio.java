package es.caib.projectebase.loginib.client;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 * Configuració de l'exemple de LoginIB.
 * El definim com a {@link ApplicationScoped} perquè només hi hagi una instància per aplicació.
 *
 * @author areus
 */
@ApplicationScoped
public class Configuracio {

    @Inject
    @ConfigProperty(name = "es.caib.projectebase.int.loginib.endpoint")
    private String endpoint;

    @Inject
    @ConfigProperty(name = "es.caib.projectebase.int.loginib.usuari")
    private String usuari;

    @Inject
    @ConfigProperty(name = "es.caib.projectebase.int.loginib.secret")
    private String secret;

    @Inject
    @ConfigProperty(name = "es.caib.projectebase.loginib.aplicacion")
    private String aplicacion;

    @Inject
    @ConfigProperty(name = "es.caib.projectebase.loginib.entidad")
    private String entidad;

    @Inject
    @ConfigProperty(name = "es.caib.projectebase.loginib.qaa")
    private int qaa;

    @Inject
    @ConfigProperty(name = "es.caib.projectebase.loginib.metodosAutenticacion")
    private String metodosAutenticacion;

    public String getEndpoint() {
        return endpoint;
    }

    public String getUsuari() {
        return usuari;
    }

    public String getSecret() {
        return secret;
    }

    public String getAplicacion() {
        return aplicacion;
    }

    public String getEntidad() {
        return entidad;
    }

    public int getQaa() {
        return qaa;
    }

    public String getMetodosAutenticacion() {
        return metodosAutenticacion;
    }
}

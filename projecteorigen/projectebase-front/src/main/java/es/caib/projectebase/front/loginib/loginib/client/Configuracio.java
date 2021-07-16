package es.caib.projectebase.front.loginib.loginib.client;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 * Configuració per la integració amb LoginIB.
 * El definim com a {@link ApplicationScoped} perquè només hi hagi una instància per aplicació.
 *
 * @author areus
 */
@ApplicationScoped
public class Configuracio {

	@Inject
    @ConfigProperty(name = "es.caib.projectebase.front.int.loginib.endpoint")
    private String endpoint;

    @Inject
    @ConfigProperty(name = "es.caib.projectebase.front.int.loginib.usuari")
    private String usuari;

    @Inject
    @ConfigProperty(name = "es.caib.projectebase.front.int.loginib.secret")
    private String secret;

    @Inject
    @ConfigProperty(name = "es.caib.projectebase.front.loginib.aplicacion")
    private String aplicacion;

    @Inject
    @ConfigProperty(name = "es.caib.projectebase.front.loginib.entidad")
    private String entidad;

    @Inject
    @ConfigProperty(name = "es.caib.projectebase.front.loginib.qaa")
    private int qaa;

    @Inject
    @ConfigProperty(name = "es.caib.projectebase.front.loginib.metodosAutenticacion")
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

    @Override
    public String toString() {
        return "Configuracio{" +
                "endpoint='" + endpoint + '\'' +
                ", usuari='" + usuari + '\'' +
                ", secret='" + secret + '\'' +
                ", aplicacion='" + aplicacion + '\'' +
                ", entidad='" + entidad + '\'' +
                ", qaa=" + qaa +
                ", metodosAutenticacion='" + metodosAutenticacion + '\'' +
                '}';
    }
}

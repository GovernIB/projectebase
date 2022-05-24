package es.caib.projectebase.distribucio.integracio;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 * Configuració de propietats per accés al backoffice d'integració.
 * El definim com a {@link ApplicationScoped} perquè només hi hagi una instància per aplicació.
 *
 * @author areus
 */
@ApplicationScoped
public class Configuracio {

    @Inject
    @ConfigProperty(name = "es.caib.projectebase.distribucio.int.distribucio.endpoint")
    private String endpoint;

    @Inject
    @ConfigProperty(name = "es.caib.projectebase.distribucio.int.distribucio.usuari")
    private String usuari;

    @Inject
    @ConfigProperty(name = "es.caib.projectebase.distribucio.int.distribucio.secret")
    private String secret;

    public String getEndpoint() {
        return endpoint;
    }

    public String getUsuari() {
        return usuari;
    }

    public String getSecret() {
        return secret;
    }
}

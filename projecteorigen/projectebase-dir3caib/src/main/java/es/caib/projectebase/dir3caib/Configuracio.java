package es.caib.projectebase.dir3caib;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;


/**
 * Configuració de l'exemple de Dir3Caib. Accedeix al fitxer dir3caib/Dir3Caib.properties per llegir les propietats
 * de configuració de l'exemple.
 * El definim com a {@link ApplicationScoped} perquè només hi hagi una instància per aplicació.
 *
 * @author areus
 */
@ApplicationScoped
public class Configuracio {

    @Inject
    @ConfigProperty(name="es.caib.projectebase.dir3caib.baseUrl")
    private String baseUrl;

    @Inject
    @ConfigProperty(name="es.caib.projectebase.dir3caib.usuari")
    private String usuari;

    @Inject
    @ConfigProperty(name="es.caib.projectebase.dir3caib.secret")
    private String secret;

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getUsuari() {
        return usuari;
    }

    public String getSecret() {
        return secret;
    }
}

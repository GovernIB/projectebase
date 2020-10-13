#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.notib;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Configuració de l'exemple de notib.
 * El definim com a {@link ApplicationScoped} perquè només hi hagi una instància per aplicació.
 *
 * @author areus
 */
@Named
@ApplicationScoped
public class Configuracio {

    @Inject
    @ConfigProperty(name="${package}.notib.baseUrl")
    private String baseUrl;

    @Inject
    @ConfigProperty(name="${package}.notib.usuari")
    private String usuari;

    @Inject
    @ConfigProperty(name="${package}.notib.secret")
    private String secret;

    @Inject
    @ConfigProperty(name="${package}.notib.procedimentCodi")
    private String procedimentCodi;

    @Inject
    @ConfigProperty(name="${package}.notib.emisorDir3Codi")
    private String emisorDir3Codi;

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getUsuari() {
        return usuari;
    }

    public String getSecret() {
        return secret;
    }

    public String getProcedimentCodi() {
        return procedimentCodi;
    }

    public String getEmisorDir3Codi() {
        return emisorDir3Codi;
    }
}

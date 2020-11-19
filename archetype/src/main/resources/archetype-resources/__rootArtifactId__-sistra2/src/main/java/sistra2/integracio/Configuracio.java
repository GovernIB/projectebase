#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.sistra2.integracio;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Configuració de propietats per accés al backoffice d'integració.
 * El definim com a {@link ApplicationScoped} perquè només hi hagi una instància per aplicació.
 *
 * @author areus
 */
@ApplicationScoped
public class Configuracio {

    @Inject
    @ConfigProperty(name = "${package}.sistra2.int.distribucio.endpoint")
    private String endpoint;

    @Inject
    @ConfigProperty(name = "${package}.sistra2.int.distribucio.usuari")
    private String usuari;

    @Inject
    @ConfigProperty(name = "${package}.sistra2.int.distribucio.secret")
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

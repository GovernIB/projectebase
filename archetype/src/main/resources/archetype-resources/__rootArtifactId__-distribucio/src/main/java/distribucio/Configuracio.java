#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.distribucio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Configuració de l'exemple de Distribucio. Accedeix al fitxer distribucio/Distribucio.properties per llegir les
 * propietats de configuració de l'exemple.
 * El definim com a {@link ApplicationScoped} perquè només hi hagi una instància per aplicació.
 *
 * @author areus
 */
@Named
@ApplicationScoped
public class Configuracio {

    private static final Logger LOG = LoggerFactory.getLogger(Configuracio.class);

    private static final String ENDPOINT_PROPERTY = "${package}.distribucio.endpoint";
    private static final String USERNAME_PROPERTY = "${package}.distribucio.username";
    private static final String PASSWORD_PROPERTY = "${package}.distribucio.password";

    private String endpoint;
    private String username;
    private String password;

    /**
     * Carrega el fitxer de properties registre/Registre.properties per inicialitzar la configuració.
     */
    @PostConstruct
    protected void init() {
        LOG.info("Carregant properties...");
        Properties properties = new Properties();
        try (InputStream is = this.getClass().getResourceAsStream("/distribucio/Distribucio.properties")) {
            properties.load(is);
            LOG.info("Properties carregades: {}", properties);
        } catch (IOException io) {
            throw new RuntimeException("No s'han pogut llegir les propietats de configuració", io);
        }

        endpoint = properties.getProperty(ENDPOINT_PROPERTY);
        username = properties.getProperty(USERNAME_PROPERTY);
        password = properties.getProperty(PASSWORD_PROPERTY);
    }

    public String getEndpoint() {
        return endpoint;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}

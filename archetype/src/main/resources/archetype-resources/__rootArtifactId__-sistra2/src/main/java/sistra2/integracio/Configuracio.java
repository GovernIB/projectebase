#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.sistra2.integracio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Configuració de propietats.
 * El definim com a {@link ApplicationScoped} perquè només hi hagi una instància per aplicació.
 *
 * @author areus
 */
@ApplicationScoped
public class Configuracio {

    private static final Logger LOG = LoggerFactory.getLogger(Configuracio.class);

    private static final String CONFIG_FILE = "/sistra2/Sistra2.properties";

    private final Properties properties = new Properties();

    /**
     * Carrega el fitxer de properties.
     */
    @PostConstruct
    protected void init() {
        LOG.info("Carregant properties...");
        try (InputStream is = this.getClass().getResourceAsStream(CONFIG_FILE)) {
            properties.load(is);
            LOG.info("Properties carregades: {}", properties);
        } catch (IOException io) {
            throw new RuntimeException("No s'han pogut llegir les propietats de configuració", io);
        }
    }

    public Properties getProperties() {
        return properties;
    }
}

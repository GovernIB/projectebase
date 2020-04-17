package es.caib.projectebase.notib;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Configuració de l'exemple de notib. Accedeix al fitxer notib/Notib.properties per llegir les propietats
 * de configuració de l'exemple.
 * El definim com a {@link ApplicationScoped} perquè només hi hagi una instància per aplicació.
 *
 * @author areus
 */
@Named
@ApplicationScoped
public class Configuracio {

    private static final Logger LOG = LoggerFactory.getLogger(Configuracio.class);

    private static final String URL_PROPERTY = "es.caib.projectebase.notib.url";
    private static final String USERNAME_PROPERTY = "es.caib.projectebase.notib.username";
    private static final String PASSWORD_PROPERTY = "es.caib.projectebase.notib.password";
    private static final String PROCEDIMENT_PROPERTY = "es.caib.projectebase.notib.procedimentCodi";
    private static final String EMISORDIR3_PROPERTY = "es.caib.projectebase.notib.emisorDir3Codi";

    private String url;
    private String username;
    private String password;
    private String procedimentCodi;
    private String emisorDir3Codi;

    /**
     * Carrega el fitxer de properties notib/Notib.properties per inicialitzar la configuració.
     */
    @PostConstruct
    protected void init() {
        LOG.info("Carregant properties...");
        Properties properties = new Properties();
        try (InputStream is = this.getClass().getResourceAsStream("/notib/Notib.properties")) {
            properties.load(is);

            LOG.info("Properties carregades: {}", properties);
        } catch (IOException io) {
            throw new RuntimeException("No s'han pogut llegir les propietats de configuració", io);
        }

        url = properties.getProperty(URL_PROPERTY);
        username = properties.getProperty(USERNAME_PROPERTY);
        password = properties.getProperty(PASSWORD_PROPERTY);
        procedimentCodi = properties.getProperty(PROCEDIMENT_PROPERTY);
        emisorDir3Codi = properties.getProperty(EMISORDIR3_PROPERTY);
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getProcedimentCodi() {
        return procedimentCodi;
    }

    public String getEmisorDir3Codi() {
        return emisorDir3Codi;
    }
}

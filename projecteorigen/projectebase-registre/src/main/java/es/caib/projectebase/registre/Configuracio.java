package es.caib.projectebase.registre;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Configuració de l'exemple de registre. Accedeix al fitxer registre/Registre.properties per llegir les propietats
 * de configuració de l'exemple.
 * El definim com a {@link ApplicationScoped} perquè només hi hagi una instància per aplicació.
 *
 * @author areus
 */
@Named
@ApplicationScoped
public class Configuracio {

    private static final Logger LOG = LoggerFactory.getLogger(Configuracio.class);

    private static final String ENDPOINT_PROPERTY = "es.caib.projectebase.registre.endpoint";
    private static final String USERNAME_PROPERTY = "es.caib.projectebase.registre.username";
    private static final String PASSWORD_PROPERTY = "es.caib.projectebase.registre.password";
    private static final String ENTITAT_PROPERTY = "es.caib.projectebase.registre.entitat";
    private static final String OFICINA_PROPERTY = "es.caib.projectebase.registre.oficina";
    private static final String LLIBRE_PROPERTY = "es.caib.projectebase.registre.llibre";
    private static final String ORGANISME_DESTI_PROPERTY = "es.caib.projectebase.registre.organismeDesti";

    private String endpoint;
    private String username;
    private String password;
    private String entitat;
    private String oficina;
    private String llibre;
    private String organismeDesti;

    /**
     * Carrega el fitxer de properties registre/Registre.properties per inicialitzar la configuració.
     */
    @PostConstruct
    protected void init() {
        LOG.info("Carregant properties...");
        Properties properties = new Properties();
        try (InputStream is = this.getClass().getResourceAsStream("/registre/Registre.properties")) {
            properties.load(is);

            LOG.info("Properties carregades: {}", properties);
        } catch (IOException io) {
            throw new RuntimeException("No s'han pogut llegir les propietats de configuració", io);
        }

        endpoint = properties.getProperty(ENDPOINT_PROPERTY);
        username = properties.getProperty(USERNAME_PROPERTY);
        password = properties.getProperty(PASSWORD_PROPERTY);
        entitat = properties.getProperty(ENTITAT_PROPERTY);
        oficina = properties.getProperty(OFICINA_PROPERTY);
        llibre = properties.getProperty(LLIBRE_PROPERTY);
        organismeDesti = properties.getProperty(ORGANISME_DESTI_PROPERTY);
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

    public String getEntitat() {
        return entitat;
    }

    public String getOficina() {
        return oficina;
    }

    public String getLlibre() {
        return llibre;
    }

    public String getOrganismeDesti() {
        return organismeDesti;
    }
}

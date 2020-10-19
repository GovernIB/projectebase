#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.sistra2.integracio;

import es.caib.plugins.arxiu.api.IArxiuPlugin;
import es.caib.plugins.arxiu.caib.ArxiuPluginCaib;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import java.util.Properties;

/**
 * Per instanciar el plugin d'arxiu.
 *
 * @author areus
 */
public class ArxiuPluginProducer {
    
    private static final Logger LOG = LoggerFactory.getLogger(ArxiuPluginProducer.class);

    @Inject
    @ConfigProperty(name = "${package}.sistra2.arxiu.baseUrl")
    private String baseUrl;

    @Inject
    @ConfigProperty(name = "${package}.sistra2.arxiu.aplicacioCodi")
    private String aplicacioCodi;

    @Inject
    @ConfigProperty(name = "${package}.sistra2.arxiu.usuari")
    private String usuari;

    @Inject
    @ConfigProperty(name = "${package}.sistra2.arxiu.secret")
    private String secret;

    /**
     * Instancia el plugin d'arxiu. El marcam com a @ApplicationScoped per garantir que només s'en
     * instancia un.
     * @param configuracio per accedir a les propietats de configuració
     */
    @Produces
    @ApplicationScoped
    public IArxiuPlugin getArxiuPlugin(Configuracio configuracio) {
        LOG.info("Instanciant plugin arxiu...");

        // per instanciar el plugin necessitam adaptar les propietats
        Properties properties = new Properties();
        properties.setProperty("plugin.arxiu.caib.base.url", baseUrl);
        properties.setProperty("plugin.arxiu.caib.aplicacio.codi", aplicacioCodi);
        properties.setProperty("plugin.arxiu.caib.usuari", usuari);
        properties.setProperty("plugin.arxiu.caib.contrasenya", secret);

        IArxiuPlugin plugin = new ArxiuPluginCaib("", properties);
        LOG.info("Plugin instanciat");
        return plugin;
    }
}
#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.arxiu;

import es.caib.plugins.arxiu.api.IArxiuPlugin;
import es.caib.plugins.arxiu.caib.ArxiuPluginCaib;
import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import java.util.Properties;

/**
 * Bean que produeix les instàncies de plugins.
 * 
 * @author areus
 */
public class PluginProducer {
    
    private static final Logger LOG = LoggerFactory.getLogger(PluginProducer.class);

    @Inject
    @ConfigProperty(name = "${package}.arxiu.baseUrl")
    private String baseUrl;

    @Inject
    @ConfigProperty(name = "${package}.arxiu.aplicacioCodi")
    private String aplicacioCodi;

    @Inject
    @ConfigProperty(name = "${package}.arxiu.usuari")
    private String usuari;

    @Inject
    @ConfigProperty(name = "${package}.arxiu.secret")
    private String secret;

    /**
     * Instancia el plugin d'arxiu. El marcam amb @Produces per poder injectar el plugin d'arxiu
     * dins qualsevol controlador. El marcam com a @ApplicationScoped per garantir que només s'en
     * instancia un.
     * 
     * @return instància del plugin d'arxiu.
     */
    @Produces
    @ApplicationScoped
    public IArxiuPlugin getArxiuPlugin(Config config) {
        LOG.info("getArxiuPlugin");

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

package es.caib.projectebase.sistra2.integracio;

import es.caib.plugins.arxiu.api.IArxiuPlugin;
import es.caib.plugins.arxiu.caib.ArxiuPluginCaib;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Per instanciar el plugin d'arxiu.
 *
 * @author areus
 */
public class ArxiuPluginProducer {
    
    private static final Logger LOG = LoggerFactory.getLogger(ArxiuPluginProducer.class);
    
    /**
     * Instancia el plugin d'arxiu. El marcam com a @ApplicationScoped per garantir que només s'en
     * instancia un.
     * @param configuracio per accedir a les propietats de configuració
     */
    @Produces
    @ApplicationScoped
    public IArxiuPlugin getArxiuPlugin(Configuracio configuracio) {
        LOG.info("Instanciant plugin arxiu...");
        IArxiuPlugin plugin = new ArxiuPluginCaib(
                "es.caib.projectebase.sistra2.",
                configuracio.getProperties());
        LOG.info("Plugin instanciat");
        return plugin;
    }
}
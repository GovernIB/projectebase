#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.sistra2.integracio;

import es.caib.plugins.arxiu.api.IArxiuPlugin;
import es.caib.plugins.arxiu.caib.ArxiuPluginCaib;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ArxiuPluginProducer {
    
    private static final Logger LOG = LoggerFactory.getLogger(ArxiuPluginProducer.class);
    
    /**
     * Instancia el plugin d'arxiu. El marcam amb @Produces per poder injectar el plugin d'arxiu
     * dins qualsevol controlador. El marcam com a @ApplicationScoped per garantir que només s'en
     * instancia un.
     * 
     * @return instància del plugin d'arxiu.
     */
    @Produces
    @ApplicationScoped
    public IArxiuPlugin getArxiuPlugin(Configuracio configuracio) {
        LOG.info("Instanciant plugin arxiu...");
        IArxiuPlugin plugin = new ArxiuPluginCaib("${package}.sistra2.", configuracio.getProperties());
        LOG.info("Plugin instanciat");
        return plugin;
    }
}
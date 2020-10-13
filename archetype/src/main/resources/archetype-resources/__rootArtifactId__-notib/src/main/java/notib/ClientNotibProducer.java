#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.notib;

import es.caib.notib.client.NotificacioRestClient;
import es.caib.notib.client.NotificacioRestClientFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

/**
 * Bean que configura i instancia el client de notib.
 *
 * @author areus
 */
public class ClientNotibProducer {

    private static final Logger LOG = LoggerFactory.getLogger(ClientNotibProducer.class);

    /**
     * Instancia el client de Notib. Permet que s'injecti onsevulla utilitzant CDI.
     * El definim com a {@link ApplicationScoped} perquè només hi hagi una instància per aplicació.
     *
     * @param configuracio la configuració de l'aplicació. És injectat automàticament per CDI
     * @return instància del client de Notib.
     */
    @Produces
    @ApplicationScoped
    public NotificacioRestClient getNotificacioRestClient(Configuracio configuracio) {
        LOG.info("getNotificacioRestClient");

        NotificacioRestClient client = NotificacioRestClientFactory.getRestClient(
				configuracio.getBaseUrl(),
				configuracio.getUsuari(),
				configuracio.getSecret());

        LOG.info("Client creat");

        return client;
    }
}

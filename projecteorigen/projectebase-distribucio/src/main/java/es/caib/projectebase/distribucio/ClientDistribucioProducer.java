package es.caib.projectebase.distribucio;

import es.caib.distribucio.ws.client.BustiaV1WsClientFactory;
import es.caib.distribucio.ws.v1.bustia.BustiaV1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import java.net.MalformedURLException;

/**
 * Bean que configura i instancia el client de distribució
 *
 * @author areus
 */
@ApplicationScoped
public class ClientDistribucioProducer {

    private static final Logger LOG = LoggerFactory.getLogger(ClientDistribucioProducer.class);

    /**
     * Instancia el client de distribució. Permet que s'injecti onsevulla utilitzant CDI.
     * El definim com a {@link ApplicationScoped} perquè només hi hagi una instància per aplicació.
     *
     * @param configuracio la configuració de l'aplicació. És injectat automàticament per CDI
     * @return instància del client de distribució.
     */
    @Produces
    @ApplicationScoped
    public BustiaV1 getBustiaClient(Configuracio configuracio) {
        LOG.info("getRegWebAsientoRegistralWs");
        try {
            BustiaV1 client = BustiaV1WsClientFactory.getWsClient(
                    configuracio.getEndpoint(),
                    configuracio.getUsername(),
                    configuracio.getPassword());

            LOG.info("Client creat");
            return client;

        } catch (MalformedURLException e) {
            throw new RuntimeException("No s'ha pogut crear el client de Distribució", e);
        }
    }
}

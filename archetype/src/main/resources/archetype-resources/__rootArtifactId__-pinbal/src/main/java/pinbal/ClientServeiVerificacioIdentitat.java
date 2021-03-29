#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.pinbal;

import es.caib.pinbal.client.recobriment.model.ScspJustificante;
import es.caib.pinbal.client.recobriment.model.ScspRespuesta;
import es.caib.pinbal.client.recobriment.svddgpviws02.ClientSvddgpviws02;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.IOException;
import java.util.List;

/**
 * Adapter entorn al client del servei de verificació d'identitat.
 */
@ApplicationScoped
public class ClientServeiVerificacioIdentitat {

    private static final Logger LOG = LoggerFactory.getLogger(ClientServeiVerificacioIdentitat.class);

    @Inject
    private Configuracio configuracio;

    private ClientSvddgpviws02 clientSvddgpviws02;

    @PostConstruct
    protected void init() {
        LOG.info("Iniciant client");
        clientSvddgpviws02 = new ClientSvddgpviws02(
                configuracio.getEndpoint(),
                configuracio.getUsuari(),
                configuracio.getSecret());
        LOG.info("Client creat");
    }

    public ScspRespuesta peticioSincrona(ClientSvddgpviws02.SolicitudSvddgpviws02... solicituds) throws IOException {
        return clientSvddgpviws02.peticionSincrona(List.of(solicituds));
    }

    public ScspJustificante getJustificant(String idPeticio) throws IOException {
        return clientSvddgpviws02.getJustificante(idPeticio);
    }

}

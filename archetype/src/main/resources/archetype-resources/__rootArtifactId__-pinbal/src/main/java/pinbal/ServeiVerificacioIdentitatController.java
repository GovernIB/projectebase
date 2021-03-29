#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.pinbal;

import es.caib.pinbal.client.recobriment.model.ScspJustificante;
import es.caib.pinbal.client.recobriment.model.ScspRespuesta;
import es.caib.pinbal.client.recobriment.model.ScspSolicitante;
import es.caib.pinbal.client.recobriment.svddgpviws02.ClientSvddgpviws02;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.Valid;
import java.io.IOException;
import java.io.Serializable;

import static javax.faces.application.FacesMessage.SEVERITY_ERROR;

/**
 * Controlador d'exemple per emprar el servei de verificació d'identitat.
 *
 * @author areus
 */
@Named("sviController")
@ViewScoped
public class ServeiVerificacioIdentitatController implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private static final Logger LOG = LoggerFactory.getLogger(ServeiVerificacioIdentitatController.class);

    /**
     * Injecta l'API del client del servei de verificació d'identitat
     */
    @Inject
    private ClientServeiVerificacioIdentitat clientSvi;

    @Inject
    private Configuracio configuracio;
    
    @Inject
    private FacesContext context;

    @Valid
    private TitularModel titular;

    public TitularModel getTitular() {
        return titular;
    }

    @Valid
    private FuncionariModel funcionari;

    public FuncionariModel getFuncionari() {
        return funcionari;
    }

    private ScspRespuesta resposta;

    public ScspRespuesta getResposta() {
        return resposta;
    }

    @PostConstruct
    protected void init() {
        LOG.info("init");
        titular = new TitularModel();
        funcionari = new FuncionariModel();
    }

    /**
     * Neteja la resposta
     */
    public void neteja() {
        resposta = null;
    }

    /**
     * Cridat en fer un submit del formulari per fer la consulta al servei.
     */
    public void verificarIdentitat() {
        LOG.info("verificarIdentitat");

        ClientSvddgpviws02.SolicitudSvddgpviws02 solicitud = new ClientSvddgpviws02.SolicitudSvddgpviws02();
        solicitud.setIdentificadorSolicitante(configuracio.getOrganismeSolicitant());
        solicitud.setUnidadTramitadora(configuracio.getUnitatTramitadora());
        solicitud.setCodigoProcedimiento(configuracio.getCodiProcediment());
        solicitud.setFinalidad(configuracio.getFinalitat());
        solicitud.setConsentimiento(ScspSolicitante.ScspConsentimiento.Si);

        solicitud.setFuncionario(funcionari.toScspFuncionario());
        solicitud.setTitular(titular.toScspTitular());

        try {
            resposta = clientSvi.peticioSincrona(solicitud);
        } catch (Exception e) {
            FacesMessage message = new FacesMessage(SEVERITY_ERROR, "Error al client Pinbal", e.getMessage());
            context.addMessage(null, message);
        }
    }

    /**
     * Cridat per obtenir el justificant de la petició en curs.
     * @throws IOException si es produeix un error de comunicació
     */
    public void descarregarJustificant() throws IOException {
        if (resposta == null) {
            throw new IllegalStateException("No hi ha resposta");
        }

        String idPeticio = resposta.getAtributos().getIdPeticion();
        ScspJustificante justificant = clientSvi.getJustificant(idPeticio);
        download(justificant.getNom(), justificant.getContentType(), justificant.getContingut());
    }

    /**
     * Mètode d'utilitat per descarregar un fitxer
     *
     * @param filename Nom del fitxer
     * @param mimetype tipus mime pel content-type
     * @param content contingut del fitxer
     * @throws IOException Si es produeix un error I/O
     */
    private void download(String filename, String mimetype, byte[] content) throws IOException {
        ExternalContext ec = context.getExternalContext();
        ec.responseReset(); // Hem de resetejar la reposta per si hi ha cap capçalera o res.
        ec.setResponseContentType(mimetype);
        ec.setResponseContentLength(content.length);
        ec.setResponseHeader("Content-Disposition", "attachment; filename=${symbol_escape}"" + filename + "${symbol_escape}"");
        ec.getResponseOutputStream().write(content);
        context.responseComplete(); // Important perquè JSF no intenti seguir processant la resposta.
    }
}

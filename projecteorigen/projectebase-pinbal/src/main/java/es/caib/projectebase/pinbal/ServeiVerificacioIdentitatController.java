package es.caib.projectebase.pinbal;

import es.caib.pinbal.client.recobriment.model.ScspFuncionario;
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
    private FacesContext context;

    @Valid
    private TitularModel model;

    public TitularModel getModel() {
        return model;
    }

    private ScspRespuesta resposta;

    public ScspRespuesta getResposta() {
        return resposta;
    }

    @PostConstruct
    public void init() {
        LOG.info("init");
        model = new TitularModel();
        resposta = null;
    }

    /**
     * Cridat en fer un submit del formulari per fer la consulta al servei.
     */
    public void verificarIdentitat() {
        LOG.info("verificarIdentitat");

        ClientSvddgpviws02.SolicitudSvddgpviws02 solicitud = new ClientSvddgpviws02.SolicitudSvddgpviws02();
        solicitud.setIdentificadorSolicitante("S0711001H");
        solicitud.setCodigoProcedimiento("CODSVDR_GBA_20121107");
        solicitud.setUnidadTramitadora("Departament de test");
        solicitud.setFinalidad("Test peticionSincrona");
        solicitud.setConsentimiento(ScspSolicitante.ScspConsentimiento.Si);

        ScspFuncionario funcionario = new ScspFuncionario();
        funcionario.setNifFuncionario("00000000T");
        funcionario.setNombreCompletoFuncionario("Funcionari CAIB");
        solicitud.setFuncionario(funcionario);

        solicitud.setTitular(model.toScspTitular());

        try {
            resposta = clientSvi.peticioSincrona(solicitud);
        } catch (Exception e) {
            FacesMessage message = new FacesMessage(SEVERITY_ERROR, "Error al client Pinbal", e.getMessage());
            context.addMessage(null, message);
        }
    }

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
        ec.setResponseHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
        ec.getResponseOutputStream().write(content);
        context.responseComplete(); // Important perquè JSF no intenti seguir processant la resposta.
    }
}

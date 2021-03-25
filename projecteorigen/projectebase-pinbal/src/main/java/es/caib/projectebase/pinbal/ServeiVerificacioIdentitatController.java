package es.caib.projectebase.pinbal;

import es.caib.pinbal.client.recobriment.model.ScspFuncionario;
import es.caib.pinbal.client.recobriment.model.ScspRespuesta;
import es.caib.pinbal.client.recobriment.model.ScspSolicitante;
import es.caib.pinbal.client.recobriment.model.ScspTitular;
import es.caib.pinbal.client.recobriment.model.ScspTransmisionDatos;
import es.caib.pinbal.client.recobriment.svddgpviws02.ClientSvddgpviws02;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.Valid;
import java.io.IOException;
import java.io.Serializable;

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
    private VerificacioModel model;

    public VerificacioModel getModel() {
        return model;
    }

    private ScspRespuesta resposta;

    public ScspRespuesta getResposta() {
        return resposta;
    }

    @PostConstruct
    public void init() {
        LOG.info("init");
        model = new VerificacioModel();
    }

    /**
     * Cridat en fer un submit del formulari per fer la consulta al servei.
     */
    public void verificarIdentitat() throws IOException {
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

        ScspTitular titular = new ScspTitular();
        titular.setTipoDocumentacion(model.getTipusDocumentacio());
        titular.setDocumentacion(model.getDocumentacio());
        titular.setNombre(model.getNom());
        titular.setApellido1(model.getPrimerCognom());
        titular.setApellido2(model.getSegonCognom());
        solicitud.setTitular(titular);
        
        resposta = clientSvi.peticioSincrona(solicitud);
        LOG.info("idPeticion: {}", resposta.getAtributos().getIdPeticion());
    }
}

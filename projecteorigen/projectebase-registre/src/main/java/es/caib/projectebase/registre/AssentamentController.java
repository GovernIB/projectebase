package es.caib.projectebase.registre;

import es.caib.regweb3.ws.api.v3.AnexoWs;
import es.caib.regweb3.ws.api.v3.AsientoRegistralWs;
import es.caib.regweb3.ws.api.v3.DatosInteresadoWs;
import es.caib.regweb3.ws.api.v3.InteresadoWs;
import es.caib.regweb3.ws.api.v3.JustificanteWs;
import es.caib.regweb3.ws.api.v3.RegWebAsientoRegistralWs;
import es.caib.regweb3.ws.api.v3.WsI18NException;
import es.caib.regweb3.ws.api.v3.WsValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;
import javax.xml.ws.WebServiceException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * Controlador per gestionar la creació d'assentaments registrals.
 *
 * @author areus
 */
@Named
@SessionScoped
public class AssentamentController implements Serializable {

    private static final long serialVersionUID = 7851876209589793985L;

    private static final Logger LOG = LoggerFactory.getLogger(AssentamentController.class);

    @Inject
    FacesContext context;

    /**
     * Obté el client de Registre a través de {@link ClientRegistreProducer#getRegWebAsientoRegistralWs(Configuracio)} ()}
     */
    @Inject
    private RegWebAsientoRegistralWs asientoRegistralWs;

    /**
     * Permet accedir a la configuració.
     */
    @Inject
    private Configuracio configuracio;

    /**
     * Model de dades del formulari
     */
    @Inject
    private AssentamentModel model;

    /* Mantenim en memòria una llista dels assetaments creats durat aquesta sessió. */
    private final List<AsientoRegistralWs> assentamentsCreats = new ArrayList<>();

    public List<AsientoRegistralWs> getAssentamentsCreats() {
        return assentamentsCreats;
    }

    /**
     * Construeix una assentament a partir del model i l'envia a registrar amb el client de registre.
     *
     * @return identificador de la vista carregar
     */
    public String registrarAssentament() {
        LOG.info("registrarAssentament");

        try {
            // Cream l'assentament a registrar i l'emplenam
            AsientoRegistralWs asiento = new AsientoRegistralWs();

            /*
            1: Entrada
            2: Sortida
             */
            asiento.setTipoRegistro(1L);

            asiento.setCodigoUsuario(configuracio.getUsuari());
            // Ofincina de registre
            asiento.setEntidadRegistralOrigenCodigo(configuracio.getOficina());
            // Llibre de registre 
            asiento.setLibroCodigo(configuracio.getLlibre());
            asiento.setPresencial(false);

            /*
            1: Documentació adjunta en suport PAPER (o altres suports)
            2: Documentació adjunta digitalitzada i complementàriament en paper
            3: Documentació adjunta digitalitzada
            */
            asiento.setTipoDocumentacionFisicaCodigo(3L);
            /* 07 = Altres, registre electrònic */
            asiento.setTipoTransporte("07");

            /* és obligatori per sortides */
            //asiento.setUnidadTramitacionOrigenCodigo(null);
            /* és obligatori per entrades */
            asiento.setUnidadTramitacionDestinoCodigo(configuracio.getOrganismeDesti());
            
            /* 
            1: Català
            2: Castellà
            3: Galleg
            4: Euskera
            5: Anglès
            6: Altres */
            asiento.setIdioma(model.getIdioma());
            asiento.setResumen(model.getAssumpte());

            asiento.setExpone("Exposa que....");
            asiento.setSolicita("Solicita que ....");

            /* camps opcionals */
            //asiento.setCodigoAsunto(null);
            //asiento.setCodigoSia(null);
            //asiento.setReferenciaExterna("FE4567Y");
            //asiento.setNumeroExpediente("34567Y/2019");

            asiento.setObservaciones("Assentament realitzat per WS per mòdul exemple");

            // Dades d'exemplme d'un interessat
            DatosInteresadoWs personaFisica = new DatosInteresadoWs();
            /*
            1: ADMINISTRACIÓ
            2: PERSONA FÍSICA
            3: PERSONA JURÍDICA
            */
            personaFisica.setTipoInteresado(2L);
            personaFisica.setTipoDocumentoIdentificacion("N");
            personaFisica.setDocumento("46164250F");
            personaFisica.setEmail("pgarcia@gmail.com");
            personaFisica.setNombre("Julian");
            personaFisica.setApellido1("González");
            personaFisica.setCanal(1L);
            personaFisica.setDireccion("Calle Aragón, 24, 5ºD");
            personaFisica.setLocalidad(407L);
            personaFisica.setPais(724L);
            personaFisica.setProvincia(7L);

            // Afegim l'interessat a l'assentament
            InteresadoWs interesado = new InteresadoWs();
            interesado.setInteresado(personaFisica);
            asiento.getInteresados().add(interesado);

            // Afegim un annex si s'ha introduit un fitxer
            Part file = model.getAnnex();
            if (file != null) {

                AnexoWs anexo = new AnexoWs();
                anexo.setTitulo("Document annex");
                anexo.setNombreFicheroAnexado(file.getSubmittedFileName());
                anexo.setTipoMIMEFicheroAnexado(file.getContentType());

                // Metadada NTI. TD14 = Solicitud del ciutadà
                anexo.setTipoDocumental("TD14");
                /*
                01: Còpia
                03: Còpia original
                04: Original
                */
                anexo.setValidezDocumento("01");
                /*
                01: Formulari
                02: Document adjuntar
                03: Fitxer Tècnic
                */
                anexo.setTipoDocumento("02");
                
                /*
                0: Ciutadà
                1: Administració
                */
                anexo.setOrigenCiudadanoAdmin(0);

                anexo.setFechaCaptura(Timestamp.from(Instant.now()));
                
                /*
                0: Document sense firmar
                1: Document firmat (ATTACHED)
                2: Firma en document separat (DETACHED)
                */
                anexo.setModoFirma(0);

                anexo.setObservaciones("Annexat per mòdul exemple");

                // Ficam el contingut en forma d'array de bytes
                try (InputStream inputStream = file.getInputStream()) {
                    ByteArrayOutputStream baos = new ByteArrayOutputStream((int) file.getSize());
                    inputStream.transferTo(baos);
                    anexo.setFicheroAnexado(baos.toByteArray());
                } catch (IOException ioe) {
                    throw new RuntimeException(ioe);
                }

                asiento.getAnexos().add(anexo);
            }

            LOG.info("Cridant: crearAsientoRegistral");

            // Opcional. Podem obtenir un idSessio per emprar a la creació del registgre per saber
            // en cas d'error (p.e. timeout) si el registre s'ha creat o no 
            //Long idSessio = asientoRegistralWs.obtenerSesionRegistro(configuracio.getEntitat());
            Long idSessio = null;

            // Si volem que comenci automàticament la creació de justificant
            boolean crearJustificant = false;
            // Si volem que es distribueixi automàticament
            boolean distribuir = false;
            // Nomé per registres de sortida
            Long tipusOperacio = null;

            AsientoRegistralWs asientoCreat = asientoRegistralWs.crearAsientoRegistral(
                    idSessio, configuracio.getEntitat(), asiento, tipusOperacio, crearJustificant, distribuir);

            LOG.info("Registre creat: {}", asientoCreat.getNumeroRegistroFormateado());
            assentamentsCreats.add(asientoCreat);

            context.addMessage(null, new FacesMessage(
                    "Creat assentament: " + asientoCreat.getNumeroRegistroFormateado(), ""));

        } catch (WsI18NException | WsValidationException | WebServiceException ex) {
            LOG.error("Error a l'api de Registre", ex);

            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error a l'api de Registre.",
                    ex.getMessage()));
        }

        context.getExternalContext().getFlash().setKeepMessages(true);
        return context.getViewRoot().getViewId() + "?faces-redirect=true";
    }

    /**
     * Obté i descarrega el justificant d'un assentament registral.
     *
     * @param assentament assentament registral
     * @return la pàgina de navegació si hi ha un error, null en cas que tot hagi anat bé i el fitxer s'hagi descarregat
     * @throws IOException si es produeix un error I/O
     */
    public String descarregarJustificant(AsientoRegistralWs assentament) throws IOException {
        LOG.info("descarregarJustificant({})", assentament.getNumeroRegistroFormateado());

        // Obté el justificant
        JustificanteWs justificant;
        try {
            justificant = asientoRegistralWs.obtenerJustificante(
                    configuracio.getEntitat(), assentament.getNumeroRegistroFormateado(), assentament.getTipoRegistro());
        } catch (WebServiceException | WsI18NException | WsValidationException ex) {
            LOG.error("Error descarregant justificant", ex);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error descarregant justificant.",
                    ex.getMessage()));
            context.getExternalContext().getFlash().setKeepMessages(true);
            return context.getViewRoot().getViewId() + "?faces-redirect=true";
        }

        String filename = "Justificant_" + assentament.getNumeroRegistroFormateado().replace('/', '_') + ".pdf";
        download(filename, "application/pdf", justificant.getJustificante());
        return null;
    }

    /**
     * Descarrega l'annex indicat
     *
     * @param annex Annex a descarregar
     * @return la pàgina de navegació si hi ha un error, null en cas que tot hagi anat bé i el fitxer s'hagi descarregat
     * @throws IOException si es produeix un error I/O
     */
    public String descarregarAnnex(AnexoWs annex) throws IOException {
        LOG.info("descarregarAnnex({})", annex.getNombreFicheroAnexado());
        download(annex.getNombreFicheroAnexado(), annex.getTipoMIMEFicheroAnexado(), annex.getFicheroAnexado());
        return null;
    }

    /**
     * Mètode d'utilitat per descarregar un fitxer
     *
     * @param filename Nom del fitxer
     * @param mimetype tipus mime pel content-type
     * @param content  contingut del fitxer
     * @throws IOException Si es produeix un error I/O
     */
    private void download(String filename, String mimetype, byte[] content) throws IOException {
        ExternalContext ec = context.getExternalContext();

        // Hem de resetejar la reposta per si hi ha cap capçalera o res.
        ec.responseReset();
        ec.setResponseContentType(mimetype);
        ec.setResponseContentLength(content.length);

        ec.setResponseHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");

        OutputStream output = ec.getResponseOutputStream();
        try (ByteArrayInputStream inputStream = new ByteArrayInputStream(content)) {
            inputStream.transferTo(output);
        }

        // Important perquè JSF no intenti seguir processant la resposta.
        context.responseComplete();
    }
}

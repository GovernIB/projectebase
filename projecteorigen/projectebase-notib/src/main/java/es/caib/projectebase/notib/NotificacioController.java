package es.caib.projectebase.notib;


import es.caib.notib.client.NotificacioRestClientV2;
import es.caib.notib.client.domini.Certificacio;
import es.caib.notib.client.domini.DocumentV2;
import es.caib.notib.client.domini.EntregaDeh;
import es.caib.notib.client.domini.Enviament;
import es.caib.notib.client.domini.EnviamentEstatEnum;
import es.caib.notib.client.domini.EnviamentTipusEnum;
import es.caib.notib.client.domini.InteressatTipusEnumDto;
import es.caib.notib.client.domini.NotificaServeiTipusEnumDto;
import es.caib.notib.client.domini.NotificacioV2;
import es.caib.notib.client.domini.Persona;
import es.caib.notib.client.domini.RespostaAltaV2;
import es.caib.notib.client.domini.RespostaConsultaEstatEnviamentV2;
import es.caib.notib.client.domini.RespostaConsultaEstatNotificacioV2;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * Controlador per gestionar la creació de notificacions.
 *
 * @author areus
 * @author anadal (upgrade to Notib client 1.1.19)
 */
@Named
@SessionScoped
public class NotificacioController implements Serializable {

    private static final long serialVersionUID = -9139731221657909836L;

    private static final Logger LOG = LoggerFactory.getLogger(NotificacioController.class);

    @Inject
    FacesContext context;

    /**
     * Obté el client de Notificacio a través de {@link ClientNotibProducer#getNotificacioRestClient(Configuracio)}
     */
    @Inject
    private NotificacioRestClientV2 client;

    /**
     * Model de dades del formulari
     */
    @Inject
    private NotificacioModel model;

    /* Mantenim en memòria una llista de les notificacions enviades. */
    private final List<RespostaAltaV2> respostes = new ArrayList<>();

    public List<RespostaAltaV2> getRespostes() {
        return respostes;
    }

    /* Mantenim en memòria l'estat dels enviaments */
    private final Map<String, EnviamentEstatEnum> estatReferencies = new HashMap<>();

    public Map<String, EnviamentEstatEnum> getEstatReferencies() {
        return estatReferencies;
    }

    /* Mantenim en memòria les certificacions dels enviaments */
    private final Map<String, Certificacio> certificacioReferencies = new HashMap<>();

    public Map<String, Certificacio> getCertificacioReferencies() {
        return certificacioReferencies;
    }

    /**
     * Construeix una notificació partir del model i l'envia amb el client.
     *
     * @return identificador de la vista carregar
     */
    public String crearNotificacio() {
        LOG.info("crearNotificacio");

        NotificacioV2 notificacio = new NotificacioV2();
        notificacio.setUsuariCodi(model.getUsuariCodi());
        
        notificacio.setEmisorDir3Codi(model.getEmisorDir3Codi());
        notificacio.setProcedimentCodi(model.getProcedimentCodi());
        
        notificacio.setConcepte(model.getConcepte());
        notificacio.setDescripcio(model.getDescripcio());
        notificacio.setEnviamentTipus(EnviamentTipusEnum.NOTIFICACIO);
        notificacio.setEnviamentDataProgramada(null);
        notificacio.setRetard(5);
        
        // El dia d'avui + 12 dies.
        XMLGregorianCalendar calendar = DatatypeFactory.newDefaultInstance().newXMLGregorianCalendar(
                GregorianCalendar.from(ZonedDateTime.now().plusDays(12)));
        notificacio.setCaducitat(calendar.toGregorianCalendar().getTime());
        
        DocumentV2 document = new DocumentV2();
        document.setArxiuNom("documentArxiuNom.pdf");
        
        Part fitxer = model.getFitxer();
        try (InputStream inputStream = fitxer.getInputStream()) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream((int) fitxer.getSize());
            inputStream.transferTo(baos);
            String contingutBase64 = Base64.getEncoder().encodeToString(baos.toByteArray());
            document.setContingutBase64(contingutBase64);
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
        
        document.setNormalitzat(false);
        notificacio.setDocument(document);
        
        Enviament enviament = new Enviament();

        // Titular de la notificació
        Persona titular = new Persona();
        titular.setNom(model.getTitularNom());
        titular.setLlinatge1(model.getTitularLlinatge1());
        titular.setLlinatge2(model.getTitularLlinatge2());
        titular.setNif(model.getTitularNif());
        titular.setEmail(model.getTitularEmail());
        titular.setInteressatTipus(InteressatTipusEnumDto.FISICA);
        enviament.setTitular(titular);

        // Entrega a la DEH
        EntregaDeh entregaDeh = new EntregaDeh();
        entregaDeh.setObligat(true);
        entregaDeh.setProcedimentCodi(model.getProcedimentCodi());
        enviament.setEntregaDeh(entregaDeh);

        // NORMAL o URGENT
        enviament.setServeiTipus(NotificaServeiTipusEnumDto.NORMAL);

        notificacio.getEnviaments().add(enviament);
        
        try {
            RespostaAltaV2 resposta = client.alta(notificacio);
            if (!resposta.isError()) {
                LOG.info("Notificacio creada: {}", resposta.getIdentificador());
                
                respostes.add(resposta);
                resposta.getReferencies().forEach(
                        referencia -> estatReferencies.put(referencia.getReferencia(), EnviamentEstatEnum.PENDENT));
                
                context.addMessage(null, new FacesMessage(
                    "Creada notificació: [" + resposta.getIdentificador() + "]", ""));
            } else {
                LOG.error("Error creant Notificació: {}", resposta.getErrorDescripcio());
                
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error creant Notificació.",
                    resposta.getErrorDescripcio()));
            }
            
        } catch (RuntimeException ex) {
            LOG.error("Error cridant API Notib", ex);

            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error cridant API Notib.",
                    ex.getMessage()));
        }
        
        context.getExternalContext().getFlash().setKeepMessages(true);
        return context.getViewRoot().getViewId() + "?faces-redirect=true";
    }

    /**
     * Consulta l'estat de la notificació, i actualitza la resposta amb el nou estat.
     * @param resposta informació sobre la notificació creada.
     * @return identificador de la vista carregar
     */
    public String consultaEstatNotificacio(RespostaAltaV2 resposta) {
        LOG.info("consultaEstatNotificacio({})", resposta.getIdentificador());
        
        RespostaConsultaEstatNotificacioV2 estatNotificacio = 
                client.consultaEstatNotificacio(resposta.getIdentificador());
        
        if (!estatNotificacio.isError()) {
            resposta.setEstat(estatNotificacio.getEstat());
        } else {
            LOG.error("Error consultant estat Notificacio: {}", estatNotificacio.getErrorDescripcio());
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error consultant estat Notificació.",
                    estatNotificacio.getErrorDescripcio()));
        }
            
        context.getExternalContext().getFlash().setKeepMessages(true);
        return context.getViewRoot().getViewId() + "?faces-redirect=true";
    }

    /**
     * Consulta l'estat d'un enviament mitjançant la seva referència. Guarda l'estat i la
     * certificació si es reb.
     * @param referencia identificador de l'enviament
     * @return identificador de la vista carregar
     */
    public String consultaEstatEnviament(String referencia) {
        LOG.info("consultaEstatEnviament({})", referencia);
        
        RespostaConsultaEstatEnviamentV2 estatEnviament =
                client.consultaEstatEnviament(referencia);
        
        if (!estatEnviament.isError()) {
            estatReferencies.put(referencia, estatEnviament.getEstat());
            if (estatEnviament.getCertificacio() != null) {
                certificacioReferencies.put(referencia, estatEnviament.getCertificacio());
            }
        } else {
            LOG.error("Error consultant estat Enviament: {}", estatEnviament.getErrorDescripcio());
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error consultant estat Enviament.",
                    estatEnviament.getErrorDescripcio()));
        }
            
        context.getExternalContext().getFlash().setKeepMessages(true);
        return context.getViewRoot().getViewId() + "?faces-redirect=true";
    }

    /**
     * Permet descarregar la certificació d'un enviament.
     * @param referencia identificador de l'enviament.
     * @return null atès que no cal retornar cap vista
     * @throws IOException si es produeix un error I/O
     */
    public String descarregaCertificacio(String referencia) throws IOException {
        LOG.info("descarregaCertificacio({})", referencia);
        Certificacio certificacio = certificacioReferencies.get(referencia);
        byte[] content = Base64.getDecoder().decode(certificacio.getContingutBase64());
        download("certificacio.pdf", certificacio.getTipusMime(), content);
        return null;
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

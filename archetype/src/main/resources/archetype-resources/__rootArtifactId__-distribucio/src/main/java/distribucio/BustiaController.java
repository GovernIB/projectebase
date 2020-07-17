#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.distribucio;

import es.caib.distribucio.ws.v1.bustia.BustiaV1;
import es.caib.distribucio.ws.v1.bustia.RegistreAnnex;
import es.caib.distribucio.ws.v1.bustia.RegistreAnotacio;
import es.caib.distribucio.ws.v1.bustia.RegistreInteressat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.Valid;
import javax.xml.datatype.DatatypeFactory;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.GregorianCalendar;
import java.util.UUID;

/**
 * Controlador d'exemple per enviar una anotació amb l'API Bustia de Distribució.
 *
 * @author areus
 */
@Named
@ViewScoped
public class BustiaController implements Serializable {
    
    private static final long serialVersionUID = -3671578659314269597L;

    private static final Logger LOG = LoggerFactory.getLogger(BustiaController.class);

    /**
     * Injecta l'API de Bustia que es generada per {@link ClientDistribucioProducer${symbol_pound}getBustiaClient(Configuracio)}
     */
    @Inject
    BustiaV1 bustia;
    
    @Inject
    private Configuracio configuracio;
    
    @Inject
    private FacesContext context;

    /**
     * Model de dades emprat dins el formulari.
     * Amb l'anotació @Valid activam la validació.
     */
    @Valid
    AnotacioModel model;

    @PostConstruct
    public void init() {
        LOG.info("init");
        loadModel();
    }

    private void loadModel() {
        model = new AnotacioModel();

        // Preemplenam amb dades d'exemple
        model.setTipus(TipusAnotacio.ENTRADA);
        model.setIdioma(IdiomaAnotacio.CATALA);
        model.setEntitat("A04003003");
        model.setUnitat("A04027005");
        model.setOficina("O00009560");
        model.setLlibre("16");
        
        model.setInteressatNif("99999999R");
        model.setInteressatNom("PRUEBAS");
        model.setInteressatPrimerLlinatge("EIDAS");
        model.setInteressatSegonLlinatge("CERTIFICADO");
        model.setInteressatPais("Espanya");
        model.setInteressatProvincia("Illes Balears");
        model.setInteressatMunicipi("Palma");
        model.setInteressatAdresa("Carrer s/n");
        model.setInteressatCodiPostal("07001");
        model.setInteressatEmail("pruebas@fundaciobit.org");
    }

    /**
     * Cridat en fer un submit del formulari per enviar una anotació amb l'API de bustia a partir de les dades
     * emplenades.
     */
    public void crearAnotacio() {
        LOG.info("crearAnotacio");

        RegistreAnotacio anotacio = getRegistreAnotacio();

        RegistreInteressat interessat = getRegistreInteressat();
        anotacio.getInteressats().add(interessat);

        if (model.getAnnex() != null) {
            RegistreAnnex annex = getRegistreAnnex();
            anotacio.getAnnexos().add(annex);
        }

        try {
            bustia.enviarAnotacioRegistreEntrada(model.getEntitat(), model.getUnitat(), anotacio);  
            LOG.info("Anotació enviada amb èxit");
            context.addMessage(null, new FacesMessage("Anotació enviada amb èxit", ""));
            loadModel();
        } catch (RuntimeException e) {
            LOG.error("Error enviant anotació", e);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error enviant anotació", e.getMessage()));
        }
    }

    /**
     * Crea l'annex a partir de les dades del formulari
     */
    private RegistreAnnex getRegistreAnnex() {
        RegistreAnnex annex = new RegistreAnnex();

        annex.setTitol("Annex");
        annex.setFitxerNom(model.getAnnex().getSubmittedFileName());
        annex.setFitxerTamany((int) model.getAnnex().getSize());
        annex.setFitxerTipusMime(model.getAnnex().getContentType());

        try (InputStream inputStream = model.getAnnex().getInputStream();
             ByteArrayOutputStream baos = new ByteArrayOutputStream((int) model.getAnnex().getSize())) {
            inputStream.transferTo(baos);
            annex.setFitxerContingut(baos.toByteArray());
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }


        annex.setEniDataCaptura(DatatypeFactory.newDefaultInstance().newXMLGregorianCalendar(
                GregorianCalendar.from(ZonedDateTime.now())));

        // 0 ciudadano, 1 administración
        annex.setEniOrigen("0");
        // "EE01" original
        annex.setEniEstatElaboracio("EE01");
        // Metadada NTI. TD14 = Solicitud del ciutadà
        annex.setEniTipusDocumental("TD14");
        return annex;
    }

    /**
     * Crea l'anotació a partir de les dades del formulari i la configuració.
     */
    private RegistreAnotacio getRegistreAnotacio() {
        RegistreAnotacio anotacio = new RegistreAnotacio();
        anotacio.setTipusES(model.getTipus().getCodi());
        anotacio.setIdiomaCodi(model.getIdioma().getCodi());

        // El moment actual
        anotacio.setData(DatatypeFactory.newDefaultInstance().newXMLGregorianCalendar(
                GregorianCalendar.from(ZonedDateTime.now())));

        anotacio.setAplicacioCodi("${projectname}");

        // Un número aleatori perquè no es repeteixi
        anotacio.setNumero("${projectname}-" + UUID.randomUUID().toString());

        anotacio.setIdentificador("${projectname}-distribucio");

        anotacio.setEntitatCodi(model.getEntitat());
        anotacio.setOficinaCodi(model.getOficina());
        anotacio.setLlibreCodi(model.getLlibre());

        anotacio.setExtracte(model.getExtracte());

        anotacio.setUsuariCodi(configuracio.getUsername());
        return anotacio;
    }

    /**
     * Crea l'interessat a partir de les dades del formulari.
     */
    private RegistreInteressat getRegistreInteressat() {
        RegistreInteressat interessat = new RegistreInteressat();
        // persona física
        interessat.setTipus("2");
        // 'N' Nif, 'E' Nie, ...
        interessat.setDocumentTipus("N");

        interessat.setDocumentNum(model.getInteressatNif());
        interessat.setNom(model.getInteressatNom());
        interessat.setLlinatge1(model.getInteressatPrimerLlinatge());
        interessat.setLlinatge2(model.getInteressatSegonLlinatge());

        interessat.setPais(model.getInteressatPais());
        interessat.setProvincia(model.getInteressatProvincia());
        interessat.setMunicipi(model.getInteressatMunicipi());
        interessat.setAdresa(model.getInteressatAdresa());
        interessat.setCodiPostal(model.getInteressatCodiPostal());
        interessat.setEmail(model.getInteressatEmail());
        return interessat;
    }

    // Getters & Setters

    public AnotacioModel getModel() {
        return model;
    }
}

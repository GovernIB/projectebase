package es.caib.projectebase.distribucio;

import es.caib.distribucio.ws.v1.bustia.BustiaV1;
import es.caib.distribucio.ws.v1.bustia.RegistreAnotacio;
import es.caib.distribucio.ws.v1.bustia.RegistreInteressat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.GregorianCalendar;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

@Named
@ViewScoped
public class BustiaController implements Serializable {
    
    private static final long serialVersionUID = -3671578659314269597L;

    private static final Logger LOG = LoggerFactory.getLogger(BustiaController.class);

    @Inject
    BustiaV1 bustia;
    
    @Inject
    private Configuracio configuracio;
    
    @Inject
    private FacesContext context;

    BustiaModel model;

    @PostConstruct
    public void init() {
        LOG.info("init");
        loadModel();
    }

    private void loadModel() {
        model = new BustiaModel();
        model.setTipus(TipusAnotacio.ENTRADA);
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

    public void crearAnotacio() {
        LOG.info("crearAnotacio");

        RegistreAnotacio anotacio = new RegistreAnotacio();
        anotacio.setTipusES(model.getTipus().getCodi());
        
        // El moment actual
        XMLGregorianCalendar data = DatatypeFactory.newDefaultInstance().newXMLGregorianCalendar(
                GregorianCalendar.from(ZonedDateTime.now()));
        anotacio.setData(data);
        
        anotacio.setAplicacioCodi("ProjecteBase");
        
        // Un número aleatori perquè no es repeteixi
        anotacio.setNumero("ProjecteBase-" + UUID.randomUUID().toString());
        
        anotacio.setIdentificador("ProjecteBase-distribucio");
        
        anotacio.setEntitatCodi(model.getEntitat());
        anotacio.setOficinaCodi(model.getOficina());
        anotacio.setLlibreCodi(model.getLlibre());
       
        anotacio.setExtracte(model.getExtracte());
        
        // TODO: permetre seleccionar
        anotacio.setIdiomaCodi("1");
        
        anotacio.setUsuariCodi(configuracio.getUsername());
        
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
        
        anotacio.getInteressats().add(interessat);
        
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
    
    // Getters & Setters

    public BustiaModel getModel() {
        return model;
    }
}

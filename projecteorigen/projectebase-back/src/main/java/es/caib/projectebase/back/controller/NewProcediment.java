package es.caib.projectebase.back.controller;

import es.caib.projectebase.service.facade.ProcedimentServiceFacade;
import es.caib.projectebase.service.model.ProcedimentDTO;
import es.caib.projectebase.service.model.UnitatOrganicaDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ResourceBundle;

/**
 * Controlador per la creació de Procediments. El definim a l'scope de view perquè a nivell de request es
 * reconstruiria per cada petició AJAX, com ara amb els errors de validació. Amb view es manté mentre no es canvii
 * de vista.
 *
 * @author areus
 */
@Named
@ViewScoped
public class NewProcediment extends AbstractController implements Serializable {

    private static final long serialVersionUID = -6369618058993094891L;

    private static final Logger LOG = LoggerFactory.getLogger(NewProcediment.class);

    @EJB
    ProcedimentServiceFacade procedimentService;

    // PROPIETATS + GETTERS/SETTERS

    private UnitatOrganicaDTO unitat;
    private ProcedimentDTO procediment;

    /**
     * Obté la unitat orgànica a la que pertany el procediment que s'està editant
     */
    public UnitatOrganicaDTO getUnitat() {
        return unitat;
    }
    
    public void setUnitat(UnitatOrganicaDTO unitat) {
        this.unitat = unitat;
    }

    /**
     * Obté el procediment que s'està editant.
     */
    public ProcedimentDTO getProcediment() {
        return procediment;
    }

    /**
     * Inicialitzam el bean amb les dades inicials.
     */
    @PostConstruct
    public void init() {
        LOG.debug("init");
        procediment = new ProcedimentDTO();
    }

    /**
     * Crea el procediment. Afegeix un missatge si s'ha fet amb èxit
     * i redirecciona cap a la pàgina de llistat.
     *
     * @return navegació cap al llistat d'unitats orgàniques.
     */
    public String save() {
        LOG.debug("save");

        procedimentService.create(procediment, unitat.getId());
        
        ResourceBundle labelsBundle = getBundle("labels");
        addGlobalMessage(labelsBundle.getString("msg.creaciocorrecta"));
        
        // Els missatges no aguanten una redirecció ja que no es la mateixa petició
        // així asseguram que es guardin fins la visualització
        keepMessages();

        // Redireccionam cap al llistat de procediments, mantenint l'identificador de unitat orgànica
        return "/listProcediment?faces-redirect=true&includeViewParams=true";
    }
}

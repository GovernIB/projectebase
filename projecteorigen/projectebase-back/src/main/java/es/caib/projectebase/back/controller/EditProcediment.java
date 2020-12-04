package es.caib.projectebase.back.controller;

import es.caib.projectebase.back.model.ProcedimentModel;
import es.caib.projectebase.service.facade.ProcedimentServiceFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ResourceBundle;

/**
 * Controlador per l'edició de Procediments. El definim a l'scope de view perquè a nivell de request es
 * reconstruiria per cada petició AJAX, com ara amb els errors de validació. Amb view es manté mentre no es canvii
 * de vista.
 *
 * @author areus
 * @author anadal
 */
@Named
@ViewScoped
public class EditProcediment extends AbstractController implements Serializable {

    private static final long serialVersionUID = -6369618058993094891L;

    private static final Logger LOG = LoggerFactory.getLogger(EditProcediment.class);

    @EJB
    ProcedimentServiceFacade procedimentService;

    @Inject
    private ProcedimentModel procediment;

    // ACCIONS

    /**
     * Actualitza el procediment que s'està editant. Afegeix un missatge si s'ha fet amb èxit
     * i redirecciona cap a la pàgina de llistat.
     *
     * @return navegació cap al llistat d'unitats orgàniques.
     */
    public String update() {
        LOG.debug("update");

        procedimentService.update(procediment.getValue());
        
        ResourceBundle labelsBundle = getBundle("labels");
        addGlobalMessage(labelsBundle.getString("msg.actualitzaciocorrecta"));

        // Els missatges no aguanten una redirecció ja que no es la mateixa petició
        // així asseguram que es guardin fins la visualització
        keepMessages();

        // Redireccionam cap al llistat de procediments, mantenint l'identificador de unitat orgànica
        return "/listProcediment?faces-redirect=true&includeViewParams=true";
    }
}

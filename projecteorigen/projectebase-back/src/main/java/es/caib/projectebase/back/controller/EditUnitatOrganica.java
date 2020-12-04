package es.caib.projectebase.back.controller;

import java.io.Serializable;
import java.util.ResourceBundle;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import es.caib.projectebase.back.model.UnitatModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.caib.projectebase.service.facade.UnitatOrganicaServiceFacade;

/**
 * Controlador per l'edició d'Unitats Organiques. El definim a l'scope de view perquè a nivell
 * de request es reconstruiria per cada petició AJAX, com ara amb els errors de validació. Amb
 * view es manté mentre no es canvii de vista.
 *
 * @author areus
 */
@Named
@ViewScoped
public class EditUnitatOrganica extends AbstractController implements Serializable {

    private static final long serialVersionUID = -4092311228270716321L;

    private static final Logger LOG = LoggerFactory.getLogger(EditUnitatOrganica.class);

    @EJB
    UnitatOrganicaServiceFacade unitatOrganicaService;

    @Inject
    private UnitatModel unitat;

    // ACCIONS
    
    /**
     * Actualitza la unitat orgànica que s'està editant. Afegeix un missatge si s'ha fet
     * amb èxit i redirecciona cap a la pàgina de llistat.
     *
     * @return navegació cap al llistat d'unitats orgàniques.
     */
    public String update() {
        LOG.debug("update");

        unitatOrganicaService.update(unitat.getValue());
            
        ResourceBundle labelsBundle = getBundle("labels");
        addGlobalMessage(labelsBundle.getString("msg.actualitzaciocorrecta"));

        // Els missatges no aguanten una redirecció ja que no es la mateixa petició
        // Així asseguram que es guardin fins la visualització
        keepMessages();

        // Redireccionam cap al llistat d'unitats orgàniques
        return "/listUnitatOrganica?faces-redirect=true";
    }
}

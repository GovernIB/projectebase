#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.back.controller;

import ${package}.back.utils.I18NTranslatorBack;
import ${package}.commons.i18n.I18NException;
import ${package}.ejb.UnitatOrganicaService;
import ${package}.persistence.UnitatOrganica;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Controlador per l'edició d'Unitats Organiques. El definim a l'scope de view perquè a nivell
 * de request es reconstruiria per cada petició AJAX, com ara amb els errors de validació. Amb
 * view es manté mentre no es canvii de vista.
 *
 * @author areus
 */
@Named("editUnitatOrganica")
@ViewScoped
public class EditUnitatOrganicaController implements Serializable {

    private Logger log = LoggerFactory.getLogger(EditUnitatOrganicaController.class);

    @Inject
    private FacesContext context;

    @Inject
    private Flash flash;

    @EJB
    UnitatOrganicaService unitatOrganicaService;

    // PROPIETATS + GETTERS/SETTERS

    private UnitatOrganica current;

    /**
     * Obté la unitat orgànica que s'està editant
     */
    public UnitatOrganica getCurrent() {
        return current;
    }

    /**
     * Indica si és una creació o una actualització segons s'hagi fixat o no l'id de la unitat
     * orgànica.
     * 
     * @return <code>true</code> si l'id és null, i per tant és una creació, <code>false</code>
     *         en cas contrari.
     */
    public boolean isCreate() {
        return current.getId() == null;
    }

    /**
     * Inicialitzam el bean amb les dades inicials.
     */
    @PostConstruct
    public void init() {
        log.debug("init");
        current = new UnitatOrganica();
    }

    // ACCIONS

    /**
     * Carrega la unitat orgànica per editar.
     */
    public void load() {
        log.debug("load");
        if (current.getId() != null) {
            current = unitatOrganicaService.findById(current.getId());
        }
    }

    /**
     * Crea o actualitza la unitat orgànica que s'està editant. Afegeix un missatge si s'ha fet
     * amb èxit i redirecciona cap a la pàgina de llistat.
     * 
     * @return navegació cap al llistat d'unitats orgàniques.
     */
    public String saveOrUpdate() {
        log.debug("saveOrUpdate");
        try {
          if (isCreate()) {
              unitatOrganicaService.create(current);
              // Creació correcta
              context.addMessage(null, new FacesMessage(I18NTranslatorBack.translate("msg.creaciocorrecta")));
          } else {
              unitatOrganicaService.update(current);
              // Actualització correcta
              context.addMessage(null, new FacesMessage(I18NTranslatorBack.translate("msg.actualitzaciocorrecta")));
          }
        } catch (I18NException i18ne) {

            String msgError = I18NTranslatorBack.translate(i18ne);

            log.error("${symbol_escape}nError saveOrUpdate() => " + msgError + "${symbol_escape}n"); 

            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msgError, ""));
        }
        // Els missatges no aguanten una redirecció ja que no es la mateixa petició
        // amb l'objecte flash podem assegurar que es guardin fins la visualització
        flash.setKeepMessages(true);
        // Redireccionam cap al llistat d'unitats orgàniques
        return "/listUnitatOrganica?faces-redirect=true";
    }


    /**
     * 
     * @return
     */
    public String testError() {

        {
            String[] labels = { "example.error", // EJB
                    "error.query", // persistence
                    "javax.validation.constraints.Size.message", // ValidationMessages.properties
                    "accessibilitat_title" // BACK
            };

            log.info("${symbol_escape}n${symbol_escape}n${symbol_escape}n");
            log.info("======= TRADUCCIONS BACK =============");

              for (String label : labels) {
                  try {
                  log.info("Traduccio[" + label + "] => |"
                          + I18NTranslatorBack.translate(label) + "|");
                } catch (Throwable th) {
                    log.error("NO TROB TRADUCCIO PER [" + label + "] => " + th.getMessage(), th);
                }
              }
           

            log.info("${symbol_escape}n${symbol_escape}n${symbol_escape}n");
            
        }


        try {
            unitatOrganicaService.testTranslationError();
        } catch (I18NException i18ne) {

            String msgError = I18NTranslatorBack.translate(i18ne);

            log.error("${symbol_escape}nError cridant a testTranslationError() => " + msgError + "${symbol_escape}n"); 

            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msgError, ""));
            
            // Redireccionam cap a l'unitat orgànica que estam editant
            flash.setKeepMessages(true);
            return "/editUnitatOrganica?faces-redirect=true&includeViewParams=true";
        }

        // Els missatges no aguanten una redirecció ja que no es la mateixa petició
        // amb l'objecte flash podem assegurar que es guardin fins la visualització
        flash.setKeepMessages(true);
        return "/listUnitatOrganica?faces-redirect=true";

    }

}

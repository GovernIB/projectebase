package es.caib.projectebase.back.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/**
 * Proporciona les opcions d'idioma. Atès que són les mateixes sempre per tots els usuaris
 * ho ficam com un bean a l'scope d'aplicació perquè només es carregui una vegada.
 *
 * @author areus
 */
@Named
@ApplicationScoped
public class LanguageController {

    private static final Logger LOG = LoggerFactory.getLogger(LanguageController.class);

    @Inject
    private FacesContext context;

    private List<String> languages;

    /**
     * Obté la llista d'idiomes disponibles
     */
    public List<String> getLanguages() {
        return languages;
    }

    /**
     * Dins l'inialització és quan carregam la llista d'idiomes.
     */
    @PostConstruct
    private void init() {
        LOG.info("Inicialitzat idiomes disponibles");
        languages = new ArrayList<>();
        Iterator<Locale> it = context.getApplication().getSupportedLocales();
        while (it.hasNext()) {
            languages.add(it.next().toLanguageTag());
        }
        LOG.info("Idiomes disponibles: " + languages);
    }
}

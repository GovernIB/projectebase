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
 * Proporcina les opcions d'idioma. Atès que són les mateixes sempre per tots els usuaris
 * ho ficam com un bean d'aplicació comú
 *
 * @author areus
 */
@Named
@ApplicationScoped
public class LanguageController {

    private static final Logger log = LoggerFactory.getLogger(LanguageController.class);

    @Inject
    private FacesContext context;

    private List<String> languages;

    public List<String> getLanguages() {
        return languages;
    }

    @PostConstruct
    private void init() {
        log.info("Inicialitzat idiomes disponibles");
        languages = new ArrayList<>();
        Iterator<Locale> it = context.getApplication().getSupportedLocales();
        while (it.hasNext()) {
            languages.add(it.next().toLanguageTag());
        }
    }
}

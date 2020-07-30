#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.back.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Proporciona les opcions d'idioma.
 *
 * @author areus
 */
@Named
@ApplicationScoped
public class ApplicationLocales {

    private static final Logger LOG = LoggerFactory.getLogger(ApplicationLocales.class);

    @Inject
    private FacesContext context;

    private List<Locale> available;

    public List<Locale> getAvailable() {
        return available;
    }

    /**
     * Dins l'inialització és quan carregam la llista d'idiomes.
     */
    @PostConstruct
    private void init() {
        LOG.info("Inicialitzat idiomes disponibles");
        available = new ArrayList<>();
        context.getApplication().getSupportedLocales()
                .forEachRemaining(available::add);
        LOG.info("Idiomes disponibles: " + available);
    }
}

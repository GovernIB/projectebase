package es.caib.projectebase.ejb;

import es.caib.projectebase.commons.i18n.I18NArgumentCode;
import es.caib.projectebase.commons.i18n.I18NArgumentString;
import es.caib.projectebase.commons.i18n.I18NException;
import es.caib.projectebase.ejb.interceptor.Logged;
import es.caib.projectebase.ejb.utils.I18NTranslatorEjb;
import es.caib.projectebase.jpa.UnitatOrganica;
import es.caib.projectebase.jpa.dao.UnitatOrganicaDAO;

import javax.ejb.Stateless;
import java.util.Locale;

/**
 * Servei EJB per gestionar {@link UnitatOrganica}. Li aplicam l'interceptor {@link Logged},
 * per tant, totes les cridades es loguejeran.
 *
 * @author areus
 */
@Logged
@Stateless
public class UnitatOrganicaEJB extends UnitatOrganicaDAO implements UnitatOrganicaService {

    
    @Override
    public void testTranslationError() throws I18NException {

        Locale[] locales = new Locale[] { new Locale("es"), new Locale("en") };

        String[] labels = { "example.error", // EJB
                "error.query", // JPA
                "javax.validation.constraints.Size.message" // ValidationMessages.properties
        };

        log.info("\n\n\n");
        log.info("======= TRADUCCIONS EJB =============");

        for (Locale locale : locales) {

            for (String label : labels) {
                log.info("Traduccio{" + locale.getLanguage() + "}[" + label + "] => |"
                        + I18NTranslatorEjb.translate(locale, label) + "|");
            }
        }

        log.info("\n\n\n");

        throw new I18NException("example.error", new I18NArgumentCode("PARAM1.CODE"),
                new I18NArgumentString("AIXO ES UN ARGUMENT STRING"));

    }
}

#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.ejb;

import ${package}.commons.i18n.I18NArgumentCode;
import ${package}.commons.i18n.I18NArgumentString;
import ${package}.commons.i18n.I18NException;
import ${package}.commons.utils.Constants;
import ${package}.ejb.interceptor.Logged;
import ${package}.ejb.utils.I18NTranslatorEjb;
import ${package}.persistence.UnitatOrganica;
import ${package}.persistence.dao.UnitatOrganicaDAO;

import javax.annotation.security.RolesAllowed;
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
@RolesAllowed(Constants.${prefixuppercase}_ADMIN)
public class UnitatOrganicaEJB extends UnitatOrganicaDAO implements UnitatOrganicaService {

    @Override
    public void testTranslationError() throws I18NException {

        Locale[] locales = new Locale[] { new Locale("es"), new Locale("en") };

        String[] labels = { "example.error", // EJB
                "error.query", // persistence
                "javax.validation.constraints.Size.message" // ValidationMessages.properties
        };

        log.info("${symbol_escape}n${symbol_escape}n${symbol_escape}n");
        log.info("======= TRADUCCIONS EJB =============");

        for (Locale locale : locales) {

            for (String label : labels) {
                log.info("Traduccio{" + locale.getLanguage() + "}[" + label + "] => |"
                        + I18NTranslatorEjb.translate(locale, label) + "|");
            }
        }

        log.info("${symbol_escape}n${symbol_escape}n${symbol_escape}n");

        throw new I18NException("example.error", new I18NArgumentCode("PARAM1.CODE"),
                new I18NArgumentString("AIXO ES UN ARGUMENT STRING"));

    }
}

#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.api.providers;

import ${package}.api.utils.I18NTranslatorRest;
import ${package}.commons.i18n.I18NException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Permet mapejar a una respota comuna a les excepcions de tipus I18NException.
 *
 * @author areus
 */
@Provider
public class I18NExceptionMapper implements ExceptionMapper<I18NException> {

    private static final Logger LOG = LoggerFactory.getLogger(I18NExceptionMapper.class);

    private List<Locale> supportedLocales;

    @Context
    private HttpHeaders headers;

    @Context
    private HttpServletRequest request;

    @Context
    private ServletContext servletContext;

    @PostConstruct
    private void init() {
        String supportedLocalesParam = servletContext.getInitParameter("supportedLocales");
        supportedLocales = Stream.of(supportedLocalesParam.split(","))
                .map(String::trim)
                .map(Locale::forLanguageTag)
                .collect(Collectors.toList());
    }

    @Override
    public Response toResponse(I18NException e) {
        LOG.error("Rebuda una I18NException de la capa EJB: " + e.getMessage());

        Locale locale = supportedLocales.isEmpty() ? Locale.getDefault() : supportedLocales.get(0);
        String lang = request.getParameter("lang");
        if (lang != null && !lang.isEmpty()) {
            Locale langLocale = Locale.forLanguageTag(lang);
            if (supportedLocales.contains(langLocale)) {
                locale = langLocale;
            }
        } else {
            List<Locale> acceptableLangauges = headers.getAcceptableLanguages();
            for (Locale acceptableLocale : acceptableLangauges) {
                if (supportedLocales.contains(acceptableLocale)) {
                    locale = acceptableLocale;
                    break;
                }
            }
        }
        LOG.error("Emprant locale: " + locale);

        String msg = I18NTranslatorRest.translate(e, locale);
        return Response.status(Response.Status.BAD_REQUEST).entity(msg).build();
    }
}

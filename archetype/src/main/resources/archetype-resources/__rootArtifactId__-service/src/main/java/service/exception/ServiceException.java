#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service.exception;

import java.util.Locale;

/**
 * Excepció general de la capa de serveis.
 *
 * @author areus
 */
public class ServiceException extends AbstractServiceException {

    private static final long serialVersionUID = 1L;

    public ServiceException() {
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    @Override
    public String getLocalizedMessage(Locale locale) {
        return translate(locale, "error.intern");
    }
}

#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service.exception;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Definició dels mètodes d'utilitat per la jerarquia d'exepcions de servei.
 */
public abstract class AbstractServiceException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public AbstractServiceException() {
        super();
    }

    public AbstractServiceException(Throwable cause) {
        super(cause);
    }

    @Override
    public String getLocalizedMessage() {
        return getLocalizedMessage(Locale.getDefault());
    }

    public abstract String getLocalizedMessage(Locale locale);

    private ResourceBundle getBundle(Locale locale) {
        return ResourceBundle.getBundle("service.ExceptionMessages", locale);
    }

    protected String translate(Locale locale, String key, Object... params) {
        String format = getBundle(locale).getString(key);
        return MessageFormat.format(format, params);
    }

    protected String translate(Locale locale, String key) {
        return getBundle(locale).getString(key);
    }
}

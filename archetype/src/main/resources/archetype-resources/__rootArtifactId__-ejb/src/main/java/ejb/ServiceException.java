#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.ejb;

import ${package}.commons.i18n.I18NException;
import ${package}.commons.i18n.I18NTranslator;

import javax.ejb.ApplicationException;

/**
 * Excpecions de la capa de serveis. Són excepcions traduïbles que empren les etiquetes definides a
 * <code>ejb.LabelsEJB</code>. Les marcam com a excepcions d'aplicació amb
 * <code>rollback=true</code> perquè tirin enrera la transacció quan es produeixen.
 */
@ApplicationException(rollback = true)
public class ServiceException extends I18NException {

    private static final long serialVersionUID = -228470180626814339L;

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Object... parameters) {
        super(message, parameters);
    }

    public ServiceException(Throwable cause, String message) {
        super(cause, message);
    }

    public ServiceException(Throwable cause, String message, Object... parameters) {
        super(cause, message, parameters);
    }

    @Override
    protected I18NTranslator getTranslator() {
        return I18NTranslator.from("ejb.LabelsEJB");
    }
}

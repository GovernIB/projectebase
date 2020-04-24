package es.caib.projectebase.persistence.dao;

import es.caib.projectebase.commons.i18n.I18NException;
import es.caib.projectebase.commons.i18n.I18NTranslator;

import javax.ejb.ApplicationException;

/**
 * Excpecions de la capa DAO. Són excepcions traduïbles que empren les etiquetes definides a
 * <code>persistence.LabelsPersistence</code>. Les marcam com a excepcions d'aplicació amb
 * <code>rollback=true</code> perquè tirin enrera la transacció quan es produeixen.
 */
@ApplicationException(rollback = true)
public class DAOException extends I18NException {

    private static final long serialVersionUID = -2669001717398599810L;

    public DAOException(String message) {
        super(message);
    }

    public DAOException(String message, Object... parameters) {
        super(message, parameters);
    }

    public DAOException(Throwable cause, String message) {
        super(cause, message);
    }

    public DAOException(Throwable cause, String message, Object... parameters) {
        super(cause, message, parameters);
    }

    @Override
    protected I18NTranslator getTranslator() {
        return I18NTranslator.from("persistence.LabelsPersistence");
    }
}

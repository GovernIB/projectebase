package es.caib.projectebase.service.exception;

/**
 * Excepció de la capa de servei.
 *
 * @author areus
 */
public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}

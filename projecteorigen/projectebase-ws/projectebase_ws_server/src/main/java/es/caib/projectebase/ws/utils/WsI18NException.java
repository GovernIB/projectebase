package es.caib.projectebase.ws.utils;

import javax.xml.ws.WebFault;

/**
 * Representació d'una excepció WS per la seva seralització en xml dins una respota WS.
 *
 * @author anadal
 */
@WebFault(name = "WsI18NError")
public class WsI18NException extends Exception {

    private static final long serialVersionUID = -1486765710138742787L;

    /**
     *
     */
    public WsI18NException() {
        super();
    }

    /**
     * @param message
     */
    public WsI18NException(String message) {
        super(message);
    }

    /**
     * @param message
     */
    public WsI18NException(String message, Throwable cause) {
        super(message, cause);
    }
}
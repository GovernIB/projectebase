package es.caib.projectebase.ws.utils;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.WebFault;

/**
 * Representació d'una excepció traduïble per la seva seralització en xml dins una respota WS.
 *
 * @author anadal
 */
@WebFault(name = "WsI18NError")
@XmlSeeAlso({WsI18NTranslation.class})
public class WsI18NException extends Exception {

    private static final long serialVersionUID = -1486765710138742787L;

    @XmlElement
    protected WsI18NTranslation translation;

    /**
     *
     */
    public WsI18NException() {
        super();
    }

    /**
     * @param message
     */
    public WsI18NException(WsI18NTranslation translation, String message) {
        super(message);
        this.translation = translation;
    }

    /**
     * @param message
     */
    public WsI18NException(WsI18NTranslation translation, String message, Throwable cause) {
        super(message, cause);
        this.translation = translation;
    }

    public WsI18NTranslation getTranslation() {
        return translation;
    }

    public void setTranslation(WsI18NTranslation translation) {
        this.translation = translation;
    }

}
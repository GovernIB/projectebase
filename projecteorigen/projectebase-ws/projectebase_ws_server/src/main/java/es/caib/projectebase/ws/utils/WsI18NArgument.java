package es.caib.projectebase.ws.utils;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Representació d'un argument de traducció per la seva seralització en xml dins una respota WS.
 *
 * @author anadal
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"value", "translate"})
public class WsI18NArgument {

    @XmlElement(name = "value")
    protected String value;

    @XmlElement(name = "translate")
    protected boolean translate;

    /**
     *
     */
    public WsI18NArgument() {
        super();
    }

    /**
     * @param value
     */
    public WsI18NArgument(String value, boolean translate) {
        super();
        this.value = value;
        this.translate = translate;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isTranslate() {
        return translate;
    }

    public void setTranslate(boolean translate) {
        this.translate = translate;
    }

    @Override
    public String toString() {
        return (translate ? "*" : "") + this.value;
    }

}

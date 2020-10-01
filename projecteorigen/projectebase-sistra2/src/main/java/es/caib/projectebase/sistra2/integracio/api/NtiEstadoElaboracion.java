
package es.caib.projectebase.sistra2.integracio.api;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ntiEstadoElaboracion.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ntiEstadoElaboracion"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="ORIGINAL"/&gt;
 *     &lt;enumeration value="COPIA_ELECT_AUTENTICA_CANVI_FORMAT"/&gt;
 *     &lt;enumeration value="COPIA_ELECT_AUTENTICA_PAPER"/&gt;
 *     &lt;enumeration value="COPIA_ELECT_AUTENTICA_PARCIAL"/&gt;
 *     &lt;enumeration value="ALTRES"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "ntiEstadoElaboracion")
@XmlEnum
public enum NtiEstadoElaboracion {

    ORIGINAL,
    COPIA_ELECT_AUTENTICA_CANVI_FORMAT,
    COPIA_ELECT_AUTENTICA_PAPER,
    COPIA_ELECT_AUTENTICA_PARCIAL,
    ALTRES;

    public String value() {
        return name();
    }

    public static NtiEstadoElaboracion fromValue(String v) {
        return valueOf(v);
    }

}

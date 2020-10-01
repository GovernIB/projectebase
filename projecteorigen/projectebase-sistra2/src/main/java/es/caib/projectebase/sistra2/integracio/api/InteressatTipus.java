
package es.caib.projectebase.sistra2.integracio.api;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for interessatTipus.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="interessatTipus"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="PERSONA_FISICA"/&gt;
 *     &lt;enumeration value="PERSONA_JURIDICA"/&gt;
 *     &lt;enumeration value="ADMINISTRACIO"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "interessatTipus")
@XmlEnum
public enum InteressatTipus {

    PERSONA_FISICA,
    PERSONA_JURIDICA,
    ADMINISTRACIO;

    public String value() {
        return name();
    }

    public static InteressatTipus fromValue(String v) {
        return valueOf(v);
    }

}

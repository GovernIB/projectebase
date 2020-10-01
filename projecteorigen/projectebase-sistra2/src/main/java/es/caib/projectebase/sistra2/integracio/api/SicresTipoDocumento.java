
package es.caib.projectebase.sistra2.integracio.api;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for sicresTipoDocumento.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="sicresTipoDocumento"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="FORMULARI"/&gt;
 *     &lt;enumeration value="ADJUNT"/&gt;
 *     &lt;enumeration value="TECNIC_INTERN"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "sicresTipoDocumento")
@XmlEnum
public enum SicresTipoDocumento {

    FORMULARI,
    ADJUNT,
    TECNIC_INTERN;

    public String value() {
        return name();
    }

    public static SicresTipoDocumento fromValue(String v) {
        return valueOf(v);
    }

}

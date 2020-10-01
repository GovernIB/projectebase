
package es.caib.projectebase.sistra2.integracio.api;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for documentTipus.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="documentTipus"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="NIF"/&gt;
 *     &lt;enumeration value="CIF"/&gt;
 *     &lt;enumeration value="PASSAPORT"/&gt;
 *     &lt;enumeration value="NIE"/&gt;
 *     &lt;enumeration value="CODI_ORIGEN"/&gt;
 *     &lt;enumeration value="ALTRES"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "documentTipus")
@XmlEnum
public enum DocumentTipus {

    NIF,
    CIF,
    PASSAPORT,
    NIE,
    CODI_ORIGEN,
    ALTRES;

    public String value() {
        return name();
    }

    public static DocumentTipus fromValue(String v) {
        return valueOf(v);
    }

}

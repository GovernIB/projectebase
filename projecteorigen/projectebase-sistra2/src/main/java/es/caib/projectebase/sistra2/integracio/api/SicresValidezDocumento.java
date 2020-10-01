
package es.caib.projectebase.sistra2.integracio.api;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for sicresValidezDocumento.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="sicresValidezDocumento"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="COPIA"/&gt;
 *     &lt;enumeration value="COPIA_ELEC_AUTENTICA"/&gt;
 *     &lt;enumeration value="ORIGINAL"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "sicresValidezDocumento")
@XmlEnum
public enum SicresValidezDocumento {

    COPIA,
    COPIA_ELEC_AUTENTICA,
    ORIGINAL;

    public String value() {
        return name();
    }

    public static SicresValidezDocumento fromValue(String v) {
        return valueOf(v);
    }

}

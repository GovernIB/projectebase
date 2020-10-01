
package es.caib.projectebase.sistra2.integracio.api;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for firmaPerfil.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="firmaPerfil"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="BES"/&gt;
 *     &lt;enumeration value="EPES"/&gt;
 *     &lt;enumeration value="LTV"/&gt;
 *     &lt;enumeration value="T"/&gt;
 *     &lt;enumeration value="C"/&gt;
 *     &lt;enumeration value="X"/&gt;
 *     &lt;enumeration value="XL"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "firmaPerfil")
@XmlEnum
public enum FirmaPerfil {

    BES,
    EPES,
    LTV,
    T,
    C,
    X,
    XL;

    public String value() {
        return name();
    }

    public static FirmaPerfil fromValue(String v) {
        return valueOf(v);
    }

}

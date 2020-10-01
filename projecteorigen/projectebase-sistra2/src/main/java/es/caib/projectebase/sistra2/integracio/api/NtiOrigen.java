
package es.caib.projectebase.sistra2.integracio.api;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ntiOrigen.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ntiOrigen"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="CIUTADA"/&gt;
 *     &lt;enumeration value="ADMINISTRACIO"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "ntiOrigen")
@XmlEnum
public enum NtiOrigen {

    CIUTADA,
    ADMINISTRACIO;

    public String value() {
        return name();
    }

    public static NtiOrigen fromValue(String v) {
        return valueOf(v);
    }

}

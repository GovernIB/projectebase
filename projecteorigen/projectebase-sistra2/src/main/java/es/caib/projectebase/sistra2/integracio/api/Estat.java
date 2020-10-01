
package es.caib.projectebase.sistra2.integracio.api;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for estat.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="estat"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="PENDENT"/&gt;
 *     &lt;enumeration value="REBUDA"/&gt;
 *     &lt;enumeration value="PROCESSADA"/&gt;
 *     &lt;enumeration value="REBUTJADA"/&gt;
 *     &lt;enumeration value="ERROR"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "estat")
@XmlEnum
public enum Estat {

    PENDENT,
    REBUDA,
    PROCESSADA,
    REBUTJADA,
    ERROR;

    public String value() {
        return name();
    }

    public static Estat fromValue(String v) {
        return valueOf(v);
    }

}

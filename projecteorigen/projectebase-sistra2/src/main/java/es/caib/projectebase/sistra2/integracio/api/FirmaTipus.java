
package es.caib.projectebase.sistra2.integracio.api;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for firmaTipus.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="firmaTipus"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="CSV"/&gt;
 *     &lt;enumeration value="XADES_DET"/&gt;
 *     &lt;enumeration value="XADES_ENV"/&gt;
 *     &lt;enumeration value="CADES_DET"/&gt;
 *     &lt;enumeration value="CADES_ATT"/&gt;
 *     &lt;enumeration value="PADES"/&gt;
 *     &lt;enumeration value="SMIME"/&gt;
 *     &lt;enumeration value="ODT"/&gt;
 *     &lt;enumeration value="OOXML"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "firmaTipus")
@XmlEnum
public enum FirmaTipus {

    CSV,
    XADES_DET,
    XADES_ENV,
    CADES_DET,
    CADES_ATT,
    PADES,
    SMIME,
    ODT,
    OOXML;

    public String value() {
        return name();
    }

    public static FirmaTipus fromValue(String v) {
        return valueOf(v);
    }

}

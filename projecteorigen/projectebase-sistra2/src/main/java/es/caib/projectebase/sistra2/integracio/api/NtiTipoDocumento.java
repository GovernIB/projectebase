
package es.caib.projectebase.sistra2.integracio.api;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ntiTipoDocumento.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ntiTipoDocumento"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="RESOLUCIO"/&gt;
 *     &lt;enumeration value="ACORD"/&gt;
 *     &lt;enumeration value="CONTRACTE"/&gt;
 *     &lt;enumeration value="CONVENI"/&gt;
 *     &lt;enumeration value="DECLARACIO"/&gt;
 *     &lt;enumeration value="COMUNICACIO"/&gt;
 *     &lt;enumeration value="NOTIFICACIO"/&gt;
 *     &lt;enumeration value="PUBLICACIO"/&gt;
 *     &lt;enumeration value="JUSTIFICANT_RECEPCIO"/&gt;
 *     &lt;enumeration value="ACTA"/&gt;
 *     &lt;enumeration value="CERTIFICAT"/&gt;
 *     &lt;enumeration value="DILIGENCIA"/&gt;
 *     &lt;enumeration value="INFORME"/&gt;
 *     &lt;enumeration value="SOLICITUD"/&gt;
 *     &lt;enumeration value="DENUNCIA"/&gt;
 *     &lt;enumeration value="ALEGACIO"/&gt;
 *     &lt;enumeration value="RECURS"/&gt;
 *     &lt;enumeration value="COMUNICACIO_CIUTADA"/&gt;
 *     &lt;enumeration value="FACTURA"/&gt;
 *     &lt;enumeration value="ALTRES_INCAUTATS"/&gt;
 *     &lt;enumeration value="ALTRES"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "ntiTipoDocumento")
@XmlEnum
public enum NtiTipoDocumento {

    RESOLUCIO,
    ACORD,
    CONTRACTE,
    CONVENI,
    DECLARACIO,
    COMUNICACIO,
    NOTIFICACIO,
    PUBLICACIO,
    JUSTIFICANT_RECEPCIO,
    ACTA,
    CERTIFICAT,
    DILIGENCIA,
    INFORME,
    SOLICITUD,
    DENUNCIA,
    ALEGACIO,
    RECURS,
    COMUNICACIO_CIUTADA,
    FACTURA,
    ALTRES_INCAUTATS,
    ALTRES;

    public String value() {
        return name();
    }

    public static NtiTipoDocumento fromValue(String v) {
        return valueOf(v);
    }

}

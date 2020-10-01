
package es.caib.projectebase.sistra2.integracio.api;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for consulta complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="consulta"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="id" type="{http://www.caib.es/distribucio/ws/backofficeIntegracio}anotacioRegistreId"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "consulta", propOrder = {
    "id"
})
public class Consulta {

    @XmlElement(required = true)
    protected AnotacioRegistreId id;

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link AnotacioRegistreId }
     *     
     */
    public AnotacioRegistreId getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link AnotacioRegistreId }
     *     
     */
    public void setId(AnotacioRegistreId value) {
        this.id = value;
    }

}

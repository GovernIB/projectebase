
package es.caib.projectebase.sistra2.integracio.api;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for canviEstat complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="canviEstat"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="id" type="{http://www.caib.es/distribucio/ws/backofficeIntegracio}anotacioRegistreId"/&gt;
 *         &lt;element name="estat" type="{http://www.caib.es/distribucio/ws/backofficeIntegracio}estat"/&gt;
 *         &lt;element name="observacions" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "canviEstat", propOrder = {
    "id",
    "estat",
    "observacions"
})
public class CanviEstat {

    @XmlElement(required = true)
    protected AnotacioRegistreId id;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected Estat estat;
    protected String observacions;

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

    /**
     * Gets the value of the estat property.
     * 
     * @return
     *     possible object is
     *     {@link Estat }
     *     
     */
    public Estat getEstat() {
        return estat;
    }

    /**
     * Sets the value of the estat property.
     * 
     * @param value
     *     allowed object is
     *     {@link Estat }
     *     
     */
    public void setEstat(Estat value) {
        this.estat = value;
    }

    /**
     * Gets the value of the observacions property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObservacions() {
        return observacions;
    }

    /**
     * Sets the value of the observacions property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObservacions(String value) {
        this.observacions = value;
    }

}

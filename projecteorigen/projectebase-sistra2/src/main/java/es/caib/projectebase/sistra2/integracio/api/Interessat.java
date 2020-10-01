
package es.caib.projectebase.sistra2.integracio.api;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for interessat complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="interessat"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.caib.es/distribucio/ws/backofficeIntegracio}interessatBase"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="representant" type="{http://www.caib.es/distribucio/ws/backofficeIntegracio}representant" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "interessat", propOrder = {
    "representant"
})
public class Interessat
    extends InteressatBase
{

    protected Representant representant;

    /**
     * Gets the value of the representant property.
     * 
     * @return
     *     possible object is
     *     {@link Representant }
     *     
     */
    public Representant getRepresentant() {
        return representant;
    }

    /**
     * Sets the value of the representant property.
     * 
     * @param value
     *     allowed object is
     *     {@link Representant }
     *     
     */
    public void setRepresentant(Representant value) {
        this.representant = value;
    }

}

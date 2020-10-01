
package es.caib.projectebase.sistra2.integracio.api;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anotacioRegistreEntrada complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="anotacioRegistreEntrada"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.caib.es/distribucio/ws/backofficeIntegracio}anotacioRegistreBase"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="destiCodi" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="destiDescripcio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "anotacioRegistreEntrada", propOrder = {
    "destiCodi",
    "destiDescripcio"
})
public class AnotacioRegistreEntrada
    extends AnotacioRegistreBase
{

    protected String destiCodi;
    protected String destiDescripcio;

    /**
     * Gets the value of the destiCodi property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDestiCodi() {
        return destiCodi;
    }

    /**
     * Sets the value of the destiCodi property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDestiCodi(String value) {
        this.destiCodi = value;
    }

    /**
     * Gets the value of the destiDescripcio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDestiDescripcio() {
        return destiDescripcio;
    }

    /**
     * Sets the value of the destiDescripcio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDestiDescripcio(String value) {
        this.destiDescripcio = value;
    }

}

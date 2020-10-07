#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )

package ${package}.sistra2.backoffice.api;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anotacioRegistreId complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="anotacioRegistreId"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="clauAcces" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="indetificador" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "anotacioRegistreId", propOrder = {
    "clauAcces",
    "indetificador"
})
public class AnotacioRegistreId {

    protected String clauAcces;
    protected String indetificador;

    /**
     * Gets the value of the clauAcces property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClauAcces() {
        return clauAcces;
    }

    /**
     * Sets the value of the clauAcces property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClauAcces(String value) {
        this.clauAcces = value;
    }

    /**
     * Gets the value of the indetificador property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIndetificador() {
        return indetificador;
    }

    /**
     * Sets the value of the indetificador property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIndetificador(String value) {
        this.indetificador = value;
    }

}

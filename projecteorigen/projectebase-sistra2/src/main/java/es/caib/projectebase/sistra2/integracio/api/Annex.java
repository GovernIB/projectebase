
package es.caib.projectebase.sistra2.integracio.api;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for annex complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="annex"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="contingut" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/&gt;
 *         &lt;element name="firmaContingut" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/&gt;
 *         &lt;element name="firmaNom" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="firmaPerfil" type="{http://www.caib.es/distribucio/ws/backofficeIntegracio}firmaPerfil" minOccurs="0"/&gt;
 *         &lt;element name="firmaTamany" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="firmaTipus" type="{http://www.caib.es/distribucio/ws/backofficeIntegracio}firmaTipus" minOccurs="0"/&gt;
 *         &lt;element name="firmaTipusMime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="nom" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ntiEstadoElaboracion" type="{http://www.caib.es/distribucio/ws/backofficeIntegracio}ntiEstadoElaboracion" minOccurs="0"/&gt;
 *         &lt;element name="ntiFechaCaptura" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="ntiOrigen" type="{http://www.caib.es/distribucio/ws/backofficeIntegracio}ntiOrigen" minOccurs="0"/&gt;
 *         &lt;element name="ntiTipoDocumental" type="{http://www.caib.es/distribucio/ws/backofficeIntegracio}ntiTipoDocumento" minOccurs="0"/&gt;
 *         &lt;element name="observacions" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="sicresTipoDocumento" type="{http://www.caib.es/distribucio/ws/backofficeIntegracio}sicresTipoDocumento" minOccurs="0"/&gt;
 *         &lt;element name="sicresValidezDocumento" type="{http://www.caib.es/distribucio/ws/backofficeIntegracio}sicresValidezDocumento" minOccurs="0"/&gt;
 *         &lt;element name="tamany" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="tipusMime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="titol" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="uuid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "annex", propOrder = {
    "contingut",
    "firmaContingut",
    "firmaNom",
    "firmaPerfil",
    "firmaTamany",
    "firmaTipus",
    "firmaTipusMime",
    "nom",
    "ntiEstadoElaboracion",
    "ntiFechaCaptura",
    "ntiOrigen",
    "ntiTipoDocumental",
    "observacions",
    "sicresTipoDocumento",
    "sicresValidezDocumento",
    "tamany",
    "tipusMime",
    "titol",
    "uuid"
})
public class Annex {

    protected byte[] contingut;
    protected byte[] firmaContingut;
    protected String firmaNom;
    @XmlSchemaType(name = "string")
    protected FirmaPerfil firmaPerfil;
    protected long firmaTamany;
    @XmlSchemaType(name = "string")
    protected FirmaTipus firmaTipus;
    protected String firmaTipusMime;
    protected String nom;
    @XmlSchemaType(name = "string")
    protected NtiEstadoElaboracion ntiEstadoElaboracion;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar ntiFechaCaptura;
    @XmlSchemaType(name = "string")
    protected NtiOrigen ntiOrigen;
    @XmlSchemaType(name = "string")
    protected NtiTipoDocumento ntiTipoDocumental;
    protected String observacions;
    @XmlSchemaType(name = "string")
    protected SicresTipoDocumento sicresTipoDocumento;
    @XmlSchemaType(name = "string")
    protected SicresValidezDocumento sicresValidezDocumento;
    protected long tamany;
    protected String tipusMime;
    protected String titol;
    protected String uuid;

    /**
     * Gets the value of the contingut property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getContingut() {
        return contingut;
    }

    /**
     * Sets the value of the contingut property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setContingut(byte[] value) {
        this.contingut = value;
    }

    /**
     * Gets the value of the firmaContingut property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getFirmaContingut() {
        return firmaContingut;
    }

    /**
     * Sets the value of the firmaContingut property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setFirmaContingut(byte[] value) {
        this.firmaContingut = value;
    }

    /**
     * Gets the value of the firmaNom property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFirmaNom() {
        return firmaNom;
    }

    /**
     * Sets the value of the firmaNom property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFirmaNom(String value) {
        this.firmaNom = value;
    }

    /**
     * Gets the value of the firmaPerfil property.
     * 
     * @return
     *     possible object is
     *     {@link FirmaPerfil }
     *     
     */
    public FirmaPerfil getFirmaPerfil() {
        return firmaPerfil;
    }

    /**
     * Sets the value of the firmaPerfil property.
     * 
     * @param value
     *     allowed object is
     *     {@link FirmaPerfil }
     *     
     */
    public void setFirmaPerfil(FirmaPerfil value) {
        this.firmaPerfil = value;
    }

    /**
     * Gets the value of the firmaTamany property.
     * 
     */
    public long getFirmaTamany() {
        return firmaTamany;
    }

    /**
     * Sets the value of the firmaTamany property.
     * 
     */
    public void setFirmaTamany(long value) {
        this.firmaTamany = value;
    }

    /**
     * Gets the value of the firmaTipus property.
     * 
     * @return
     *     possible object is
     *     {@link FirmaTipus }
     *     
     */
    public FirmaTipus getFirmaTipus() {
        return firmaTipus;
    }

    /**
     * Sets the value of the firmaTipus property.
     * 
     * @param value
     *     allowed object is
     *     {@link FirmaTipus }
     *     
     */
    public void setFirmaTipus(FirmaTipus value) {
        this.firmaTipus = value;
    }

    /**
     * Gets the value of the firmaTipusMime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFirmaTipusMime() {
        return firmaTipusMime;
    }

    /**
     * Sets the value of the firmaTipusMime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFirmaTipusMime(String value) {
        this.firmaTipusMime = value;
    }

    /**
     * Gets the value of the nom property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNom() {
        return nom;
    }

    /**
     * Sets the value of the nom property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNom(String value) {
        this.nom = value;
    }

    /**
     * Gets the value of the ntiEstadoElaboracion property.
     * 
     * @return
     *     possible object is
     *     {@link NtiEstadoElaboracion }
     *     
     */
    public NtiEstadoElaboracion getNtiEstadoElaboracion() {
        return ntiEstadoElaboracion;
    }

    /**
     * Sets the value of the ntiEstadoElaboracion property.
     * 
     * @param value
     *     allowed object is
     *     {@link NtiEstadoElaboracion }
     *     
     */
    public void setNtiEstadoElaboracion(NtiEstadoElaboracion value) {
        this.ntiEstadoElaboracion = value;
    }

    /**
     * Gets the value of the ntiFechaCaptura property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getNtiFechaCaptura() {
        return ntiFechaCaptura;
    }

    /**
     * Sets the value of the ntiFechaCaptura property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setNtiFechaCaptura(XMLGregorianCalendar value) {
        this.ntiFechaCaptura = value;
    }

    /**
     * Gets the value of the ntiOrigen property.
     * 
     * @return
     *     possible object is
     *     {@link NtiOrigen }
     *     
     */
    public NtiOrigen getNtiOrigen() {
        return ntiOrigen;
    }

    /**
     * Sets the value of the ntiOrigen property.
     * 
     * @param value
     *     allowed object is
     *     {@link NtiOrigen }
     *     
     */
    public void setNtiOrigen(NtiOrigen value) {
        this.ntiOrigen = value;
    }

    /**
     * Gets the value of the ntiTipoDocumental property.
     * 
     * @return
     *     possible object is
     *     {@link NtiTipoDocumento }
     *     
     */
    public NtiTipoDocumento getNtiTipoDocumental() {
        return ntiTipoDocumental;
    }

    /**
     * Sets the value of the ntiTipoDocumental property.
     * 
     * @param value
     *     allowed object is
     *     {@link NtiTipoDocumento }
     *     
     */
    public void setNtiTipoDocumental(NtiTipoDocumento value) {
        this.ntiTipoDocumental = value;
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

    /**
     * Gets the value of the sicresTipoDocumento property.
     * 
     * @return
     *     possible object is
     *     {@link SicresTipoDocumento }
     *     
     */
    public SicresTipoDocumento getSicresTipoDocumento() {
        return sicresTipoDocumento;
    }

    /**
     * Sets the value of the sicresTipoDocumento property.
     * 
     * @param value
     *     allowed object is
     *     {@link SicresTipoDocumento }
     *     
     */
    public void setSicresTipoDocumento(SicresTipoDocumento value) {
        this.sicresTipoDocumento = value;
    }

    /**
     * Gets the value of the sicresValidezDocumento property.
     * 
     * @return
     *     possible object is
     *     {@link SicresValidezDocumento }
     *     
     */
    public SicresValidezDocumento getSicresValidezDocumento() {
        return sicresValidezDocumento;
    }

    /**
     * Sets the value of the sicresValidezDocumento property.
     * 
     * @param value
     *     allowed object is
     *     {@link SicresValidezDocumento }
     *     
     */
    public void setSicresValidezDocumento(SicresValidezDocumento value) {
        this.sicresValidezDocumento = value;
    }

    /**
     * Gets the value of the tamany property.
     * 
     */
    public long getTamany() {
        return tamany;
    }

    /**
     * Sets the value of the tamany property.
     * 
     */
    public void setTamany(long value) {
        this.tamany = value;
    }

    /**
     * Gets the value of the tipusMime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipusMime() {
        return tipusMime;
    }

    /**
     * Sets the value of the tipusMime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipusMime(String value) {
        this.tipusMime = value;
    }

    /**
     * Gets the value of the titol property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitol() {
        return titol;
    }

    /**
     * Sets the value of the titol property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitol(String value) {
        this.titol = value;
    }

    /**
     * Gets the value of the uuid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * Sets the value of the uuid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUuid(String value) {
        this.uuid = value;
    }

}

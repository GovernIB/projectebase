
package es.caib.projectebase.sistra2.integracio.api;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for anotacioRegistreBase complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="anotacioRegistreBase"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="annexos" type="{http://www.caib.es/distribucio/ws/backofficeIntegracio}annex" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="aplicacioCodi" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="aplicacioVersio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="assumpteCodiCodi" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="assumpteCodiDescripcio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="assumpteTipusCodi" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="assumpteTipusDescripcio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="data" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="docFisicaCodi" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="docFisicaDescripcio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="entitatCodi" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="entitatDescripcio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="expedientNumero" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="exposa" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="extracte" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="identificador" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="idiomaCodi" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="idomaDescripcio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="interessats" type="{http://www.caib.es/distribucio/ws/backofficeIntegracio}interessat" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="llibreCodi" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="llibreDescripcio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="observacions" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="oficinaCodi" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="oficinaDescripcio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="origenData" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="origenRegistreNumero" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="procedimentCodi" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="refExterna" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="solicita" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="transportNumero" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="transportTipusCodi" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="transportTipusDescripcio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="usuariCodi" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="usuariNom" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "anotacioRegistreBase", propOrder = {
    "annexos",
    "aplicacioCodi",
    "aplicacioVersio",
    "assumpteCodiCodi",
    "assumpteCodiDescripcio",
    "assumpteTipusCodi",
    "assumpteTipusDescripcio",
    "data",
    "docFisicaCodi",
    "docFisicaDescripcio",
    "entitatCodi",
    "entitatDescripcio",
    "expedientNumero",
    "exposa",
    "extracte",
    "identificador",
    "idiomaCodi",
    "idomaDescripcio",
    "interessats",
    "llibreCodi",
    "llibreDescripcio",
    "observacions",
    "oficinaCodi",
    "oficinaDescripcio",
    "origenData",
    "origenRegistreNumero",
    "procedimentCodi",
    "refExterna",
    "solicita",
    "transportNumero",
    "transportTipusCodi",
    "transportTipusDescripcio",
    "usuariCodi",
    "usuariNom"
})
@XmlSeeAlso({
    AnotacioRegistreEntrada.class
})
public class AnotacioRegistreBase {

    @XmlElement(nillable = true)
    protected List<Annex> annexos;
    protected String aplicacioCodi;
    protected String aplicacioVersio;
    protected String assumpteCodiCodi;
    protected String assumpteCodiDescripcio;
    protected String assumpteTipusCodi;
    protected String assumpteTipusDescripcio;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar data;
    protected String docFisicaCodi;
    protected String docFisicaDescripcio;
    protected String entitatCodi;
    protected String entitatDescripcio;
    protected String expedientNumero;
    protected String exposa;
    protected String extracte;
    protected String identificador;
    protected String idiomaCodi;
    protected String idomaDescripcio;
    @XmlElement(nillable = true)
    protected List<Interessat> interessats;
    protected String llibreCodi;
    protected String llibreDescripcio;
    protected String observacions;
    protected String oficinaCodi;
    protected String oficinaDescripcio;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar origenData;
    protected String origenRegistreNumero;
    protected String procedimentCodi;
    protected String refExterna;
    protected String solicita;
    protected String transportNumero;
    protected String transportTipusCodi;
    protected String transportTipusDescripcio;
    protected String usuariCodi;
    protected String usuariNom;

    /**
     * Gets the value of the annexos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the annexos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAnnexos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Annex }
     * 
     * 
     */
    public List<Annex> getAnnexos() {
        if (annexos == null) {
            annexos = new ArrayList<Annex>();
        }
        return this.annexos;
    }

    /**
     * Gets the value of the aplicacioCodi property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAplicacioCodi() {
        return aplicacioCodi;
    }

    /**
     * Sets the value of the aplicacioCodi property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAplicacioCodi(String value) {
        this.aplicacioCodi = value;
    }

    /**
     * Gets the value of the aplicacioVersio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAplicacioVersio() {
        return aplicacioVersio;
    }

    /**
     * Sets the value of the aplicacioVersio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAplicacioVersio(String value) {
        this.aplicacioVersio = value;
    }

    /**
     * Gets the value of the assumpteCodiCodi property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAssumpteCodiCodi() {
        return assumpteCodiCodi;
    }

    /**
     * Sets the value of the assumpteCodiCodi property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAssumpteCodiCodi(String value) {
        this.assumpteCodiCodi = value;
    }

    /**
     * Gets the value of the assumpteCodiDescripcio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAssumpteCodiDescripcio() {
        return assumpteCodiDescripcio;
    }

    /**
     * Sets the value of the assumpteCodiDescripcio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAssumpteCodiDescripcio(String value) {
        this.assumpteCodiDescripcio = value;
    }

    /**
     * Gets the value of the assumpteTipusCodi property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAssumpteTipusCodi() {
        return assumpteTipusCodi;
    }

    /**
     * Sets the value of the assumpteTipusCodi property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAssumpteTipusCodi(String value) {
        this.assumpteTipusCodi = value;
    }

    /**
     * Gets the value of the assumpteTipusDescripcio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAssumpteTipusDescripcio() {
        return assumpteTipusDescripcio;
    }

    /**
     * Sets the value of the assumpteTipusDescripcio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAssumpteTipusDescripcio(String value) {
        this.assumpteTipusDescripcio = value;
    }

    /**
     * Gets the value of the data property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getData() {
        return data;
    }

    /**
     * Sets the value of the data property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setData(XMLGregorianCalendar value) {
        this.data = value;
    }

    /**
     * Gets the value of the docFisicaCodi property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocFisicaCodi() {
        return docFisicaCodi;
    }

    /**
     * Sets the value of the docFisicaCodi property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocFisicaCodi(String value) {
        this.docFisicaCodi = value;
    }

    /**
     * Gets the value of the docFisicaDescripcio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocFisicaDescripcio() {
        return docFisicaDescripcio;
    }

    /**
     * Sets the value of the docFisicaDescripcio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocFisicaDescripcio(String value) {
        this.docFisicaDescripcio = value;
    }

    /**
     * Gets the value of the entitatCodi property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEntitatCodi() {
        return entitatCodi;
    }

    /**
     * Sets the value of the entitatCodi property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEntitatCodi(String value) {
        this.entitatCodi = value;
    }

    /**
     * Gets the value of the entitatDescripcio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEntitatDescripcio() {
        return entitatDescripcio;
    }

    /**
     * Sets the value of the entitatDescripcio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEntitatDescripcio(String value) {
        this.entitatDescripcio = value;
    }

    /**
     * Gets the value of the expedientNumero property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExpedientNumero() {
        return expedientNumero;
    }

    /**
     * Sets the value of the expedientNumero property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExpedientNumero(String value) {
        this.expedientNumero = value;
    }

    /**
     * Gets the value of the exposa property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExposa() {
        return exposa;
    }

    /**
     * Sets the value of the exposa property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExposa(String value) {
        this.exposa = value;
    }

    /**
     * Gets the value of the extracte property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExtracte() {
        return extracte;
    }

    /**
     * Sets the value of the extracte property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExtracte(String value) {
        this.extracte = value;
    }

    /**
     * Gets the value of the identificador property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentificador() {
        return identificador;
    }

    /**
     * Sets the value of the identificador property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentificador(String value) {
        this.identificador = value;
    }

    /**
     * Gets the value of the idiomaCodi property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdiomaCodi() {
        return idiomaCodi;
    }

    /**
     * Sets the value of the idiomaCodi property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdiomaCodi(String value) {
        this.idiomaCodi = value;
    }

    /**
     * Gets the value of the idomaDescripcio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdomaDescripcio() {
        return idomaDescripcio;
    }

    /**
     * Sets the value of the idomaDescripcio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdomaDescripcio(String value) {
        this.idomaDescripcio = value;
    }

    /**
     * Gets the value of the interessats property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the interessats property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInteressats().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Interessat }
     * 
     * 
     */
    public List<Interessat> getInteressats() {
        if (interessats == null) {
            interessats = new ArrayList<Interessat>();
        }
        return this.interessats;
    }

    /**
     * Gets the value of the llibreCodi property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLlibreCodi() {
        return llibreCodi;
    }

    /**
     * Sets the value of the llibreCodi property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLlibreCodi(String value) {
        this.llibreCodi = value;
    }

    /**
     * Gets the value of the llibreDescripcio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLlibreDescripcio() {
        return llibreDescripcio;
    }

    /**
     * Sets the value of the llibreDescripcio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLlibreDescripcio(String value) {
        this.llibreDescripcio = value;
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
     * Gets the value of the oficinaCodi property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOficinaCodi() {
        return oficinaCodi;
    }

    /**
     * Sets the value of the oficinaCodi property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOficinaCodi(String value) {
        this.oficinaCodi = value;
    }

    /**
     * Gets the value of the oficinaDescripcio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOficinaDescripcio() {
        return oficinaDescripcio;
    }

    /**
     * Sets the value of the oficinaDescripcio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOficinaDescripcio(String value) {
        this.oficinaDescripcio = value;
    }

    /**
     * Gets the value of the origenData property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getOrigenData() {
        return origenData;
    }

    /**
     * Sets the value of the origenData property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setOrigenData(XMLGregorianCalendar value) {
        this.origenData = value;
    }

    /**
     * Gets the value of the origenRegistreNumero property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrigenRegistreNumero() {
        return origenRegistreNumero;
    }

    /**
     * Sets the value of the origenRegistreNumero property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrigenRegistreNumero(String value) {
        this.origenRegistreNumero = value;
    }

    /**
     * Gets the value of the procedimentCodi property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProcedimentCodi() {
        return procedimentCodi;
    }

    /**
     * Sets the value of the procedimentCodi property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProcedimentCodi(String value) {
        this.procedimentCodi = value;
    }

    /**
     * Gets the value of the refExterna property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRefExterna() {
        return refExterna;
    }

    /**
     * Sets the value of the refExterna property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRefExterna(String value) {
        this.refExterna = value;
    }

    /**
     * Gets the value of the solicita property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSolicita() {
        return solicita;
    }

    /**
     * Sets the value of the solicita property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSolicita(String value) {
        this.solicita = value;
    }

    /**
     * Gets the value of the transportNumero property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransportNumero() {
        return transportNumero;
    }

    /**
     * Sets the value of the transportNumero property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransportNumero(String value) {
        this.transportNumero = value;
    }

    /**
     * Gets the value of the transportTipusCodi property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransportTipusCodi() {
        return transportTipusCodi;
    }

    /**
     * Sets the value of the transportTipusCodi property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransportTipusCodi(String value) {
        this.transportTipusCodi = value;
    }

    /**
     * Gets the value of the transportTipusDescripcio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransportTipusDescripcio() {
        return transportTipusDescripcio;
    }

    /**
     * Sets the value of the transportTipusDescripcio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransportTipusDescripcio(String value) {
        this.transportTipusDescripcio = value;
    }

    /**
     * Gets the value of the usuariCodi property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsuariCodi() {
        return usuariCodi;
    }

    /**
     * Sets the value of the usuariCodi property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsuariCodi(String value) {
        this.usuariCodi = value;
    }

    /**
     * Gets the value of the usuariNom property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsuariNom() {
        return usuariNom;
    }

    /**
     * Sets the value of the usuariNom property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsuariNom(String value) {
        this.usuariNom = value;
    }

}

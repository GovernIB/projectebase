
package es.caib.projectebase.sistra2.integracio.api;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for interessatBase complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="interessatBase"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="adresa" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="adresaElectronica" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="canal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="cp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="documentNumero" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="documentTipus" type="{http://www.caib.es/distribucio/ws/backofficeIntegracio}documentTipus" minOccurs="0"/&gt;
 *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="llinatge1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="llinatge2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="municipi" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="municipiCodi" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="nom" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="observacions" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="organCodi" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="pais" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="paisCodi" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="provincia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="provinciaCodi" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="raoSocial" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="telefon" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="tipus" type="{http://www.caib.es/distribucio/ws/backofficeIntegracio}interessatTipus" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "interessatBase", propOrder = {
    "adresa",
    "adresaElectronica",
    "canal",
    "cp",
    "documentNumero",
    "documentTipus",
    "email",
    "llinatge1",
    "llinatge2",
    "municipi",
    "municipiCodi",
    "nom",
    "observacions",
    "organCodi",
    "pais",
    "paisCodi",
    "provincia",
    "provinciaCodi",
    "raoSocial",
    "telefon",
    "tipus"
})
@XmlSeeAlso({
    Interessat.class,
    Representant.class
})
public class InteressatBase {

    protected String adresa;
    protected String adresaElectronica;
    protected String canal;
    protected String cp;
    protected String documentNumero;
    @XmlSchemaType(name = "string")
    protected DocumentTipus documentTipus;
    protected String email;
    protected String llinatge1;
    protected String llinatge2;
    protected String municipi;
    protected String municipiCodi;
    protected String nom;
    protected String observacions;
    protected String organCodi;
    protected String pais;
    protected String paisCodi;
    protected String provincia;
    protected String provinciaCodi;
    protected String raoSocial;
    protected String telefon;
    @XmlSchemaType(name = "string")
    protected InteressatTipus tipus;

    /**
     * Gets the value of the adresa property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdresa() {
        return adresa;
    }

    /**
     * Sets the value of the adresa property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdresa(String value) {
        this.adresa = value;
    }

    /**
     * Gets the value of the adresaElectronica property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdresaElectronica() {
        return adresaElectronica;
    }

    /**
     * Sets the value of the adresaElectronica property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdresaElectronica(String value) {
        this.adresaElectronica = value;
    }

    /**
     * Gets the value of the canal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCanal() {
        return canal;
    }

    /**
     * Sets the value of the canal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCanal(String value) {
        this.canal = value;
    }

    /**
     * Gets the value of the cp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCp() {
        return cp;
    }

    /**
     * Sets the value of the cp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCp(String value) {
        this.cp = value;
    }

    /**
     * Gets the value of the documentNumero property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocumentNumero() {
        return documentNumero;
    }

    /**
     * Sets the value of the documentNumero property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocumentNumero(String value) {
        this.documentNumero = value;
    }

    /**
     * Gets the value of the documentTipus property.
     * 
     * @return
     *     possible object is
     *     {@link DocumentTipus }
     *     
     */
    public DocumentTipus getDocumentTipus() {
        return documentTipus;
    }

    /**
     * Sets the value of the documentTipus property.
     * 
     * @param value
     *     allowed object is
     *     {@link DocumentTipus }
     *     
     */
    public void setDocumentTipus(DocumentTipus value) {
        this.documentTipus = value;
    }

    /**
     * Gets the value of the email property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the value of the email property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     * Gets the value of the llinatge1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLlinatge1() {
        return llinatge1;
    }

    /**
     * Sets the value of the llinatge1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLlinatge1(String value) {
        this.llinatge1 = value;
    }

    /**
     * Gets the value of the llinatge2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLlinatge2() {
        return llinatge2;
    }

    /**
     * Sets the value of the llinatge2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLlinatge2(String value) {
        this.llinatge2 = value;
    }

    /**
     * Gets the value of the municipi property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMunicipi() {
        return municipi;
    }

    /**
     * Sets the value of the municipi property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMunicipi(String value) {
        this.municipi = value;
    }

    /**
     * Gets the value of the municipiCodi property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMunicipiCodi() {
        return municipiCodi;
    }

    /**
     * Sets the value of the municipiCodi property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMunicipiCodi(String value) {
        this.municipiCodi = value;
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
     * Gets the value of the organCodi property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrganCodi() {
        return organCodi;
    }

    /**
     * Sets the value of the organCodi property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrganCodi(String value) {
        this.organCodi = value;
    }

    /**
     * Gets the value of the pais property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPais() {
        return pais;
    }

    /**
     * Sets the value of the pais property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPais(String value) {
        this.pais = value;
    }

    /**
     * Gets the value of the paisCodi property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaisCodi() {
        return paisCodi;
    }

    /**
     * Sets the value of the paisCodi property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaisCodi(String value) {
        this.paisCodi = value;
    }

    /**
     * Gets the value of the provincia property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProvincia() {
        return provincia;
    }

    /**
     * Sets the value of the provincia property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProvincia(String value) {
        this.provincia = value;
    }

    /**
     * Gets the value of the provinciaCodi property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProvinciaCodi() {
        return provinciaCodi;
    }

    /**
     * Sets the value of the provinciaCodi property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProvinciaCodi(String value) {
        this.provinciaCodi = value;
    }

    /**
     * Gets the value of the raoSocial property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRaoSocial() {
        return raoSocial;
    }

    /**
     * Sets the value of the raoSocial property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRaoSocial(String value) {
        this.raoSocial = value;
    }

    /**
     * Gets the value of the telefon property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTelefon() {
        return telefon;
    }

    /**
     * Sets the value of the telefon property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTelefon(String value) {
        this.telefon = value;
    }

    /**
     * Gets the value of the tipus property.
     * 
     * @return
     *     possible object is
     *     {@link InteressatTipus }
     *     
     */
    public InteressatTipus getTipus() {
        return tipus;
    }

    /**
     * Sets the value of the tipus property.
     * 
     * @param value
     *     allowed object is
     *     {@link InteressatTipus }
     *     
     */
    public void setTipus(InteressatTipus value) {
        this.tipus = value;
    }

}

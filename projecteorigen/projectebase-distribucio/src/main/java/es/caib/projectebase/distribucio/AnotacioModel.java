package es.caib.projectebase.distribucio;

import javax.servlet.http.Part;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Encapçula les dades del formulari d'una anotació, amb les seves validacions corresponents.
 *
 * @author areus
 */
public class AnotacioModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull private TipusAnotacio tipus;
    @NotNull private IdiomaAnotacio idioma;
    @NotEmpty @Size(max=240) private String extracte;
    @NotEmpty private String entitat;
    @NotEmpty private String unitat;
    @NotEmpty private String oficina;
    @NotEmpty private String llibre;

    @NotEmpty private String interessatNif;
    @NotEmpty private String interessatNom;
    @NotEmpty private String interessatPrimerLlinatge;
    @NotEmpty private String interessatSegonLlinatge;
    @NotEmpty private String interessatPais;
    @NotEmpty private String interessatProvincia;
    @NotEmpty private String interessatMunicipi;
    @NotEmpty private String interessatAdresa;
    @NotEmpty private String interessatCodiPostal;
    @NotEmpty private String interessatEmail;

    private Part annex;

    // Getters & Setters

    public TipusAnotacio getTipus() {
        return tipus;
    }

    public void setTipus(TipusAnotacio tipus) {
        this.tipus = tipus;
    }

    public IdiomaAnotacio getIdioma() {
        return idioma;
    }

    public void setIdioma(IdiomaAnotacio idioma) {
        this.idioma = idioma;
    }

    public String getExtracte() {
        return extracte;
    }

    public void setExtracte(String extracte) {
        this.extracte = extracte;
    }

    public String getEntitat() {
        return entitat;
    }

    public void setEntitat(String entitat) {
        this.entitat = entitat;
    }

    public String getUnitat() {
        return unitat;
    }

    public void setUnitat(String unitat) {
        this.unitat = unitat;
    }

    public String getOficina() {
        return oficina;
    }

    public void setOficina(String oficina) {
        this.oficina = oficina;
    }

    public String getLlibre() {
        return llibre;
    }

    public void setLlibre(String llibre) {
        this.llibre = llibre;
    }

    public String getInteressatNif() {
        return interessatNif;
    }

    public void setInteressatNif(String interessatNif) {
        this.interessatNif = interessatNif;
    }

    public String getInteressatNom() {
        return interessatNom;
    }

    public void setInteressatNom(String interessatNom) {
        this.interessatNom = interessatNom;
    }

    public String getInteressatPrimerLlinatge() {
        return interessatPrimerLlinatge;
    }

    public void setInteressatPrimerLlinatge(String interessatPrimerLlinatge) {
        this.interessatPrimerLlinatge = interessatPrimerLlinatge;
    }

    public String getInteressatSegonLlinatge() {
        return interessatSegonLlinatge;
    }

    public void setInteressatSegonLlinatge(String interessatSegonLlinatge) {
        this.interessatSegonLlinatge = interessatSegonLlinatge;
    }

    public String getInteressatPais() {
        return interessatPais;
    }

    public void setInteressatPais(String interessatPais) {
        this.interessatPais = interessatPais;
    }

    public String getInteressatProvincia() {
        return interessatProvincia;
    }

    public void setInteressatProvincia(String interessatProvincia) {
        this.interessatProvincia = interessatProvincia;
    }

    public String getInteressatMunicipi() {
        return interessatMunicipi;
    }

    public void setInteressatMunicipi(String interessatMunicipi) {
        this.interessatMunicipi = interessatMunicipi;
    }

    public String getInteressatAdresa() {
        return interessatAdresa;
    }

    public void setInteressatAdresa(String interessatAdresa) {
        this.interessatAdresa = interessatAdresa;
    }

    public String getInteressatCodiPostal() {
        return interessatCodiPostal;
    }

    public void setInteressatCodiPostal(String interessatCodiPostal) {
        this.interessatCodiPostal = interessatCodiPostal;
    }

    public String getInteressatEmail() {
        return interessatEmail;
    }

    public void setInteressatEmail(String interessatEmail) {
        this.interessatEmail = interessatEmail;
    }

    public Part getAnnex() {
        return annex;
    }

    public void setAnnex(Part annex) {
        this.annex = annex;
    }
}

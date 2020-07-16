package es.caib.projectebase.distribucio;

import java.io.Serializable;

public class BustiaModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private TipusAnotacio tipus;
    private String extracte;
    private String entitat;
    private String unitat;
    private String oficina;
    private String llibre;
         
    private String interessatNif;
    private String interessatNom;
    private String interessatPrimerLlinatge;
    private String interessatSegonLlinatge;
    private String interessatPais;
    private String interessatProvincia;
    private String interessatMunicipi;
    private String interessatAdresa;
    private String interessatCodiPostal;
    private String interessatEmail;

    // Getters & Setters

    public TipusAnotacio getTipus() {
        return tipus;
    }

    public void setTipus(TipusAnotacio tipus) {
        this.tipus = tipus;
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
}

package es.caib.projectebase.pinbal;

import es.caib.pinbal.client.recobriment.model.ScspTitular;
import es.caib.projectebase.pinbal.scsp.TipoDocumentacionSubset;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class VerificacioModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotEmpty
    @Size(max=9) private String documentacio;

    @NotNull
    @TipoDocumentacionSubset(anyOf = {ScspTitular.ScspTipoDocumentacion.DNI, ScspTitular.ScspTipoDocumentacion.NIE})
    private ScspTitular.ScspTipoDocumentacion tipusDocumentacio;

    private String nom;
    private String primerCognom;
    private String segonCognom;

    public String getDocumentacio() {
        return documentacio;
    }

    public void setDocumentacio(String documentacio) {
        this.documentacio = documentacio;
    }

    public ScspTitular.ScspTipoDocumentacion getTipusDocumentacio() {
        return tipusDocumentacio;
    }

    public void setTipusDocumentacio(ScspTitular.ScspTipoDocumentacion tipusDocumentacio) {
        this.tipusDocumentacio = tipusDocumentacio;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrimerCognom() {
        return primerCognom;
    }

    public void setPrimerCognom(String primerCognom) {
        this.primerCognom = primerCognom;
    }

    public String getSegonCognom() {
        return segonCognom;
    }

    public void setSegonCognom(String segonCognom) {
        this.segonCognom = segonCognom;
    }
}

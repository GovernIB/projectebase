package es.caib.projectebase.notib;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.servlet.http.Part;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Bean per recollir les dades del formulari de creació d'una notificacio.
 *
 * @author areus
 */
@Model
public class NotificacioModel {

    /**
     * Permet accedir a la configuració.
     */
    @Inject
    private Configuracio configuracio;

    /**
     * Agafar valors per defecte de la configuració durant la inicialització.
     */
    @PostConstruct
    public void init() {
        procedimentCodi = configuracio.getProcedimentCodi();
        emisorDir3Codi = configuracio.getEmisorDir3Codi();
    }

    @NotEmpty
    private String usuariCodi;
    
    @NotEmpty
    private String concepte;
    
    @NotEmpty
    private String descripcio;
    
    @NotNull
    private Part fitxer;
    
    @NotEmpty
    private String procedimentCodi;
    
    @NotEmpty
    private String emisorDir3Codi;
    
    @NotEmpty
    private String titularLlinatge1;
    
    @NotEmpty
    private String titularLlinatge2;
    
    @NotEmpty
    private String titularNom;

    @NotEmpty
    private String titularNif;
    
    @NotNull
    @Email
    private String titularEmail;

    public String getUsuariCodi() {
        return usuariCodi;
    }

    public void setUsuariCodi(String usuariCodi) {
        this.usuariCodi = usuariCodi;
    }

    public String getConcepte() {
        return concepte;
    }

    public void setConcepte(String concepte) {
        this.concepte = concepte;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    public Part getFitxer() {
        return fitxer;
    }

    public void setFitxer(Part fitxer) {
        this.fitxer = fitxer;
    }

    public String getProcedimentCodi() {
        return procedimentCodi;
    }

    public void setProcedimentCodi(String procedimentCodi) {
        this.procedimentCodi = procedimentCodi;
    }

    public String getEmisorDir3Codi() {
        return emisorDir3Codi;
    }

    public void setEmisorDir3Codi(String emisorDir3Codi) {
        this.emisorDir3Codi = emisorDir3Codi;
    }

    public String getTitularLlinatge1() {
        return titularLlinatge1;
    }

    public void setTitularLlinatge1(String titularLlinatge1) {
        this.titularLlinatge1 = titularLlinatge1;
    }

    public String getTitularLlinatge2() {
        return titularLlinatge2;
    }

    public void setTitularLlinatge2(String titularLlinatge2) {
        this.titularLlinatge2 = titularLlinatge2;
    }

    public String getTitularNom() {
        return titularNom;
    }

    public void setTitularNom(String titularNom) {
        this.titularNom = titularNom;
    }

    public String getTitularNif() {
        return titularNif;
    }

    public void setTitularNif(String titularNif) {
        this.titularNif = titularNif;
    }

    public String getTitularEmail() {
        return titularEmail;
    }

    public void setTitularEmail(String titularEmail) {
        this.titularEmail = titularEmail;
    }
}

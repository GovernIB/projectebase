package es.caib.projectebase.service.model;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;

/**
 * Dades d'una Unitat Orgànica.
 *
 * @author areus
 */
@Schema(name = "Unitat")
public class UnitatOrganicaDTO {

    private Long id;

    @NotNull @Pattern(regexp = "[AEIJLU][0-9]{8}", message = "{codidir3.Pattern.message}")
    private String codiDir3;

    @NotEmpty @Size(max = 50)
    private String nom;

    @NotNull @PastOrPresent
    private LocalDate dataCreacio;

    @NotNull
    private EstatPublicacio estat;

    public UnitatOrganicaDTO() {
    }

    public UnitatOrganicaDTO(Long id, String codiDir3, String nom, LocalDate dataCreacio, EstatPublicacio estat) {
        this.id = id;
        this.codiDir3 = codiDir3;
        this.nom = nom;
        this.dataCreacio = dataCreacio;
        this.estat = estat;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodiDir3() {
        return codiDir3;
    }

    public void setCodiDir3(String codiDir3) {
        this.codiDir3 = codiDir3;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public LocalDate getDataCreacio() {
        return dataCreacio;
    }

    public void setDataCreacio(LocalDate dataCreacio) {
        this.dataCreacio = dataCreacio;
    }

    public EstatPublicacio getEstat() {
        return estat;
    }

    public void setEstat(EstatPublicacio estat) {
        this.estat = estat;
    }

    @Override
    public String toString() {
        return "UnitatOrganicaDTO{" +
                "id=" + id +
                ", codiDir3='" + codiDir3 + '\'' +
                ", nom='" + nom + '\'' +
                ", dataCreacio=" + dataCreacio +
                ", estat=" + estat +
                '}';
    }
}

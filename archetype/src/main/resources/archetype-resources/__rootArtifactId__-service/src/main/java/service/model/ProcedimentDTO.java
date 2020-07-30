#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service.model;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Dades d'un Procediment.
 *
 * @author areus
 */
@Schema(name = "Procediment")
public class ProcedimentDTO {

    private Long id;

    @NotNull @Pattern(regexp = "[0-9]{6,8}", message = "{codiSia.Pattern.message}")
    private String codiSia;

    @NotEmpty @Size(max = 50)
    private String nom;

    private Long idUnitat;

    public ProcedimentDTO() {
    }

    public ProcedimentDTO(Long id, String codiSia, String nom, Long idUnitat) {
        this.id = id;
        this.codiSia = codiSia;
        this.nom = nom;
        this.idUnitat = idUnitat;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodiSia() {
        return codiSia;
    }

    public void setCodiSia(String codiSia) {
        this.codiSia = codiSia;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Long getIdUnitat() {
        return idUnitat;
    }

    public void setIdUnitat(Long idUnitat) {
        this.idUnitat = idUnitat;
    }

    @Override
    public String toString() {
        return "ProcedimentDTO{" +
                "id=" + id +
                ", codiSia='" + codiSia + '${symbol_escape}'' +
                ", nom='" + nom + '${symbol_escape}'' +
                ", idUnitat=" + idUnitat +
                '}';
    }
}

#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.registre;

import javax.enterprise.inject.Model;
import javax.servlet.http.Part;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Bean per recollir les dades del formulari de creació d'un assentament registral.
 *
 * @author areus
 */
@Model
public class AssentamentModel {

    @NotEmpty
    private String assumpte;

    @NotNull
    private Long idioma;

    private Part annex;

    public String getAssumpte() {
        return assumpte;
    }

    public void setAssumpte(String assumpte) {
        this.assumpte = assumpte;
    }

    public Long getIdioma() {
        return idioma;
    }

    public void setIdioma(Long idioma) {
        this.idioma = idioma;
    }

    public Part getAnnex() {
        return annex;
    }

    public void setAnnex(Part annex) {
        this.annex = annex;
    }
}

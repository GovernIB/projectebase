package es.caib.projectebase.pinbal;

import es.caib.pinbal.client.recobriment.model.ScspFuncionario;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Dades emprades al formulari per indicar les dades del funcionari.
 */
public class FuncionariModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotEmpty
    @Size(max=9) private String nif;

    @NotEmpty
    private String nomComplet;

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getNomComplet() {
        return nomComplet;
    }

    public void setNomComplet(String nomComplet) {
        this.nomComplet = nomComplet;
    }

    public ScspFuncionario toScspFuncionario() {
        ScspFuncionario funcionario = new ScspFuncionario();
        funcionario.setNifFuncionario(nif);
        funcionario.setNombreCompletoFuncionario(nomComplet);
        return funcionario;
    }
}

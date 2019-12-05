package es.caib.projectebase.jpa;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Representació d'una unitat orgànica.
 * Requisits de les classes persistents:
 * L'etiqueta Entity, constructor buid. Ni la classe ni els seus mètodes o camps poden ser final. Si les hem de passar
 * com a paràmetres detached ha d'implementar serializable (i per tant també és recomanable el serialVersionUID).
 * Els camps persistents han de ser private protected o package-private i només es poden accedir mitjançant els mètodes
 *
 * @author areus
 */
@Entity
@SequenceGenerator(name = "uo-sequence", sequenceName = "PBS_UNITATORGANICA_SEQ", allocationSize = 1)
@Table(name = "PBS_UNITATORGANICA",
        uniqueConstraints = {@UniqueConstraint(name = "BLP_UNITATORGANICA_CODIDIR3_UK", columnNames = "CODIDIR3")}
)
public class UnitatOrganica implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "uo-sequence")
    @Column(name = "ID", nullable = false, length = 19)
    private Long id;

    @NotEmpty
    @Size(min = 9, max = 9)
    @Column(name = "CODIDIR3", nullable = false, length = 9)
    private String codiDir3;

    @NotEmpty
    @Size(max = 50)
    @Column(name = "NOM", nullable = false, length = 50)
    private String nom;

    /**
     * Dia de creació. Ha de ser el dia d'avui o un dia passat (no pot ser futor).
     * En la serialitzacio/deserialització JSON s'empra el format dd-mm-aaaa
     */
    @NotNull @PastOrPresent
    @Column(name = "DATACREACIO", nullable = false)
    @JsonbDateFormat("dd-MM-yyyy")
    private LocalDate dataCreacio;

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

	/*
      La implementació de equals i hashCode s'hauria de fer sempre que es pugui amb una clau natural, o en cas que
      no n'hi hagi amb l'id, però comparant-ho només si no és null, i retornant un valor fix al hashCode per evitar
      que canvii després de cridar persist.
      Veure: https://docs.jboss.org/hibernate/orm/5.3/userguide/html_single/Hibernate_User_Guide.html
      Apartat: 2.5.7. Implementing equals() and hashCode()
    */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UnitatOrganica unitatOrganica = (UnitatOrganica) o;
        return codiDir3.equals(unitatOrganica.codiDir3);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codiDir3);
    }

    @Override
    public String toString() {
        return "UnitatOrganica{" +
                "id=" + id +
                ", codiDir3='" + codiDir3 + '\'' +
                '}';
    }
}

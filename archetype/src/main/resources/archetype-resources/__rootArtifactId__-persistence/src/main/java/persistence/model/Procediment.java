#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Objects;

/**
 * Representació d'un procediment. A nivell de classe definim la seqüència que emprarem, i les claus úniques.
 *
 * @author areus
 */
@Entity
@SequenceGenerator(name = "procediment-sequence", sequenceName = "${prefixuppercase}_PROCEDIMENT_SEQ", allocationSize = 1)
@Table(name = "${prefixuppercase}_PROCEDIMENT",
        uniqueConstraints = {
                @UniqueConstraint(name = "${prefixuppercase}_PROCEDIMENT_CODISIA_UK", columnNames = "CODISIA")
        },
        indexes = {
                @Index(name = "${prefixuppercase}_PROCEDIMENT_PK_I", columnList = "ID"),
                @Index(name = "${prefixuppercase}_PROCEDIMENT_CODISIA_UK_I", columnList = "CODISIA"),
                @Index(name = "${prefixuppercase}_PROCEDIMENT_UNITAT_FK_I", columnList = "UNITATORGANICAID")
        }
)
public class Procediment extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "procediment-sequence")
    @Column(name = "ID", nullable = false, length = 19)
    private Long id;

    /**
     * Codi SIR que identifica el procediment. És únic, i per tant una clau natural.
     * Ha de ser un nombre de entre 6 i 8 dígits.
     * No es pot actualitzar una vegada creat.
     */
    @Column(name = "CODISIA", nullable = false, updatable = false, length = 8)
    @NotNull @Pattern(regexp = "[0-9]{6,8}", message = "{codiSia.Pattern.message}")
    private String codiSia;

    /**
     * Nom del procediment.
     */
    @Column(name = "NOM", nullable = false, length = 50)
    @NotEmpty @Size(max = 50)
    private String nom;

    /**
     * La unitat orgànica responsable del procediment. Marcam com a transient per JSON per evitar que es
     * seralitzi/deserialitzi.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UNITATORGANICAID", nullable = false,
            foreignKey = @ForeignKey(name = "${prefixuppercase}_PROCEDIMENT_UNITAT_FK"))
    private UnitatOrganica unitatOrganica;

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

    public UnitatOrganica getUnitatOrganica() {
        return unitatOrganica;
    }

    public void setUnitatOrganica(UnitatOrganica unitatOrganica) {
        this.unitatOrganica = unitatOrganica;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Procediment)) return false;
        Procediment that = (Procediment) o;
        return Objects.equals(codiSia, that.codiSia);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codiSia);
    }

    @Override
    public String toString() {
        return "Procediment{" +
                "id=" + id +
                ", codiSia='" + codiSia + '${symbol_escape}'' +
                '}';
    }
}
#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.persistence.model;

import ${package}.service.model.EstatPublicacio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.time.LocalDate;
import java.util.Objects;

import static javax.persistence.EnumType.ORDINAL;

/**
 * Representació d'una unitat orgànica. Sempre convé definir-les serializable, per si hi mantenim referències dins
 * coses serializables. A nivell de classe definim la seqüència que emprarem, i les claus úniques.
 * Amb l'anotació Schema de openapi li assignam un nom a l'schema generat.
 *
 * @author areus
 */
@Entity
@SequenceGenerator(name = "uo-sequence", sequenceName = "${prefixuppercase}_UNITATORGANICA_SEQ", allocationSize = 1)
@Table(name = "${prefixuppercase}_UNITATORGANICA",
        uniqueConstraints = {
                @UniqueConstraint(name = "${prefixuppercase}_UNITAT_CODIDIR3_UK", columnNames = "CODIDIR3")
        },
        indexes = {
                @Index(name = "${prefixuppercase}_UNITAT_PK_I", columnList = "ID"),
                @Index(name = "${prefixuppercase}_UNITAT_CODIDIR3_UK_I", columnList = "CODIDIR3")
        }
)
public class UnitatOrganica extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * Camp identificador generat per una seqüència.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "uo-sequence")
    @Column(name = "ID", nullable = false, length = 19)
    private Long id;

    /**
     * Codi DIR3 que identifica la únicat orgànica. És únic, i per tant una clau natural.
     * Ha de seguir el patró d'una lletra (A, E, I, J, L, U) seguida de 8 dígits. Ficam un missatge de validació personalitzat.
     */
    @Column(name = "CODIDIR3", nullable = false, length = 9)
    private String codiDir3;

    /**
     * Nom de la únitat orgànica. Ha de ser una cadena no buida, de màxim 50 caràcters.
     */
    @Column(name = "NOM", nullable = false, length = 50)
    private String nom;

    /**
     * Dia de creació. Ha de ser el dia d'avui o un dia passat (no pot ser futur).
     * En la serialitzacio/deserialització JSON s'empra el format dd-mm-aaaa.
     */
    @Column(name = "DATACREACIO", nullable = false)
    private LocalDate dataCreacio;

    /**
     * Representa l'estat de publicació. S'emmagatzema a base de dades com un nombre, en funció de l'ordre en que
     * està definida l'enumeració {@link EstatPublicacio}, això vol dir que canviar d'ordre els valors de l'enumeració
     * fa que els valors de base de dades facin referència a altres valors.
     */
    @Enumerated(ORDINAL)
    @Column(name = "ESTAT", nullable = false)
    private EstatPublicacio estat;

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
        return Objects.equals(codiDir3, unitatOrganica.codiDir3);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codiDir3);
    }

    /*
    La implementació de toString, amb les dades bàsiques permet fer logs més bons de seguir.
     */

    @Override
    public String toString() {
        return "UnitatOrganica{" +
                "id=" + id +
                ", codiDir3='" + codiDir3 + '${symbol_escape}'' +
                '}';
    }
}

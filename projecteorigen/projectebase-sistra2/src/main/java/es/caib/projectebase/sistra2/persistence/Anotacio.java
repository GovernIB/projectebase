package es.caib.projectebase.sistra2.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

import static javax.persistence.EnumType.ORDINAL;

@Entity
@Table(name = "PBS_ANOTACIO")
@NamedQueries(
        @NamedQuery(
                name = Anotacio.IDS_BY_ESTAT,
                query = "SELECT an.id FROM Anotacio an WHERE an.estat = :estat"
        )
)
public class Anotacio {

    public static final String IDS_BY_ESTAT = "Anotacio.IDS_BY_ESTAT";

    @Id
    @Column(name = "ID", nullable = false, length = 100)
    @NotEmpty
    private String id;

    @Column(name = "CLAU", nullable = false, length = 44)
    @NotEmpty
    private String clau;

    @Enumerated(ORDINAL)
    @Column(name = "ESTAT", nullable = false)
    @NotNull
    private EstatAnotacio estat;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClau() {
        return clau;
    }

    public void setClau(String clau) {
        this.clau = clau;
    }

    public EstatAnotacio getEstat() {
        return estat;
    }

    public void setEstat(EstatAnotacio estat) {
        this.estat = estat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Anotacio)) return false;
        Anotacio anotacio = (Anotacio) o;
        return id.equals(anotacio.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

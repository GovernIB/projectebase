#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.sistra2.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@SequenceGenerator(name = "anotacioinbox-sequence", sequenceName = "${prefixuppercase}_ANOTACIOINBOX_SEQ", allocationSize = 50)
@Table(
        name = "${prefixuppercase}_ANOTACIOINBOX",
        indexes = {
            @Index(name = "${prefixuppercase}_ANOTACIOINBOX_PK_I", columnList = "ID"),
})
@NamedQueries({
        @NamedQuery(
                name = AnotacioInbox.FIND_ALL_BYESTAT,
                query = "select ai.id from AnotacioInbox ai where ai.estat = :estat order by ai.id")
})
public class AnotacioInbox implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String FIND_ALL_BYESTAT = "AnotacioInbox.FIND_ALL_BYESTAT";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "anotacioinbox-sequence")
    @Column(name = "ID", nullable = false, length = 19)
    private Long id;

    @Column(name = "IDENTIFICADOR", nullable = false, length = 100)
    @NotEmpty @Size(max = 100)
    private String identificador;

    @Column(name = "CLAUACCES", nullable = false, length = 44)
    @NotEmpty @Size(max = 44)
    private String clauAcces;

    @Column(name = "CONTINGUT")
    @Lob
    private String contingut;

    @Column(name ="EXPEDIENT", length = 256)
    @Size(max = 256)
    private String expedientArxiu;

    @Column(name = "ESTAT")
    @Enumerated(EnumType.STRING)
    @NotNull
    private Estat estat = Estat.PENDENT;

    @Column(name = "CREATED", nullable = false, updatable = false)
    private LocalDateTime created;

    @PrePersist
    protected void prePersist() {
        created = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getClauAcces() {
        return clauAcces;
    }

    public void setClauAcces(String clauAcces) {
        this.clauAcces = clauAcces;
    }

    public String getContingut() {
        return contingut;
    }

    public void setContingut(String contingut) {
        this.contingut = contingut;
    }

    public String getExpedientArxiu() {
        return expedientArxiu;
    }

    public void setExpedientArxiu(String expedientArxiu) {
        this.expedientArxiu = expedientArxiu;
    }

    public Estat getEstat() {
        return estat;
    }

    public void setEstat(Estat estat) {
        this.estat = estat;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public enum Estat {
        PENDENT,
        REBUDA,
        PROCESSADA,
        REBUTJADA,
        ERROR;
    }
}

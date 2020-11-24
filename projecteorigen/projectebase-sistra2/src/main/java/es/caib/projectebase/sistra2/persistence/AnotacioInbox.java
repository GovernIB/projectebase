package es.caib.projectebase.sistra2.persistence;

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

/**
 * Emmagatzema la informació de les anotacions rebudes de distribució.
 */
@Entity
@SequenceGenerator(name = "anotacioinbox-sequence", sequenceName = "PBS_ANOTACIOINBOX_SEQ", allocationSize = 50)
@Table(
        name = "PBS_ANOTACIOINBOX",
        indexes = {
            @Index(name = "PBS_ANOTACIOINBOX_PK_I", columnList = "ID"),
})
@NamedQueries({
        @NamedQuery(
                name = AnotacioInbox.FIND_ALL_BYESTAT,
                query = "select ai.id from AnotacioInbox ai where ai.estat = :estat order by ai.id")
})
public class AnotacioInbox implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String FIND_ALL_BYESTAT = "AnotacioInbox.FIND_ALL_BYESTAT";

    /**
     * Id autogenerat amb seqüència.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "anotacioinbox-sequence")
    @Column(name = "ID", nullable = false, length = 19)
    private Long id;

    /**
     * Identificador de l'anotació enviat per Distribució.
     */
    @Column(name = "IDENTIFICADOR", nullable = false, length = 100)
    @NotEmpty @Size(max = 100)
    private String identificador;

    /**
     * Clau d'accés de l'anotació enviat per Distribució.
     */
    @Column(name = "CLAUACCES", nullable = false, length = 44)
    @NotEmpty @Size(max = 44)
    private String clauAcces;

    /**
     * Contingut complet serializat de l'anotació de registre d'entrada retornat per la consulta a Distribució.
     */
    @Column(name = "CONTINGUT")
    @Lob
    private String contingut;

    /**
     * Identificador de l'expedient creat a Arxiu.
     */
    @Column(name ="EXPEDIENT", length = 256)
    @Size(max = 256)
    private String expedientArxiu;

    /**
     * Estat de l'anotació.
     */
    @Column(name = "ESTAT")
    @Enumerated(EnumType.STRING)
    @NotNull
    private Estat estat = Estat.PENDENT;

    /**
     * Timestamp del moment de creació.
     */
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
        PENDENT,    // rebut identificador i clau de Distribució.
        REBUDA,     // consultada anotació i comunicat canvi d'estat a REBUDA a Distribució.
        PROCESSADA, // creat expedient dins l'arxiu i  comunicat canvi d'estat a PROCESSADA a Distribució.
        REBUTJADA,  // no emprat
        ERROR       // produit un error en crear l'expedient a arxiu i comunicat canvi d'estat a ERROR a Distribució
    }
}

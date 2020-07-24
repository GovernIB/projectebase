package es.caib.projectebase.persistence.model;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * Permet definir camps comuns o mètodes comuns a tots els entitys, per temes com auditoria.
 *
 * @author areus
 */
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
}

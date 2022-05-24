package es.caib.projectebase.distribucio.repository;

import java.util.List;

import es.caib.projectebase.distribucio.persistence.AnotacioInbox;

/**
 * Interfície de les operacions bàsiques sobre AnotacioInbox.
 * Són les generals per CRUD més les específiques l'entitat.
 *
 * @author areus
 */
public interface AnotacioInboxRepository extends CrudRepository<AnotacioInbox, Long> {

    List<Long> findAllPendents();

    List<Long> findAllRebudes();
}

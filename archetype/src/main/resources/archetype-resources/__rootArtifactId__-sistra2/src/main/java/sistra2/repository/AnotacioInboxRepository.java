#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.sistra2.repository;

import ${package}.sistra2.persistence.AnotacioInbox;

import java.util.List;

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

#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.ejb.repository;

import java.util.Optional;

/**
 * Operacions bàsiques d'un repositori CRUD
 * @param <E> tipus de l'entity
 * @param <PK> tipus de la clau primària
 */
public interface CrudRepository<E, PK> {

    void create(E entity);

    void update(E entity);

    Optional<E> findById(PK id);

    E getReference(PK id);

    void delete(E entity);
}

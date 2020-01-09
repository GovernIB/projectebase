#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.jpa.dao;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import ${package}.commons.i18n.I18NException;
import ${package}.commons.query.OrderBy;


/**
 * 
 * @author anadal
 */
public interface IAbstractJPAManager<E extends Serializable, PK> {

    /**
     * Crea un item
     *
     * @return El item creat.
     */
    public E create(E item) throws I18NException;

    /**
     * Actualitza un item.
     *
     */
    public E update(E item) throws I18NException;

    /**
     * Esborra un item a partir de l'identificador indicat.
     *
     * @param id
     *            Identificador del entity
     */
    public void deleteById(PK id);

    /**
     * 
     * @param entity
     */
    public void delete(E entity);

    /**
     * Obté un entity amb l'identificador indicat.
     *
     * @param id
     *            identificador del entity.
     * @return El entity o <code>null</code> si id és null o no n'hi ha cap entitat amb l'id
     *         indicat.
     */
    public E findById(PK id);

    /**
     * Obté el total d'entrades de la bbdd
     * 
     * @return
     * @throws Exception
     */
    public Long count();

    /**
     * Obte totes les entrades de la bbdd
     * 
     * @return
     * @throws Exception
     */
    public List<E> selectAll(OrderBy... orderBy) throws I18NException;

    /**
     * 
     * @param first
     * @param pageSize
     * @return
     */
    public List<E> selectPagination(@PositiveOrZero int first, @Positive int pageSize, OrderBy... orderBy)
            throws I18NException;

}

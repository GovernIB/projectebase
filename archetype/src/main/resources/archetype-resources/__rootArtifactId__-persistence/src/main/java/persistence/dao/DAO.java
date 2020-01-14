#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.persistence.dao;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import java.io.Serializable;
import java.util.List;

import ${package}.commons.i18n.I18NException;
import ${package}.commons.query.OrderBy;

/**
 * Defineix les operacions dels Data Access Object per una entitat.
 * 
 * 
 * @param <PK>
 *            Tipus de la clau primària de l'entitat.
 * @param <E>
 *            Tipus de l'entitat.
 *
 * @author anadal
 * @author areus
 */
public interface DAO<E extends Serializable, PK> {

    /**
     * Crea un item
     *
     * @return El item creat.
     */
    public E create(E item) throws I18NException;
    
    /**
     * Crea N items
     *
     * @return El item creat.
     */
    public void bulkCreate(List<E> entities) throws I18NException;

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
     * Esborra una entitat
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
    public Long countAll();
    
    /**
     * Retorna el nombre total d'entitats que compleixen el filtre indicat.
     * La cadena de filtre es cerca dins tots els camps de tipus String de l'entitat.
     *
     * @param filter   cadena de caràcters que es cercarà dins els camps de tipus string.
     * @return nombre d'entitats que compleixen el filtre.
     */
    public long countFilter(String filter);
    
    /**
     * Obte totes les entrades de la bbdd
     * 
     * @return
     * @throws Exception
     */
    public List<E> selectAll(OrderBy... orderBy) throws I18NException;

    
    public List<E> selectAll(String filter, OrderBy... orderBy) throws I18NException;
    
    /**
     *
     * @param firstResult
     *            índex del primer resultat.
     * @param size
     *            nombre màxim de resultats.
     * @return llista d'entitats.
     */
    public List<E> selectAll(@PositiveOrZero int first, @Positive int pageSize, OrderBy... orderBy)
            throws I18NException;
    
    
    public List<E> selectAll(String filter, @PositiveOrZero int first, @Positive int pageSize, OrderBy... orderBy)
            throws I18NException;

    /**
     * Obté una entitat en forma de referència, per tant sense carregar les seves dades. Emprat
     * bàsicament per fixar claus foranes.
     *
     * @param id
     *            Identificador de l'entitat a carregar.
     * @return L'entitat en forma de referència.
     */
    public E getReference(PK id);

}
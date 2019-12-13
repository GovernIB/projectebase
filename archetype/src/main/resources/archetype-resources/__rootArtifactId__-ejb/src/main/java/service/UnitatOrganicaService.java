#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service;

import ${package}.jpa.UnitatOrganica;

import javax.ejb.Local;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

/**
 * Interfície del servei per gestionar {@link UnitatOrganica}
 *
 * @author areus
 */
@Local
public interface UnitatOrganicaService {

    /**
     * Crea una llista d'unitats orgàniques
     *
     * @param unitats llista d'unitats orgàniques
     */
    void bulkCreate(@NotNull List<UnitatOrganica> unitats);

    /**
     * Crea una unitat orgànica.
     *
     * @param unitatOrganica unitat orgànica
     * @return la unitat orgànica creada.
     */
    UnitatOrganica create(UnitatOrganica unitatOrganica);

    /**
     * Actualitza una unitat orgànica.
     *
     * @param unitatOrganica unitat orgànica
     * @return la unitat orgànica actualitzada
     */
    UnitatOrganica update(UnitatOrganica unitatOrganica);

    /**
     * Esborra la unitat orgànica amb l'identificador indicat.
     *
     * @param id Identificador de la unitat orgànica.
     */
    void deleteById(Long id);

    /**
     * Obté la unitat orgànica amb l'identificador indicat.
     *
     * @param id identificador de la unitat orgànica.
     * @return la unitat orgànica o <code>null</code> si no n'hi ha cap amb l'id indicat.
     */
    UnitatOrganica findById(Long id);

    /**
     * Obté una llista de totes les unitats orgàniques.
     *
     * @return llista d'unitats orgàniques.
     */
    List<UnitatOrganica> findAll();

    /**
     * Obté una llista de les unitats orgàniques per paginacions.
     *
     * @param first    el número d'ordre de la primera unitat orgànica a tornar. La primera és 0.
     * @param pageSize el nombre màxim d'unitats orgàniques a tornar.
     * @return llista d'unitats orgàniques.
     */
    List<UnitatOrganica> findAllPaged(@PositiveOrZero int first, @Positive int pageSize);

    /**
     * Retorna el nombre total d'unitats orgàniques.
     *
     * @return nombre d'unitats orgàniques.
     */
    long countAll();
}
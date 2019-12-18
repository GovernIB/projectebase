#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service;

import ${package}.jpa.Procediment;

import javax.ejb.Local;
import java.util.List;

/**
 * Interfície del servei per gestionar {@link Procediment}
 *
 * @author areus
 */
@Local
public interface ProcedimentService {

    /**
     * Crea un procediment depenent d'una unitat orgànica.
     *
     * @param procediment procediment.
     * @param unitatOrganicaId identificador de la unitat orgànica.
     * @return El procediment creat.
     */
    Procediment create(Procediment procediment, Long unitatOrganicaId);

    /**
     * Actualitza un procediment.
     *
     * @param procediment procediment
     * @return El procediment actualitzat
     */
    Procediment update(Procediment procediment);

    /**
     * Esborra un procediment amb l'identificador indicat.
     *
     * @param id Identificador del procediment.
     */
    void deleteById(Long id);

    /**
     * Obté un procediment amb l'identificador indicat.
     *
     * @param id identificador del procediment.
     * @return El procediment o <code>null</code> si no n'hi ha cap amb l'id indicat.
     */
    Procediment findById(Long id);

    /**
     * Obté una llista de tots els procediments d'una unitat orgànica.
     *
     * @param unitatOrganicaId identificador de la unitat orgànica.
     * @return llista de procediments de la unitat orgànica.
     */
    List<Procediment> findAllByUnitatOrganica(Long unitatOrganicaId);

    /**
     * Obté el nombre de procediments d'una unitat orgànica
     * @param unitatOrganicaId identificador de la unitat orgànica.
     * @return
     */
    Long countAllByUnitatOrganica(Long unitatOrganicaId);
}

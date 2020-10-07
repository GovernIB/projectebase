#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service.facade;

import ${package}.service.exception.ProcedimentDuplicatException;
import ${package}.service.exception.RecursNoTrobatException;
import ${package}.service.model.Pagina;
import ${package}.service.model.ProcedimentDTO;

import java.util.Optional;

/**
 * Servei per els casos d'ús de mateniment d'un Procediment.
 *
 * @author areus
 */
public interface ProcedimentServiceFacade {

    /**
     * Crea un nou procediment a la base de dades relacionat amb la unitat indicada.
     * @param dto dades del procediment
     * @param idUnitat identificador de la unitat
     * @return l'identificador del nou procediment
     * @throws RecursNoTrobatException si la unitat no existeix
     * @throws ProcedimentDuplicatException si ja existeix un procediment amb el mateix codi SIA
     */
    Long create(ProcedimentDTO dto, Long idUnitat) throws RecursNoTrobatException, ProcedimentDuplicatException;

    /**
     * Actualitza les dades d'un procediment a la base de dades. El codiSia no es sobreescriu.
     * @param dto noves dades del procediment.
     * @throws RecursNoTrobatException si el procediment amb identificador dto.id no existeix.
     */
    void update(ProcedimentDTO dto) throws RecursNoTrobatException;

    /**
     * Esborra un procediment de la base de dades.
     * @param id identificador del procediment a esborrar.
     * @throws RecursNoTrobatException si el procediment amb identificador id no existeix.
     */
    void delete(Long id) throws RecursNoTrobatException;

    /**
     * Retorna un opcional amb el procediment indicat per l'identificador.
     * @param id identificador del procediment a cercar
     * @return un opcional amb les dades del procediment indicat o buid si no existeix.
     */
    Optional<ProcedimentDTO> findById(Long id);

    /**
     * Retorna una pàgina dels procediments relacionats amb la unitat indicada.
     * @param firstResult primer resultat del rang de la pàgina
     * @param maxResult nombre d'elements màxim de la pàgina.
     * @param idUnitat identificador de la unitat.
     * @return una pàgina amb el nombre total de procediments i la llista de procediments pel rang indicat.
     */
    Pagina<ProcedimentDTO> findByUnitat(int firstResult, int maxResult, Long idUnitat);
}

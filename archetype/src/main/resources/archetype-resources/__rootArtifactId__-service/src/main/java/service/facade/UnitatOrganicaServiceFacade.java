#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service.facade;

import ${package}.service.exception.RecursNoTrobatException;
import ${package}.service.exception.UnitatDuplicadaException;
import ${package}.service.exception.UnitatTeProcedimentsException;
import ${package}.service.model.Ordre;
import ${package}.service.model.Pagina;
import ${package}.service.model.UnitatOrganicaDTO;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Servei per els casos d'ús de mateniment d'una Unitat Orgànica.
 *
 * @author areus
 */
public interface UnitatOrganicaServiceFacade {

    /**
     * Crea una nova unitat a la base de dades.
     * @param dto dades de la unitat.
     * @return l'identificador de la nova unitat.
     * @throws UnitatDuplicadaException si ja existeix una unitat amb el mateix codiDir3
     */
    Long create(UnitatOrganicaDTO dto) throws UnitatDuplicadaException;

    /**
     * Actualitza les dades d'una unitat al a base de dades. El codiDir3 no es sobreescriu.
     * @param dto noves dades de la unitat.
     * @throws RecursNoTrobatException si la unitat amb identificador dto.id no existeix.
     */
    void update(UnitatOrganicaDTO dto) throws RecursNoTrobatException;

    /**
     * Esborra una unitat de la base de dades.
     * @param id identificador de la unitat a esborrar
     * @throws UnitatTeProcedimentsException si la unitat té procediments associats i per tant no es pot esborrar.
     * @throws RecursNoTrobatException si la unitat amb identificador id no existeix.
     */
    void delete(Long id) throws UnitatTeProcedimentsException, RecursNoTrobatException;

    /**
     * Retorna un opcional amb la unitat indicada per l'identificador.
     * @param id identificador de la unitat a cercar
     * @return un opcional amb les dades de la unitat indicada o buid si no existeix.
     */
    Optional<UnitatOrganicaDTO> findById(Long id);

    /**
     * Retorna una pàgina de unitats que compleixen els filtres i les ordenacions indicades
     * @param firstResult primer resultat del rang de la pàgina
     * @param maxResult nombre d'elements màxim de la pàgina.
     * @param filters filtres a aplicar
     * @param ordenacio criteris d'ordenació
     * @return una pàgina amb el nombre d'unitats que compleixen els filtres i la llista d'unitats pel rang indicat.
     */
    Pagina<UnitatOrganicaDTO> findFiltered(int firstResult, int maxResult,
                                           Map<String, Object> filters, List<Ordre> ordenacio);
}

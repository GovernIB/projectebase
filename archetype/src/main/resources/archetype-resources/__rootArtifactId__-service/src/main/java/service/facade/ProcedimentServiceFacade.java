#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service.facade;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import ${package}.service.exception.ProcedimentDuplicatExeption;
import ${package}.service.exception.RecursNoTrobatException;
import ${package}.service.model.Page;
import ${package}.service.model.ProcedimentDTO;

/**
 * Servei per els casos d'ús de mateniment d'un Procediment.
 *
 * @author areus
 */
public interface ProcedimentServiceFacade {

    Long create(@NotNull @Valid ProcedimentDTO dto, @NotNull Long idUnitat) throws ProcedimentDuplicatExeption;

    void update(@NotNull @Valid ProcedimentDTO dto) throws ProcedimentDuplicatExeption;

    void delete(@NotNull Long id);

    ProcedimentDTO findById(@NotNull Long id) throws RecursNoTrobatException;

    Page<ProcedimentDTO> findByUnitat(@PositiveOrZero int firstResult, @Positive int maxResult, @NotNull Long idUnitat);
}

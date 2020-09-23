package es.caib.projectebase.service.facade;

import es.caib.projectebase.service.exception.ProcedimentDuplicatException;
import es.caib.projectebase.service.model.Pagina;
import es.caib.projectebase.service.model.ProcedimentDTO;

import java.util.Optional;

/**
 * Servei per els casos d'ús de mateniment d'un Procediment.
 *
 * @author areus
 */
public interface ProcedimentServiceFacade {

    Long create(ProcedimentDTO dto, Long idUnitat) throws ProcedimentDuplicatException;

    void update(ProcedimentDTO dto);

    void delete(Long id);

    Optional<ProcedimentDTO> findById(Long id);

    Pagina<ProcedimentDTO> findByUnitat(int firstResult, int maxResult, Long idUnitat);
}

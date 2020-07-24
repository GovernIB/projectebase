package es.caib.projectebase.service.facade;

import es.caib.projectebase.service.model.Page;
import es.caib.projectebase.service.model.ProcedimentDTO;

/**
 * Servei per els casos d'ús de mateniment d'una Unitat Organica
 */
public interface ProcedimentServiceFacade {

    Long create(ProcedimentDTO dto, Long idUnitat);

    void update(ProcedimentDTO dto);

    void delete(Long id);

    ProcedimentDTO findById(Long id);

    Page<ProcedimentDTO> findByUnitat(int firstResult, int maxResult, Long idUnitat);
}

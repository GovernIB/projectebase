package es.caib.projectebase.ejb.repository;

import es.caib.projectebase.persistence.model.Procediment;
import es.caib.projectebase.service.model.ProcedimentDTO;

import java.util.List;

public interface ProcedimentRepository extends CrudRepository<Procediment, Long> {

    List<ProcedimentDTO> findPagedByUnitat(int firstResult, int maxResult, Long idUnitat);

    long countByUnitat(Long idUnitat);
}

package es.caib.projectebase.ejb.repository;

import es.caib.projectebase.persistence.model.UnitatOrganica;
import es.caib.projectebase.service.model.Ordre;
import es.caib.projectebase.service.model.UnitatOrganicaDTO;

import java.util.List;
import java.util.Map;

public interface UnitatOrganicaRepository extends CrudRepository<UnitatOrganica, Long> {

    List<UnitatOrganicaDTO> findPagedByFilterAndOrder(int firstResult, int maxResult,
                                                      Map<String, Object> filters,
                                                      List<Ordre> ordenacio);

    long countByFilter(Map<String, Object> filters);
}

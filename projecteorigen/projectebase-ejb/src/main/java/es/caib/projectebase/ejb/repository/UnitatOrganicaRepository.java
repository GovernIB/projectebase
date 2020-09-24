package es.caib.projectebase.ejb.repository;

import es.caib.projectebase.persistence.model.UnitatOrganica;
import es.caib.projectebase.service.model.Ordre;
import es.caib.projectebase.service.model.UnitatOrganicaDTO;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Interfície de les operacions bàsiques sobre Unitats Orgàniques.
 * Són les generals per CRUD més les específiques per procediments.
 *
 * @author areus
 */
public interface UnitatOrganicaRepository extends CrudRepository<UnitatOrganica, Long> {

    Optional<UnitatOrganica> findByCodiDir3(String codiDir3);

    List<UnitatOrganicaDTO> findPagedByFilterAndOrder(int firstResult, int maxResult,
                                                      Map<String, Object> filters,
                                                      List<Ordre> ordenacio);

    long countByFilter(Map<String, Object> filters);

}

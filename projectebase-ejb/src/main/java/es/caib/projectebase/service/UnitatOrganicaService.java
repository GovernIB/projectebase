package es.caib.projectebase.service;

import es.caib.projectebase.jpa.UnitatOrganica;

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

    void bulkCreate(@NotNull List<UnitatOrganica> unitats);

    UnitatOrganica create(UnitatOrganica unitatOrganica);

    UnitatOrganica update(UnitatOrganica unitatOrganica);

    void deleteById(Long id);

    UnitatOrganica findById(Long id);

    List<UnitatOrganica> findAll();

    List<UnitatOrganica> findAllPaged(@PositiveOrZero int first, @Positive int pageSize);

    long countAll();
}

package es.caib.projectebase.service;

import es.caib.projectebase.jpa.UnitatOrganica;

import javax.ejb.Local;
import java.util.List;

/**
 * Interfície del servei per gestionar {@link UnitatOrganica}
 * @author areus
 */
@Local
public interface UnitatOrganicaService {

	UnitatOrganica create(UnitatOrganica unitatOrganica);

	UnitatOrganica update(UnitatOrganica unitatOrganica);

	void deleteById(Long id);

	UnitatOrganica findById(Long id);

	List<UnitatOrganica> findAll();
}

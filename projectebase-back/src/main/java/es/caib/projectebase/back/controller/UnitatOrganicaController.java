package es.caib.projectebase.back.controller;

import es.caib.projectebase.jpa.UnitatOrganica;
import es.caib.projectebase.service.UnitatOrganicaService;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Controlador de Unitats Organiques
 * @author areus
 */
@Named("unitatOrganicaController")
@ViewScoped
public class UnitatOrganicaController implements Serializable {

	private static final Logger log = LoggerFactory.getLogger(UnitatOrganicaController.class);

	@EJB
	UnitatOrganicaService unitatOrganicaService;

	// PROPIETATS + GETTERS/SETTERS
	private UnitatOrganica current;
	private LazyDataModel<UnitatOrganica> lazyModel;

	public UnitatOrganica getCurrent() {
		return current;
	}

	public LazyDataModel<UnitatOrganica> getLazyModel() {
		return lazyModel;
	}

	@PostConstruct
	public void init() {
		log.info("init");
		current = new UnitatOrganica();
		lazyModel = new LazyDataModel<>() {
			@Override
			public List<UnitatOrganica> load(int first, int pageSize, String sortField, SortOrder sortOrder,
											 Map<String, Object> filters) {
				setRowCount((int) unitatOrganicaService.countAll());
				//TODO implementar ordenació i filtres
				return unitatOrganicaService.findAllPaged(first, pageSize);
			}
		};
	}

	// ACCIONS

	public void loadCurent(Long id) {
		log.info("loadCurrent");
		current = unitatOrganicaService.findById(id);
	}

	public void saveOrUpdate() {
		log.info("saveOrUpdate");
		if (current.getId() != null) {
			unitatOrganicaService.update(current);
		} else {
			unitatOrganicaService.create(current);
		}
		current = new UnitatOrganica();
	}

	public void delete(Long id) {
		log.info("delete");
		unitatOrganicaService.deleteById(id);
	}


}

package es.caib.projectebase.back.controller;

import es.caib.projectebase.jpa.UnitatOrganica;
import es.caib.projectebase.service.UnitatOrganicaService;
import org.slf4j.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * Controlador de Unitats Organiques
 * @author areus
 */
@Named("unitatOrganicaController")
@ViewScoped
public class UnitatOrganicaController implements Serializable {

	@Inject
	private Logger log;

	@EJB
	UnitatOrganicaService unitatOrganicaService;

	// PROPIETATS + GETTERS/SETTERS
	private UnitatOrganica current;
	private List<UnitatOrganica> values;

	public UnitatOrganica getCurrent() {
		return current;
	}

	public List<UnitatOrganica> getValues() {
		return values;
	}

	@PostConstruct
	public void init() {
		log.info("init");
		current = new UnitatOrganica();
		loadValues();
	}

	private void loadValues() {
		log.info("loadValues");
		values = unitatOrganicaService.findAll();
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
		loadValues();
	}

	public void delete(Long id) {
		log.info("delete");
		unitatOrganicaService.deleteById(id);
		loadValues();
	}
}

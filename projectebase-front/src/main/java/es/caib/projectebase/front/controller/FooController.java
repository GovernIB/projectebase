package es.caib.projectebase.front.controller;

import es.caib.projectebase.jpa.FooEntity;
import es.caib.projectebase.service.FooServiceInterface;
import org.slf4j.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * Controlador de prueba para el frontal de la aplicacion de prueba
 * @author [u91310] Pedro Bauzá Mascaró 
 */
@Named("fooFrontController")
@ViewScoped
public class FooController implements Serializable {

	@Inject
	private Logger log;
	
	@EJB
	FooServiceInterface fooService;
	
	private String value;
	private List<FooEntity> values;

	@PostConstruct
	public void init() {
		log.info("Proxy a fooService "+this.fooService);
		value = this.fooService.getDefaultValue();
		loadValues();
	}

	private void loadValues() {
		log.info("Load values");
		values = this.fooService.list();
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void addValue() {
		FooEntity foo = new FooEntity();
		foo.setValue(value);
		this.fooService.addFooEntity(foo);
		loadValues();
	}

	public List<FooEntity> getValues() {
		return this.values;
	}
}

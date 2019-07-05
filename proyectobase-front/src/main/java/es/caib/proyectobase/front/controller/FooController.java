package es.caib.proyectobase.front.controller;

import es.caib.proyectobase.entity.FooEntity;
import es.caib.proyectobase.service.FooServiceInterface;
import org.slf4j.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Controlador de prueba para el frontal de la aplicacion de prueba
 * @author [u91310] Pedro Bauzá Mascaró 
 */
@Named("fooFrontController")
@RequestScoped
public class FooController {

	@Inject
	private Logger log;
	
	@EJB
	FooServiceInterface fooService;
	
	private String value;
	private List<FooEntity> values;
	
	@PostConstruct
	public void init() {
		log.info("Proxy a fooService "+this.fooService);
		this.value = this.fooService.getDefaultValue();
	}
	
	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public void addValue() {
		FooEntity foo = new FooEntity();
		foo.setValue(this.value);
		this.fooService.addFooEntity(foo);
	}

	public List<FooEntity> getValues() {
		if (fooService != null) {
		  this.values = this.fooService.list();
		  return this.values;
		}
		
		throw new Error("HOLAAAAAAAAA");
	}
	
	
}

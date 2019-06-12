package es.caib.proyectobase.service;

import java.util.List;

import javax.ejb.Local;

import es.caib.proyectobase.entity.FooEntity;

/**
 * Interface del servicio (EJB) FooService
 * @author [u91310] Pedro Bauzá Mascaró
 */
@Local
public interface FooServiceInterface {

  public static final String JNDI_NAME = "java:app/es.caib.proyectobase-proyectobase-ejb-0.0.1-SNAPSHOT/FooService";
	String getDefaultValue();
	Boolean addFooEntity(FooEntity fooEntity);
	List<FooEntity> list();
	
}

package es.caib.projectebase.service;

import es.caib.projectebase.entity.FooEntity;

import javax.ejb.Local;
import java.util.List;

/**
 * Interface del servicio (EJB) FooService
 * @author [u91310] Pedro Bauzá Mascaró
 */
@Local
public interface FooServiceInterface {

   String JNDI_NAME = "java:app/projectebase-ejb-0.0.1-SNAPSHOT/FooService";

	String getDefaultValue();

	Boolean addFooEntity(FooEntity fooEntity);

	List<FooEntity> list();
	
}

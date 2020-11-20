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

	String getDefaultValue();
	Boolean addFooEntity(FooEntity fooEntity);
	List<FooEntity> list();
	
}

package es.caib.proyectobase.service;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import es.caib.proyectobase.entity.FooEntity;

/**
 * Servicio (EJB) para realizar inserciones y consultas de prueba en BBDD
 * @author [u91310] Pedro Bauzá Mascaró
 */
@Stateless
//@RolesAllowed({"tothom", "PB_ADMIN"})
public class FooService implements FooServiceInterface {

	private final static Logger LOGGER = Logger.getLogger(FooService.class);
	
	@PersistenceContext(unitName="H2DS")
	EntityManager entityManager;
	
	private String value = "Hola soy Proyecto Base!!!";

	@PostConstruct
	public void init() {
		LOGGER.info("Proxy a entityManager: "+this.entityManager);
	}
	
	public String getDefaultValue() {
		return value;
	}
	
	public Boolean addFooEntity(FooEntity fooEntity) {
		try {
			this.entityManager.persist(fooEntity);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public List<FooEntity> list() {
		return this.entityManager.createQuery("FROM FooEntity").getResultList();
	}

}

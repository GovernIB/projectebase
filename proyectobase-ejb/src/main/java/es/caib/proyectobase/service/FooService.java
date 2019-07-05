package es.caib.proyectobase.service;

import es.caib.proyectobase.entity.FooEntity;
import org.slf4j.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Servicio (EJB) para realizar inserciones y consultas de prueba en BBDD
 * @author [u91310] Pedro Bauzá Mascaró
 */
@Stateless
//@RolesAllowed({"tothom", "PB_ADMIN"})
public class FooService implements FooServiceInterface {

	@Inject
	private Logger log;
	
	@PersistenceContext
	EntityManager entityManager;
	
	private String value = "Hola soy Proyecto Base!!!";

	@PostConstruct
	public void init() {
		log.info("Proxy a entityManager: "+this.entityManager);
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

	/* La clausula "select" és obligatoria. Punt 4.2.1 de l'especificació JPA. */
	public List<FooEntity> list() {
		return this.entityManager.createQuery("SELECT f FROM FooEntity f", FooEntity.class).getResultList();
	}

}

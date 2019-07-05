package es.caib.proyectobase.ws;

import es.caib.proyectobase.entity.FooEntity;
import org.jboss.ws.api.annotation.WebContext;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
@WebService(endpointInterface = "es.caib.proyectobase.ws.FooWebServiceInterface")
@WebContext(contextRoot="/ProyectoBaseServices")
public class FooWebService {

	@PersistenceContext
	EntityManager entityManager;
	
	@WebResult(name="FoosRecuperados")
	@WebMethod(operationName="recuperarFoos")
	public List<FooEntity> getFoos() {
		return this.entityManager.createQuery("select f FROM FooEntity f", FooEntity.class).getResultList();
	}
	
	@WebResult(name="FooAgregado")
	@WebMethod(operationName="agregarFoo")
	public FooEntity addFoo(String value) {
		FooEntity fooEntity = new FooEntity();
		fooEntity.setValue(value);
		try {
			this.entityManager.persist(fooEntity);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return fooEntity;
	}
	
}

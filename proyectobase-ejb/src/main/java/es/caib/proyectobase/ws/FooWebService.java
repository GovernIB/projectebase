package es.caib.proyectobase.ws;

import java.util.List;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.wsf.spi.annotation.WebContext;

import es.caib.proyectobase.entity.FooEntity;

@Stateless
@WebService(endpointInterface = "es.caib.proyectobase.ws.FooWebServiceInterface")
@WebContext(contextRoot="/ProyectoBaseServices")
public class FooWebService {

	@PersistenceContext(unitName="proyectobasePU")
	EntityManager entityManager;
	
	@WebResult(name="FoosRecuperados")
	@WebMethod(operationName="recuperarFoos")
	public List<FooEntity> getFoos() {
		return this.entityManager.createQuery("FROM FooEntity").getResultList();
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

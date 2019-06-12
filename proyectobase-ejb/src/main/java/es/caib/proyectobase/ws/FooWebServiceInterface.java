package es.caib.proyectobase.ws;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import es.caib.proyectobase.entity.FooEntity;

@WebService
@SOAPBinding(style = Style.RPC)
public interface FooWebServiceInterface {
	
	@WebResult(name="FoosRecuperados")
	@WebMethod(operationName="recuperarFoos")
	List<FooEntity> getFoos();
	
	@WebResult(name="FooAgregado")
	@WebMethod(operationName="agregarFoo")
	FooEntity addFoo(@WebParam(name="value") String value);
	
}

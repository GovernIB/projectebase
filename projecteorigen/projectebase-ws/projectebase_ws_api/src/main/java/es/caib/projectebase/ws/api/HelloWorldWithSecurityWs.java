package es.caib.projectebase.ws.api;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * This class was generated by Apache CXF 3.2.12
 * 2020-03-04T14:03:39.219+01:00
 * Generated source version: 3.2.12
 *
 */
@WebService(targetNamespace = "http://impl.ws.projectebase.caib.es/", name = "HelloWorldWithSecurityWs")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface HelloWorldWithSecurityWs {

    @WebMethod
    @WebResult(name = "return", targetNamespace = "http://impl.ws.projectebase.caib.es/", partName = "return")
    public java.lang.String echo(
        @WebParam(partName = "echo", name = "echo")
        java.lang.String echo
    );

    @WebMethod
    @WebResult(name = "return", targetNamespace = "http://impl.ws.projectebase.caib.es/", partName = "return")
    public java.lang.String getVersion();
}

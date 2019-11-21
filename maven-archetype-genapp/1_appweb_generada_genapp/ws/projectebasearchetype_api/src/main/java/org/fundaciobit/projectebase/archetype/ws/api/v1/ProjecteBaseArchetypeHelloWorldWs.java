package org.fundaciobit.projectebase.archetype.ws.api.v1;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * This class was generated by Apache CXF 2.2.12-patch-04
 * Fri Jul 04 08:48:34 CEST 2014
 * Generated source version: 2.2.12-patch-04
 * 
 */
 
@WebService(targetNamespace = "http://impl.v1.ws.archetype.projectebase.fundaciobit.org/", name = "ProjecteBaseArchetypeHelloWorldWs")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface ProjecteBaseArchetypeHelloWorldWs {

    @WebResult(name = "return", targetNamespace = "http://impl.v1.ws.archetype.projectebase.fundaciobit.org/", partName = "return")
    @WebMethod
    public int getVersionWs();

    @WebResult(name = "return", targetNamespace = "http://impl.v1.ws.archetype.projectebase.fundaciobit.org/", partName = "return")
    @WebMethod
    public java.lang.String getVersion();

    @WebResult(name = "return", targetNamespace = "http://impl.v1.ws.archetype.projectebase.fundaciobit.org/", partName = "return")
    @WebMethod
    public java.lang.String echo(
        @WebParam(partName = "echo", name = "echo")
        java.lang.String echo
    );
}

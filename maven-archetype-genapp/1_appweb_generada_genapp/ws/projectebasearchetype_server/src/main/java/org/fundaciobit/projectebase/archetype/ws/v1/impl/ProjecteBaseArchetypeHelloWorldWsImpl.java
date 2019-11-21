package org.fundaciobit.projectebase.archetype.ws.v1.impl;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.validation.constraints.Null;

import org.jboss.wsf.spi.annotation.TransportGuarantee;
import org.jboss.wsf.spi.annotation.WebContext;

import org.fundaciobit.projectebase.archetype.ws.utils.BaseWsImpl;

/**
 * 
 * @author anadal
 * 
 */
@Stateless(name= ProjecteBaseArchetypeHelloWorldWsImpl.NAME + "Ejb")
@SOAPBinding(style = SOAPBinding.Style.RPC)
@WebService
(
    name=ProjecteBaseArchetypeHelloWorldWsImpl.NAME_WS,
    portName = ProjecteBaseArchetypeHelloWorldWsImpl.NAME_WS,
    serviceName = ProjecteBaseArchetypeHelloWorldWsImpl.NAME_WS + "Service"
)
@WebContext
(
    contextRoot="/projectebasearchetype/ws",
    urlPattern="/v1/" + ProjecteBaseArchetypeHelloWorldWsImpl.NAME,    
    transportGuarantee= TransportGuarantee.NONE,
    secureWSDLAccess = false
)
public class ProjecteBaseArchetypeHelloWorldWsImpl extends BaseWsImpl {
  
  public static final String NAME = "ProjecteBaseArchetypeHelloWorld";
  
  public static final String NAME_WS = NAME + "Ws";
  
  @WebMethod
  public String echo(@WebParam (name ="echo") @Null String echo) {
    log.info("ProjecteBaseArchetypeHelloWorldWsImpl :: echo = " + echo);
    return echo;
  }

}

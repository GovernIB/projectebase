package org.fundaciobit.projectebase.archetype.ws.v1.impl;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.WebServiceContext;

import org.jboss.ejb3.annotation.SecurityDomain;
import org.jboss.wsf.spi.annotation.TransportGuarantee;
import org.jboss.wsf.spi.annotation.WebContext;

import javax.validation.constraints.Null;

import org.fundaciobit.genapp.common.ws.WsI18NException;
import org.fundaciobit.genapp.common.ws.WsValidationException;

import org.fundaciobit.projectebase.archetype.utils.Constants;
import org.fundaciobit.projectebase.archetype.ws.utils.AuthenticatedBaseWsImpl;


/**
 * 
 * @author anadal
 * 
 */
@SecurityDomain(Constants.SECURITY_DOMAIN)
@Stateless(name = ProjecteBaseArchetypeHelloWorldWithSecurityWsImpl.NAME + "Ejb")
@RolesAllowed({ Constants.PBS_USER, Constants.PBS_ADMIN })
@SOAPBinding(style = SOAPBinding.Style.RPC)
@org.apache.cxf.interceptor.InInterceptors(interceptors = { "org.fundaciobit.projectebase.archetype.ws.utils.ProjecteBaseArchetypeInInterceptor" })
@org.apache.cxf.interceptor.InFaultInterceptors(interceptors = { "org.fundaciobit.projectebase.archetype.ws.utils.ProjecteBaseArchetypeInInterceptor" })
@WebService(name = ProjecteBaseArchetypeHelloWorldWithSecurityWsImpl.NAME_WS, portName = ProjecteBaseArchetypeHelloWorldWithSecurityWsImpl.NAME_WS, serviceName = ProjecteBaseArchetypeHelloWorldWithSecurityWsImpl.NAME_WS
    + "Service")
@WebContext(contextRoot = "/projectebasearchetype/ws", urlPattern = "/v1/"
    + ProjecteBaseArchetypeHelloWorldWithSecurityWsImpl.NAME, transportGuarantee = TransportGuarantee.NONE, secureWSDLAccess = false, authMethod = "WSBASIC")
public class ProjecteBaseArchetypeHelloWorldWithSecurityWsImpl extends AuthenticatedBaseWsImpl {

  public static final String NAME = "ProjecteBaseArchetypeHelloWorldWithSecurity";

  public static final String NAME_WS = NAME + "Ws";
  

  @Resource
  private WebServiceContext wsContext;

  @RolesAllowed({ PBS_ADMIN, PBS_USER})
  @WebMethod
  public String echo(@WebParam (name ="echo") @Null String echo) throws WsValidationException, WsI18NException, Throwable {

  	/* Quan hi hagi fixers
  	 FitxerPA.enableEncryptedFileIDGeneration();
  	try {
  	*/
    log.info("ProjecteBaseArchetypeHelloWorldWithSecurityWsImpl :: echo = " + echo);
    return "USER: " + wsContext.getUserPrincipal().getName() + " | ECHO: " + echo;
  	/* Quan hi hagi fixers
  	} finally {
  	  FitxerJPA.disableEncryptedFileIDGeneration();
  	}      
  	*/
  }
  

}

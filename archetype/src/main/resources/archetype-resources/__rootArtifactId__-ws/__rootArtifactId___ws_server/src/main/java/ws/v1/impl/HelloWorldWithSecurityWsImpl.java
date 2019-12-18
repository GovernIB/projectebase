#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.ws.v1.impl;

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

import ${package}.commons.utils.Constants;
import ${package}.ws.utils.AuthenticatedBaseWsImpl;

/**
 * 
 * @author anadal
 * 
 */
@Stateless(name = HelloWorldWithSecurityWsImpl.NAME + "Ejb")
@RolesAllowed({ Constants.${prefixuppercase}_USER, Constants.${prefixuppercase}_ADMIN })
@SOAPBinding(style = SOAPBinding.Style.RPC)
@org.apache.cxf.interceptor.InInterceptors(interceptors = {
    "${package}.ws.utils.WsInInterceptor" })
@org.apache.cxf.interceptor.InFaultInterceptors(interceptors = {
    "${package}.ws.utils.WsOutInterceptor" })
@WebService(
    name = HelloWorldWithSecurityWsImpl.NAME_WS,
    portName = HelloWorldWithSecurityWsImpl.NAME_WS,
    serviceName = HelloWorldWithSecurityWsImpl.NAME_WS + "Service")
@WebContext(contextRoot = "/${artifactId}/ws/v1/" + HelloWorldWithSecurityWsImpl.NAME,
    //urlPattern = "/v1/" + HelloWorldWithSecurityWsImpl.NAME,
    transportGuarantee = TransportGuarantee.NONE,
    secureWSDLAccess = false,
    authMethod = "BASIC")
public class HelloWorldWithSecurityWsImpl extends AuthenticatedBaseWsImpl
    implements Constants {

  public static final String NAME = "HelloWorldWithSecurity";

  public static final String NAME_WS = NAME + "Ws";

  @Resource
  private WebServiceContext wsContext;

  @RolesAllowed({ ${prefixuppercase}_ADMIN, ${prefixuppercase}_USER })
  @WebMethod
  public String echo(@WebParam(name = "echo") @Null String echo)
      throws WsValidationException, WsI18NException, Throwable {

    /*
     * Quan hi hagi fixers FitxerPA.enableEncryptedFileIDGeneration(); try {
     */
    log.info("HelloWorldWithSecurityWsImpl :: echo = " + echo);
    return "USER: " + wsContext.getUserPrincipal().getName() + " | ECHO: " + echo;
    /*
     * Quan hi hagi fixers } finally { FitxerJPA.disableEncryptedFileIDGeneration(); }
     */
  }

}

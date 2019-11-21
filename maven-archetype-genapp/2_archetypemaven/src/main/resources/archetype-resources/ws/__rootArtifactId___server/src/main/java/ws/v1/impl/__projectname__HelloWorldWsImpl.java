#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.ws.v1.impl;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.validation.constraints.Null;

import org.jboss.wsf.spi.annotation.TransportGuarantee;
import org.jboss.wsf.spi.annotation.WebContext;

import ${package}.ws.utils.BaseWsImpl;

/**
 * 
 * @author anadal
 * 
 */
@Stateless(name= ${projectname}HelloWorldWsImpl.NAME + "Ejb")
@SOAPBinding(style = SOAPBinding.Style.RPC)
@WebService
(
    name=${projectname}HelloWorldWsImpl.NAME_WS,
    portName = ${projectname}HelloWorldWsImpl.NAME_WS,
    serviceName = ${projectname}HelloWorldWsImpl.NAME_WS + "Service"
)
@WebContext
(
    contextRoot="/${artifactId}/ws",
    urlPattern="/v1/" + ${projectname}HelloWorldWsImpl.NAME,    
    transportGuarantee= TransportGuarantee.NONE,
    secureWSDLAccess = false
)
public class ${projectname}HelloWorldWsImpl extends BaseWsImpl {
  
  public static final String NAME = "${projectname}HelloWorld";
  
  public static final String NAME_WS = NAME + "Ws";
  
  @WebMethod
  public String echo(@WebParam (name ="echo") @Null String echo) {
    log.info("${projectname}HelloWorldWsImpl :: echo = " + echo);
    return echo;
  }

}

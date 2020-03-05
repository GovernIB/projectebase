#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.ws.impl;

import ${package}.ws.utils.BaseWsImpl;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.WebServiceContext;

import static ${package}.commons.utils.Constants.${prefixuppercase}_ADMIN;
import static ${package}.commons.utils.Constants.${prefixuppercase}_USER;

/**
 * @author anadal
 */
@Stateless(name = HelloWorldWithSecurityWsImpl.NAME + "Ejb")
@RolesAllowed({${prefixuppercase}_USER, ${prefixuppercase}_ADMIN})
@SOAPBinding(style = SOAPBinding.Style.RPC)
@org.apache.cxf.interceptor.InInterceptors(interceptors = {
        "${package}.ws.utils.WsInInterceptor"})
@org.apache.cxf.interceptor.InFaultInterceptors(interceptors = {
        "${package}.ws.utils.WsOutInterceptor"})
@WebService(
        name = HelloWorldWithSecurityWsImpl.NAME_WS,
        portName = HelloWorldWithSecurityWsImpl.NAME_WS,
        serviceName = HelloWorldWithSecurityWsImpl.NAME_WS + "Service")
public class HelloWorldWithSecurityWsImpl extends BaseWsImpl {

    public static final String NAME = "HelloWorldWithSecurity";

    public static final String NAME_WS = NAME + "Ws";

    @Resource
    private WebServiceContext wsContext;

    @RolesAllowed({${prefixuppercase}_ADMIN, ${prefixuppercase}_USER})
    @WebMethod
    public String echo(@WebParam(name = "echo") String echo) {
        log.info("HelloWorldWithSecurityWsImpl :: echo = " + echo);
        return "USER: " + wsContext.getUserPrincipal().getName() + " | ECHO: " + echo;
    }

}

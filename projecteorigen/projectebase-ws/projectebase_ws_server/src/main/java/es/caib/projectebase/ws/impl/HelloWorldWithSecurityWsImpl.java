package es.caib.projectebase.ws.impl;

import es.caib.projectebase.ws.utils.BaseWsImpl;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.WebServiceContext;

import static es.caib.projectebase.commons.utils.Constants.PBS_ADMIN;
import static es.caib.projectebase.commons.utils.Constants.PBS_USER;

/**
 * @author anadal
 */
@Stateless(name = HelloWorldWithSecurityWsImpl.NAME + "Ejb")
@RolesAllowed({PBS_USER, PBS_ADMIN})
@SOAPBinding(style = SOAPBinding.Style.RPC)
@org.apache.cxf.interceptor.InInterceptors(interceptors = {
        "es.caib.projectebase.ws.utils.WsInInterceptor"})
@org.apache.cxf.interceptor.InFaultInterceptors(interceptors = {
        "es.caib.projectebase.ws.utils.WsOutInterceptor"})
@WebService(
        name = HelloWorldWithSecurityWsImpl.NAME_WS,
        portName = HelloWorldWithSecurityWsImpl.NAME_WS,
        serviceName = HelloWorldWithSecurityWsImpl.NAME_WS + "Service")
public class HelloWorldWithSecurityWsImpl extends BaseWsImpl {

    public static final String NAME = "HelloWorldWithSecurity";

    public static final String NAME_WS = NAME + "Ws";

    @Resource
    private WebServiceContext wsContext;

    @RolesAllowed({PBS_ADMIN, PBS_USER})
    @WebMethod
    public String echo(@WebParam(name = "echo") String echo) {

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

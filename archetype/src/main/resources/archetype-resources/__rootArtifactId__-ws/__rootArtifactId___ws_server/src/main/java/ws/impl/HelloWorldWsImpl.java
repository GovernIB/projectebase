#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.ws.impl;

import ${package}.ws.utils.BaseWsImpl;
import ${package}.ws.utils.WsI18NException;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * @author anadal
 */
@Stateless(name = HelloWorldWsImpl.NAME + "Ejb")
@SOAPBinding(style = SOAPBinding.Style.RPC)
@WebService(
        name = HelloWorldWsImpl.NAME_WS,
        portName = HelloWorldWsImpl.NAME_WS,
        serviceName = HelloWorldWsImpl.NAME_WS + "Service")
public class HelloWorldWsImpl extends BaseWsImpl {

    public static final String NAME = "HelloWorld";

    public static final String NAME_WS = NAME + "Ws";

    @WebMethod
    public String echo(@WebParam(name = "echo") String echo) throws WsI18NException {
        log.info("HelloWorldWsImpl :: echo = " + echo);
        return echo;
    }

}

#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.ws.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;


/**
 * 
 * @author anadal
 */
public class WsOutInterceptor extends AbstractPhaseInterceptor<Message> {

  protected final Logger log = LoggerFactory.getLogger(getClass());

  public WsOutInterceptor() {
    // Veure https://cxf.apache.org/docs/interceptors.html
    super(Phase.SEND);
  }

  
  public void handleMessage(Message message) throws Fault {
    //UsuariAplicacioCache.remove();
  }


}
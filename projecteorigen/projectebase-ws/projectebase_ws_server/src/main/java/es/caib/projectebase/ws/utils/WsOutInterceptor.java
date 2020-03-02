package es.caib.projectebase.ws.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;


/**
 * Intercepta les cridades sortints a un WS. Veure: Veure https://cxf.apache.org/docs/interceptors.html
 *
 * @author anadal
 */
public class WsOutInterceptor extends AbstractPhaseInterceptor<Message> {

    private static final Logger LOG = LoggerFactory.getLogger(WsOutInterceptor.class);

    public WsOutInterceptor() {
        super(Phase.SEND);
    }

    @Override
    public void handleMessage(Message message) throws Fault {
        LOG.debug("WsInInterceptor::handleMessage() =>  Thread = " + Thread.currentThread().getId());
    }
}
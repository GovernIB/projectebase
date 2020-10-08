#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.sistra2.integracio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Set;

public class LoggingHandler implements SOAPHandler<SOAPMessageContext> {

    private static final Logger LOG = LoggerFactory.getLogger(LoggingHandler.class);

    public Set<QName> getHeaders() {
        return null;
    }

    public boolean handleMessage(SOAPMessageContext smc) {
        logMessageContext(smc);
        return true;
    }

    public boolean handleFault(SOAPMessageContext smc) {
        logMessageContext(smc);
        return true;
    }

    public void close(MessageContext messageContext) {
    }

    private void logMessageContext(SOAPMessageContext smc) {
        if (LOG.isDebugEnabled()) {
            try {
                QName operation = (QName) smc.get(MessageContext.WSDL_OPERATION);
                String message = toString(smc.getMessage());
                Boolean outboundProperty = (Boolean) smc.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
                if (outboundProperty) {
                    LOG.debug("${symbol_escape}n{} <-- outbound message:${symbol_escape}n{}", operation, message);
                } else {
                    LOG.debug("${symbol_escape}n{} --> inbound message:${symbol_escape}n{}", operation, message);
                }
            } catch (IOException|SOAPException exception) {
                LOG.error("Error imprimint missatge soap", exception);
            }
        }
    }

    private String toString(SOAPMessage message) throws IOException, SOAPException {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            message.writeTo(baos);
            return baos.toString(StandardCharsets.UTF_8);
        }
    }
}
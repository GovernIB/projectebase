
package es.caib.projectebase.sistra2.backoffice.impl;

import es.caib.projectebase.sistra2.backoffice.api.AnotacioRegistreId;
import es.caib.projectebase.sistra2.backoffice.api.Backoffice;
import es.caib.projectebase.sistra2.converter.AnotacioRegistreConverter;
import es.caib.projectebase.sistra2.persistence.AnotacioInbox;
import es.caib.projectebase.sistra2.repository.AnotacioInboxRepository;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.jws.HandlerChain;
import javax.jws.WebService;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFactory;
import javax.xml.soap.SOAPFault;
import javax.xml.ws.soap.SOAPFaultException;
import java.util.List;

/**
 * Implementació del backoffice de distribució.
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)

@WebService(portName = "BackofficeServicePort",
        serviceName = "BackofficeService",
        targetNamespace = "http://www.caib.es/distribucio/ws/backoffice",
        wsdlLocation = "/wsdl/backoffice.wsdl",
        endpointInterface = "es.caib.projectebase.sistra2.backoffice.api.Backoffice")
@HandlerChain(file = "/handlers/backoffice-handlers.xml")
public class BackofficeServicePort implements Backoffice {

    private static final String SERVER_FAULT_CODE = "Server";
    private static final String CLIENT_FAULT_CODE = "Client";

    @Inject
    private AnotacioInboxRepository anotacioInboxRepository;

    @Inject
    private AnotacioRegistreConverter anotacioRegistreConverter;

    /**
     * Reb una llista d'anotacions pendents de processar
     * @param ids llista d'identificadors d'anotació.
     */
    public void comunicarAnotacionsPendents(List<AnotacioRegistreId> ids) {
        List<AnotacioInbox> anotacioList = anotacioRegistreConverter.toAnotacioInbox(ids);
        anotacioInboxRepository.createBulk(anotacioList);
    }

    private void throwFault(String message, String faultCode) {
        try {
            SOAPFactory soapFactory = SOAPFactory.newInstance();
            SOAPFault fault = soapFactory.createFault(message, new QName(faultCode));
            throw new SOAPFaultException(fault);
        } catch (SOAPException exception) {
            throw new RuntimeException(exception);
        }
    }
}

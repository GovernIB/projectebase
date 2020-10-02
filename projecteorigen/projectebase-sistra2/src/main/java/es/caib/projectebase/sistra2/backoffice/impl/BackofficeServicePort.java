
package es.caib.projectebase.sistra2.backoffice.impl;

import es.caib.projectebase.sistra2.backoffice.api.AnotacioRegistreId;
import es.caib.projectebase.sistra2.backoffice.api.Backoffice;
import es.caib.projectebase.sistra2.facade.api.AnotacioFacadeService;
import es.caib.projectebase.sistra2.facade.exception.AnotacioIdInvalidException;

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
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)

@WebService(portName = "BackofficeServicePort",
        serviceName = "BackofficeService",
        targetNamespace = "http://www.caib.es/distribucio/ws/backoffice",
        wsdlLocation = "/wsdl/backoffice.wsdl",
        endpointInterface = "es.caib.projectebase.sistra2.backoffice.api.Backoffice")
@HandlerChain(file = "/handlers/backoffice-handlers.xml")
public class BackofficeServicePort implements Backoffice {

    public BackofficeServicePort() {
    }

    @Inject
    private AnotacioFacadeService anotacioFacadeService;

    /**
     * Reb una llista d'anotacions pendents de processar
     * @param ids llista d'identificadors d'anotació.
     */
    public void comunicarAnotacionsPendents(List<AnotacioRegistreId> ids) {
        try {
            anotacioFacadeService.rebreAnotacions(ids);
        } catch (AnotacioIdInvalidException exception) {
            throwClientFault(exception.getMessage());
        }
    }

    /**
     * Llança una soap:Fault amb el missatge indicant al faultcode que és un error del client.
     * @param message missatge d'error que anirà dins el faultstring
     */
    private void throwClientFault(String message) {
        try {
            SOAPFactory soapFactory = SOAPFactory.newInstance();
            SOAPFault fault = soapFactory.createFault(message, new QName("Client"));
            throw new SOAPFaultException(fault);
        } catch (SOAPException exception) {
            throw new RuntimeException(exception);
        }
    }
}

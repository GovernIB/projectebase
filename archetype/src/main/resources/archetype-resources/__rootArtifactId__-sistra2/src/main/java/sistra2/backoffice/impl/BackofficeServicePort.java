#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )

package ${package}.sistra2.backoffice.impl;

import ${package}.sistra2.backoffice.api.AnotacioRegistreId;
import ${package}.sistra2.backoffice.api.Backoffice;
import ${package}.sistra2.converter.AnotacioInboxConverter;
import ${package}.sistra2.persistence.AnotacioInbox;
import ${package}.sistra2.repository.AnotacioInboxRepository;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.jws.HandlerChain;
import javax.jws.WebService;
import java.util.List;

/**
 * Implementació del backoffice de distribució.
 *
 * @author areus
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)

@WebService(portName = "BackofficeServicePort",
        serviceName = "BackofficeService",
        targetNamespace = "http://www.caib.es/distribucio/ws/backoffice",
        wsdlLocation = "/wsdl/backoffice.wsdl",
        endpointInterface = "${package}.sistra2.backoffice.api.Backoffice")

// registrar els handlers per el servidor
@HandlerChain(file = "/handlers/backoffice-handlers.xml")

public class BackofficeServicePort implements Backoffice {

    @Inject
    private AnotacioInboxRepository anotacioInboxRepository;

    @Inject
    private AnotacioInboxConverter anotacioInboxConverter;

    /**
     * Reb una llista d'anotacions pendents de processar i les emmagatzema a la base de dades.
     * @param ids llista d'identificadors d'anotació.
     */
    public void comunicarAnotacionsPendents(List<AnotacioRegistreId> ids) {
        List<AnotacioInbox> anotacioList = anotacioInboxConverter.toAnotacioInbox(ids);
        anotacioInboxRepository.createBulk(anotacioList);
    }
}

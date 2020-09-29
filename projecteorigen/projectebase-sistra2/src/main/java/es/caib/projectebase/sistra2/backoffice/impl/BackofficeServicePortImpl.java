
package es.caib.projectebase.sistra2.backoffice.impl;

import es.caib.projectebase.sistra2.backoffice.api.AnotacioRegistreId;
import es.caib.projectebase.sistra2.backoffice.api.Backoffice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jws.WebService;
import javax.xml.ws.BindingType;
import java.util.List;


/**
 * Implementació del backoffice de distribució
 */
@WebService(portName = "BackofficeServicePort",
        serviceName = "BackofficeService",
        targetNamespace = "http://www.caib.es/distribucio/ws/backoffice",
        wsdlLocation = "/wsdl/backoffice.wsdl",
        endpointInterface = "es.caib.projectebase.sistra2.backoffice.Backoffice")
@BindingType("http://schemas.xmlsoap.org/wsdl/soap/http")
public class BackofficeServicePortImpl implements Backoffice {

    private static final Logger LOG = LoggerFactory.getLogger(BackofficeServicePortImpl.class);

    public BackofficeServicePortImpl() {
    }

    /**
     * Reb una llista d'anotacions pendents de processar
     * @param ids llista d'identificadors d'anotació.
     */
    public void comunicarAnotacionsPendents(List<AnotacioRegistreId> ids) {
        for (AnotacioRegistreId anotacioId : ids) {
            LOG.info("id: {}, Clau: {}", anotacioId.getClauAcces(), anotacioId.getIndetificador());
        }
    }

}

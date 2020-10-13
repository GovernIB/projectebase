#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.sistra2.test.integracio;

import es.caib.distribucio.ws.backofficeintegracio.AnotacioRegistreEntrada;
import es.caib.distribucio.ws.backofficeintegracio.AnotacioRegistreId;
import es.caib.distribucio.ws.backofficeintegracio.BackofficeIntegracio;
import es.caib.distribucio.ws.backofficeintegracio.Estat;

import javax.jws.WebService;
import javax.xml.ws.BindingType;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * servidor del backoffice d'integració mock
 */
@WebService(
        portName = "BackofficeIntegracioServicePort",
        serviceName = "BackofficeIntegracioService",
        targetNamespace = "http://www.caib.es/distribucio/ws/backofficeIntegracio",
        wsdlLocation = "/wsdl/backofficeIntegracio.wsdl",
        endpointInterface = "es.caib.distribucio.ws.backofficeintegracio.BackofficeIntegracio")
@BindingType("http://schemas.xmlsoap.org/wsdl/soap/http")
public class BackofficeIntegracioServicePortImpl implements BackofficeIntegracio {

    private final CountDownLatch countDownLatch;

    public BackofficeIntegracioServicePortImpl(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    private static final AtomicInteger CONSULTA_COUNTER = new AtomicInteger(0);
    private static final AtomicInteger ESTAT_COUNTER = new AtomicInteger(0);

    public AnotacioRegistreEntrada consulta(AnotacioRegistreId id) {
        System.out.println("cosulta: " + id.getIndetificador() +
                ". Total rebuts: " + CONSULTA_COUNTER.incrementAndGet());
        AnotacioRegistreEntrada registreEntrada = new AnotacioRegistreEntrada();
        registreEntrada.setIdentificador(id.getIndetificador());
        registreEntrada.setDestiCodi("prova");
        registreEntrada.setDestiDescripcio("descripcio");
        registreEntrada.setObservacions("observacions");

        return registreEntrada;
    }

    public void canviEstat(AnotacioRegistreId id, Estat estat, String observacions) {
        countDownLatch.countDown();
        System.out.println("canviEStat: " + id.getIndetificador() +
                ", Estat: " + estat +
                ". Total rebuts: " + ESTAT_COUNTER.incrementAndGet());
    }

}

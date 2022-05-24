package es.caib.projectebase.distribucio.integracio;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import es.caib.projectebase.distribucio.backoffice.api.AnotacioRegistreId;
import es.caib.projectebase.distribucio.backoffice.api.Backoffice;
import es.caib.projectebase.distribucio.backoffice.api.BackofficeService;

import javax.xml.ws.BindingProvider;
import javax.xml.ws.Endpoint;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;

@Ignore
public class BackofficeIntegracioIT {

    private static final String BACKOFFICE_ADRESS =
            "http://localhost:8080/projectebase-distribucio/BackofficeService/BackofficeServicePort";

    private static Backoffice backoffice;

    private static final int NOMBRE_ANOTACIONS = 5;

    private static final CountDownLatch COUNT_DOWN_LATCH = new CountDownLatch(NOMBRE_ANOTACIONS * 2);

    @BeforeClass
    public static void beforeClass() {
        Endpoint.publish("http://localhost:8888/distribucio/ws/backofficeIntegracio",
                new BackofficeIntegracioServicePortImpl(COUNT_DOWN_LATCH));

        backoffice = new BackofficeService().getBackofficeServicePort();
        Map<String, Object> requestContext = ((BindingProvider) backoffice).getRequestContext();
        requestContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, BACKOFFICE_ADRESS);
    }

    @Test
    public void testBackoffice() {

        List<AnotacioRegistreId> anotacions = new ArrayList<>();

        for (int i = 0; i < NOMBRE_ANOTACIONS; i++) {
            AnotacioRegistreId anotacio = new AnotacioRegistreId();
            anotacio.setIndetificador(UUID.randomUUID().toString());
            anotacio.setClauAcces("XXX");
            anotacions.add(anotacio);
        }

        backoffice.comunicarAnotacionsPendents(anotacions);

        try {
            COUNT_DOWN_LATCH.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

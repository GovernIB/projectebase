package es.caib.projectebase.sistra2.test.integracio;

import es.caib.projectebase.sistra2.backoffice.api.AnotacioRegistreId;
import es.caib.projectebase.sistra2.backoffice.api.Backoffice;
import es.caib.projectebase.sistra2.backoffice.api.BackofficeService;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.xml.ws.BindingProvider;
import javax.xml.ws.Endpoint;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class TestIntegracio {

    private static final String BACKOFFICE_ADRESS =
            "http://localhost:8080/projectebase-sistra2/BackofficeService/BackofficeServicePort";

    private static Backoffice backoffice;

    @BeforeClass
    public static void beforeClass() {
        Endpoint.publish("http://localhost:8888/distribucio/ws/backofficeIntegracio",
                new BackofficeIntegracioServicePortImpl());

        backoffice = new BackofficeService().getBackofficeServicePort();
        Map<String, Object> requestContext = ((BindingProvider) backoffice).getRequestContext();
        requestContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, BACKOFFICE_ADRESS);
    }

    @Test
    public void testBackoffice() {

        List<AnotacioRegistreId> anotacions = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            AnotacioRegistreId anotacio = new AnotacioRegistreId();
            anotacio.setIndetificador(UUID.randomUUID().toString());
            anotacio.setClauAcces("XXX");
            anotacions.add(anotacio);
        }

        backoffice.comunicarAnotacionsPendents(anotacions);

        try {
            Thread.sleep(300_000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    }
}

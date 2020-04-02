package es.caib.projectebase.registre;

import es.caib.regweb3.ws.api.v3.RegWebAsientoRegistralWs;
import es.caib.regweb3.ws.api.v3.RegWebAsientoRegistralWsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.faces.annotation.FacesConfig;
import javax.xml.ws.BindingProvider;
import java.net.URL;
import java.util.Map;

import static javax.faces.annotation.FacesConfig.Version.JSF_2_3;

/**
 * Bean que configura i instancia el client de registre.
 *
 * @author areus
 */
@FacesConfig(version = JSF_2_3)
public class ClientRegistreProducer {

    private static final Logger LOG = LoggerFactory.getLogger(ClientRegistreProducer.class);

    /**
     * Instancia el client de registre d'assentaments regsitrals. Permet que s'injecti onsevulla utilitzant CDI.
     * El definim com a {@link ApplicationScoped} perquè només hi hagi una instància per aplicació.
     *
     * @param configuracio la configuració de l'aplicació. És injectat automàticament per CDI
     * @return instància del client de registre.
     */
    @Produces
    @ApplicationScoped
    public RegWebAsientoRegistralWs getRegWebAsientoRegistralWs(Configuracio configuracio) {
        LOG.info("getRegWebAsientoRegistralWs");

        /* Accedir al recurs WSDL de dins el JAR evita una cridada HTTP per carregar-lo */
        URL wsdl = this.getClass().getResource("/wsdl/RegWebAsientoRegistral.wsdl");
        /*
        URL wsdl;
        try {
            wsdl = new URL(endpoint + "?wsdl");
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error accendint a WSDL de l'api de Registre", e);
        }*/

        RegWebAsientoRegistralWsService asientoService = new RegWebAsientoRegistralWsService(wsdl);
        RegWebAsientoRegistralWs client = asientoService.getRegWebAsientoRegistralWs();

        Map<String, Object> reqContext = ((BindingProvider) client).getRequestContext();
        reqContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, configuracio.getEndpoint());
        reqContext.put(BindingProvider.USERNAME_PROPERTY, configuracio.getUsername());
        reqContext.put(BindingProvider.PASSWORD_PROPERTY, configuracio.getPassword());

        LOG.info("Client creat");

        return client;
    }
}

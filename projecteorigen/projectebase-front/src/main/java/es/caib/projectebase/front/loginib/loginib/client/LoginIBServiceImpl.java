package es.caib.projectebase.front.loginib.loginib.client;

import es.caib.loginib.rest.api.v1.RDatosAutenticacion;
import es.caib.loginib.rest.api.v1.RLoginParams;
import es.caib.loginib.rest.api.v1.RLogoutParams;
import es.caib.projectebase.commons.rest.client.BasicAuthenticator;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

/**
 * Implementació del servei per accedir a LoginIB mitjançant un client REST
 *
 * @author areus
 */
@ApplicationScoped
public class LoginIBServiceImpl implements LoginIBService {

    private Client client;

    @Inject
    private Configuracio configuracio;

    @PostConstruct
    protected void init() {
        client = ClientBuilder.newClient()
                .register(new BasicAuthenticator(configuracio.getUsuari(), configuracio.getSecret()));
    }

    @Override
    public String initateLogin(String idioma, String urlCallback, String urlCallbackError) {
        RLoginParams params = new RLoginParams();
        params.setAplicacion(configuracio.getAplicacion());
        params.setEntidad(configuracio.getEntidad());
        params.setIdioma(idioma);
        params.setForzarAutenticacion(false);
        params.setQaa(configuracio.getQaa());
        params.setMetodosAutenticacion(configuracio.getMetodosAutenticacion());
        params.setUrlCallback(urlCallback);
        params.setUrlCallbackError(urlCallbackError);

        return client.target(configuracio.getEndpoint() + "/login")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.json(params), String.class);
    }

    @Override
    public String initiateLogout(String idioma, String urlCallback) {
        RLogoutParams params = new RLogoutParams();
        params.setAplicacion(configuracio.getAplicacion());
        params.setEntidad(configuracio.getEntidad());
        params.setIdioma(idioma);
        params.setUrlCallback(urlCallback);

        return client.target(configuracio.getEndpoint() + "/logout")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.json(params), String.class);
    }

    @Override
    public RDatosAutenticacion checkTicket(String ticket) {
        return client.target(configuracio.getEndpoint() + "/ticket/" + ticket)
                .request(MediaType.APPLICATION_JSON)
                .get(RDatosAutenticacion.class);
    }
}

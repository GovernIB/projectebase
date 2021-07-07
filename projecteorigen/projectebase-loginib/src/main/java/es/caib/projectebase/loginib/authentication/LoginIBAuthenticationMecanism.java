package es.caib.projectebase.loginib.authentication;

import es.caib.loginib.rest.api.v1.RDatosAutenticacion;
import es.caib.loginib.rest.api.v1.RLoginParams;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.security.enterprise.AuthenticationException;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.authentication.mechanism.http.AutoApplySession;
import javax.security.enterprise.authentication.mechanism.http.HttpAuthenticationMechanism;
import javax.security.enterprise.authentication.mechanism.http.HttpMessageContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import java.security.Principal;
import java.util.Set;

@AutoApplySession
@ApplicationScoped
public class LoginIBAuthenticationMecanism implements HttpAuthenticationMechanism {

    private static final Logger LOG = LoggerFactory.getLogger(LoginIBAuthenticationMecanism.class);

    @Inject
    @ConfigProperty(name = "es.caib.projectebase.int.loginib.endpoint")
    private String endpoint;

    @Inject
    @ConfigProperty(name = "es.caib.projectebase.int.loginib.usuari")
    private String usuari;

    @Inject
    @ConfigProperty(name = "es.caib.projectebase.int.loginib.secret")
    private String secret;

    @Inject
    @ConfigProperty(name = "es.caib.projectebase.loginib.aplicacion")
    private String aplicacion;

    @Inject
    @ConfigProperty(name = "es.caib.projectebase.loginib.entidad")
    private String entidad;

    @Inject
    @ConfigProperty(name = "es.caib.projectebase.loginib.qaa")
    private int qaa;

    @Inject
    @ConfigProperty(name = "es.caib.projectebase.loginib.metodosAutenticacion")
    private String metodosAutenticacion;

    private Client client;

    @PostConstruct
    protected void init() {
        client = ClientBuilder.newClient().register(new BasicAuthenticator(usuari, secret));
    }

    @Override
    public AuthenticationStatus validateRequest(HttpServletRequest request, HttpServletResponse response,
                                                HttpMessageContext httpMessageContext) throws AuthenticationException {
        LOG.info("validateRequest: {}", request.getServletPath());
        LOG.info("isProtected {}", httpMessageContext.isProtected());
        LOG.info("isAuthenticationRequest {}", httpMessageContext.isAuthenticationRequest());

        if (!httpMessageContext.isProtected()) {
            return httpMessageContext.doNothing();
        }

        if (request.getMethod().equals("POST") && request.getParameter("result") != null) {
            String ticket = request.getParameter("ticket");
            if (ticket == null) {
                return httpMessageContext.responseUnauthorized();
            }

            RDatosAutenticacion datos = client.target(endpoint + "/ticket/" + ticket)
                    .request(MediaType.APPLICATION_JSON)
                    .get(RDatosAutenticacion.class);

            LOG.info("Datos: {} {} {} ({})", datos.getNombre(), datos.getApellido1(), datos.getApellido2(), datos.getNif());

            Principal principal = new LoginIBPrincipal(datos);
            return httpMessageContext.notifyContainerAboutLogin(principal, Set.of("NORMAL"));
        }

        RLoginParams params = new RLoginParams();
        params.setAplicacion(aplicacion);
        params.setEntidad(entidad);
        params.setIdioma("ca");
        params.setForzarAutenticacion(false);
        params.setQaa(qaa);
        params.setMetodosAutenticacion(metodosAutenticacion);
        params.setUrlCallback(request.getRequestURL().append("?result=ok").toString());
        params.setUrlCallbackError(request.getRequestURL().append("?result=error").toString());

        String url = client.target(endpoint + "/login")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.json(params), String.class);

        return httpMessageContext.redirect(url);
    }
}

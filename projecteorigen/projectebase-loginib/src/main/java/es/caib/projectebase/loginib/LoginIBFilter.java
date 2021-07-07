package es.caib.projectebase.loginib;

import es.caib.loginib.rest.api.v1.RDatosAutenticacion;
import es.caib.projectebase.loginib.client.LoginIBService;

import javax.inject.Inject;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Filtre per protegir els recursos pels quals es requreixi autenticació.
 * A les urls que s'aplica si l'usuari no està autenticat, iniciarà l'autenticació redireccionant
 * cap a LOGINIB, i de tornada, si reb el tiquet activarà l'autenticació.
 *
 * @author areus
 */
@WebFilter("/secured/*")
public class LoginIBFilter extends HttpFilter {

	private static final long serialVersionUID = 1L;

	@Inject
    private LoginIBModel loginIBModel;

    @Inject
    private LoginIBService loginIBService;

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        // Si l'usuari ja està autenticat, no feim res.
        if (loginIBModel.isLogged()) {
            chain.doFilter(request, response);
            return;
        }

        // Els callbacks de LoginIB ens arribaran com un POST amb el paràmetre "ticket"
        if (request.getMethod().equals("POST") && request.getParameter("ticket") != null) {
            String ticket = request.getParameter("ticket");
            RDatosAutenticacion datos = loginIBService.checkTicket(ticket);
            loginIBModel.setDatos(datos);
            chain.doFilter(request, response);
            return;
        }

        String currentUrl = request.getRequestURL().toString();
        String idioma = request.getLocale().getLanguage();
        String redirectUrl = loginIBService.initateLogin(idioma, currentUrl, currentUrl);
        response.sendRedirect(redirectUrl);
    }
}

package es.caib.projectebase.front.loginib.loginib;

import es.caib.projectebase.front.loginib.loginib.client.LoginIBService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet que permet efectuar el logout.
 *
 * @author areus
 */
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Inject
    private LoginIBService loginIBService;

    @Inject
    private LoginIBModel loginIBModel;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Agafam la URL base externa:  requestURL - requestURI
        String baseUrl = request.getRequestURL()
                .substring(0, request.getRequestURL().length() - request.getRequestURI().length());
        // La URL de retonr serà la URL de la pàgina principal
        String returnUrl = baseUrl + request.getContextPath() + "/";

        String idioma = request.getLocale().getLanguage();

        String redirectUrl = loginIBService.initiateLogout(idioma, returnUrl);
        response.sendRedirect(redirectUrl);

        // Netejam les dades d'autenticació dins el model
        loginIBModel.setDatos(null);
    }
}

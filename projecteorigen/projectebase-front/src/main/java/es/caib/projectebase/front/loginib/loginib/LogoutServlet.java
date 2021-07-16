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

        // Demanam que ens redireccioni al mateix servlet quan torni
        String currentUrl = request.getRequestURL().toString();
        String idioma = request.getLocale().getLanguage();

        String redirectUrl = loginIBService.initiateLogout(idioma, currentUrl);
        response.sendRedirect(redirectUrl);

        // Netejam les dades d'autenticació dins el model
        loginIBModel.setDatos(null);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // quan rebem un post és que ens ha crdiat LoginIB retornant de fer un logut, per tant, redireccionam
        // a la pàgina principal.
        response.sendRedirect(request.getContextPath());
    }
}

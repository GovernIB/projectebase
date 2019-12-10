package cat.toni.userinfo;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.Principal;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.representations.IDToken;

import cat.toni.userinfologic.UserInfoLogicLocal;

/**
 * Servlet implementation class UserInfoServlet
 */
@WebServlet(description = "Authenticated User Information Servlet", urlPatterns = { "/userInfo" })
public class UserInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB(name="UserInfoLogic/Local")
    private UserInfoLogicLocal userInfoLogic;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserInfoServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String correu="SENSE";
		String llinatges="";
		String nom="";
		String altres="";
		String id="";
		//String llista="";
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		
		// Accedim als atributs
		final Principal userPrincipal = request.getUserPrincipal();
		if (userPrincipal instanceof KeycloakPrincipal) {
			KeycloakPrincipal<KeycloakSecurityContext> kp = (KeycloakPrincipal<KeycloakSecurityContext>) userPrincipal;
			IDToken token = kp.getKeycloakSecurityContext().getIdToken();
			Map<String,Object> otherClaims = token.getOtherClaims();
			id=token.getPreferredUsername();
			correu=token.getEmail();
			llinatges=token.getFamilyName();
			nom=token.getGivenName();
			for (String key : otherClaims.keySet()) {
				altres=altres + "<tr><td>" + key + "</td><td>" + String.valueOf(otherClaims.get(key)) + "</td></tr>";
				System.out.println(altres);
			}
			/*llista = "<tr><td>" + token.getAcr() + "</td></tr><tr><td>" + token.getClaimsLocales() + token.getExpiration() + token.getEmail() +
					 " - " + token.getGender() +  " -  <br/> " + token.getId() +  " - " + token.getIssuer() +  " - " +
					token.getMiddleName() +  " -  <br/> " + token.getNonce() +  " - " + token.getPreferredUsername() +
					  " -  <br/> " + token.getProfile() + " -  <br/> " + token.getSubject();*/
		}
		
		
		
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html><html lang=\"es\"><head> <title>GOIB\\ Usuari autenticat</title> <meta charset=\"utf-8\"> <link rel=\"stylesheet\" type=\"text/css\" href=\"userinfo.css\"></head><style> body{ background-color: #f2f2f2; } #principal{ width:500px; margin: 0 auto; margin-top: 60px; padding: 20px; font-family: arial, sans-serif; background-color: white; border-radius: 10px; } header{ border-bottom: 4px solid #c30045; margin-bottom: 20px; } #cos { margin: 20px; } table{ border-collapse: collapse; width: 100%; } td, th { border: 1px solid #dddddd; text-align: left; padding: 8px; } tr:nth-child(even) { background-color: #ffe6ee; }</style><body><section id=\"principal\"> <header id=\"cabecera\"><img src=\"img/goib.png\" alt=\"Govern de les Illes Balears\" height=\"100px\"/> </header> <section id=\"cos\"><p> Dades de l'usuari autenticat: </p> "
				+ "<table>"
				+ "<tr><th>Atribut</th><th>Valor</th></tr>"
				+ "<tr><td> id </td><td>" + id + "</td></tr>"
				+ "<tr><td> Nom </td><td>" + nom + "</td></tr>"
				+ "<tr><td> Llinatges </td><td>" + llinatges + "</td></tr>"
				+ "<tr><td> Correu </td><td>" + correu + "</td></tr>"
				+ altres
				+ "<tr><td> EJB aleatori </td><td>" + userInfoLogic.aleatori() + "</td></tr>"
				//+ "<tr><td> Tècnica </td><td>" + llista + "</td></tr>"
				+ "</table></section></section></body></html>"
				);
				
		/*out.println("<HEAD><TITLE>Informació de l'usuari</TITLE></HEAD>"+
				"<BODY><H1>Informació de l'usuari A: Buit:" + x + "</H1>"
				);
		out.append("Served at: ").append(request.getContextPath()).append("<br/>");
		out.append("id: ").append(id).append("<br/>");
		out.append("Correu: ").append(correu).append("<br/>");
		out.append("Llinatges: ").append(llinatges).append("<br/>");
		out.append("Nom: ").append(nom).append("<br/>");
		out.append("<br/> Llista: -> ").append(llista).append("<br/>");
		out.append(altres);
		
		// ACCÉS PER EJB
		out.append("Número aleatòri generat per EJB: "+ userInfoLogic.aleatori() + ".<br/>");*/
		out.close();
		
		//doGet(request, response);
	}

}

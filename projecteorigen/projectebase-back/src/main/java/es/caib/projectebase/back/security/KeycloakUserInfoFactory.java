package es.caib.projectebase.back.security;

import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.representations.AccessToken;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Keycloak
public class KeycloakUserInfoFactory implements UserInfoFactory {

    @Override
    public UserInfo createUserInfo(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        if (!(principal instanceof KeycloakPrincipal<?>)) {
            throw new IllegalStateException("No keycloak principal");
        }

        @SuppressWarnings("unchecked")
        KeycloakPrincipal<KeycloakSecurityContext> kp = (KeycloakPrincipal<KeycloakSecurityContext>) principal;
        KeycloakSecurityContext keycloakSecurityContext = kp.getKeycloakSecurityContext();
        AccessToken token = keycloakSecurityContext.getToken();
        String username = token.getPreferredUsername();
        String email = token.getEmail();
        String llinatges = token.getFamilyName();
        String nom = token.getGivenName();
        String nif = (String) token.getOtherClaims().get("nif");
        return new UserInfo(username, nom, llinatges, email, nif);
    }
}

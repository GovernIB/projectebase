#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.back.security;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

/**
 * Bean CDI per obtenir instàncies de UserInfo
 * @author areus
 */
@ApplicationScoped
public class UserInfoProducer {

    UserInfoFactory factory;

    /**
     * Si keycloak està present al sistema emprar factory de keycloak, sinó un factory per defecte.
     */
    @PostConstruct
    protected void init() {
        try {
            Class.forName("org.keycloak.KeycloakPrincipal");
            factory = new KeycloakUserInfoFactory();
        } catch (ClassNotFoundException e) {
            factory = new DefaultUserInfoFactory();
        }
    }

    /**
     * Produim el bean amb SessionScoped perquè només ho faci un pic per sessió.
     * Si la informació d'usuari pogués variar durant la sessió bastaria posar-li RequestScoped.
     */
    @Produces
    @SessionScoped
    @Named("userInfo")
    public UserInfo produceUserInfo(HttpServletRequest request) {
        return factory.createUserInfo(request);
    }
}

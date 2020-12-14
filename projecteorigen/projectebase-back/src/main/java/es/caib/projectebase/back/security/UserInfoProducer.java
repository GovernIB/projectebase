package es.caib.projectebase.back.security;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

@ApplicationScoped
public class UserInfoProducer {

    @Inject
    @Any
    Instance<UserInfoFactory> userInfoInstance;

    @Produces
    @SessionScoped
    @Named("userInfo")
    public UserInfo produceUserInfo(HttpServletRequest request) {
        UserInfoFactory userInfoFactory;
        try {
            Class.forName("org.keycloak.KeycloakPrincipal");
            userInfoFactory = userInfoInstance.select(Keycloak.Literal.INSTANCE).get();
        } catch (ClassNotFoundException e) {
            userInfoFactory = userInfoInstance.select(Default.Literal.INSTANCE).get();
        }
        return userInfoFactory.createUserInfo(request);
    }
}

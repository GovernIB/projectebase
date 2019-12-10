package cat.toni.userinfologic;

import javax.annotation.security.RolesAllowed;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.jboss.ejb3.annotation.SecurityDomain;

@Stateless
@LocalBean
//@SecurityDomain("keycloak")
public class UserInfoLogic implements UserInfoLogicRemote, UserInfoLogicLocal {
    
    @Override
    @RolesAllowed({"EBO_SUPERVISOR"})
    public int aleatori() {
    	int random = (int)(Math.random()*100);
    	System.out.println("Número aleatòri generat: " + random);
		return random;
    }
}
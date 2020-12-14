#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.back.security;

import javax.servlet.http.HttpServletRequest;

/**
 * Factory per defecte per extreure la informació d'usuari de la petició.
 */
public class DefaultUserInfoFactory implements UserInfoFactory {

    @Override
    public UserInfo createUserInfo(HttpServletRequest request) {
        return new UserInfo(request.getRemoteUser(), null, null, null, null);
    }
}

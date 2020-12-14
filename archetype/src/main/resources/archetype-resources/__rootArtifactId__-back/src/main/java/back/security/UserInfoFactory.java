#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.back.security;

import javax.servlet.http.HttpServletRequest;

public interface UserInfoFactory {

    UserInfo createUserInfo(HttpServletRequest request);

}

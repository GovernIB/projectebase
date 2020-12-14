package es.caib.projectebase.back.security;

import javax.servlet.http.HttpServletRequest;

public class DefaultUserInfoFactory implements UserInfoFactory {

    @Override
    public UserInfo createUserInfo(HttpServletRequest request) {
        return new UserInfo(request.getRemoteUser(), null, null, null, null);
    }
}

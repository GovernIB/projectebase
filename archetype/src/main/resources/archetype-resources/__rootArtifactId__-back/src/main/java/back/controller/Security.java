#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.back.controller;

import ${package}.commons.utils.Constants;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

/**
 * Proveeix una forma centralitzada de comprovar els permisos de l'usuari dins l'aplicació web.
 * 
 * @author areus
 */
@Named
@ApplicationScoped
public class Security {

    @Inject
    private HttpServletRequest request;

    public boolean isAdmin() {
        return request.isUserInRole(Constants.${prefixuppercase}_ADMIN);
    }
    
    public boolean isUser() {
        return request.isUserInRole(Constants.${prefixuppercase}_USER);
    }
    
    public boolean isUserOrAdmin() {
        return isUser() || isAdmin();
    }
}

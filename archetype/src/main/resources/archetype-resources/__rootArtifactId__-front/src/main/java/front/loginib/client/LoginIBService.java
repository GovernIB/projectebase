#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.front.loginib.client;

import es.caib.loginib.rest.api.v1.RDatosAutenticacion;

/**
 * Definició de la interfície de LoginIB
 *
 * @author areus
 */
public interface LoginIBService {

    String initateLogin(String idioma, String urlCallback, String urlCallbackError);

    String initiateLogout(String idioma, String urlCallback);

    RDatosAutenticacion checkTicket(String ticket);
}

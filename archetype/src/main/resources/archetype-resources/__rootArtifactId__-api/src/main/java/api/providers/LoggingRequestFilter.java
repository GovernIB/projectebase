#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.api.providers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;

/**
 * Realitza un log de totes les peticions rebudes.
 *
 * @author areus
 */
@Provider
public class LoggingRequestFilter implements ContainerRequestFilter {

    private static final Logger LOG = LoggerFactory.getLogger(LoggingRequestFilter.class);

    /**
     * Realitza un log del mètode HTTP i el request URI.
     * @param request informació de context de la petició
     */
    @Override
    public void filter(ContainerRequestContext request) {
        LOG.debug("Rest call: " + request.getMethod() + " " + request.getUriInfo().getRequestUri().toString());
    }
}
#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.distribucio.integracio;

/**
 * Servei de proces d'anotacions.
 */
public interface ProcessarAnotacioService {

    void processarAnotacioPendent(Long id);

    void processarAnotacioRebuda(Long id);

}

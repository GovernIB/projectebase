package es.caib.projectebase.distribucio.integracio;

/**
 * Servei de proces d'anotacions.
 */
public interface ProcessarAnotacioService {

    void processarAnotacioPendent(Long id);

    void processarAnotacioRebuda(Long id);

}

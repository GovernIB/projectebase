package es.caib.projectebase.sistra2.integracio;

/**
 * Servei de proces d'anotacions.
 */
public interface ProcessarAnotacioService {

    void processarAnotacioPendent(Long id);

    void processarAnotacioRebuda(Long id);

}

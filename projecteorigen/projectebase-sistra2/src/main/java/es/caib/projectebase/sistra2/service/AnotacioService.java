package es.caib.projectebase.sistra2.service;

import es.caib.projectebase.sistra2.persistence.Anotacio;

import java.util.List;

public interface AnotacioService {

    void createAnotacio(String id, String clau) throws AnotacioDuplicadaException;

    List<String> findIdsPendents(int maxResults);

    Anotacio lockById(String id);
}

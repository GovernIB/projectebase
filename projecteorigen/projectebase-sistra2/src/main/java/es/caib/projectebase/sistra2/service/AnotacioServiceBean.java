package es.caib.projectebase.sistra2.service;

import es.caib.projectebase.sistra2.persistence.Anotacio;
import es.caib.projectebase.sistra2.persistence.EstatAnotacio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

@Stateless
@Local(AnotacioService.class)
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class AnotacioServiceBean implements AnotacioService {

    private static final Logger LOG = LoggerFactory.getLogger(AnotacioServiceBean.class);

    @PersistenceContext(unitName = "projectebaseSistra2PU")
    private EntityManager entityManager;

    /**
     * Crea una nova anotació amb estat pendent.
     * La marcam amb un REQUIRES_NEW per si l'anotació ja existeix l'error d'SQL
     * no provoqui un rollback de la transacció del client.
     * @param id identificador
     * @param clau clau
     * @throws AnotacioDuplicadaException si l'anotació amb l'id indicat ja existeix
     */
    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void createAnotacio(String id, String clau) throws AnotacioDuplicadaException {
        Anotacio anotacio = new Anotacio();
        anotacio.setId(id);
        anotacio.setClau(clau);
        anotacio.setEstat(EstatAnotacio.PENDENT);
        try {
            entityManager.persist(anotacio);
            entityManager.flush();
        } catch (PersistenceException exception) {
            processSQLConstraintException(exception, () -> new AnotacioDuplicadaException(id));
        }
    }

    @Override
    public List<String> findIdsPendents(int maxResults) {
        TypedQuery<String> query = entityManager.createNamedQuery(Anotacio.IDS_BY_ESTAT, String.class);
        query.setParameter("estat", EstatAnotacio.PENDENT);
        query.setMaxResults(maxResults);
        return query.getResultList();
    }

    @Override
    public Anotacio lockById(String id) {
        return entityManager.find(Anotacio.class, id, LockModeType.PESSIMISTIC_WRITE,
                Map.of("javax.persistence.lock.timeout", 1000));
    }

    /**
     * Processa una excepció de persistència, i si es tracta d'un error de violació de constraint (SQL STATE 23XXX),
     * llança l'excepció generada per la funció indicada, en cas contrari llança la mateixa excepció de persistència.
     * @param exception excepció de persistència
     * @param supplier funció per generar l'excepció a llançar
     */
    private void processSQLConstraintException(PersistenceException exception, Supplier<RuntimeException> supplier) {
        Throwable cause = exception.getCause();
        while (cause != null) {
            if (cause instanceof SQLException) {
                SQLException sqlException = (SQLException) cause;
                if (sqlException.getSQLState().startsWith("23")) {
                    throw supplier.get();
                }
            }
            cause = cause.getCause();
        }
        throw exception;
    }
}

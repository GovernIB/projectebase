package es.caib.projectebase.jpa.test;

import es.caib.projectebase.jpa.UnitatOrganica;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;
import java.time.LocalDate;
import java.util.List;

/**
 * Tests amb {@link UnitatOrganica}.
 *
 * @author areus
 */
class TestUnitatOrganica {

    private static EntityManagerFactory emf;
    private static EntityManager em;

    @BeforeAll
    static void init() {
        emf = Persistence.createEntityManagerFactory("testPU");
        em = emf.createEntityManager();
    }

    @Test
    void testCreacioUnitatOrganica() {
        UnitatOrganica unitat = new UnitatOrganica();
        unitat.setCodiDir3("A12345678");
        unitat.setNom("Test");
        unitat.setDataCreacio(LocalDate.now());

        em.getTransaction().begin();
        em.persist(unitat);
        em.getTransaction().commit();

        List<UnitatOrganica> list = em.createQuery("select u from UnitatOrganica u", UnitatOrganica.class).getResultList();
        Assertions.assertEquals(1, list.size());
    }

    @Test
    void testValidacionsUnitatOrganica() {
        UnitatOrganica unitat = new UnitatOrganica();
        unitat.setCodiDir3("A1234567"); // pattern incorrecte
        unitat.setNom(""); // nom no pot ser buid
        unitat.setDataCreacio(LocalDate.now().plusDays(2)); // la data ha de ser passada

        em.getTransaction().begin();
        em.persist(unitat);

        // Comprovam que el commit llança una excepció de persistencia.
        var exception = Assertions.assertThrows(PersistenceException.class,  () -> em.getTransaction().commit());

        // Comprovam que l'excepció ha estat causada per la validació
        Assertions.assertTrue(exception.getCause() instanceof ConstraintViolationException);

        // Comprovam que el nombre de validacions que han fallat són 3
        ConstraintViolationException cvException = (ConstraintViolationException) exception.getCause();
        Assertions.assertEquals(3, cvException.getConstraintViolations().size());
    }

    @AfterAll
    static void tearDown(){
        em.clear();
        em.close();
        emf.close();
    }
}

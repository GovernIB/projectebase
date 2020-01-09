#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.jpa.test;

import ${package}.jpa.Procediment;
import ${package}.jpa.UnitatOrganica;

import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.hibernate.validator.resourceloading.AggregateResourceBundleLocator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * Tests amb {@link Procediment}.
 *
 * @author areus
 * @author anadal
 */
class TestProcediment extends JPATest {

    private static Long unitatId;

    @BeforeAll
    static void init() {
        JPATest.init();

        em = emf.createEntityManager();
        UnitatOrganica unitat = new UnitatOrganica();
        unitat.setCodiDir3("E00000001");
        unitat.setNom("Test procediment");
        unitat.setDataCreacio(LocalDate.now());

        em.getTransaction().begin();
        em.persist(unitat);
        em.getTransaction().commit();

        unitatId = unitat.getId();

        em.clear();
        em.close();
    }

    @Test
    void testCreacioProcediment() {

        Procediment procediment = new Procediment();
        procediment.setCodiSia("123456");
        procediment.setNom("Procediment test");

        UnitatOrganica unitatOrganica = em.getReference(UnitatOrganica.class, unitatId);
        procediment.setUnitatOrganica(unitatOrganica);

        em.getTransaction().begin();
        em.persist(procediment);
        em.getTransaction().commit();

        List<Procediment> list = em.createQuery("select p from Procediment p", Procediment.class).getResultList();
        // Comprovam que s'ha creat el procediment
        Assertions.assertEquals(1, list.size());

        // Comprovam que està enllaçat amb la unitat orgànica
        Assertions.assertEquals(unitatId, list.get(0).getUnitatOrganica().getId());
    }

    @Test
    void testValidacionsProcediment() {

        Procediment procediment = new Procediment();
        procediment.setCodiSia("12345"); // pattern incorrecte
        procediment.setNom(""); // nom no pot ser buid

        UnitatOrganica unitatOrganica = em.getReference(UnitatOrganica.class, unitatId);
        procediment.setUnitatOrganica(unitatOrganica);

        em.getTransaction().begin();
        em.persist(procediment);

        // Comprovam que el commit llança una excepció de persistencia.
        var exception = Assertions.assertThrows(PersistenceException.class, () -> em.getTransaction().commit());

        // Comprovam que l'excepció ha estat causada per la validació
        Assertions.assertTrue(exception.getCause() instanceof ConstraintViolationException);

        // Comprovam que el nombre de validacions que han fallat són 2
        ConstraintViolationException cvException = (ConstraintViolationException) exception.getCause();
        Assertions.assertEquals(2, cvException.getConstraintViolations().size());
    }

    @Test
    public void validation() {

        System.out.println(" ${symbol_escape}n${symbol_escape}n ENTRA  ${symbol_escape}n${symbol_escape}n");

        Procediment procediment = new Procediment();
        procediment.setCodiSia("12345"); // pattern incorrecte
        procediment.setNom(""); // nom no pot ser buid

        UnitatOrganica unitatOrganica = em.getReference(UnitatOrganica.class, unitatId);
        procediment.setUnitatOrganica(unitatOrganica);

        em.getTransaction().begin();
        em.persist(procediment);

        // Comprovam que el commit llança una excepció de persistencia.
        var exception = Assertions.assertThrows(PersistenceException.class, () -> em.getTransaction().commit());

        System.out.println(" ${symbol_escape}n${symbol_escape}n ABANS DE STACKTRACE  ${symbol_escape}n${symbol_escape}n");

        // exception.getCause().printStackTrace();

        // Comprovam que l'excepció ha estat causada per la validació
        Assertions.assertTrue(exception.getCause() instanceof ConstraintViolationException);

        ConstraintViolationException cve = (ConstraintViolationException) exception.getCause();

        for (ConstraintViolation<?> cv : cve.getConstraintViolations()) {

            System.out.println(" ================================ ");

            System.out.println("MESSAGE: " + cv.getMessage());
            System.out.println("   CODE: " + cv.getMessageTemplate());

        }

        Validator validator2 = Validation.byDefaultProvider().configure()
                .messageInterpolator(new ResourceBundleMessageInterpolator(
                        new AggregateResourceBundleLocator(Arrays.asList("${parentArtifactId}.jpa.ValidationMessages"))))
                .buildValidatorFactory().getValidator();

        Set<ConstraintViolation<Procediment>> constraints = validator2.validate(procediment);

        for (ConstraintViolation<?> cv : constraints) {

            System.out.println(" ================================ ");

            System.out.println("MESSAGE22: " + cv.getMessage());
            System.out.println("   CODE22: " + cv.getMessageTemplate());

        }

    }

}

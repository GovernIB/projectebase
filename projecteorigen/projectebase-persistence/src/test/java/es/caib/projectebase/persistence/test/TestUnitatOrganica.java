package es.caib.projectebase.persistence.test;

import es.caib.projectebase.persistence.EstatPublicacio;
import es.caib.projectebase.persistence.UnitatOrganica;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Status;
import javax.transaction.UserTransaction;
import javax.validation.ConstraintViolationException;
import java.time.LocalDate;

@RunWith(Arquillian.class)
public class TestUnitatOrganica {

    @Deployment
    public static JavaArchive createDeployment() {
        JavaArchive jar = ShrinkWrap.create(JavaArchive.class, "test.jar")
                .addPackages(true, "es.caib.projectebase.persistence")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsResource("META-INF/arquillian-persistence.xml", "META-INF/persistence.xml");
        System.out.println(jar.toString(true));
        return jar;
    }

    @PersistenceContext
    EntityManager em;

    @Inject
    UserTransaction utx;

    @Before
    public void startTransaction() throws Exception {
        utx.begin();
        em.joinTransaction();
    }

    @After
    public void commitTransaction() throws Exception {
        if (utx.getStatus() == Status.STATUS_MARKED_ROLLBACK) {
            utx.rollback();
        } else {
            utx.commit();
        }
    }

    @Test
    @InSequence(1)
    public void testCreateUnitat() {
        UnitatOrganica unitatOrganica = new UnitatOrganica();
        unitatOrganica.setCodiDir3("U87654321");
        unitatOrganica.setNom("Unitat test arquillian");
        unitatOrganica.setDataCreacio(LocalDate.now());
        unitatOrganica.setEstat(EstatPublicacio.ACTIU);
        em.persist(unitatOrganica);
        em.flush();

        Assert.assertNotNull(unitatOrganica.getId());
    }

    @Test
    @InSequence(2)
    public void testQueryUnitat() {
        TypedQuery<UnitatOrganica> query = em.createQuery(
                "select u from UnitatOrganica u where u.codiDir3 = :codiDir3", UnitatOrganica.class);
        query.setParameter("codiDir3", "U87654321");
        UnitatOrganica unitat = query.getSingleResult();

        Assert.assertEquals("Unitat test arquillian", unitat.getNom());
    }

    @Test
    @InSequence(3)
    public void testRemoveUnitat() {
        TypedQuery<UnitatOrganica> query = em.createQuery(
                "select u from UnitatOrganica u where u.codiDir3 = :codiDir3", UnitatOrganica.class);
        query.setParameter("codiDir3", "U87654321");
        UnitatOrganica unitat = query.getSingleResult();
        em.remove(unitat);
        em.flush();

        Assert.assertFalse(em.contains(unitat));
    }

    @Test
    @InSequence(4)
    public void testConstraintsUnitat() {

        UnitatOrganica unitatOrganica = new UnitatOrganica();
        unitatOrganica.setCodiDir3("Z87654321"); // inclumpleix el pattern
        unitatOrganica.setNom(""); // incumpleix que no potser buid
        unitatOrganica.setDataCreacio(LocalDate.now().plusDays(1)); // inclumpleix que no pot se futur
        unitatOrganica.setEstat(null); // inclumpleix que no pot ser null

        try {
            em.persist(unitatOrganica);
            em.flush();
            Assert.fail("Hauria d'haver donat un error de validació");
        } catch (ConstraintViolationException cve) {
            Assert.assertEquals(4, cve.getConstraintViolations().size());
        }
    }
}

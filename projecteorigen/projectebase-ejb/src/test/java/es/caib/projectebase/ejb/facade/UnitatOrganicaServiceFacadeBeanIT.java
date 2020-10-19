package es.caib.projectebase.ejb.facade;

import es.caib.projectebase.service.exception.ServiceException;
import es.caib.projectebase.service.facade.UnitatOrganicaServiceFacade;
import es.caib.projectebase.service.model.EstatPublicacio;
import es.caib.projectebase.service.model.UnitatOrganicaDTO;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;
import java.time.LocalDate;
import java.util.Optional;

/**
 * Realitza tests de persistència i validació damunt Unitats Orgàniques.
 *
 * Els tests s'executen sobre una instància de JBoss que o bé s'arranca automàticament (-Parq-jboss-managed), o bé
 * ja està en marxa (-Parq-jboss-remote).
 */
@RunWith(Arquillian.class)
public class UnitatOrganicaServiceFacadeBeanIT {;

    /**
     * Crea l'arxiu de deploy que es desplegarà sobre JBoss per fer els tests.
     * @return arxiu desplegable.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        try {
            JavaArchive jar = ShrinkWrap.create(JavaArchive.class, "test.jar")
                    .addPackages(true, "es.caib.projectebase.ejb")
                    .addPackages(true, "es.caib.projectebase.persistence")
                    .addPackages(true, "es.caib.projectebase.service")
                    .addAsResource(
                            ServiceException.class.getResource("/service/ExceptionMessages_ca.properties"),
                            "service/ExceptionMessages_ca.properties")
                    .addAsResource(
                            ServiceException.class.getResource("/service/ExceptionMessages_es.properties"),
                            "service/ExceptionMessages_es.properties")
                    .addAsResource("META-INF/beans.xml")
                    .addAsResource("META-INF/ejb-jar.xml")
                    .addAsResource("META-INF/arquillian-persistence.xml", "META-INF/persistence.xml")
                    .addAsResource("META-INF/arquillian-ds.xml");
            System.out.println(jar.toString(true));
            return jar;
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @EJB
    private UnitatOrganicaServiceFacade unitatOrganicaServiceFacade;

    @EJB
    private AdminManager adminManager;

    /**
     * Crea una unitat orgància amb codiDir3 "U87654321".
     */
    @Test
    @InSequence(1)
    public void testCreateUnitat() {
        UnitatOrganicaDTO dto = new UnitatOrganicaDTO();
        dto.setCodiDir3("U87654321");
        dto.setNom("Unitat test arquillian");
        dto.setDataCreacio(LocalDate.now());
        dto.setEstat(EstatPublicacio.ACTIU);

        adminManager.exec(() -> {
            Long result = unitatOrganicaServiceFacade.create(dto);
            Assert.assertNotNull(result);
        });
    }

    /**
     * Selecciona la unitat orgànica amb codiDir3 "U87654321".
     */
    @Test
    @InSequence(2)
    public void testFindById() {

        adminManager.exec(() -> {
            Optional<UnitatOrganicaDTO> dto = unitatOrganicaServiceFacade.findById(1L);

            Assert.assertTrue(dto.isPresent());
            Assert.assertEquals("Unitat test arquillian", dto.get().getNom());
            Assert.assertEquals("U87654321", dto.get().getCodiDir3());
        });
    }
}

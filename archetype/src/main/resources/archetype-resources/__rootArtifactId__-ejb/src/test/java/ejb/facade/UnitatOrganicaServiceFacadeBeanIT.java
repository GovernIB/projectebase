#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.ejb.facade;

import ${package}.service.exception.RecursNoTrobatException;
import ${package}.service.exception.UnitatDuplicadaException;
import ${package}.service.exception.UnitatTeProcedimentsException;
import ${package}.service.facade.UnitatOrganicaServiceFacade;
import ${package}.service.model.EstatPublicacio;
import ${package}.service.model.Ordre;
import ${package}.service.model.Pagina;
import ${package}.service.model.UnitatOrganicaDTO;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;
import javax.ejb.EJBAccessException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Realitza tests de persistència i validació damunt Unitats Orgàniques.
 *
 * Els tests s'executen sobre una instància de JBoss que o bé s'arranca automàticament (-Parq-jboss-managed), o bé
 * ja està en marxa (-Parq-jboss-remote).
 */
@RunWith(Arquillian.class)
public class UnitatOrganicaServiceFacadeBeanIT extends AbstractFacadeIT {;

    @EJB
    private UnitatOrganicaServiceFacade unitatOrganicaServiceFacade;

    /**
     * Crea una unitat orgància amb codiDir3 "U87654321".
     */
    @Test
    @InSequence(1)
    public void testCreateUnitat() {
        UnitatOrganicaDTO dto = new UnitatOrganicaDTO();
        dto.setCodiDir3("U87654321");
        dto.setNom("Unitat test arquillian");
        dto.setDataCreacio(LocalDate.of(2020, 10, 19));
        dto.setEstat(EstatPublicacio.ACTIU);

        adminManager.exec(() -> {
            Long result = unitatOrganicaServiceFacade.create(dto);
            Assert.assertNotNull(result);
        });
    }

    /**
     * Crea una unitat orgància amb codiDir3 "U87654321".
     */
    @Test(expected = UnitatDuplicadaException.class)
    @InSequence(2)
    public void testCreateUnitatDuplicat() {
        UnitatOrganicaDTO dto = new UnitatOrganicaDTO();
        dto.setCodiDir3("U87654321");
        dto.setNom("Unitat test arquillian duplicada");
        dto.setDataCreacio(LocalDate.of(2020, 10, 19));
        dto.setEstat(EstatPublicacio.ACTIU);

        adminManager.exec(() -> {
            unitatOrganicaServiceFacade.create(dto);
            Assert.fail("No s'hauria d'haver pogut crear");
        });
    }

    /**
     * Selecciona la unitat orgànica
     */
    @Test
    @InSequence(3)
    public void testFindById() {

        userManager.exec(() -> {
            Optional<UnitatOrganicaDTO> dto = unitatOrganicaServiceFacade.findById(101L);

            Assert.assertTrue(dto.isPresent());
            Assert.assertEquals("Unitat test arquillian", dto.get().getNom());
            Assert.assertEquals("U87654321", dto.get().getCodiDir3());
        });
    }

    /**
     * Selecciona la unitat orgànica inexistent
     */
    @Test
    @InSequence(4)
    public void testFindByIdError() {

        userManager.exec(() -> {
            Optional<UnitatOrganicaDTO> dto = unitatOrganicaServiceFacade.findById(999L);
            Assert.assertTrue(dto.isEmpty());
        });
    }

    /**
     * Actualitza la unitat orgànica
     */
    @Test
    @InSequence(5)
    public void testUpdateUnitat() {
        var dto = new UnitatOrganicaDTO();
        dto.setId(101L);
        dto.setCodiDir3("A87654321");
        dto.setNom("Unitat test arquillian 2");
        dto.setDataCreacio(LocalDate.of(2020, 10, 20));
        dto.setEstat(EstatPublicacio.INACTIU);

        adminManager.exec(() -> {
            unitatOrganicaServiceFacade.update(dto);

            var updated = unitatOrganicaServiceFacade.findById(101L).orElseThrow();
            Assert.assertEquals(dto.getId(), updated.getId());
            Assert.assertNotEquals(dto.getCodiDir3(), updated.getCodiDir3()); // el codiDir3 no s'actualitza
            Assert.assertEquals(dto.getNom(), updated.getNom());
            Assert.assertEquals(dto.getDataCreacio(), updated.getDataCreacio());
            Assert.assertEquals(dto.getEstat(), updated.getEstat());
        });
    }

    /**
     * Actualitza la unitat orgànica inexistent
     */
    @Test(expected = RecursNoTrobatException.class)
    @InSequence(6)
    public void testUpdateUnitatError() {
        UnitatOrganicaDTO dto = new UnitatOrganicaDTO();
        dto.setId(999L);
        dto.setCodiDir3("J87654321");
        dto.setNom("Unitat test arquillian 2 error");
        dto.setDataCreacio(LocalDate.of(2020, 10, 20));
        dto.setEstat(EstatPublicacio.INACTIU);

        adminManager.exec(() -> {
            unitatOrganicaServiceFacade.update(dto);
            Assert.fail("No s'hauria de poder haver actualitzat sense error");
        });
    }

    /**
     * Esborra la unitat
     */
    @Test
    @InSequence(7)
    public void testDelete() {
        adminManager.exec(() -> unitatOrganicaServiceFacade.delete(101L));
    }

    /**
     * Esborra una unitat que no existeix
     */
    @Test(expected = RecursNoTrobatException.class)
    @InSequence(8)
    public void testDeleteError() {
        adminManager.exec(() -> {
            unitatOrganicaServiceFacade.delete(999L);
            Assert.fail("No s'hauria de poder haver borrat sense error");
        });
    }

    /**
     * Esborra una unitat amb dependències
     */
    @Test(expected = UnitatTeProcedimentsException.class)
    @InSequence(9)
    public void testDeleteErrorDepencencies() {
        adminManager.exec(() -> {
            unitatOrganicaServiceFacade.delete(1L);
            Assert.fail("No s'hauria de poder haver borrat sense error");
        });
    }

    /**
     * Llistat
     */
    @Test
    @InSequence(10)
    public void testLlistat() {
        // No calen permisos
        //userManager.exec(() -> {
            Pagina<UnitatOrganicaDTO> llistat = unitatOrganicaServiceFacade.findFiltered(1, 10,
                    Collections.singletonMap("codiDir3", "A"), List.of(Ordre.descendent("codiDir3")));
            Assert.assertEquals(12L, llistat.getTotal());
            Assert.assertEquals(10, llistat.getItems().size());
            Assert.assertEquals("A00000011", llistat.getItems().get(0).getCodiDir3());
        //});
    }

    @Test(expected = EJBAccessException.class)
    @InSequence(11)
    public void testCreateSensePermisos() {
        UnitatOrganicaDTO dto = new UnitatOrganicaDTO();
        userManager.exec(() -> {
            unitatOrganicaServiceFacade.create(dto);
            Assert.fail("No hauria de poder crear");
        });
    }

    @Test(expected = EJBAccessException.class)
    @InSequence(12)
    public void testUpdateSensePermisos() {
        UnitatOrganicaDTO dto = new UnitatOrganicaDTO();
        userManager.exec(() -> {
            unitatOrganicaServiceFacade.update(dto);
            Assert.fail("No hauria de poder actualitzar");
        });
    }

    @Test(expected = EJBAccessException.class)
    @InSequence(13)
    public void testDeleteSensePermisos() {
        userManager.exec(() -> {
            unitatOrganicaServiceFacade.delete(1L);
            Assert.fail("No hauria de poder esborrar");
        });
    }
}

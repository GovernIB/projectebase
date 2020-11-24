#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.ejb.facade;

import ${package}.service.exception.ProcedimentDuplicatException;
import ${package}.service.exception.RecursNoTrobatException;
import ${package}.service.facade.ProcedimentServiceFacade;
import ${package}.service.model.Pagina;
import ${package}.service.model.ProcedimentDTO;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;
import java.util.Optional;

/**
 * Realitza tests de persistència i validació damunt Unitats Orgàniques.
 *
 * Els tests s'executen sobre una instància de JBoss que o bé s'arranca automàticament (-Parq-jboss-managed), o bé
 * ja està en marxa (-Parq-jboss-remote).
 */
@RunWith(Arquillian.class)
public class ProcedimentServiceFacadeBeanIT extends AbstractFacadeIT {;

    @EJB
    private ProcedimentServiceFacade procedimentServiceFacade;

    /**
     * Crea un procediment amb codiSia "0123456".
     */
    @Test
    @InSequence(1)
    public void testCreateProcediment() {
        var dto = new ProcedimentDTO();
        dto.setCodiSia("0123456");
        dto.setNom("Procediment test arquillian");

        adminManager.exec(() -> {
            Long result = procedimentServiceFacade.create(dto, 2L);
            Assert.assertNotNull(result);
        });
    }

    /**
     * Crea un procediment duplicat
     */
    @Test(expected = ProcedimentDuplicatException.class)
    @InSequence(2)
    public void testCreateProcedimentDuplicat() {
        var dto = new ProcedimentDTO();
        dto.setCodiSia("0123456");
        dto.setNom("Procediment test arquillian duplicat");

        adminManager.exec(() -> {
            procedimentServiceFacade.create(dto, 2L);
            Assert.fail("No s'hauria d'haver pogut crear");
        });
    }

    /**
     * Crea un procediment amb una unitat inexistent
     */
    @Test(expected = RecursNoTrobatException.class)
    @InSequence(3)
    public void testCreateProcedimentUnitatInexistent() {
        var dto = new ProcedimentDTO();
        dto.setCodiSia("01234567");
        dto.setNom("Procediment test arquillian amb unitat inexistent");

        adminManager.exec(() -> {
            procedimentServiceFacade.create(dto, 999L);
            Assert.fail("No s'hauria d'haver pogut crear");
        });
    }

    /**
     * Selecciona un procediment
     */
    @Test
    @InSequence(4)
    public void testFindById() {
        adminManager.exec(() -> {
            Optional<ProcedimentDTO> dto = procedimentServiceFacade.findById(101L);
            Assert.assertTrue(dto.isPresent());
            Assert.assertEquals("Procediment test arquillian", dto.get().getNom());
            Assert.assertEquals("0123456", dto.get().getCodiSia());
        });
    }

    /**
     * Selecciona procediment inexistent
     */
    @Test
    @InSequence(5)
    public void testFindByIdError() {
        adminManager.exec(() -> {
            Optional<ProcedimentDTO> dto = procedimentServiceFacade.findById(999L);
            Assert.assertTrue(dto.isEmpty());
        });
    }

    /**
     * Actualitza el procediment
     */
    @Test
    @InSequence(6)
    public void testUpdatProcediment() {
        var dto = new ProcedimentDTO();
        dto.setId(101L);
        dto.setCodiSia("01234567");
        dto.setNom("Procediment test arquillian 2");

        adminManager.exec(() -> {
            procedimentServiceFacade.update(dto);

            var updated = procedimentServiceFacade.findById(101L).orElseThrow();
            Assert.assertEquals(dto.getId(), updated.getId());
            Assert.assertNotEquals(dto.getCodiSia(), updated.getCodiSia()); // el codiSia no s'actualitza
            Assert.assertEquals(dto.getNom(), updated.getNom());
        });
    }

    /**
     * Actualitza un procediment inexistent
     */
    @Test(expected = RecursNoTrobatException.class)
    @InSequence(7)
    public void testUpdateProcedimentError() {
        var dto = new ProcedimentDTO();
        dto.setId(999L);
        dto.setCodiSia("01234567");
        dto.setNom("Procediment test arquillian 2 error");

        adminManager.exec(() -> {
            procedimentServiceFacade.update(dto);
            Assert.fail("No s'hauria de poder haver actualitzat sense error");
        });
    }

    /**
     * Esborra un procediment
     */
    @Test
    @InSequence(8)
    public void testDelete() {
        adminManager.exec(() -> procedimentServiceFacade.delete(101L));
    }

    /**
     * Esborra un procediment que no existeix
     */
    @Test(expected = RecursNoTrobatException.class)
    @InSequence(9)
    public void testDeleteError() {
        adminManager.exec(() -> {
            procedimentServiceFacade.delete(999L);
            Assert.fail("No s'hauria de poder haver borrat sense error");
        });
    }

    /**
     * Llistat
     */
    @Test
    @InSequence(10)
    public void testLlistat() {
        adminManager.exec(() -> {

            Pagina<ProcedimentDTO> llistat = procedimentServiceFacade.findByUnitat(1, 10, 1L);
            Assert.assertEquals(11L, llistat.getTotal());
            Assert.assertEquals(10, llistat.getItems().size());
        });
    }
}

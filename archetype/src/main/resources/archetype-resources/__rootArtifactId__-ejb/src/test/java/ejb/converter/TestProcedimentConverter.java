#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.ejb.converter;

import ${package}.persistence.model.Procediment;
import ${package}.persistence.model.UnitatOrganica;
import ${package}.service.model.EstatPublicacio;
import ${package}.service.model.ProcedimentDTO;
import ${package}.service.model.UnitatOrganicaDTO;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;

public class TestProcedimentConverter {

    private final ProcedimentConverter converter = new ProcedimentConverterImpl();

    @Test
    public void testToDTO() {
        Procediment entity = new Procediment();
        entity.setId(1L);
        entity.setCodiSia("000001");
        entity.setNom("Nom Test");

        UnitatOrganica unitat = new UnitatOrganica();
        unitat.setId(2L);
        entity.setUnitatOrganica(unitat);

        ProcedimentDTO dto = converter.toDTO(entity);

        Assert.assertEquals(1L, (long) dto.getId());
        Assert.assertEquals("000001", dto.getCodiSia());
        Assert.assertEquals("Nom Test", dto.getNom());
        Assert.assertEquals(2L, (long) dto.getIdUnitat());
    }

    @Test
    public void testToEntity() {
        ProcedimentDTO dto = new ProcedimentDTO();
        dto.setId(1L);
        dto.setCodiSia("000001");
        dto.setNom("Nom Test");
        dto.setIdUnitat(2L);

        Procediment entity = converter.toEntity(dto);

        Assert.assertEquals(1L, (long) entity.getId());
        Assert.assertEquals("000001", entity.getCodiSia());
        Assert.assertEquals("Nom Test", entity.getNom());
        Assert.assertNull(entity.getUnitatOrganica()); // no s'actualitza
    }


    @Test
    public void testUpdateFromDTO() {
        Procediment entity = new Procediment();
        entity.setId(1L);
        entity.setCodiSia("000001");
        entity.setNom("Nom Test");

        UnitatOrganica unitat = new UnitatOrganica();
        unitat.setId(2L);
        entity.setUnitatOrganica(unitat);

        ProcedimentDTO dto = new ProcedimentDTO();
        dto.setId(3L);
        dto.setCodiSia("100001");
        dto.setNom("Nom Test bis");
        dto.setIdUnitat(4L);

        converter.updateFromDTO(entity, dto);

        Assert.assertEquals(3L, (long) entity.getId());
        Assert.assertEquals("000001", entity.getCodiSia()); // no s'actualitza
        Assert.assertEquals("Nom Test bis", entity.getNom());
        Assert.assertEquals(2L, (long)entity.getUnitatOrganica().getId()); // no s'actualitza
    }
}

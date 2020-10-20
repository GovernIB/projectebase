#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.ejb.converter;

import ${package}.persistence.model.UnitatOrganica;
import ${package}.service.model.EstatPublicacio;
import ${package}.service.model.UnitatOrganicaDTO;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;

public class TestUnitatOrganicaConverter {

    private final UnitatOrganicaConverter converter = new UnitatOrganicaConverterImpl();

    @Test
    public void testToDTO() {
        UnitatOrganica entity = new UnitatOrganica();
        entity.setId(1L);
        entity.setCodiDir3("A00000001");
        entity.setNom("Nom test");
        entity.setEstat(EstatPublicacio.INACTIU);
        entity.setDataCreacio(LocalDate.of(2020, Month.OCTOBER, 14));

        UnitatOrganicaDTO dto = converter.toDTO(entity);
        Assert.assertEquals(1L, (long) dto.getId());
        Assert.assertEquals("A00000001", dto.getCodiDir3());
        Assert.assertEquals("Nom test", dto.getNom());
        Assert.assertEquals(EstatPublicacio.INACTIU, dto.getEstat());
        Assert.assertEquals(LocalDate.of(2020, Month.OCTOBER, 14), dto.getDataCreacio());
    }

    @Test
    public void testToEntity() {
        UnitatOrganicaDTO dto = new UnitatOrganicaDTO();
        dto.setId(1L);
        dto.setCodiDir3("A00000001");
        dto.setNom("Nom test");
        dto.setEstat(EstatPublicacio.INACTIU);
        dto.setDataCreacio(LocalDate.of(2020, Month.OCTOBER, 14));

        UnitatOrganica entity = converter.toEntity(dto);
        Assert.assertEquals(1L, (long) entity.getId());
        Assert.assertEquals("A00000001", entity.getCodiDir3());
        Assert.assertEquals("Nom test", entity.getNom());
        Assert.assertEquals(EstatPublicacio.INACTIU, entity.getEstat());
        Assert.assertEquals(LocalDate.of(2020, Month.OCTOBER, 14), entity.getDataCreacio());
    }

    @Test
    public void testUpdateFromDTO() {
        UnitatOrganica entity = new UnitatOrganica();
        entity.setId(1L);
        entity.setCodiDir3("A00000001");
        entity.setNom("Nom test");
        entity.setEstat(EstatPublicacio.INACTIU);
        entity.setDataCreacio(LocalDate.of(2020, Month.OCTOBER, 14));

        UnitatOrganicaDTO dto = new UnitatOrganicaDTO();
        dto.setId(2L);
        dto.setCodiDir3("A00000002");
        dto.setNom("Nom test 2");
        dto.setEstat(EstatPublicacio.ACTIU);
        dto.setDataCreacio(LocalDate.of(2019, Month.SEPTEMBER, 13));

        converter.updateFromDTO(entity, dto);
        Assert.assertEquals(2L, (long) entity.getId());
        Assert.assertEquals("A00000001", entity.getCodiDir3()); // no ha de canviar
        Assert.assertEquals("Nom test 2", entity.getNom());
        Assert.assertEquals(EstatPublicacio.ACTIU, entity.getEstat());
        Assert.assertEquals(LocalDate.of(2019, Month.SEPTEMBER, 13), entity.getDataCreacio());
    }
}

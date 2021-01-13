package es.caib.projectebase.api.interna.sistra2;

import es.caib.projectebase.service.model.EstatPublicacio;
import es.caib.projectebase.service.model.UnitatOrganicaDTO;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.Map;

public class TestUnitatConverter {

    private final UnitatConverter converter = new UnitatConverterImpl();

    @Test
    public void testToMap() {
        UnitatOrganicaDTO dto = new UnitatOrganicaDTO();
        dto.setId(1L);
        dto.setCodiDir3("A00000001");
        dto.setNom("Nom test");
        dto.setEstat(EstatPublicacio.INACTIU);
        dto.setDataCreacio(LocalDate.of(2020, Month.OCTOBER, 14));


        Map<String, String> map = converter.toMap(dto);
        Assert.assertEquals("1", map.get("id"));
        Assert.assertEquals(dto.getCodiDir3(), map.get("codiDir3"));
        Assert.assertEquals(dto.getNom(), map.get("nom"));
        Assert.assertEquals("INACTIU", map.get("estat"));
        Assert.assertEquals("2020-10-14", map.get("dataCreacio"));
    }
}

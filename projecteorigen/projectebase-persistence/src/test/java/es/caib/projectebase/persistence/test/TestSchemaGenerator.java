package es.caib.projectebase.persistence.test;

import org.junit.Test;

import javax.persistence.Persistence;
import java.util.Collections;
import java.util.Map;

/**
 * Test per provar la generació de l'schema de base de dades.
 */
public class TestSchemaGenerator {

    @Test
    public void testCreateSchema() {
        Persistence.generateSchema("testPU", Collections.emptyMap());
    }
}

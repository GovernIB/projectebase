package es.caib.projectebase.persistence.test;

import org.junit.Test;

import javax.persistence.Persistence;

/**
 * Test per provar la generació de l'schema de base de dades.
 */
public class TestSchemaGenerator {

    @Test
    public void testCreateSchema() {
        Persistence.createEntityManagerFactory("testPU");
    }
}

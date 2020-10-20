#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.persistence;

import org.junit.Ignore;
import org.junit.Test;

import javax.persistence.Persistence;
import java.util.Collections;

/**
 * Test per realitzar la generació de l'schema de base de dades.
 */
@Ignore
public class TestSchemaGenerator {

    /**
     * A partir del persistence.xml definit a test genera l'schema de base de dades.
     */
    @Test
    public void testCreateSchema() {
        // Les propietats per la generació es poden indicar dins el persistence.xml o es poden
        // passar dins el Map.
        Persistence.generateSchema("testPU", Collections.emptyMap());
    }
}

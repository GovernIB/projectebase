package es.caib.projectebase.ws.test;

import org.junit.Assert;
import org.junit.BeforeClass;

import es.caib.projectebase.ws.api.HelloWorldWs;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Test de petició a WebService.
 *
 * @author anadal
 */
@Ignore
public class HelloWorldIT {

    protected static HelloWorldWs helloWorldApi;

    /**
     * S'executa una vegada abans de l'execució de tots els tests d'aquesta classe
     *
     * @throws Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        helloWorldApi = TestUtils.getHelloWorldApi();
    }

    @Test
    public void testVersio() throws Exception {
        Assert.assertEquals("1.0.0", helloWorldApi.getVersion());
    }

    @Test
    public void testEcho() throws Exception {
        final String echo = "eco ecooooo";
        Assert.assertEquals(echo, helloWorldApi.echo(echo));
    }
}

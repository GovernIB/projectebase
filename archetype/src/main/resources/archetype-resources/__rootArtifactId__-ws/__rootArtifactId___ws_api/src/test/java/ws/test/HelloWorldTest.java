#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.ws.test;

import org.junit.Assert;
import org.junit.BeforeClass;

import ${package}.ws.api.HelloWorldWs;
import org.junit.Test;

/**
 * Test de petició a WebService.
 *
 * @author anadal
 */
public class HelloWorldTest {

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
        Assert.assertEquals("${version}", helloWorldApi.getVersion());
    }

    @Test
    public void testEcho() throws Exception {
        final String echo = "eco ecooooo";
        Assert.assertEquals(echo, helloWorldApi.echo(echo));
    }
}

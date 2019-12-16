#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.ws.v1.test;

import org.junit.Assert;
import org.junit.BeforeClass;

import ${package}.ws.api.v1.HelloWorldWs;

/**
 * 
 * @author anadal
 * 
 */
public class HelloWorldTest extends TestUtils {
  
 
  
  protected static HelloWorldWs helloWorldApi;
  
  /**
   * S'executa una vegada abans de l'execució de tots els tests d'aquesta classe
   * 
   * @throws Exception
   */
  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
    helloWorldApi = getHelloWorldApi();
  }
  
  //@Test
  public void testVersio() throws Exception {
    if (isCAIB()) {
      Assert.assertEquals("${version}-caib", helloWorldApi.getVersion());
    } else {
      Assert.assertEquals("${version}", helloWorldApi.getVersion());
    }
  }

  //@Test
  public void testVersioWs() throws Exception {
    Assert.assertEquals(1,helloWorldApi.getVersionWs());
  }
  
  
  //@Test
  public void testEcho() throws Exception {
    final String echo = "eco ecooooo";
    Assert.assertEquals(helloWorldApi.echo(echo), echo);
  }
  

  public static void main(String[] args) {
    try {
      
      HelloWorldWs helloApi = getHelloWorldApi();

      System.out.println("Versió: " + helloApi.getVersion());
      System.out.println("Versió WS: " + helloApi.getVersionWs());


    } catch (Throwable th) {
      th.printStackTrace();
    }
  }


}

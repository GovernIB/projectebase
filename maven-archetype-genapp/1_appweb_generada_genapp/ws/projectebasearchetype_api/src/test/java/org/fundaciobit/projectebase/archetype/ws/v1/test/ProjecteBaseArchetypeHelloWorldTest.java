package org.fundaciobit.projectebase.archetype.ws.v1.test;

import java.util.Locale;

import org.junit.Assert;
import org.junit.BeforeClass;

import org.fundaciobit.projectebase.archetype.ws.api.v1.ProjecteBaseArchetypeHelloWorldWs;
import org.fundaciobit.projectebase.archetype.ws.api.v1.utils.I18NUtils;

/**
 * 
 * @author anadal
 * 
 */
public class ProjecteBaseArchetypeHelloWorldTest extends ProjecteBaseArchetypeTestUtils {
  
 
  
  protected static ProjecteBaseArchetypeHelloWorldWs helloWorldApi;
  
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
      Assert.assertEquals("1.0.0-caib", helloWorldApi.getVersion());
    } else {
      Assert.assertEquals("1.0.0", helloWorldApi.getVersion());
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
      
      System.out.println(I18NUtils.tradueix(null, new Locale("ca"), "signant", new String[]{}));

      ProjecteBaseArchetypeHelloWorldWs helloApi = getHelloWorldApi();

      System.out.println("Versió ProjecteBaseArchetype   : " + helloApi.getVersion());
      System.out.println("Versió ProjecteBaseArchetype-WS: " + helloApi.getVersionWs());


    } catch (Throwable th) {
      th.printStackTrace();
    }
  }


}

package org.fundaciobit.projectebase.archetype.ws.v1.test;

import java.io.FileInputStream;
import java.util.Map;
import java.util.Properties;

import javax.xml.ws.BindingProvider;

import org.fundaciobit.projectebase.archetype.ws.api.v1.ProjecteBaseArchetypeHelloWorldWs;
import org.fundaciobit.projectebase.archetype.ws.api.v1.ProjecteBaseArchetypeHelloWorldWsService;
import org.fundaciobit.projectebase.archetype.ws.api.v1.ProjecteBaseArchetypeHelloWorldWithSecurityWs;
import org.fundaciobit.projectebase.archetype.ws.api.v1.ProjecteBaseArchetypeHelloWorldWithSecurityWsService;
import org.fundaciobit.projectebase.archetype.ws.api.v1.utils.I18NUtils;

/**
 * 
 * @author anadal
 * 
 */
public abstract class ProjecteBaseArchetypeTestUtils {

  public static final String HELLO_WORLD = "ProjecteBaseArchetypeHelloWorld";
  
  public static final String HELLO_WORLD_WITH_SECURITY = "ProjecteBaseArchetypeHelloWorldWithSecurity";
 
  // TODO GEN APP ADD OTHERS
  
  private static Properties testProperties = new Properties();
  
  static {
    // Traduccions
    try {
      Class.forName(I18NUtils.class.getName());
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    // Propietats del Servidor
    try {
      testProperties.load(new FileInputStream("test.properties"));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
 

  public static String getEndPoint(String api) {
    return testProperties.getProperty("test_host") + api;
  }

  public static String getTestAppUserName() {
    String caib = System.getProperty("caib");
    return testProperties.getProperty("test_usr" + (caib == null? "" : "_caib"));
  }
  
  public static boolean isCAIB() {
    return System.getProperty("caib") != null;
  }

  public static String getTestAppPassword() {
    String caib = System.getProperty("caib");
    return testProperties.getProperty("test_pwd"  + (caib == null? "" : "_caib"));
  }


  public static void configAddressUserPassword(String usr, String pwd,
      String endpoint, Object api) {

    Map<String, Object> reqContext = ((BindingProvider) api).getRequestContext();
    reqContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpoint);
    reqContext.put(BindingProvider.USERNAME_PROPERTY, usr);
    reqContext.put(BindingProvider.PASSWORD_PROPERTY, pwd);
  }

  public static ProjecteBaseArchetypeHelloWorldWs getHelloWorldApi() {

    final String endpoint = getEndPoint(HELLO_WORLD);

    ProjecteBaseArchetypeHelloWorldWsService helloService = new ProjecteBaseArchetypeHelloWorldWsService();

    ProjecteBaseArchetypeHelloWorldWs helloApi = helloService.getProjecteBaseArchetypeHelloWorldWs();

    // Adreça servidor
    Map<String, Object> reqContext = ((BindingProvider) helloApi).getRequestContext();
    reqContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpoint);

    return helloApi;

  }


  
 
  public static ProjecteBaseArchetypeHelloWorldWithSecurityWs getHelloWorldWithSecurityApi() {
    final String endpoint = getEndPoint(HELLO_WORLD_WITH_SECURITY);

    ProjecteBaseArchetypeHelloWorldWithSecurityWsService service = new ProjecteBaseArchetypeHelloWorldWithSecurityWsService();

    ProjecteBaseArchetypeHelloWorldWithSecurityWs api = service.getProjecteBaseArchetypeHelloWorldWithSecurityWs();

    configAddressUserPassword(getTestAppUserName(), getTestAppPassword(), endpoint, api);

    return api;
  }

  
}

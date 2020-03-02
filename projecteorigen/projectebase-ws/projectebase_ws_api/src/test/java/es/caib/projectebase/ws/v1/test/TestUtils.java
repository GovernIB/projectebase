package es.caib.projectebase.ws.v1.test;

import java.io.FileInputStream;
import java.util.Map;
import java.util.Properties;

import javax.xml.ws.BindingProvider;

import es.caib.projectebase.ws.api.v1.HelloWorldWs;
import es.caib.projectebase.ws.api.v1.HelloWorldWsService;
import es.caib.projectebase.ws.api.v1.HelloWorldWithSecurityWs;
import es.caib.projectebase.ws.api.v1.HelloWorldWithSecurityWsService;

/**
 * 
 * @author anadal
 * 
 */
public abstract class TestUtils {

  public static final String HELLO_WORLD = "HelloWorld";
  
  public static final String HELLO_WORLD_WITH_SECURITY = "HelloWorldWithSecurity";
 
  private static Properties testProperties = new Properties();
  
  static {
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

  public static HelloWorldWs getHelloWorldApi() {

    final String endpoint = getEndPoint(HELLO_WORLD);

    HelloWorldWsService helloService = new HelloWorldWsService();

    HelloWorldWs helloApi = helloService.getHelloWorldWs();

    // Adreça servidor
    Map<String, Object> reqContext = ((BindingProvider) helloApi).getRequestContext();
    reqContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpoint);

    return helloApi;

  }



 
  public static HelloWorldWithSecurityWs getHelloWorldWithSecurityApi() {
    final String endpoint = getEndPoint(HELLO_WORLD_WITH_SECURITY);

    HelloWorldWithSecurityWsService service = new HelloWorldWithSecurityWsService();

    HelloWorldWithSecurityWs api = service.getHelloWorldWithSecurityWs();

    configAddressUserPassword(getTestAppUserName(), getTestAppPassword(), endpoint, api);

    return api;
  }

  
}

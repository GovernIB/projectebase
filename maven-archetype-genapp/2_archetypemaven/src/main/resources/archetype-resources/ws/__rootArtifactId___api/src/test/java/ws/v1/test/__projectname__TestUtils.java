#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.ws.v1.test;

import java.io.FileInputStream;
import java.util.Map;
import java.util.Properties;

import javax.xml.ws.BindingProvider;

import ${package}.ws.api.v1.${projectname}HelloWorldWs;
import ${package}.ws.api.v1.${projectname}HelloWorldWsService;
import ${package}.ws.api.v1.${projectname}HelloWorldWithSecurityWs;
import ${package}.ws.api.v1.${projectname}HelloWorldWithSecurityWsService;
import ${package}.ws.api.v1.utils.I18NUtils;

/**
 * 
 * @author anadal
 * 
 */
public abstract class ${projectname}TestUtils {

  public static final String HELLO_WORLD = "${projectname}HelloWorld";
  
  public static final String HELLO_WORLD_WITH_SECURITY = "${projectname}HelloWorldWithSecurity";
 
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

  public static ${projectname}HelloWorldWs getHelloWorldApi() {

    final String endpoint = getEndPoint(HELLO_WORLD);

    ${projectname}HelloWorldWsService helloService = new ${projectname}HelloWorldWsService();

    ${projectname}HelloWorldWs helloApi = helloService.get${projectname}HelloWorldWs();

    // Adreça servidor
    Map<String, Object> reqContext = ((BindingProvider) helloApi).getRequestContext();
    reqContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpoint);

    return helloApi;

  }


  
 
  public static ${projectname}HelloWorldWithSecurityWs getHelloWorldWithSecurityApi() {
    final String endpoint = getEndPoint(HELLO_WORLD_WITH_SECURITY);

    ${projectname}HelloWorldWithSecurityWsService service = new ${projectname}HelloWorldWithSecurityWsService();

    ${projectname}HelloWorldWithSecurityWs api = service.get${projectname}HelloWorldWithSecurityWs();

    configAddressUserPassword(getTestAppUserName(), getTestAppPassword(), endpoint, api);

    return api;
  }

  
}

#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.ws.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

import javax.xml.ws.BindingProvider;

import ${package}.ws.api.HelloWorldWs;
import ${package}.ws.api.HelloWorldWsService;
import ${package}.ws.api.HelloWorldWithSecurityWs;
import ${package}.ws.api.HelloWorldWithSecurityWsService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Mètodes d'utilitat per configurar els serveis web a emprar durant els tests.
 *
 * @author anadal
 */
public abstract class TestUtils {

    private static final Logger LOG = LoggerFactory.getLogger(TestUtils.class);
    
    public static final String HELLO_WORLD = "HelloWorldWsService/HelloWorldWs";

    public static final String HELLO_WORLD_WITH_SECURITY = "HelloWorldWithSecurityWsService/HelloWorldWithSecurityWs";

    private static final Properties testProperties = new Properties();

    static {
        // Carrega la configuració a emprar pel test, urls, noms d'usuari...
        try (InputStream inputStream = new FileInputStream("test.properties")) {
            testProperties.load(inputStream);
        } catch (IOException ioe) {
            LOG.error("Error llegint test.properties", ioe);
        }
    }

    public static String getEndPoint(String api) {
        return testProperties.getProperty("test_host") + api;
    }

    public static String getTestAppUserName() {
        return testProperties.getProperty("test_user");
    }

    public static String getTestAppPassword() {
        return testProperties.getProperty("test_password");
    }

    public static void configAddressUserPassword(String usr, String pwd,
                                                 String endpoint, Object api) {

        Map<String, Object> reqContext = ((BindingProvider) api).getRequestContext();
        reqContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpoint);
        reqContext.put(BindingProvider.USERNAME_PROPERTY, usr);
        reqContext.put(BindingProvider.PASSWORD_PROPERTY, pwd);
    }

    public static HelloWorldWs getHelloWorldApi() {
        HelloWorldWsService helloService = new HelloWorldWsService();
        HelloWorldWs helloApi = helloService.getHelloWorldWs();

        // Adreça servidor
        Map<String, Object> reqContext = ((BindingProvider) helloApi).getRequestContext();
        reqContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, getEndPoint(HELLO_WORLD));

        return helloApi;
    }


    public static HelloWorldWithSecurityWs getHelloWorldWithSecurityApi() {

        HelloWorldWithSecurityWsService service = new HelloWorldWithSecurityWsService();
        HelloWorldWithSecurityWs api = service.getHelloWorldWithSecurityWs();

        configAddressUserPassword(
                getTestAppUserName(),
                getTestAppPassword(),
                getEndPoint(HELLO_WORLD_WITH_SECURITY), api);

        return api;
    }
}

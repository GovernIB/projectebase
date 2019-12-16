
/*
 * 
 */

package es.caib.projectebase.ws.api.v1;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.2.12-patch-04
 * Fri Jul 04 08:48:34 CEST 2014
 * Generated source version: 2.2.12-patch-04
 * 
 */


@WebServiceClient(name = "HelloWorldWithSecurityWsService", 
                  wsdlLocation = "http://localhost:8080/projectebase-ws-api/ws/v1/HelloWorldWithSecurity?wsdl",
                  targetNamespace = "http://impl.v1.ws.projectebase.caib.es/") 
public class HelloWorldWithSecurityWsService extends Service {

    public final static URL WSDL_LOCATION;
    public final static QName SERVICE = new QName("http://impl.v1.ws.projectebase.caib.es/", "HelloWorldWithSecurityWsService");
    public final static QName HelloWorldWithSecurityWs = new QName("http://impl.v1.ws.projectebase.caib.es/", "HelloWorldWithSecurityWs");
    static {
        URL url = null;
        try {
            url = new URL("http://localhost:8080/projectebase-ws-api/ws/v1/HelloWorldWithSecurity?wsdl");
        } catch (MalformedURLException e) {
            System.err.println("Can not initialize the default wsdl from http://localhost:8080/projectebase-ws-api/ws/v1/HelloWorldWithSecurity?wsdl");
            // e.printStackTrace();
        }
        WSDL_LOCATION = url;
    }

    public HelloWorldWithSecurityWsService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public HelloWorldWithSecurityWsService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public HelloWorldWithSecurityWsService() {
        super(WSDL_LOCATION, SERVICE);
    }
    

    /**
     * 
     * @return
     *     returns HelloWorldWithSecurityWs
     */
    @WebEndpoint(name = "HelloWorldWithSecurityWs")
    public HelloWorldWithSecurityWs getHelloWorldWithSecurityWs() {
        return super.getPort(HelloWorldWithSecurityWs, HelloWorldWithSecurityWs.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns HelloWorldWithSecurityWs
     */
    @WebEndpoint(name = "HelloWorldWithSecurityWs")
    public HelloWorldWithSecurityWs getHelloWorldWithSecurityWs(WebServiceFeature... features) {
        return super.getPort(HelloWorldWithSecurityWs, HelloWorldWithSecurityWs.class, features);
    }

}

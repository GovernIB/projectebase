#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )

/*
 * 
 */

package ${package}.ws.api.v1;

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


@WebServiceClient(name = "${projectname}HelloWorldWithSecurityWsService", 
                  wsdlLocation = "http://localhost:8080/${artifactId}/ws/v1/${projectname}HelloWorldWithSecurity?wsdl",
                  targetNamespace = "http://impl.v1.ws.${packageinverse}/") 
public class ${projectname}HelloWorldWithSecurityWsService extends Service {

    public final static URL WSDL_LOCATION;
    public final static QName SERVICE = new QName("http://impl.v1.ws.${packageinverse}/", "${projectname}HelloWorldWithSecurityWsService");
    public final static QName ${projectname}HelloWorldWithSecurityWs = new QName("http://impl.v1.ws.${packageinverse}/", "${projectname}HelloWorldWithSecurityWs");
    static {
        URL url = null;
        try {
            url = new URL("http://localhost:8080/${artifactId}/ws/v1/${projectname}HelloWorldWithSecurity?wsdl");
        } catch (MalformedURLException e) {
            System.err.println("Can not initialize the default wsdl from http://localhost:8080/${artifactId}/ws/v1/${projectname}HelloWorldWithSecurity?wsdl");
            // e.printStackTrace();
        }
        WSDL_LOCATION = url;
    }

    public ${projectname}HelloWorldWithSecurityWsService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public ${projectname}HelloWorldWithSecurityWsService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ${projectname}HelloWorldWithSecurityWsService() {
        super(WSDL_LOCATION, SERVICE);
    }
    

    /**
     * 
     * @return
     *     returns ${projectname}HelloWorldWithSecurityWs
     */
    @WebEndpoint(name = "${projectname}HelloWorldWithSecurityWs")
    public ${projectname}HelloWorldWithSecurityWs get${projectname}HelloWorldWithSecurityWs() {
        return super.getPort(${projectname}HelloWorldWithSecurityWs, ${projectname}HelloWorldWithSecurityWs.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ${projectname}HelloWorldWithSecurityWs
     */
    @WebEndpoint(name = "${projectname}HelloWorldWithSecurityWs")
    public ${projectname}HelloWorldWithSecurityWs get${projectname}HelloWorldWithSecurityWs(WebServiceFeature... features) {
        return super.getPort(${projectname}HelloWorldWithSecurityWs, ${projectname}HelloWorldWithSecurityWs.class, features);
    }

}
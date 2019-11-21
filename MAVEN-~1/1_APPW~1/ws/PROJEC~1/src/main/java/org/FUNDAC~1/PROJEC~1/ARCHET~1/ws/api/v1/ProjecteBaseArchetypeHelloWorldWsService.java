
/*
 * 
 */

package org.fundaciobit.projectebase.archetype.ws.api.v1;

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


@WebServiceClient(name = "ProjecteBaseArchetypeHelloWorldWsService", 
                  wsdlLocation = "http://localhost:8080/projectebasearchetype/ws/v1/ProjecteBaseArchetypeHelloWorld?wsdl",
                  targetNamespace = "http://impl.v1.ws.archetype.projectebase.fundaciobit.org/") 
public class ProjecteBaseArchetypeHelloWorldWsService extends Service {

    public final static URL WSDL_LOCATION;
    public final static QName SERVICE = new QName("http://impl.v1.ws.archetype.projectebase.fundaciobit.org/", "ProjecteBaseArchetypeHelloWorldWsService");
    public final static QName ProjecteBaseArchetypeHelloWorldWs = new QName("http://impl.v1.ws.archetype.projectebase.fundaciobit.org/", "ProjecteBaseArchetypeHelloWorldWs");
    static {
        URL url = null;
        try {
            url = new URL("http://localhost:8080/projectebasearchetype/ws/v1/ProjecteBaseArchetypeHelloWorld?wsdl");
        } catch (MalformedURLException e) {
            System.err.println("Can not initialize the default wsdl from http://localhost:8080/projectebasearchetype/ws/v1/ProjecteBaseArchetypeHelloWorld?wsdl");
            // e.printStackTrace();
        }
        WSDL_LOCATION = url;
    }

    public ProjecteBaseArchetypeHelloWorldWsService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public ProjecteBaseArchetypeHelloWorldWsService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ProjecteBaseArchetypeHelloWorldWsService() {
        super(WSDL_LOCATION, SERVICE);
    }
    

    /**
     * 
     * @return
     *     returns ProjecteBaseArchetypeHelloWorldWs
     */
    @WebEndpoint(name = "ProjecteBaseArchetypeHelloWorldWs")
    public ProjecteBaseArchetypeHelloWorldWs getProjecteBaseArchetypeHelloWorldWs() {
        return super.getPort(ProjecteBaseArchetypeHelloWorldWs, ProjecteBaseArchetypeHelloWorldWs.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ProjecteBaseArchetypeHelloWorldWs
     */
    @WebEndpoint(name = "ProjecteBaseArchetypeHelloWorldWs")
    public ProjecteBaseArchetypeHelloWorldWs getProjecteBaseArchetypeHelloWorldWs(WebServiceFeature... features) {
        return super.getPort(ProjecteBaseArchetypeHelloWorldWs, ProjecteBaseArchetypeHelloWorldWs.class, features);
    }

}

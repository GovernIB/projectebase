#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )

package ${package}.ws.api;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ${package}.ws.api package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _WsI18NError_QNAME = new QName("http://impl.ws.${rootArtifactId}.caib.es/", "WsI18NError");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ${package}.ws.api
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link WsI18NError }
     * 
     */
    public WsI18NError createWsI18NError() {
        return new WsI18NError();
    }

    /**
     * Create an instance of {@link WsI18NTranslation }
     * 
     */
    public WsI18NTranslation createWsI18NTranslation() {
        return new WsI18NTranslation();
    }

    /**
     * Create an instance of {@link WsI18NArgument }
     * 
     */
    public WsI18NArgument createWsI18NArgument() {
        return new WsI18NArgument();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WsI18NError }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link WsI18NError }{@code >}
     */
    @XmlElementDecl(namespace = "http://impl.ws.${rootArtifactId}.caib.es/", name = "WsI18NError")
    public JAXBElement<WsI18NError> createWsI18NError(WsI18NError value) {
        return new JAXBElement<WsI18NError>(_WsI18NError_QNAME, WsI18NError.class, null, value);
    }

}

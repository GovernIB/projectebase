
package es.caib.projectebase.sistra2.integracio.api;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the es.caib.projectebase.sistra2.integracio.api package. 
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

    private final static QName _CanviEstat_QNAME = new QName("http://www.caib.es/distribucio/ws/backofficeIntegracio", "canviEstat");
    private final static QName _CanviEstatResponse_QNAME = new QName("http://www.caib.es/distribucio/ws/backofficeIntegracio", "canviEstatResponse");
    private final static QName _Consulta_QNAME = new QName("http://www.caib.es/distribucio/ws/backofficeIntegracio", "consulta");
    private final static QName _ConsultaResponse_QNAME = new QName("http://www.caib.es/distribucio/ws/backofficeIntegracio", "consultaResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: es.caib.projectebase.sistra2.integracio.api
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CanviEstat }
     * 
     */
    public CanviEstat createCanviEstat() {
        return new CanviEstat();
    }

    /**
     * Create an instance of {@link CanviEstatResponse }
     * 
     */
    public CanviEstatResponse createCanviEstatResponse() {
        return new CanviEstatResponse();
    }

    /**
     * Create an instance of {@link Consulta }
     * 
     */
    public Consulta createConsulta() {
        return new Consulta();
    }

    /**
     * Create an instance of {@link ConsultaResponse }
     * 
     */
    public ConsultaResponse createConsultaResponse() {
        return new ConsultaResponse();
    }

    /**
     * Create an instance of {@link AnotacioRegistreId }
     * 
     */
    public AnotacioRegistreId createAnotacioRegistreId() {
        return new AnotacioRegistreId();
    }

    /**
     * Create an instance of {@link AnotacioRegistreEntrada }
     * 
     */
    public AnotacioRegistreEntrada createAnotacioRegistreEntrada() {
        return new AnotacioRegistreEntrada();
    }

    /**
     * Create an instance of {@link AnotacioRegistreBase }
     * 
     */
    public AnotacioRegistreBase createAnotacioRegistreBase() {
        return new AnotacioRegistreBase();
    }

    /**
     * Create an instance of {@link Annex }
     * 
     */
    public Annex createAnnex() {
        return new Annex();
    }

    /**
     * Create an instance of {@link Interessat }
     * 
     */
    public Interessat createInteressat() {
        return new Interessat();
    }

    /**
     * Create an instance of {@link InteressatBase }
     * 
     */
    public InteressatBase createInteressatBase() {
        return new InteressatBase();
    }

    /**
     * Create an instance of {@link Representant }
     * 
     */
    public Representant createRepresentant() {
        return new Representant();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CanviEstat }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CanviEstat }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.caib.es/distribucio/ws/backofficeIntegracio", name = "canviEstat")
    public JAXBElement<CanviEstat> createCanviEstat(CanviEstat value) {
        return new JAXBElement<CanviEstat>(_CanviEstat_QNAME, CanviEstat.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CanviEstatResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CanviEstatResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.caib.es/distribucio/ws/backofficeIntegracio", name = "canviEstatResponse")
    public JAXBElement<CanviEstatResponse> createCanviEstatResponse(CanviEstatResponse value) {
        return new JAXBElement<CanviEstatResponse>(_CanviEstatResponse_QNAME, CanviEstatResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Consulta }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Consulta }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.caib.es/distribucio/ws/backofficeIntegracio", name = "consulta")
    public JAXBElement<Consulta> createConsulta(Consulta value) {
        return new JAXBElement<Consulta>(_Consulta_QNAME, Consulta.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultaResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ConsultaResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.caib.es/distribucio/ws/backofficeIntegracio", name = "consultaResponse")
    public JAXBElement<ConsultaResponse> createConsultaResponse(ConsultaResponse value) {
        return new JAXBElement<ConsultaResponse>(_ConsultaResponse_QNAME, ConsultaResponse.class, null, value);
    }

}

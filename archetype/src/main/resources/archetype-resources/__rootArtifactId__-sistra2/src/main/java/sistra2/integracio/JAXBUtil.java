#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.sistra2.integracio;

import es.caib.distribucio.backoffice.utils.sistra.formulario.Formulario;
import es.caib.distribucio.backoffice.utils.sistra.pago.Pago;
import es.caib.distribucio.ws.backofficeintegracio.AnotacioRegistreEntrada;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

public class JAXBUtil {

    private static final JAXBContext JAXB_CONTEXT;

    private static final QName ANOTACIO_QNAME = new QName("anotacioRegistreEntrada");

    static {
        try {
            JAXB_CONTEXT = JAXBContext.newInstance(
                    AnotacioRegistreEntrada.class,
                    Formulario.class,
                    Pago.class);
        } catch (JAXBException e) {
            throw new RuntimeException("No s'ha pogut instanciar JAXBContext", e);
        }
    }

    public static String marshallAnotacio(AnotacioRegistreEntrada anotacioRegistreEntrada) {
        JAXBElement<AnotacioRegistreEntrada> element =
                new JAXBElement<>(ANOTACIO_QNAME, AnotacioRegistreEntrada.class, anotacioRegistreEntrada);
        try {
            Marshaller marshaller = JAXB_CONTEXT.createMarshaller();
            StringWriter writer = new StringWriter();
            marshaller.marshal(element, writer);
            return writer.toString();
        } catch (JAXBException e) {
            throw new RuntimeException("Error serialitzant", e);
        }
    }

    public static AnotacioRegistreEntrada unmarshallAnotacio(String value) {
        try {
            Unmarshaller unmarshaller = JAXB_CONTEXT.createUnmarshaller();
            StreamSource source = new StreamSource(new StringReader(value));
            JAXBElement<AnotacioRegistreEntrada> element =
                    unmarshaller.unmarshal(source, AnotacioRegistreEntrada.class);
            return element.getValue();
        } catch (JAXBException e) {
            throw new RuntimeException("Error deserializant", e);
        }
    }

    public static Formulario unmarshallFormulario(byte[] data) {
        try {
            Unmarshaller unmarshaller = JAXB_CONTEXT.createUnmarshaller();
            StringReader reader = new StringReader(new String(data, StandardCharsets.UTF_8));
            return (Formulario) unmarshaller.unmarshal(reader);
        } catch (JAXBException e) {
            throw new RuntimeException("Error deserializant", e);
        }
    }

    public static Pago unmarshallPago(byte[] data) {
        try {
            Unmarshaller unmarshaller = JAXB_CONTEXT.createUnmarshaller();
            StringReader reader = new StringReader(new String(data, StandardCharsets.UTF_8));
            return (Pago) unmarshaller.unmarshal(reader);
        } catch (JAXBException e) {
            throw new RuntimeException("Error deserializant", e);
        }
    }

}

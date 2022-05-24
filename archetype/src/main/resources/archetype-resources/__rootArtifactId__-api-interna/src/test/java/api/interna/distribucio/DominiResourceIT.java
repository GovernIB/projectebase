#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.api.interna.distribucio;


import ${package}.api.interna.distribucio.ValorsDomini;
import ${package}.service.exception.ServiceException;
import ${package}.service.model.Pagina;
import ${package}.service.model.UnitatOrganicaDTO;
import es.caib.sistra2.commons.plugins.dominio.rest.api.v1.RFiltroDominio;
import es.caib.sistra2.commons.plugins.dominio.rest.api.v1.RParametroDominio;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.modules.maven.ArtifactCoordinates;
import org.jboss.modules.maven.MavenResolver;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * Clase d'exemple de client de l'api REST de domini de SISTRA2. Empra l'api estàndard de Client de JAX-RS 2.1.
 */
@RunWith(Arquillian.class)
public class DominiResourceIT {

    @ArquillianResource URL baseUrl;

    /**
     * Crea l'arxiu de deploy que es desplegarà sobre JBoss per fer els tests.
     * @return arxiu desplegable.
     */
    @Deployment(testable = false)
    public static WebArchive createDeployment() throws IOException {
        try {
            MavenResolver resolver = MavenResolver.createDefaultResolver();

            WebArchive war = ShrinkWrap.create(WebArchive.class, "test.war")
                    .addAsWebResource(new File("target/openapi/openapi.json"), "openapi.json")
                    .addPackages(false, "${package}.api.interna")
                    .addPackages(false, "${package}.api.interna.distribucio")
                    .addPackages(true, "${package}.commons.rest")
                    .addPackages(true, "${package}.service")
                    .addPackages(true, "${package}.ejb")
                    .addPackages(true, "${package}.persistence")
                    .addAsResource(
                            ServiceException.class.getResource("/service/ExceptionMessages_ca.properties"),
                            "service/ExceptionMessages_ca.properties")
                    .addAsResource(
                            ServiceException.class.getResource("/service/ExceptionMessages_es.properties"),
                            "service/ExceptionMessages_es.properties")
                    .addAsWebInfResource("web.xml")
                    .addAsWebInfResource("beans.xml")
                    .addAsWebInfResource("ejb-jar.xml")
                    .addAsWebInfResource("arquillian-ds.xml")
                    .addAsLibrary(
                            resolver.resolveArtifact(
                                    ArtifactCoordinates.fromString("es.caib.sistra2:sistracom-plugin-dominio-rest-api:1.0.1"),
                                    "jar"))
                    .addAsResource("META-INF/arquillian-persistence.xml", "META-INF/persistence.xml")
                    .addAsResource("META-INF/sample_data.sql");;
            System.out.println(war.toString(true));
            return war;
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            throw e;
        }
    }

    // Client a reutilitzar durant test
    private static Client client;

    @BeforeClass
    public static void setUp() {
        // Construïm un client amb autenticació
        client = ClientBuilder.newClient();
                //.register(new BasicAuthenticator(USER, PASSWORD));
    }

    /**
     * Consulta una unitat
     */
    @Test
    public void testDominio1Valor() {

        RParametroDominio parametroDominio = new RParametroDominio("codiDir3", "A");
        RFiltroDominio filtro = new RFiltroDominio(List.of(parametroDominio));

        ValorsDomini valores  = client.target(baseUrl + "services/sistra2/dominis/unitats")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.json(filtro), ValorsDomini.class);

        Assert.assertEquals(12, valores.getNumeroFilas());
        Assert.assertEquals("A00000001", valores.getDatos().get(0).get("codiDir3"));
    }



    /**
     * Consulta totes les unitats.
     */
    @Test
    @Ignore
    public void testGetAllUnitats() {

        Pagina<UnitatOrganicaDTO> unitats = null;
        try {
            unitats = client.target(baseUrl + "services/unitats")
                    .request(MediaType.APPLICATION_JSON)
                    .get(new GenericType<Pagina<UnitatOrganicaDTO>>() {});
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }

        Assert.assertTrue(unitats.getItems().size() > 0);
    }

}

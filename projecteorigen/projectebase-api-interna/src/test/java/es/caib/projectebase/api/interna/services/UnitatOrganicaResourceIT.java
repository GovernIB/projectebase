package es.caib.projectebase.api.interna.services;


import es.caib.projectebase.service.exception.ServiceException;
import es.caib.projectebase.service.model.EstatPublicacio;
import es.caib.projectebase.service.model.Pagina;
import es.caib.projectebase.service.model.UnitatOrganicaDTO;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.util.UUID;

/**
 * Clase d'exemple de client de l'api REST. Empra l'api estàndard de Client de JAX-RS 2.1.
 *
 * Veure fitxer "META-INF/sample_data.sql" per les dades de prova
 */
@RunWith(Arquillian.class)
public class UnitatOrganicaResourceIT {

    @ArquillianResource URL baseUrl;

    /**
     * Crea l'arxiu de deploy que es desplegarà sobre JBoss per fer els tests.
     * @return arxiu desplegable.
     */
    @Deployment(testable = false)
    public static WebArchive createDeployment() {
        try {
            WebArchive war = ShrinkWrap.create(WebArchive.class, "test.war")
                    .addAsWebResource(new File("target/openapi/openapi.json"), "openapi.json")
                    .addPackages(false, "es.caib.projectebase.api.interna")
                    .addPackages(false, "es.caib.projectebase.api.interna.services")
                    .addPackages(true, "es.caib.projectebase.commons.rest")
                    .addPackages(true, "es.caib.projectebase.service")
                    .addPackages(true, "es.caib.projectebase.ejb")
                    .addPackages(true, "es.caib.projectebase.persistence")
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
                    .addAsResource("META-INF/arquillian-persistence.xml", "META-INF/persistence.xml")
                    .addAsResource("META-INF/sample_data.sql");
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
     * Consulta la unitat amb codi 1.
     */
    @Test
    @InSequence(1)
    public void testCreateUnitat() {

        // Dades de la nova unitat orgànica que crearem
        UnitatOrganicaDTO newUnitat = new UnitatOrganicaDTO();
        //no fixam id perquè és una creació
        newUnitat.setCodiDir3("A00000099");
        newUnitat.setNom("Created by test");
        newUnitat.setDataCreacio(LocalDate.now());
        newUnitat.setEstat(EstatPublicacio.ACTIU);

        // Feim el POST per crear-la
        Response response = client.target(baseUrl + "services/unitats")
                .request()
                .post(Entity.json(newUnitat));

        Assert.assertEquals(201, response.getStatus());
        // La resposta inclou la localitzacío del nou recurs creat.
        Assert.assertNotNull(response.getLocation());
    }

    /**
     * Crea una unitat duplicada
     */
    @Test
    @InSequence(2)
    public void testCreateUnitatError() {

        UnitatOrganicaDTO newUnitat = new UnitatOrganicaDTO();
        newUnitat.setCodiDir3("A00000001");
        newUnitat.setNom("Created by test duplicat");
        newUnitat.setDataCreacio(LocalDate.now());
        newUnitat.setEstat(EstatPublicacio.ACTIU);

        // Feim el POST per crear-la
        Response response = client.target(baseUrl + "services/unitats")
                .request()
                .post(Entity.json(newUnitat));

        Assert.assertEquals(400, response.getStatus());
    }

    /**
     * Consulta la unitat amb codi 1.
     */
    @Test
    @InSequence(2)
    public void testGetUnitat() {
        UnitatOrganicaDTO unitat = client.target(baseUrl + "services/unitats/1")
                .request(MediaType.APPLICATION_JSON)
                .get(UnitatOrganicaDTO.class);

        Assert.assertEquals(1L, (long) unitat.getId());
        Assert.assertEquals("A00000001", unitat.getCodiDir3());
    }

    /**
     * Consulta la unitat amb codi 999.
     */
    @Test
    @InSequence(2)
    public void testGetUnitatError() {
        Response response = client.target(baseUrl + "services/unitats/999")
                .request(MediaType.APPLICATION_JSON)
                .get();

        Assert.assertEquals(404, response.getStatus());
    }

    /**
     * Consulta totes les unitats.
     */
    @Test
    @InSequence(3)
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

    /**
     * Actualitza la unitat amb codi 1.
     */
    @Test
    @InSequence(4)
    public void testUpdateUnitat() {

        String randomString = UUID.randomUUID().toString();
        String newName = "Test " + randomString;

        UnitatOrganicaDTO unitat = new UnitatOrganicaDTO();
        unitat.setCodiDir3("A00000001");
        unitat.setNom(newName);
        unitat.setDataCreacio(LocalDate.now());
        unitat.setEstat(EstatPublicacio.ACTIU);

        Response response = client.target(baseUrl + "services/unitats/1")
                .request()
                .put(Entity.json(unitat)); // Les actualitzacions són un mètode PUT

        // La resposta quan tot ha anat bé és un 204, ja que no envia contingut.
        Assert.assertEquals(204, response.getStatus());

        UnitatOrganicaDTO unitatModificada = client.target(baseUrl + "services/unitats/1")
                .request(MediaType.APPLICATION_JSON)
                .get(UnitatOrganicaDTO.class);

        Assert.assertEquals(newName, unitatModificada.getNom());
        Assert.assertEquals(LocalDate.now(), unitatModificada.getDataCreacio()); // no començar el test a les 23:59:59 ;)
        Assert.assertEquals(EstatPublicacio.ACTIU, unitatModificada.getEstat());
    }

    /**
     * Actualitza la unitat amb codi 999.
     */
    @Test
    @InSequence(4)
    public void testUpdateUnitatError() {

        UnitatOrganicaDTO unitat = new UnitatOrganicaDTO();
        unitat.setCodiDir3("A99999999");
        unitat.setNom("test");
        unitat.setDataCreacio(LocalDate.now());
        unitat.setEstat(EstatPublicacio.ACTIU);

        Response response = client.target(baseUrl + "services/unitats/999")
                .request()
                .put(Entity.json(unitat)); // Les actualitzacions són un mètode PUT

        Assert.assertEquals(404, response.getStatus());
    }

    /**
     * Intenta esborrar la unitat orgànica amb codi 1.
     */
    @Test
    @InSequence(5)
    public void testDelete() {

        Response response = client.target(baseUrl + "services/unitats/15")
                .request().delete();

        // La resposta quan tot ha anat bé és un 204, ja que no envia contingut.
        Assert.assertEquals(204, response.getStatus());
    }

    /**
     * Intenta esborrar la unitat orgànica amb codi 999.
     */
    @Test
    @InSequence(5)
    public void testDeleteError() {

        Response response = client.target(baseUrl + "services/unitats/999")
                .request().delete();

        Assert.assertEquals(404, response.getStatus());
    }

    /**
     * Test capçaleres CORS OPTIONS
     */
    @Test
    @InSequence(6)
    public void testCorsHeaderPreflight() {

        Response response = client.target(baseUrl + "services/unitats")
                .request().header("Origin", "http://localhost").options();

        Assert.assertEquals(200, response.getStatus());
        Assert.assertEquals("*", response.getHeaderString("Access-Control-Allow-Origin"));
        Assert.assertEquals("GET, POST, PUT, DELETE, OPTIONS, HEAD",
                response.getHeaderString("Access-Control-Allow-Methods"));
    }

    /**
     * Test capçaleres CORS GET
     */
    @Test
    @InSequence(7)
    public void testCorsHeaderGet() {

        Response response = client.target(baseUrl + "services/unitats")
                .request().header("Origin", "http://localhost").get();

        Assert.assertEquals(200, response.getStatus());
        Assert.assertEquals("*", response.getHeaderString("Access-Control-Allow-Origin"));
        Assert.assertNull(response.getHeaderString("Access-Control-Allow-Methods"));
    }

    /**
     * Test capçaleres CORS GET
     */
    @Test
    @InSequence(8)
    public void testCorsHeaderOpenApi() {

        Response response = client.target(baseUrl + "openapi.json")
                .request().header("Origin", "http://localhost").get();

        Assert.assertEquals(200, response.getStatus());
        Assert.assertEquals("*", response.getHeaderString("Access-Control-Allow-Origin"));
        Assert.assertNull(response.getHeaderString("Access-Control-Allow-Methods"));
    }
}

package es.caib.projectebase.ejb.facade;

import es.caib.projectebase.service.exception.ServiceException;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;

import javax.ejb.EJB;

public abstract class AbstractFacadeIT {

    @EJB
    protected AdminManager adminManager;

    /**
     * Crea l'arxiu de deploy que es desplegarà sobre JBoss per fer els tests.
     * @return arxiu desplegable.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        try {
            JavaArchive jar = ShrinkWrap.create(JavaArchive.class, "test.jar")
                    .addPackages(true, "es.caib.projectebase.ejb")
                    .addPackages(true, "es.caib.projectebase.persistence")
                    .addPackages(true, "es.caib.projectebase.service")
                    .addAsResource(
                            ServiceException.class.getResource("/service/ExceptionMessages_ca.properties"),
                            "service/ExceptionMessages_ca.properties")
                    .addAsResource(
                            ServiceException.class.getResource("/service/ExceptionMessages_es.properties"),
                            "service/ExceptionMessages_es.properties")
                    .addAsResource("META-INF/beans.xml")
                    .addAsResource("META-INF/ejb-jar.xml")
                    .addAsResource("META-INF/arquillian-persistence.xml", "META-INF/persistence.xml")
                    .addAsResource("META-INF/arquillian-ds.xml")
                    .addAsResource("META-INF/sample_data.sql");
            System.out.println(jar.toString(true));
            return jar;
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            throw e;
        }
    }
}

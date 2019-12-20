#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.jpa.test;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Superclasse dels tests amb JPA que inicia abans dels tests l'EntityManagerFactory i el tanca al final,
 * a més d'obrir i tancar abans de cada test l'EntityManager.
 *
 * @author areus
 */
class JPATest {

    static EntityManagerFactory emf;
    static EntityManager em;

    @BeforeAll
    static void init() {
        emf = Persistence.createEntityManagerFactory("testPU");
    }

    @BeforeEach
    void createEntityManager() {
        em = emf.createEntityManager();
    }

    @AfterEach
    void closeEntityManager() {
        em.clear();
        em.close();
    }

    @AfterAll
    static void tearDown(){
        emf.close();
    }
}

package es.caib.projectebase.ejb;

import es.caib.projectebase.commons.i18n.I18NArgumentCode;
import es.caib.projectebase.commons.i18n.I18NArgumentString;
import es.caib.projectebase.commons.i18n.I18NException;
import es.caib.projectebase.ejb.interceptor.Logged;
import es.caib.projectebase.ejb.utils.I18NTranslatorEjb;
import es.caib.projectebase.jpa.UnitatOrganica;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Locale;

/**
 * Servei EJB per gestionar {@link UnitatOrganica}. Li aplicam l'interceptor {@link Logged},
 * per tant, totes les cridades es loguejeran.
 *
 * @author areus
 */
@Logged
@Stateless
public class UnitatOrganicaEJB implements UnitatOrganicaService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void bulkCreate(@NotNull List<UnitatOrganica> unitats) {
        unitats.forEach(entityManager::persist);
    }

    @Override
    public UnitatOrganica create(UnitatOrganica unitatOrganica) {
        entityManager.persist(unitatOrganica);
        return unitatOrganica;
    }

    @Override
    public UnitatOrganica update(UnitatOrganica unitatOrganica) {
        return entityManager.merge(unitatOrganica);
    }

    @Override
    public void deleteById(Long id) {
        UnitatOrganica unitatOrganica = entityManager.getReference(UnitatOrganica.class, id);
        entityManager.remove(unitatOrganica);
    }

    @Override
    public UnitatOrganica findById(Long id) {
        return entityManager.find(UnitatOrganica.class, id);
    }

    @Override
    public List<UnitatOrganica> findAll() {
        TypedQuery<UnitatOrganica> query = entityManager.createQuery("select uo from UnitatOrganica uo",
                UnitatOrganica.class);
        return query.getResultList();
    }

    @Override
    public List<UnitatOrganica> findAllPaged(@PositiveOrZero int first, @Positive int pageSize) {
        TypedQuery<UnitatOrganica> query = entityManager.createQuery("select uo from UnitatOrganica uo",
                UnitatOrganica.class);
        query.setFirstResult(first);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }

    @Override
    public long countAll() {
        TypedQuery<Long> query = entityManager.createQuery("select count(uo) from UnitatOrganica uo", Long.class);
        return query.getSingleResult();
    }

    @Override
    public List<UnitatOrganica> findFilteredPaged(String filter, @PositiveOrZero int first, @Positive int pageSize) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<UnitatOrganica> cq = cb.createQuery(UnitatOrganica.class);
        final Root<UnitatOrganica> root = cq.from(UnitatOrganica.class);
        cq.select(root);
        applyFilter(filter, cq, root);

        TypedQuery<UnitatOrganica> query = entityManager.createQuery(cq);
        query.setFirstResult(first);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }

    @Override
    public long countFiltered(String filter) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        final Root<UnitatOrganica> root = cq.from(UnitatOrganica.class);
        cq.select(cb.count(root));
        applyFilter(filter, cq, root);

        TypedQuery<Long> query = entityManager.createQuery(cq);
        return query.getSingleResult();
    }

    /**
     * Modifica l'objecte CriteriaQuery afegint el filtratge dels camps codiDir3 i nom.
     * 
     * @param filter
     *            Cadena a cercar dins el camps codiDir3 i nom.
     * @param cq
     *            query on aplicar el filtre.
     * @param root
     *            objecte emprant al from del select d'on s'agafen els camps codiDir3 i nom.
     */
    private void applyFilter(String filter, CriteriaQuery<?> cq, Root<UnitatOrganica> root) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        if (filter != null && !filter.isEmpty()) {
            String filterExpression = "%" + filter.toLowerCase() + "%";
            cq.where(cb.or(cb.like(cb.lower(root.get("codiDir3")), filterExpression),
                    cb.like(cb.lower(root.get("nom")), filterExpression)));
        }
    }

    @Override
    public void testTranslationError() throws I18NException {

        Locale[] locales = new Locale[] { new Locale("es"), new Locale("en") };

        String[] labels = { "example.error", // EJB
                "error.query", // JPA
                "javax.validation.constraints.Size.message" // ValidationMessages.properties
        };

        log.info("\n\n\n");
        log.info("======= TRADUCCIONS EJB =============");

        for (Locale locale : locales) {

            for (String label : labels) {
                log.info("Traduccio{" + locale.getLanguage() + "}[" + label + "] => |"
                        + I18NTranslatorEjb.tradueix(locale, label) + "|");
            }
        }

        log.info("\n\n\n");

        throw new I18NException("example.error", new I18NArgumentCode("PARAM1.CODE"),
                new I18NArgumentString("AIXO ES UN ARGUMENT STRING"));

    }
}

package es.caib.projectebase.ejb.repository;

import es.caib.projectebase.service.model.Ordre;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Classe d'utilitat per treballar amb queries Criteria.
 *
 * @author areus
 */
public class CriteriaHelper {

    private final CriteriaBuilder builder;
    private final Root<?> root;

    private CriteriaHelper(CriteriaBuilder builder, Root<?> root) {
        this.builder = builder;
        this.root = root;
    }

    public static CriteriaHelper getInstance(CriteriaBuilder builder, Root<?> root) {
        return new CriteriaHelper(builder, root);
    }

    public void applyFilters(Map<String, Object> filters, CriteriaQuery<?> criteriaQuery) {
        if (!filters.isEmpty()) {
            List<Predicate> predicates = new ArrayList<>();
            filters.forEach(
                    (key, value) -> predicates.add(getPredicate(key, value))
            );
            criteriaQuery.where(predicates.toArray(Predicate[]::new));
        }
    }

    private Predicate getPredicate(String field, Object value) {
        Path<?> path = root.get(field);
        if (path.getJavaType() == String.class) {
            // Si el tipo java és string, llavors segur que és un Path<String>
            @SuppressWarnings("unchecked")
            Path<String> stringPath = (Path<String>) path;
            return builder.like(stringPath, value + "%");
        } else {
            return builder.equal(path, value);
        }
    }

    public void applyOrder(List<Ordre> ordenacio, CriteriaQuery<?> criteriaQuery) {
        List<Order> orderList = ordenacio.stream()
                .map(o -> o.isAscendent() ? builder.asc(root.get(o.getAtribut()))
                        : builder.desc(root.get(o.getAtribut())))
                .collect(Collectors.toList());
        criteriaQuery.orderBy(orderList);
    }
}

package es.caib.projectebase.ejb.repository;

import es.caib.projectebase.persistence.model.BaseEntity;
import es.caib.projectebase.service.model.Atribut;
import es.caib.projectebase.service.model.Ordre;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Classe abstracte d'utilitat per treballar amb queries Criteria. Les subclasses bàsicament han de sobreescriure
 * el mètode {@link #getPath(Enum)} per mapejar noms d'atributs a expressions "path" que els mètodes d'aquesta
 * classe empraran per construir predicats i ordenacions.
 *
 * @param <E> tipus de l'entity
 * @param <A> enumeració que representa els atributs per filtrar o ordenar.
 * @author areus
 */
public abstract class AbstractCriteriaHelper<E extends BaseEntity, A extends Enum<A> & Atribut> {

    protected final CriteriaBuilder builder;
    protected final Root<E> root;

    public AbstractCriteriaHelper(CriteriaBuilder builder, Root<E> root) {
        this.builder = builder;
        this.root = root;
    }

    /**
     * A partir d'uns filtres construeix els predicats per filtrar.
     * @param filters map de filtres
     * @return array de predicats que representen els filtres.
     */
    public Predicate[] getPredicates(Map<A, Object> filters) {
        List<Predicate> predicates = new ArrayList<>();
        filters.forEach(
                (key, value) -> predicates.add(getPredicate(key, value))
        );
        return predicates.toArray(Predicate[]::new);
    }

    /**
     * Obté un predicat a aplicar a partir d'un atribut i un valor.
     * En el cas d'atributs que es corresponen a camp String fa un {@code like = 'valor%'}, en qualsevol altre
     * cas fa un equals.
     * Obtendrà l'expressió "path" amb la que comprar amb el mètode {@link #getPath(Enum)}. Les subclasses poden
     * sobreescriure aquest mètode per implementar el predicat d'un camp concret d'una manera diferent.
     * @param field atribut
     * @param value valor amb el que comparar.
     * @return predicat
     */
    protected Predicate getPredicate(A field, Object value) {
        Path<?> path = getPath(field);
        if (path.getJavaType() == String.class) {
            // Si el tipo java és string, llavors segur que és un Path<String>
            @SuppressWarnings("unchecked")
            Path<String> stringPath = (Path<String>) path;
            return builder.like(stringPath, value + "%");
        } else {
            return builder.equal(path, value);
        }
    }

    /**
     * Obté una llista d'objectes Order per rerepsentar l'ordenació dins JPA a partir dels atributs indicats.
     * Per obtenir l'expressió "path" amb la que construir els Order empra el mètode {@link #getPath(Enum)}.
     * @param ordenacio llista d'ordenacions per atribut.
     * @return llista d'ordenació.
     */
    public List<Order> getOrderList(List<Ordre<A>> ordenacio) {
        return ordenacio.stream()
                .map(o -> o.isAscendent() ? builder.asc(getPath(o.getAtribut()))
                        : builder.desc(getPath(o.getAtribut())))
                .collect(Collectors.toList());
    }

    /**
     * Obté una expressió "path" a partir del root pel nom d'atribut definit.
     * @param atribut atribut a partir del qual obtenir el path.
     * @return expressió path.
     */
    protected abstract Path<?> getPath(A atribut);
}

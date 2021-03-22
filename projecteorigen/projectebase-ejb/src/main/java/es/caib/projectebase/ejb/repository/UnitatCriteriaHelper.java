package es.caib.projectebase.ejb.repository;

import es.caib.projectebase.persistence.model.UnitatOrganica;
import es.caib.projectebase.persistence.model.UnitatOrganica_;
import es.caib.projectebase.service.model.AtributUnitat;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

/**
 * Implentació del mapeig entre atributs de la capa de serveis de les Unitats Orgàniques i els paths corresponents
 * de la capa de persistència.
 */
public class UnitatCriteriaHelper extends AbstractCriteriaHelper<UnitatOrganica, AtributUnitat> {

    public UnitatCriteriaHelper(CriteriaBuilder builder, Root<UnitatOrganica> root) {
        super(builder, root);
    }

    /**
     * Mapeja els noms d'atributs amb el que treballa la capa de serveis,
     * @param atribut atribut de unitats orgàniques emprat a la capa de servei.
     * @return path obtingut a partir del {@link Root<UnitatOrganica>}.
     */
    @Override
    protected Path<?> getPath(AtributUnitat atribut) {
        switch (atribut) {
            case id:
                return root.get(UnitatOrganica_.id);
            case codiDir3:
                return root.get(UnitatOrganica_.codiDir3);
            case nom:
                return root.get(UnitatOrganica_.nom);
            case dataCreacio:
                return root.get(UnitatOrganica_.dataCreacio);
            case estat:
                return root.get(UnitatOrganica_.estat);
            default:
                throw new IllegalArgumentException("Valor no vàlid " + atribut);
        }
    }
}

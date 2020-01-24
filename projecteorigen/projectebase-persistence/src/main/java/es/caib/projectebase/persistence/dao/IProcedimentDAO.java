package es.caib.projectebase.persistence.dao;

import es.caib.projectebase.persistence.Procediment;

import java.util.List;


/**
 * Interfície del servei per gestionar {@link Procediment}
 *
 * @author areus
 * @author anadal
 */
public interface IProcedimentDAO extends DAO<Procediment, Long> {

    /**
     * Obté una llista de tots els procediments d'una unitat orgànica.
     *
     * @param unitatOrganicaId
     *            identificador de la unitat orgànica.
     * @return llista de procediments de la unitat orgànica.
     */
    List<Procediment> findAllByUnitatOrganica(Long unitatOrganicaId);

    /**
     * Obté el nombre de procediments d'una unitat orgànica
     * 
     * @param unitatOrganicaId
     *            identificador de la unitat orgànica.
     * @return nombre de procediments de la unitat orgànica
     */
    Long countAllByUnitatOrganica(Long unitatOrganicaId);
    
    
}

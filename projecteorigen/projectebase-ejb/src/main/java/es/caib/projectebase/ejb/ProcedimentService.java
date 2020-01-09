package es.caib.projectebase.ejb;

import es.caib.projectebase.commons.i18n.I18NException;
import es.caib.projectebase.jpa.Procediment;
import es.caib.projectebase.jpa.dao.IProcedimentManager;

import javax.ejb.Local;


import java.util.List;

/**
 * Interfície del servei per gestionar {@link Procediment}
 *
 * @author areus
 * @author anadal
 */
@Local
public interface ProcedimentService extends IProcedimentManager {

    /**
     * Crea un procediment depenent d'una unitat orgànica.
     *
     * @param procediment
     *            procediment.
     * @param unitatOrganicaId
     *            identificador de la unitat orgànica.
     * @return El procediment creat.
     */
    Procediment create(Procediment procediment, Long unitatOrganicaId) throws I18NException;

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
     * @return
     */
    Long countAllByUnitatOrganica(Long unitatOrganicaId);
}

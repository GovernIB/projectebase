package es.caib.projectebase.service;

import es.caib.projectebase.jpa.Procediment;

import javax.ejb.Local;
import java.util.List;

/**
 * Interfície del servei per gestionar {@link Procediment}
 *
 * @author areus
 */
@Local
public interface ProcedimentService {

    /**
     * Crea un procediment depenent d'una unitat orgànica.
     *
     * @param procediment procediment.
     * @param unitatOrganicaId identificador de la unitat orgànica.
     * @return El procediment creat.
     */
    Procediment create(Procediment procediment, Long unitatOrganicaId);

    /**
     * Actualitza un procediment.
     *
     * @param procediment procediment
     * @return El procediment actualitzat
     */
    Procediment update(Procediment procediment);

    /**
     * Esborra un procediment amb l'identificador indicat.
     *
     * @param id Identificador del procediment.
     */
    void deleteById(Long id);

    /**
     * Obté un procediment amb l'identificador indicat.
     *
     * @param id identificador del procediment.
     * @return El procediment o <code>null</code> si no n'hi ha cap amb l'id indicat.
     */
    Procediment findById(Long id);

    /**
     * Obté una llista de tots els procediments d'una unitat orgànica.
     *
     * @param unitatOrganicaId identificador de la unitat orgànica.
     * @return llista de procediments de la unitat orgànica.
     */
    List<Procediment> findAllByUnitatOrganica(Long unitatOrganicaId);

    /**
     * Obté el nombre de procediments d'una unitat orgànica
     * @param unitatOrganicaId identificador de la unitat orgànica.
     * @return nombre de procediments relacionats amb la unitat orgànica
     */
    Long countAllByUnitatOrganica(Long unitatOrganicaId);

    /**
     * Obté una llista dels procediments d'una unitat orgànica que compleixen un filtre.
     * La cadena de filtre es cerca dins els camps de tipus string: codiSia i nom.
     *
     * @param unitatOrganicaId identificador de la unitat orgànica.
     * @param filter cadena de caràcters que es cercarà dins els camps de tipus string: codiSia i nom.
     * @return llista de procediments de la unitat orgànica que compleixen el filtre.
     */
    List<Procediment> findAllByUnitatOrganica(Long unitatOrganicaId, String filter);

    /**
     * Obté el nombre de procediments d'una unitat orgànica que compleixein un filtre.
     * @param unitatOrganicaId identificador de la unitat orgànica.
     * @param filter cadena de caràcters que es cercarà dins els camps de tipus string: codiSia i nom.
     * @return nombre de procediments relacionats amb la unitat orgànica que compleixen el filtre.
     */
    Long countAllByUnitatOrganica(Long unitatOrganicaId, String filter);
}

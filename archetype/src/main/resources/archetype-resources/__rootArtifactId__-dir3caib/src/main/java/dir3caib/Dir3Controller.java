#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.dir3caib;

import ${package}.dir3caib.api.CodigoValor;
import ${package}.dir3caib.api.Dir3Service;
import ${package}.dir3caib.api.Nodo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Controlador per gestionar la cerca d'organismes amb Dir3Caib
 *
 * @author areus
 */
@Named
@ViewScoped
public class Dir3Controller implements Serializable {

    private static final long serialVersionUID = -9139731221657909836L;

    private static final Logger LOG = LoggerFactory.getLogger(Dir3Controller.class);

    /**
     * Client de dir3caib
     */
    @Inject
    private Dir3Service dir3Service;

    // Llistats d'opcions per els selects
    private List<CodigoValor> comunitatsDisponibles;
    private List<CodigoValor> provinciesDisponibles;
    private List<CodigoValor> municipisDisponibles;
    private List<CodigoValor> nivellsDisponibles;

    // Camps del formulari
    private String denominacio;
    private String codi;
    private Object nivell;
    private Object comunitat;
    private Object provincia;
    private Object municipi;
    private boolean arrels;
    private boolean vigents = true;

    private List<Nodo> resultatCerca;

    @PostConstruct
    public void init() {
        LOG.info("init");
        comunitatsDisponibles = dir3Service.comunidadesAutonomas();
        nivellsDisponibles = dir3Service.nivelesAdministracion();
    }

    public void loadProvincies() {
        provinciesDisponibles = comunitat != null ? dir3Service.provinciasComunidad(comunitat) : new ArrayList<>();
    }

    public void loadMunicipis() {
        municipisDisponibles = provincia != null ? dir3Service.municipios(provincia) : new ArrayList<>();
    }

    public void cercarOrganismes() {
        LOG.info("cercarOganismes");
        resultatCerca = dir3Service.busquedaOrganismos(codi, denominacio, nivell,
                comunitat, false, arrels, provincia, municipi, vigents);
        LOG.info("Resultat: {}", resultatCerca);
    }

    // Getters & Setters

    public List<CodigoValor> getComunitatsDisponibles() {
        return comunitatsDisponibles;
    }

    public List<CodigoValor> getProvinciesDisponibles() {
        return provinciesDisponibles;
    }

    public List<CodigoValor> getMunicipisDisponibles() {
        return municipisDisponibles;
    }

    public List<CodigoValor> getNivellsDisponibles() {
        return nivellsDisponibles;
    }

    public List<Nodo> getResultatCerca() {
        return resultatCerca;
    }

    public String getDenominacio() {
        return denominacio;
    }

    public void setDenominacio(String denominacio) {
        this.denominacio = denominacio;
    }

    public String getCodi() {
        return codi;
    }

    public void setCodi(String codi) {
        this.codi = codi;
    }

    public Object getNivell() {
        return nivell;
    }

    public void setNivell(Object nivell) {
        this.nivell = nivell;
    }

    public Object getComunitat() {
        return comunitat;
    }

    public void setComunitat(Object comunitat) {
        this.comunitat = comunitat;
    }

    public Object getProvincia() {
        return provincia;
    }

    public void setProvincia(Object provincia) {
        this.provincia = provincia;
    }

    public Object getMunicipi() {
        return municipi;
    }

    public void setMunicipi(Object municipi) {
        this.municipi = municipi;
    }

    public boolean isArrels() {
        return arrels;
    }

    public void setArrels(boolean arrels) {
        this.arrels = arrels;
    }

    public boolean isVigents() {
        return vigents;
    }

    public void setVigents(boolean vigents) {
        this.vigents = vigents;
    }
}

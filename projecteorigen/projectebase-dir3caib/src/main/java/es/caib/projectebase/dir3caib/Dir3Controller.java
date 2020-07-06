package es.caib.projectebase.dir3caib;

import es.caib.projectebase.dir3caib.api.CodigoValor;
import es.caib.projectebase.dir3caib.api.Dir3Service;
import es.caib.projectebase.dir3caib.api.Nodo;
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
 * Controlador per gestionar la creació de notificacions.
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

    private List<CodigoValor> comunitatsDisponibles;
    private List<CodigoValor> provinciesDisponibles;
    private List<CodigoValor> municipisDisponibles;
    private List<CodigoValor> nivellsDisponibles;

    private Object comunitat;
    private Object provincia;
    private Object municipi;
    private Object nivell;

    private List<Nodo> resultatCerca;

    @PostConstruct
    public void init() {
        LOG.info("init");
        comunitatsDisponibles = dir3Service.comunidadesAutonomas();
        nivellsDisponibles = dir3Service.nivelesAdministracion();
    }

    public void loadProvincies() {
        provinciesDisponibles = comunitat != null ? dir3Service.provinciasComunidad(comunitat) : new ArrayList<>();
        provincia = null;
        loadMunicipis();
    }

    public void loadMunicipis() {
        municipisDisponibles = provincia != null ? dir3Service.municipios(provincia) : new ArrayList<>();
    }

    public void cercarOrganismes() {
        LOG.info("cercarOganismes");
        resultatCerca = dir3Service.busquedaOrganismos("", "", nivell,
                comunitat, false, true, provincia, municipi, true);
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

    public Object getNivell() {
        return nivell;
    }

    public void setNivell(Object nivell) {
        this.nivell = nivell;
    }
}

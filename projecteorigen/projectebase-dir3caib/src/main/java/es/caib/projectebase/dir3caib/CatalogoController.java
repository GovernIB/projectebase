package es.caib.projectebase.dir3caib;

import es.caib.projectebase.dir3caib.api.CodigoValor;
import es.caib.projectebase.dir3caib.api.Dir3Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * Controlador per gestionar la creació de notificacions.
 *
 * @author areus
 */
@Named
@ViewScoped
public class CatalogoController implements Serializable {

    private static final long serialVersionUID = -9139731221657909836L;

    private static final Logger LOG = LoggerFactory.getLogger(CatalogoController.class);

    /**
     * Client de dir3caib
     */
    @Inject
    private Dir3Service dir3Service;

    private List<CodigoValor> comunitatsDisponibles;
    private List<CodigoValor> provinciesDisponibles;
    private List<CodigoValor> municipisDisponibles;

    private Object comunitat;
    private Object provincia;
    private Object municipi;

    @PostConstruct
    public void init() {
        LOG.info("init");
        comunitatsDisponibles = dir3Service.comunidadesAutonomas();
        comunitat = comunitatsDisponibles.get(0).getId();

        loadProvincies();
    }

    public void loadProvincies() {
        provinciesDisponibles = dir3Service.provinciasComunidad(comunitat);
        provincia = provinciesDisponibles.get(0).getId();
        loadMunicipis();
    }

    public void loadMunicipis() {
        municipisDisponibles = dir3Service.municipios(provincia);
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
}

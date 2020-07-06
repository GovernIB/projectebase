package es.caib.projectebase.dir3caib.api;

/**
 * Model emprat per Api Rest Dir3Caib
 */
public class ObjetoDirectorio {

    private String codigo;
    private String denominacion;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }
}

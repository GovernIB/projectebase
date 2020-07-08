package es.caib.projectebase.dir3caib.api;

/**
 * Model emprat per Api Rest Dir3Caib per retornar llistes de valors
 */
public class CodigoValor {

    private Object id;
    private String descripcion;

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "CodigoValor{" +
                "id=" + id +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
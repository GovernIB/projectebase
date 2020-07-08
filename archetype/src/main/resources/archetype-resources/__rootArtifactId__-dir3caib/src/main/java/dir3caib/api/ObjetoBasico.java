#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.dir3caib.api;

/**
 * Model emprat per Api Rest Dir3Caib per retornar organismes.
 */
public class ObjetoBasico {

    private String codigo;
    private String denominacion;
    private String descripcionEstado;
    private String raiz;
    private String superior;
    private String localidad;
    private String edpPrincipal;

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

    public String getDescripcionEstado() {
        return descripcionEstado;
    }

    public void setDescripcionEstado(String descripcionEstado) {
        this.descripcionEstado = descripcionEstado;
    }

    public String getRaiz() {
        return raiz;
    }

    public void setRaiz(String raiz) {
        this.raiz = raiz;
    }

    public String getSuperior() {
        return superior;
    }

    public void setSuperior(String superior) {
        this.superior = superior;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getEdpPrincipal() {
        return edpPrincipal;
    }

    public void setEdpPrincipal(String edpPrincipal) {
        this.edpPrincipal = edpPrincipal;
    }

    @Override
    public String toString() {
        return "ObjetoBasico{" +
                "codigo='" + codigo + '${symbol_escape}'' +
                ", denominacion='" + denominacion + '${symbol_escape}'' +
                '}';
    }
}

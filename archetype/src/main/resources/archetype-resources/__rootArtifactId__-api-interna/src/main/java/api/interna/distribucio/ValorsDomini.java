#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.api.interna.distribucio;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Classe emprada per retornar els valors d'un domini.
 * Modelada per representar les mateixes dades que la classe específica del API de SISTRA2
 * {@link es.caib.sistra2.commons.plugins.dominio.rest.api.v1.RValoresDominio}.
 * Es requereix aquesta classa perquè per una banda, la presència de mètodes setter amb varis paràmetres a la classe
 * original provoca un error a la serialització a JSON amb JBoss 7.2.0.
 * D'altra banda, l'absència d'un mètode setDatos no permet desarialitzar-la correctament amb JAX-RS.
 * Veure https://github.com/eclipse-ee4j/yasson/issues/82
 */
public class ValorsDomini {

    private List<Map<String, String>> datos = new ArrayList<>();
    private String codigoRetorno;
    private boolean error;
    private String codigoError;
    private String descripcionError;

    public List<Map<String, String>> getDatos() {
        return datos;
    }

    public void setDatos(List<Map<String, String>> datos) {
        this.datos = datos;
    }

    public String getCodigoRetorno() {
        return codigoRetorno;
    }

    public void setCodigoRetorno(String codigoRetorno) {
        this.codigoRetorno = codigoRetorno;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getCodigoError() {
        return codigoError;
    }

    public void setCodigoError(String codigoError) {
        this.codigoError = codigoError;
    }

    public String getDescripcionError() {
        return descripcionError;
    }

    public void setDescripcionError(String descripcionError) {
        this.descripcionError = descripcionError;
    }

    public int getNumeroFilas() {
        return this.datos.size();
    }

    public List<String> getNombreColumnas() {
        List<String> nombresColumnas = new ArrayList<>();
        if (!datos.isEmpty()) {
            Map<String, String> fila = this.datos.get(0);
            nombresColumnas.addAll(fila.keySet());
        }
        return nombresColumnas;
    }
}

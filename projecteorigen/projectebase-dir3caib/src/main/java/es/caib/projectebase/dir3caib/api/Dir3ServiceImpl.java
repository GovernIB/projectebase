package es.caib.projectebase.dir3caib.api;

import es.caib.projectebase.dir3caib.Configuracio;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Implementació de les cridades a l'API REST de Dir3Caib
 *
 * @author areus
 */
@ApplicationScoped
public class Dir3ServiceImpl implements Dir3Service {

    private String baseUrl;
    private Client client;

    protected Dir3ServiceImpl() {
    	
    }
    
    @Inject
    public Dir3ServiceImpl(Configuracio configuracio) {
        baseUrl = configuracio.getBaseUrl();
    }

    private static final String CATALOGO_COMUNIDADES_AUTONOMAS = "/rest/catalogo/comunidadesAutonomas";
    private static final String CATALOGO_PROVINCIAS_COMUNIDAD = "/rest/catalogo/provincias/comunidadAutonoma";
    private static final String CATALOGO_LOCALIDADES = "/rest/catalogo/localidades/provincia/entidadGeografica";
    private static final String CATALOGO_NIVELES_ADMINISTRACION = "/rest/catalogo/nivelesAdministracion";
    private static final String BUSQUEDA_ORGANISMOS = "/rest/busqueda/organismos";

    private static final String ENTIDAD_GEOGRAFICA_MUNICIPIO = "01";

    @PostConstruct
    private void init() {
        client = ClientBuilder.newClient();
    }

    @PreDestroy
    private void destroy() {
        client.close();
    }

    @Override
    public List<CodigoValor> comunidadesAutonomas() {
        return client.target(baseUrl + CATALOGO_COMUNIDADES_AUTONOMAS)
                .request(MediaType.APPLICATION_JSON)
                .get(new GenericType<>() {});
    }

    @Override
    public List<CodigoValor> provinciasComunidad(Object idComunidad) {
        return client.target(baseUrl + CATALOGO_PROVINCIAS_COMUNIDAD)
                .queryParam("id", idComunidad)
                .request(MediaType.APPLICATION_JSON)
                .get(new GenericType<>() {});
    }

    @Override
    public List<CodigoValor> municipios(Object idProvincia) {
        return client.target(baseUrl + CATALOGO_LOCALIDADES)
                .queryParam("codigoProvincia", idProvincia)
                .queryParam("codigoEntidadGeografica", ENTIDAD_GEOGRAFICA_MUNICIPIO)
                .request(MediaType.APPLICATION_JSON)
                .get(new GenericType<>() {});
    }

    @Override
    public List<CodigoValor> nivelesAdministracion() {
        return client.target(baseUrl + CATALOGO_NIVELES_ADMINISTRACION)
                .request(MediaType.APPLICATION_JSON)
                .get(new GenericType<>() {});
    }

    @Override
    public List<Nodo> busquedaOrganismos(String codigo, String denominacion, Object codNivelAdministracion,
                                         Object codComunidadAutonoma, boolean conOficinas, boolean unidadRaiz,
                                         Object provincia, Object localidad, boolean vigentes) {
        return client.target(baseUrl + BUSQUEDA_ORGANISMOS)
                .queryParam("codigo", codigo == null ? "" : codigo)
                .queryParam("denominacion", denominacion == null ? "" : denominacion)
                .queryParam("codNivelAdministracion", codNivelAdministracion == null ? "" : codNivelAdministracion)
                .queryParam("codComunidadAutonoma", codComunidadAutonoma == null ? "" : codComunidadAutonoma)
                .queryParam("conOficinas", conOficinas)
                .queryParam("unidadRaiz", unidadRaiz)
                .queryParam("provincia", provincia == null ? "" : provincia)
                .queryParam("localidad", localidad == null ? "" : localidad + "-" + ENTIDAD_GEOGRAFICA_MUNICIPIO)
                .queryParam("vigentes", vigentes)
                .request(MediaType.APPLICATION_JSON)
                .get(new GenericType<>() {});
    }
}
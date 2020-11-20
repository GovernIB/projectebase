package es.caib.proyectobase.api.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;
import javax.naming.InitialContext;

import io.swagger.annotations.*;
import es.caib.proyectobase.entity.FooEntity;
import es.caib.proyectobase.service.FooServiceInterface;

@Path("/foo")
@Api(value="/foo", description="Foo resource")
public class FooResource {

    @SuppressWarnings("rawtypes")
    private static FooServiceInterface getService() {
        FooServiceInterface object = null;
        try {
            object = (FooServiceInterface) new InitialContext().lookup("es.caib.proyectobase.service.FooService");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return object;
    }

    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value="Retorna lista de elementos", notes="Algo más?")
    @ApiResponses(value= {
            @ApiResponse(code=200, message="OK"),
            @ApiResponse(code=500, message="Algo ha fallado en el servidor")
    })
    public Response list() {
        try {
            return Response.status(200).entity(getService().list()).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }


    @POST
    @Path("/add/{value}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value="Permite dar de alta un elemento")
    @ApiResponses(value= {
            @ApiResponse(code=200, message="OK"),
            @ApiResponse(code=500, message="Algo ha fallado en el servidor")
    })
    public Response addFooEntity(@PathParam("value")
                                 @ApiParam(value="Valor de la entidad")
                                 String value) {
        try {
            FooEntity fooEntity = new FooEntity();
            fooEntity.setValue(value);
            return Response.status(200).entity(getService().addFooEntity(fooEntity)).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

}

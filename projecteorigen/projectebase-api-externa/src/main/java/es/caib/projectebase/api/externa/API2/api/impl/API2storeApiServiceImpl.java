package es.caib.projectebase.api.externa.API2.api.impl;

import es.caib.projectebase.api.externa.API2.api.*;
import es.caib.projectebase.api.externa.API2.model.*;

import org.joda.time.DateTime;
import java.util.Map;

import java.util.List;



import java.io.InputStream;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@RequestScoped

public class API2storeApiServiceImpl implements API2storeApiService {
    public Response deleteOrder(Long orderId,SecurityContext securityContext)
    throws NotFoundException {
    // do some magic!
    return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
}
    public Response getInventory(SecurityContext securityContext)
    throws NotFoundException {
    // do some magic!
    return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
}
    public Response getOrderById(Long orderId,SecurityContext securityContext)
    throws NotFoundException {
    // do some magic!
    return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
}
    public Response placeOrder(Order body,SecurityContext securityContext)
    throws NotFoundException {
    // do some magic!
    return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
}
}

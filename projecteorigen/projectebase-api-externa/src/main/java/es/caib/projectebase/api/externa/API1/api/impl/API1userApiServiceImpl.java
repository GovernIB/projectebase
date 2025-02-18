package es.caib.projectebase.api.externa.API1.api.impl;

import es.caib.projectebase.api.externa.API1.api.*;
import es.caib.projectebase.api.externa.API1.model.*;

import java.util.List;
import java.util.Map;

import java.io.InputStream;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@RequestScoped

public class API1userApiServiceImpl implements API1userApiService {
    public Response createUser(User body,SecurityContext securityContext)
    throws NotFoundException {
    // do some magic!
    return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
}
    public Response createUsersWithListInput(List<User> body,SecurityContext securityContext)
    throws NotFoundException {
    // do some magic!
    return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
}
    public Response deleteUser(String username,SecurityContext securityContext)
    throws NotFoundException {
    // do some magic!
    return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
}
    public Response getUserByName(String username,SecurityContext securityContext)
    throws NotFoundException {
    // do some magic!
    return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
}
    public Response loginUser(String username,String password,SecurityContext securityContext)
    throws NotFoundException {
    // do some magic!
    return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
}
    public Response logoutUser(SecurityContext securityContext)
    throws NotFoundException {
    // do some magic!
    return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
}
    public Response updateUser(String username,User body,SecurityContext securityContext)
    throws NotFoundException {
    // do some magic!
    return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
}
}

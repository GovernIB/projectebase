package es.caib.projectebase.api.interna.sistra2extern.api.impl;

import es.caib.projectebase.api.interna.sistra2extern.api.*;
import es.caib.projectebase.api.interna.sistra2extern.model.*;

import es.caib.projectebase.api.interna.sistra2extern.model.ParametrosApertura;
import es.caib.projectebase.api.interna.sistra2extern.model.RetornFormulari;
import es.caib.projectebase.api.interna.sistra2extern.model.SolicitudFormulari;
import es.caib.projectebase.api.interna.sistra2extern.model.Usuario;

import java.util.List;
import java.util.Map;
import es.caib.projectebase.api.interna.sistra2extern.api.NotFoundException;

import java.io.InputStream;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@RequestScoped

public class Sistra2externApiServiceImpl implements Sistra2externApiService {
      public Response iniciaFormulari(SolicitudFormulari body,SecurityContext securityContext)
      throws NotFoundException {
      // do some magic!
      return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
  }
      public Response iniciaFormulari(String entidad,String idSesionFormulario,String idFormularioExterno,String idioma,Usuario usuario,byte[] xmlDatosActuales,ParametrosApertura parametrosApertura,String urlCallback,SecurityContext securityContext)
      throws NotFoundException {
      // do some magic!
      return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
  }
      public Response resultatFormulari(String ticket,SecurityContext securityContext)
      throws NotFoundException {
      // do some magic!
      return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
  }
}

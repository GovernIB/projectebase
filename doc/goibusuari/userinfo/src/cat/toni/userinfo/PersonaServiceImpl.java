package cat.toni.userinfo;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/persona")
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class PersonaServiceImpl implements PersonaService {
	
	private static Map<Integer,Persona> persones= new HashMap<Integer,Persona>();
	
	@Override
	@POST
	@Path("/add")
	public Resposta addPersona(Persona p) {
		Resposta resposta = new Resposta();
		if (persones.get(p.getId()) !=null) {
			resposta.setEstat(false);
			resposta.setMissatge("La persona ja existeix.");
			return resposta;
		}
		persones.put(p.getId(), p);
		resposta.setEstat(true);
		resposta.setMissatge("Persona creada satisfactòriament.");
		return resposta;
	}
	
	
	@Override
	@GET
	@Path("/{id}/delete")
	public Resposta deletePersona(int id) {
		Resposta resposta = new Resposta();
		if (persones.get(id)==null) {
			resposta.setEstat(false);
			resposta.setMissatge("La persona no existeix.");
			return resposta;
		}
		persones.remove(id);
		resposta.setEstat(true);
		resposta.setMissatge("Persona esborrada satisfactòriament.");
		return resposta;
	}

	@Override
	@GET
	@Path("/{id}/get")
	public Persona getPersona(@PathParam("id") int id) {
		return persones.get(id);
	}
	
	@Override
	@GET
	@Path("/{id}")
	public JsonObject getJsonPersona(@PathParam("id") int id) {
		return persones.get(id).toJson();
	}
	
	@GET
	@Path("/{id}/getDummie")
	public Persona getPersonaDummie(@PathParam("id") int id) {
		Persona p = new Persona();
		p.setId(id);
		p.setAny(1979);
		p.setNom("Pepet");
		return p;
	}

	@Override
	@GET
	@Path("/getAll")
	public Persona[] getTotesPersones() {
		Set<Integer> ids = persones.keySet();
		Persona[] p = new Persona[ids.size()];
		int i=0;
		for (Integer id : ids) {
			p[i++] = persones.get(id);
		}
		return p;
	}

}

package cat.toni.userinfo;

import javax.json.JsonObject;

public interface PersonaService {
	public Resposta addPersona(Persona p);
	public Resposta deletePersona (int id);
	public Persona getPersona(int id);
	public JsonObject getJsonPersona(int id);
	public Persona[] getTotesPersones();
}

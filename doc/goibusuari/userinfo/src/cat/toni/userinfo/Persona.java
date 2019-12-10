package cat.toni.userinfo;

import javax.json.Json;
import javax.json.JsonObject;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="persona")
public class Persona {
	private String nom;
	private int any;
	private int id;
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public int getAny() {
		return any;
	}
	public void setAny(int any) {
		this.any = any;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return id+"::"+nom+"::"+any;
		
	}
	
	public JsonObject toJson() {
		return Json.createObjectBuilder()
				.add("nom", this.nom)
				.add("id", this.id)
				.add("any", this.any)
				.build();
	}
}

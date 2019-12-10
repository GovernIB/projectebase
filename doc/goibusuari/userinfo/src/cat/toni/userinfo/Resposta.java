package cat.toni.userinfo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Resposta {
	private boolean estat;
	private String missatge;
	
	public boolean isEstat() {
		return estat;
	}
	public void setEstat(boolean estat) {
		this.estat = estat;
	}
	public String getMissatge() {
		return missatge;
	}
	public void setMissatge(String missatge) {
		this.missatge = missatge;
	}
}

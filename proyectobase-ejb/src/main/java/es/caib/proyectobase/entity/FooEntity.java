package es.caib.proyectobase.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Entidad de prueba para el proyecto base
 * @author [u91310] Pedro Bauzá Mascaró
 */
@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class FooEntity {
	
	@Id
	@GeneratedValue
	@XmlTransient
	private Long id;
	
	@Column
	private String value;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}

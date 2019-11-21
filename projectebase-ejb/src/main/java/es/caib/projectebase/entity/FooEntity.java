package es.caib.projectebase.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Entidad de prueba para el projecte base
 * @author [u91310] Pedro Bauzá Mascaró
 */
@Entity
public class FooEntity {
	
	@Id
	@GeneratedValue
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

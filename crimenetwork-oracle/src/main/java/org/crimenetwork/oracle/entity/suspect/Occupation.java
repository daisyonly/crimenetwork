package org.crimenetwork.oracle.entity.suspect;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * Occupation entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="occupation",schema="system")
public class Occupation {
	@Id
	private String code;
	@Column
	private String description;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column
	private String name;
}
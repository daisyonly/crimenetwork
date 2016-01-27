package org.crimenetwork.oracle.entity.share;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Gender entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="SIF", schema="SYSTEM")
public class Sif implements IBasicCode{

	@Id
	private String code;
	@Column
	private String name;
	@Column
	private String description;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString(){
		return name;	
	}
	

}
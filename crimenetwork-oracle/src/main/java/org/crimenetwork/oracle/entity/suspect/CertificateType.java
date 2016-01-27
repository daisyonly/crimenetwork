package org.crimenetwork.oracle.entity.suspect;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="Certificate_type", schema="SYSTEM")
public class CertificateType{
	@Id
	private String code;
	@Column
	private String name;
		
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
	
	@Override
	public String toString(){
		return name;	
	}
}
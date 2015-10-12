package org.crimenetwork.oracle.entity.share;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
@Table(name="location_code",schema="system")
public class Location {

	
	@Id
	@Column(name="location_code")
	private String code;
	@Column(name="name")
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
	public String getProvinceCodePrefix(){
		return getCode().substring(0,2);
	}
	
	
	@Override
	public String toString() {
		
		return ToStringBuilder.reflectionToString(this);
	}
}

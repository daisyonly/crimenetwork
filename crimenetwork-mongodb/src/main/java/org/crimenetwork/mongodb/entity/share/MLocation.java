package org.crimenetwork.mongodb.entity.share;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
public class MLocation {

	private String code;
	
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

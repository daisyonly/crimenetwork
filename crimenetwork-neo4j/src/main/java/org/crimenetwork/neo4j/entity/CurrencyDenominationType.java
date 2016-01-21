package org.crimenetwork.neo4j.entity;

import org.crimenetwork.neo4j.untils.ManualField;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;

@NodeEntity
public class CurrencyDenominationType{
	
	@GraphId
	@ManualField
	private Long id;

	@Indexed
	private String code;

	private String name;

	private String description;

	private String value_moduleType;
	
	
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
	
	
	public String getValue_moduleType() {
		return value_moduleType;
	}
	public void setValue_moduleType(String valueModuleType) {
		value_moduleType = valueModuleType;
	}
	
}
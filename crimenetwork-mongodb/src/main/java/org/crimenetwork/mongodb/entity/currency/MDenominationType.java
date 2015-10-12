package org.crimenetwork.mongodb.entity.currency;

public class MDenominationType{

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
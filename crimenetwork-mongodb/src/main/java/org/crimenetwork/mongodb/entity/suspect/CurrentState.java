package org.crimenetwork.mongodb.entity.suspect;


import org.crimenetwork.mongodb.entity.share.IBasicCode;




public class CurrentState implements IBasicCode{

	
	private String code;
	
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
	
	private String name;
}
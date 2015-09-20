package org.crimenetwork.oracle.entity.currency;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.crimenetwork.oracle.entity.share.IBasicCode;


/**
 * ForgeType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="forge_type",schema="system")
public class ForgeType implements IBasicCode{
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
	
}
package org.crimenetwork.oracle.entity.currency;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.crimenetwork.oracle.entity.share.IBasicCode;



/**
 * DataFrom entity. @author MyEclipse Persistence Tools
 */

@Entity
@Table(name="data_from", schema="system")
public class DataFrom implements IBasicCode {

	// Fields
	@Id
	private String code;
	private String name;
	private String description;


	// Constructors

	/** default constructor */
	public DataFrom() {
	}

	/** minimal constructor */
	public DataFrom(String code) {
		this.code = code;
	}

	/** full constructor */
	public DataFrom(String code, String name, String description) {
		this.code = code;
		this.name = name;
		this.description = description;
	}

	// Property accessors

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


}
package org.crimenetwork.oracle.entity.share;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * ContinentCode entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
@Entity
@Table(name="continent_code", schema="system")
public class ContinentCode implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
	@SequenceGenerator(name="seq", sequenceName="SEQUENCE_1")
	private String continentCode;
	
	@Column(name="name_cn")
	private String nameCn;
	
	@Column(name="name_en")
	private String nameEn;
	
	private String description;
	

	// Constructors

	/** default constructor */
	public ContinentCode() {
	}

	/** minimal constructor */
	public ContinentCode(String continentCode) {
		this.continentCode = continentCode;
	}

	/** full constructor */
	public ContinentCode(String continentCode, String nameCn, String nameEn,
			String description) {
		this.continentCode = continentCode;
		this.nameCn = nameCn;
		this.nameEn = nameEn;
		this.description = description;	
	}

	// Property accessors

	public String getContinentCode() {
		return this.continentCode;
	}

	public void setContinentCode(String continentCode) {
		this.continentCode = continentCode;
	}

	public String getNameCn() {
		return this.nameCn;
	}

	public void setNameCn(String nameCn) {
		this.nameCn = nameCn;
	}

	public String getNameEn() {
		return this.nameEn;
	}

	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
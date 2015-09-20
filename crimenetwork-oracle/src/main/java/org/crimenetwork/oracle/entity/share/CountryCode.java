package org.crimenetwork.oracle.entity.share;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * CountryCode entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
@Entity
@Table(name="country_code", schema="system")
public class CountryCode implements java.io.Serializable ,IBasicCode {

	// Fields
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
	@SequenceGenerator(name="seq", sequenceName="SEQUENCE_1")
	private String countryCode;
	
	private ContinentCode continentCode;
	private String nameCn;
	private String nameEn;
	private String description;

	// Constructors

	/** default constructor */
	public CountryCode() {
	}

	/** minimal constructor */
	public CountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	/** full constructor */
	public CountryCode(String countryCode, ContinentCode continentCode,
			String nameCn, String nameEn, String description) {
		this.countryCode = countryCode;
		this.continentCode = continentCode;
		this.nameCn = nameCn;
		this.nameEn = nameEn;
		this.description = description;
		
	}

	// Property accessors

	public String getCountryCode() {
		return this.countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public ContinentCode getContinentCode() {
		return this.continentCode;
	}

	public void setContinentCode(ContinentCode continentCode) {
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

	public void setCode(String code) {
		this.countryCode = code;
		
	}

	public void setName(String name) {
		this.nameCn = name;
	}

	public String getCode() {
		return this.countryCode;
	}

	public String getName() {
		return this.nameCn;
	}

}
package org.crimenetwork.oracle.entity.share;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="country_code", schema="system")
public class CountryCode {

	// Fields
	@Id
	@Column(name="country_code")
	private String countryCode;
	
	@Column(name="continent_code")
	private ContinentCode continentCode;
	
	@Column(name="name_cn")
	private String nameCn;
	
	@Column(name="name_en")
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
	
	@Override
	public String toString(){
		return nameCn;	
	}

}
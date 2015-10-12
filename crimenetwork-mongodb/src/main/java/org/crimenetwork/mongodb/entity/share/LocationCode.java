package org.crimenetwork.mongodb.entity.share;


/**
 * LocationCode entity. @author MyEclipse Persistence Tools
 */


public class LocationCode implements java.io.Serializable,RawCode {


	private String locationCode;
	

	private CountryCode countryCode;
	private String name;
	private String description;


	// Constructors

	/** default constructor */
	public LocationCode() {
	}

	/** minimal constructor */
	public LocationCode(String locationCode) {
		this.locationCode = locationCode;
	}

	/** full constructor */
	public LocationCode(String locationCode, CountryCode countryCode,
			String name, String description) {
		this.locationCode = locationCode;
		this.countryCode = countryCode;
		this.name = name;
		this.description = description;
	}

	// Property accessors

	public String getLocationCode() {
		return this.locationCode;
	}

	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}

	public CountryCode getCountryCode() {
		return this.countryCode;
	}

	public void setCountryCode(CountryCode countryCode) {
		this.countryCode = countryCode;
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


	public String getCode() {
		return locationCode;
	}

	public void setCode(String code) {
		this.locationCode = code;
	}

}
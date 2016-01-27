package org.crimenetwork.oracle.entity.share;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;



/**
 * LocationCode entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
@Entity
@Table(name="location_code", schema="system")
public class LocationCode implements java.io.Serializable,RawCode {

	// Fields
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
	@SequenceGenerator(name="seq", sequenceName="SEQUENCE_1")
	private String locationCode;
	
	@ManyToOne
	@JoinColumn(name="country_code")
	@NotFound(action=NotFoundAction.IGNORE)
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
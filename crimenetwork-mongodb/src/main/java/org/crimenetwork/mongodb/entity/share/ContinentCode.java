package org.crimenetwork.mongodb.entity.share;




@SuppressWarnings("serial")
public class ContinentCode implements java.io.Serializable {

	
	private String continentCode;
	
	
	private String nameCn;
	
	
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
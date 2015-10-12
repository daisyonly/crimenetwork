package org.crimenetwork.mongodb.entity.currency;


public class MClassificationNumber {
	
	private String code;

	private String name;
	
	private String description;

	private Long cfid;
	
	private Long smallCfid;
	
	private Long smallCfid400;
	
	private Long smallCfid96;

	private Long backCfid;

	private Long backSmallCfid;
	
	private Long backSmallCfid400;

	private Long backSmallCfid96;
	
	
	
	private MDenominationType denominationType;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getCfid() {
		return cfid;
	}
	public void setCfid(Long cfid) {
		this.cfid = cfid;
	}
	public Long getSmallCfid() {
		return smallCfid;
	}
	public void setSmallCfid(Long smallCfid) {
		this.smallCfid = smallCfid;
	}
	public Long getSmallCfid400() {
		return smallCfid400;
	}
	public void setSmallCfid400(Long smallCfid400) {
		this.smallCfid400 = smallCfid400;
	}
	public Long getSmallCfid96() {
		return smallCfid96;
	}
	public void setSmallCfid96(Long smallCfid96) {
		this.smallCfid96 = smallCfid96;
	}
	public Long getBackCfid() {
		return backCfid;
	}
	public void setBackCfid(Long backCfid) {
		this.backCfid = backCfid;
	}
	public Long getBackSmallCfid() {
		return backSmallCfid;
	}
	public void setBackSmallCfid(Long backSmallCfid) {
		this.backSmallCfid = backSmallCfid;
	}
	public Long getBackSmallCfid400() {
		return backSmallCfid400;
	}
	public void setBackSmallCfid400(Long backSmallCfid400) {
		this.backSmallCfid400 = backSmallCfid400;
	}
	public Long getBackSmallCfid96() {
		return backSmallCfid96;
	}
	public void setBackSmallCfid96(Long backSmallCfid96) {
		this.backSmallCfid96 = backSmallCfid96;
	}
	
	public MDenominationType getDenominationType() {
		return denominationType;
	}
	public void setDenominationType(MDenominationType denominationType) {
		this.denominationType = denominationType;
	}

}

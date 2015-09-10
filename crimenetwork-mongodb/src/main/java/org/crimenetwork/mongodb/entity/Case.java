package org.crimenetwork.mongodb.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Case extends AbstractDocument{
	
	private String caseId;
	
	private String caseName;
	
	private String registerUnit;

	private Float seizedAmount;		
	
	private String briefInfo;

	public String getBriefInfo() {
		return briefInfo;
	}
	
	public void setBriefInfo(String briefInfo) {
		this.briefInfo = briefInfo;
	}
	
	public String getCaseId() {
		return caseId;
	}
	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}
	public String getCaseName() {
		return caseName;
	}
	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}
	public String getRegisterUnit() {
		return registerUnit;
	}
	public void setRegisterUnit(String registerUnit) {
		this.registerUnit = registerUnit;
	}

	public Float getSeizedAmount() {
		return seizedAmount;
	}
	public void setSeizedAmount(Float seizedAmount) {
		this.seizedAmount = seizedAmount;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ToStringBuilder.reflectionToString(this);
	}
}

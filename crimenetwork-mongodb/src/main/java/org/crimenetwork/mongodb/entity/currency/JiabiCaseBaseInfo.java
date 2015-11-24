package org.crimenetwork.mongodb.entity.currency;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.crimenetwork.mongodb.entity.cases.CaseSuspectBaseInfo;
import org.crimenetwork.mongodb.entity.share.MLocation;
import org.crimenetwork.mongodb.utils.ManualField;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
public class JiabiCaseBaseInfo{
	@ManualField
	private Long cId;
	
	private String caseId;
	
	private String caseName;
	
	private String registerUnit;//立案单位
	
	
	private Date registerDate;
	
	private Float seizedAmount;
	
	
	@ManualField
	private Set<CaseSuspectBaseInfo> suspects = new HashSet<CaseSuspectBaseInfo>();
	
	private String briefInfo;
	
	@ManualField
	private MLocation caseHappenLocation;
	
	
	public Long getcId() {
		return cId;
	}
	public void setcId(Long cId) {
		this.cId = cId;
	}
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
	
	public Date getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}
	
	public Float getSeizedAmount() {
		return seizedAmount;
	}
	public void setSeizedAmount(Float seizedAmount) {
		this.seizedAmount = seizedAmount;
	}
	
	public Set<CaseSuspectBaseInfo> getSuspects() {
		return suspects;
	}
	public void setSuspects(Set<CaseSuspectBaseInfo> suspects) {
		this.suspects = suspects;
	}
	
	public MLocation getCaseHappenLocation() {
		return caseHappenLocation;
	}
	public void setCaseHappenLocation(MLocation caseHappenLocation) {
		this.caseHappenLocation = caseHappenLocation;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
}

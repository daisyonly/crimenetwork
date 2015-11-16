package org.crimenetwork.mongodb.entity.suspect;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.crimenetwork.mongodb.entity.AbstractDocument;
import org.crimenetwork.mongodb.entity.currency.BaseJiabiInfo;
import org.crimenetwork.mongodb.entity.share.MLocation;
import org.crimenetwork.mongodb.utils.ManualField;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
public class SuspectCaseBaseInfo extends AbstractDocument{
	
	private String caseId;
	
	private String caseName;
	
	private String registerUnit;//立案单位	
	
	private Date registerDate;
	
	private Float seizedAmount;
	
	@ManualField
	private Set<BaseJiabiInfo> counterfeitMoneys = new HashSet<BaseJiabiInfo>();
	
	private String briefInfo;
	
	@ManualField
	private MLocation caseHappenLocation;
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
	
	public Set<BaseJiabiInfo> getCounterfeitMoneys() {
		return counterfeitMoneys;
	}
	public void setCounterfeitMoneys(Set<BaseJiabiInfo> counterfeitMoneys) {
		this.counterfeitMoneys = counterfeitMoneys;
	}
	public MLocation getCaseHappenLocation() {
		return caseHappenLocation;
	}
	public void setCaseHappenLocation(MLocation caseHappenLocation) {
		this.caseHappenLocation = caseHappenLocation;
	}
	
}

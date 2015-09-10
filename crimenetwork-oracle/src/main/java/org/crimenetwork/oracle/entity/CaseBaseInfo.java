package org.crimenetwork.oracle.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="case_info", schema="system")
public class CaseBaseInfo {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
	@SequenceGenerator(name="seq", sequenceName="SEQUENCE_1")
	private Long id;
	@Column(name="case_id", length=50)
	private String caseId;
	@Column(name="case_name", length=100)
	private String caseName;
	@Column(name="register_unit", length=200)
	private String registerUnit;//立案单位

	@Column(name="seized_amount")
	private Float seizedAmount;
		
	@Column(name="brief_info")
	private String briefInfo;

	public String getBriefInfo() {
		return briefInfo;
	}
	public void setBriefInfo(String briefInfo) {
		this.briefInfo = briefInfo;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
}

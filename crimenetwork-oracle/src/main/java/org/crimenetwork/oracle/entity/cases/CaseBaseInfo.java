package org.crimenetwork.oracle.entity.cases;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.crimenetwork.oracle.entity.currency.JiabiBaseInfo;
import org.crimenetwork.oracle.entity.share.Location;
import org.crimenetwork.oracle.entity.suspect.SuspectBaseInfo;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;


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
	
	@Column(name="register_date", length=7)
	private Date registerDate;
	@Column(name="seized_amount")
	private Float seizedAmount;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="case_suspect",
		joinColumns=@JoinColumn(name="case_id"),
		inverseJoinColumns=@JoinColumn(name="suspect_id")
	)
	private Set<SuspectBaseInfo> suspects = new HashSet<SuspectBaseInfo>();
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="case_currency",
		joinColumns=@JoinColumn(name="case_id"),
		inverseJoinColumns=@JoinColumn(name="fmid"))
	private Set<JiabiBaseInfo> counterfeitMoneys = new HashSet<JiabiBaseInfo>();
	@Column(name="brief_info")
	private String briefInfo;
	
	@ManyToOne
	@JoinColumn(name="case_happen_location")
	@NotFound(action=NotFoundAction.IGNORE)
	private Location caseHappenLocation;
	
	@Column(name="case_happen_time")
	private Date caseHappenTime;//案发时间
	@Column(name="case_happen_detail_adress", length=200)
	private String caseHappenDetailAddress;//案发详细地址
	
	@Column(name="den_location_dettail", length=50)
	private String denLocation;//窝点详细地址
	@Column(name="prosecution_time", length=7)
	private Date prosecutionTime;//起诉时间
	@Column(name="fir_sec_trial_time", length=7)
	private Date firSecTrialTime;//一二审时间
	@Column(name="scene_Seized_Description", length=300)
	private String sceneSeizedDescription;//现场缴获
	@Column(name="seized_cc_description", length=300)
	private String seizedCcDescription;//缴获假币情况
	@Column(name="seized_total_description", length=300)
	private String seizedTotalDescription;//交货金额
	
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
	public Set<SuspectBaseInfo> getSuspects() {
		return suspects;
	}
	public void setSuspects(Set<SuspectBaseInfo> suspects) {
		this.suspects = suspects;
	}
	public Set<JiabiBaseInfo> getCounterfeitMoneys() {
		return counterfeitMoneys;
	}
	public void setCounterfeitMoneys(Set<JiabiBaseInfo> counterfeitMoneys) {
		this.counterfeitMoneys = counterfeitMoneys;
	}
	public Location getCaseHappenLocation() {
		return caseHappenLocation;
	}
	public void setCaseHappenLocation(Location caseHappenLocation) {
		this.caseHappenLocation = caseHappenLocation;
	}
	
	public Date getCaseHappenTime() {
		return caseHappenTime;
	}
	public void setCaseHappenTime(Date caseHappenTime) {
		this.caseHappenTime = caseHappenTime;
	}
	public String getCaseHappenDetailAddress() {
		return caseHappenDetailAddress;
	}
	public void setCaseHappenDetailAddress(String caseHappenDetailAddress) {
		this.caseHappenDetailAddress = caseHappenDetailAddress;
	}
	public String getDenLocation() {
		return denLocation;
	}
	public void setDenLocation(String denLocation) {
		this.denLocation = denLocation;
	}
	public Date getProsecutionTime() {
		return prosecutionTime;
	}
	public void setProsecutionTime(Date prosecutionTime) {
		this.prosecutionTime = prosecutionTime;
	}
	public Date getFirSecTrialTime() {
		return firSecTrialTime;
	}
	public void setFirSecTrialTime(Date firSecTrialTime) {
		this.firSecTrialTime = firSecTrialTime;
	}
	public String getSceneSeizedDescription() {
		return sceneSeizedDescription;
	}
	public void setSceneSeizedDescription(String sceneSeizedDescription) {
		this.sceneSeizedDescription = sceneSeizedDescription;
	}
	public String getSeizedCcDescription() {
		return seizedCcDescription;
	}
	public void setSeizedCcDescription(String seizedCcDescription) {
		this.seizedCcDescription = seizedCcDescription;
	}
	public String getSeizedTotalDescription() {
		return seizedTotalDescription;
	}
	public void setSeizedTotalDescription(String seizedTotalDescription) {
		this.seizedTotalDescription = seizedTotalDescription;
	}
	@Override
	public String toString() {
		
		return ToStringBuilder.reflectionToString(this);
	}
	
	
	
}

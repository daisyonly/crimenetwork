package org.crimenetwork.oracle.entity.cases;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;




import org.crimenetwork.oracle.entity.currency.JiabiBaseInfo;
import org.crimenetwork.oracle.entity.sharenew.Location;
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
	
	@ManyToMany
	@JoinTable(name="case_suspect",
		joinColumns=@JoinColumn(name="case_id"),
		inverseJoinColumns=@JoinColumn(name="suspect_id")
	)
	private Set<SuspectBaseInfo> suspects = new HashSet<SuspectBaseInfo>();
	
	@ManyToMany
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
	
}

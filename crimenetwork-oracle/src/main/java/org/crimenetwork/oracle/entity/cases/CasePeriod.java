package org.crimenetwork.oracle.entity.cases;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.crimenetwork.oracle.entity.share.IBasicCode;


@Entity
@Table(name="case_period",schema="system")
public class CasePeriod implements IBasicCode{
	@Id
	private String code;
	@Column(name="name", length=20)
	private String name;
	@OneToMany(mappedBy="casePeriod")
	private Set<CaseInfo> caseInfos = new HashSet<CaseInfo>(0);
	@OneToMany(mappedBy="solvePeriod")
	private Set<CaseInfo> caseInfoS = new HashSet<CaseInfo>(0);
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
	public Set<CaseInfo> getCaseInfos() {
		return caseInfos;
	}
	public void setCaseInfos(Set<CaseInfo> caseInfos) {
		this.caseInfos = caseInfos;
	}
	public Set<CaseInfo> getCaseInfoS() {
		return caseInfoS;
	}
	public void setCaseInfoS(Set<CaseInfo> caseInfoS) {
		this.caseInfoS = caseInfoS;
	}
}
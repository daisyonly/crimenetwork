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
@Table(name="case_phase")
public class CasePhase implements IBasicCode{
	@Id
	private String code;
	@Column(name="name", length=50)
	private String name;
	@OneToMany(mappedBy="casePhase")
	private Set<CaseInfo> caseInfos = new HashSet<CaseInfo>(0);
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
}
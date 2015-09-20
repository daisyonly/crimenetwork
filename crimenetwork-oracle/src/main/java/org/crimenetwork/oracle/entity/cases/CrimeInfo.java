package org.crimenetwork.oracle.entity.cases;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.crimenetwork.oracle.entity.share.IBasicCode;


/**
 * CrimeInfo entity.
 * @author Shellon
// */
@Entity
@Table(name="crime_info", schema="system")
public class CrimeInfo implements IBasicCode{
	@Id
	private String code;
	@Column
	private String name;
	@OneToMany(mappedBy="involvedCrime")
	private Set<CaseInfo> CaseInfos = new HashSet<CaseInfo>(0);
	@OneToMany(mappedBy="crimeInfo")
	private Set<BlacklistInfo> blacklistInfos = new HashSet<BlacklistInfo>(0);
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
		return CaseInfos;
	}
	public void setCaseInfos(Set<CaseInfo> caseInfos) {
		CaseInfos = caseInfos;
	}
	
}
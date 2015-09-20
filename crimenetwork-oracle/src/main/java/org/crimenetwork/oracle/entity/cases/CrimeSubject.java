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
 * CrimeSubject entity.
 * @author Shellon
 */
@Entity
@Table(name="crime_subject",schema="system")
public class CrimeSubject implements IBasicCode{
	@Id
	private String code;
	@Column(name="name", length=20)
	private String name;
	@OneToMany(mappedBy="crimeSubject")
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
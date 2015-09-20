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
@Table(name="commit_tool",schema="system")
public class CommitTool implements IBasicCode{
	@Id
	@Column(name="code", length=4,nullable=false)
	private String code;
	@Column(name="name", length=50)
	private String name;
	@Column(name="description", length=4)
	private String description;
	@OneToMany(mappedBy="crimeTool")
	private Set<CaseInfo> caseInfos = new HashSet<CaseInfo>();
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Set<CaseInfo> getCaseInfos() {
		return caseInfos;
	}
	public void setCaseInfos(Set<CaseInfo> caseInfos) {
		this.caseInfos = caseInfos;
	}
}

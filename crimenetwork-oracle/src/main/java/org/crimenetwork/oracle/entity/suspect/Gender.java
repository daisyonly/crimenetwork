package org.crimenetwork.oracle.entity.suspect;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.crimenetwork.oracle.entity.share.IBasicCode;



/**
 * Gender entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="gender", schema="system")
public class Gender implements IBasicCode{

	@Id
	private String code;
	@Column
	private String description;
//	@OneToMany(mappedBy="gender")
//	private Set<SuspectInfo> suspectInfos = new HashSet<SuspectInfo>();
//	@OneToMany(mappedBy="gender")
//	private Set<BlacklistInfo> blacklistInfos = new HashSet<BlacklistInfo>();
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
//	public Set<SuspectInfo> getSuspectInfos() {
//		return suspectInfos;
//	}
//	public void setSuspectInfos(Set<SuspectInfo> suspectInfos) {
//		this.suspectInfos = suspectInfos;
//	}
//	public Set<BlacklistInfo> getBlacklistInfos() {
//		return blacklistInfos;
//	}
//	public void setBlacklistInfos(Set<BlacklistInfo> blacklistInfos) {
//		this.blacklistInfos = blacklistInfos;
//	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column
	private String name;
}
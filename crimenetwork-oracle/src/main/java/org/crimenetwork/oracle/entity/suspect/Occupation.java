package org.crimenetwork.oracle.entity.suspect;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.crimenetwork.oracle.entity.cases.BlacklistInfo;

/**
 * Occupation entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="occupation",schema="system")
public class Occupation {
	@Id
	private String code;
	@Column
	private String description;
	@OneToMany(mappedBy="occupation")
	private Set<BlacklistInfo> blacklistInfos= new HashSet<BlacklistInfo>();
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
	public Set<BlacklistInfo> getBlacklistInfos() {
		return blacklistInfos;
	}
	public void setBlacklistInfos(Set<BlacklistInfo> blacklistInfos) {
		this.blacklistInfos = blacklistInfos;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column
	private String name;
}
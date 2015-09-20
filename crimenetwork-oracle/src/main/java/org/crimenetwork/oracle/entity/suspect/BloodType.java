package org.crimenetwork.oracle.entity.suspect;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.crimenetwork.oracle.entity.share.IBasicCode;


/**
 * BloodType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="blood_type",schema="system")
public class BloodType implements IBasicCode{
	@Id
	private String code;
	@Column
	private String name;
	@Column
	private String description;
	@OneToMany(mappedBy="bloodType")
	private Set<SuspectInfo> suspectInfos = new HashSet<SuspectInfo>();
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
	public Set<SuspectInfo> getSuspectInfos() {
		return suspectInfos;
	}
	public void setSuspectInfos(Set<SuspectInfo> suspectInfos) {
		this.suspectInfos = suspectInfos;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
package org.crimenetwork.oracle.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.crimenetwork.oracle.entity.share.IBasicCode;
import org.crimenetwork.oracle.entity.suspect.SuspectInfo;



/**
 * Gender entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="SIF", schema="SYSTEM")
public class Sif implements IBasicCode{

	@Id
	private String code;
	@Column
	private String name;
	@Column
	private String description;
	@OneToMany(mappedBy="isForeigner")//是否是境外人员
	private Set<SuspectInfo> suspectInfos1 = new HashSet<SuspectInfo>(0);
	@OneToMany(mappedBy="isLc")//是否流窜
	private Set<SuspectInfo> suspectInfos2 = new HashSet<SuspectInfo>(0);
	@OneToMany(mappedBy="isReoffender")//是否累犯
	private Set<SuspectInfo> suspectInfos3 = new HashSet<SuspectInfo>(0);
	@OneToMany(mappedBy="isHabitualOffender")//是否惯犯
	private Set<SuspectInfo> suspectInfos4 = new HashSet<SuspectInfo>(0);
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
	public Set<SuspectInfo> getSuspectInfos1() {
		return suspectInfos1;
	}
	public void setSuspectInfos1(Set<SuspectInfo> suspectInfos1) {
		this.suspectInfos1 = suspectInfos1;
	}
	public Set<SuspectInfo> getSuspectInfos2() {
		return suspectInfos2;
	}
	public void setSuspectInfos2(Set<SuspectInfo> suspectInfos2) {
		this.suspectInfos2 = suspectInfos2;
	}
	public Set<SuspectInfo> getSuspectInfos3() {
		return suspectInfos3;
	}
	public void setSuspectInfos3(Set<SuspectInfo> suspectInfos3) {
		this.suspectInfos3 = suspectInfos3;
	}
	public Set<SuspectInfo> getSuspectInfos4() {
		return suspectInfos4;
	}
	public void setSuspectInfos4(Set<SuspectInfo> suspectInfos4) {
		this.suspectInfos4 = suspectInfos4;
	}
}
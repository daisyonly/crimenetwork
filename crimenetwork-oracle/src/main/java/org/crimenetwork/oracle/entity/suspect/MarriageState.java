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
 * MarriageState entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="marriage_state",schema="system")
public class MarriageState implements IBasicCode{

	// Fields
	 
	@Id
	private String code;
	@Column
	private String name;
	@OneToMany(mappedBy="marriageState")
	private Set<SuspectInfo> suspectInfos = new HashSet<SuspectInfo>(0);
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
	public Set<SuspectInfo> getSuspectInfos() {
		return suspectInfos;
	}
	public void setSuspectInfos(Set<SuspectInfo> suspectInfos) {
		this.suspectInfos = suspectInfos;
	}

}
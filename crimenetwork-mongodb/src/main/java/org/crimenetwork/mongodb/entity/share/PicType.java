package org.crimenetwork.mongodb.entity.share;

import java.util.HashSet;
import java.util.Set;

/**
 * PicType entity. @author MyEclipse Persistence Tools
 */

public class PicType implements java.io.Serializable {

	// Fields

	private Long pcid;
	private String name;
	private Set jiabiBasePics = new HashSet(0);

	// Constructors

	/** default constructor */
	public PicType() {
	}

	/** minimal constructor */
	public PicType(Long pcid) {
		this.pcid = pcid;
	}

	/** full constructor */
	public PicType(Long pcid, String name, Set jiabiBasePics) {
		this.pcid = pcid;
		this.name = name;
		this.jiabiBasePics = jiabiBasePics;
	}

	// Property accessors

	public Long getPcid() {
		return this.pcid;
	}

	public void setPcid(Long pcid) {
		this.pcid = pcid;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set getJiabiBasePics() {
		return this.jiabiBasePics;
	}

	public void setJiabiBasePics(Set jiabiBasePics) {
		this.jiabiBasePics = jiabiBasePics;
	}

}
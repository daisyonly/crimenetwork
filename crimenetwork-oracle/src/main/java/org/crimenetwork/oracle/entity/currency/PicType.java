package org.crimenetwork.oracle.entity.currency;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

//Pic_type
@Entity
@Table(name="pic_type",schema="system")
public class PicType{

	// Fields

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
	@SequenceGenerator(name="seq", sequenceName="SEQUENCE_1")	
	private Long pcid;
	
	@OneToMany(mappedBy="picType",fetch=FetchType.LAZY)
	private Set<JiabiBasePic> jiabiBasePics = new HashSet<JiabiBasePic>(0);
	
	private String name;

	// Constructors
    
	/** default constructor */
	public PicType() {
	}

	public Set<JiabiBasePic> getJiabiBasePics() {
		return jiabiBasePics;
	}

	public void setJiabiBasePics(Set<JiabiBasePic> jiabiBasePics) {
		this.jiabiBasePics = jiabiBasePics;
	}

	/** minimal constructor */
	public PicType(Long pcid) {
		this.pcid = pcid;
	}



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


}
package org.crimenetwork.oracle.entity.currency;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
	
	private String name;

	// Constructors
    
	/** default constructor */
	public PicType() {
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
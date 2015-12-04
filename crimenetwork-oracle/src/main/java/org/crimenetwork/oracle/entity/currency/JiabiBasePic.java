package org.crimenetwork.oracle.entity.currency;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="jiabi_base_pic",schema="SYSTEM")
public class JiabiBasePic implements java.io.Serializable {

	// Fields
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
	@SequenceGenerator(name="seq", sequenceName="SEQUENCE_1")	
	private Long fbpid;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="fmid")
	private JiabiBaseInfo jiabiBaseInfo;
	
	private Long cfid;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="pcid")
	private PicType picType;
	
	@Column(name="small_cfid")
	private Long smallCfid;
	
	@Column(name="small_cfid_400")
	private Long smallCfid400;
	
	@Column(name="small_cfid_96")
	private Long smallCfid96;

	public PicType getPicType() {
		return picType;
	}

	public void setPicType(PicType picType) {
		this.picType = picType;
	}

	public Long getFbpid() {
		return this.fbpid;
	}

	public void setFbpid(Long fbpid) {
		this.fbpid = fbpid;
	}
    
	public JiabiBaseInfo getJiabiBaseInfo() {
		return this.jiabiBaseInfo;
	}

	public void setJiabiBaseInfo(JiabiBaseInfo jiabiBaseInfo) {
		this.jiabiBaseInfo = jiabiBaseInfo;
	}
	

	public Long getCfid() {
		return this.cfid;
	}

	public void setCfid(Long cfid) {
		this.cfid = cfid;
	}

	public Long getSmallCfid() {
		return this.smallCfid;
	}

	public void setSmallCfid(Long smallCfid) {
		this.smallCfid = smallCfid;
	}

	public Long getSmallCfid400() {
		return smallCfid400;
	}

	public void setSmallCfid400(Long smallCfid400) {
		this.smallCfid400 = smallCfid400;
	}

	public Long getSmallCfid96() {
		return smallCfid96;
	}

	public void setSmallCfid96(Long smallCfid96) {
		this.smallCfid96 = smallCfid96;
	}

}
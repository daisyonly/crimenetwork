package org.crimenetwork.oracle.entity.currency;

import org.crimenetwork.oracle.entity.share.PicType;


/**
 * JiabiBasePic entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class JiabiBasePic implements java.io.Serializable {

	// Fields

	private Long fbpid;
	private PicType picType;
	private JiabiBaseInfo jiabiBaseInfo;
	private Long cfid;
	private Long smallCfid;
	private Long smallCfid400;
	private Long smallCfid96;

	// Constructors

	/** default constructor */
	public JiabiBasePic() {
	}

	/** full constructor */
	public JiabiBasePic(PicType picType, JiabiBaseInfo jiabiBaseInfo,
			Long cfid, Long smallCfid, Long smallCfid400,
			Long smallCfid96) {
		this.picType = picType;
		this.jiabiBaseInfo = jiabiBaseInfo;
		this.cfid = cfid;
		this.smallCfid = smallCfid;
		this.smallCfid400 = smallCfid400;
		this.smallCfid96 = smallCfid96;
	}

	// Property accessors

	public Long getFbpid() {
		return this.fbpid;
	}

	public void setFbpid(Long fbpid) {
		this.fbpid = fbpid;
	}

	public PicType getPicType() {
		return this.picType;
	}

	public void setPicType(PicType picType) {
		this.picType = picType;
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
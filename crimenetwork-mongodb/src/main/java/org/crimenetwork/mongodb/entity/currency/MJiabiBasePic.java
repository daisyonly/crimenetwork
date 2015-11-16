package org.crimenetwork.mongodb.entity.currency;



public class MJiabiBasePic{

	private Long fbpid;
	private Long cfid;
	private Long smallCfid;
	private Long smallCfid400;
	private Long smallCfid96;

	// Constructors

	/** default constructor */
	public MJiabiBasePic() {
	}

	/** full constructor */
	public MJiabiBasePic(BaseJiabiInfo jiabiBaseInfo,
			Long cfid, Long smallCfid, Long smallCfid400,
			Long smallCfid96) {
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
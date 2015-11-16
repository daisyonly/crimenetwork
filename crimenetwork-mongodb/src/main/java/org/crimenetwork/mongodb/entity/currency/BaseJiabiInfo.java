package org.crimenetwork.mongodb.entity.currency;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import org.crimenetwork.mongodb.utils.ManualField;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class BaseJiabiInfo{
	
	private Long fmid;	
	
	@ManualField
	private String currencyType;	
	
	@ManualField
	private String versionType;
	
	@ManualField
	private String categoryType;
	
	@ManualField
	private MDenominationType denominationType;//假币面额
	

	private String cfmid;
	
	
	private String guanzihao;
	
	private String identifyId;
	
	private String identifyId1;
	
	@ManualField
	private MClassificationNumber identifyId2;
	
	private String comValue;
	
	private String runningNumber;
	
	
	private Timestamp receiveTime;

	
	private Timestamp createTime;
	
	private Long quantity;
	
	private String caseNo;	
	
	private Long total;//总额	
	
	private Boolean isPaperMoney;
	
	private String state;
	
	private String remark;
	
	private Long small_pic;
	
	private String piaoyang_number;
	
	@ManualField
	private Set<MJiabiExtendInfo> jiabiExtendInfos = new HashSet<MJiabiExtendInfo>(0);
	
	@ManualField
	private Set<MJiabiBasePic> jiabiBasePics = new HashSet<MJiabiBasePic>(0);
	
	public Long getFmid() {
		return fmid;
	}
	public void setFmid(Long fmid) {
		this.fmid = fmid;
	}
	
	public String getCurrencyType() {
		return currencyType;
	}
	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}
	public String getVersionType() {
		return versionType;
	}
	public void setVersionType(String versionType) {
		this.versionType = versionType;
	}
	public String getCategoryType() {
		return categoryType;
	}
	public void setCategoryType(String categoryType) {
		this.categoryType = categoryType;
	}
	
	
	public MDenominationType getDenominationType() {
		return denominationType;
	}
	public void setDenominationType(MDenominationType denominationType) {
		this.denominationType = denominationType;
	}
	
	public String getCfmid() {
		return cfmid;
	}
	public void setCfmid(String cfmid) {
		this.cfmid = cfmid;
	}
	public String getGuanzihao() {
		return guanzihao;
	}
	public void setGuanzihao(String guanzihao) {
		this.guanzihao = guanzihao;
	}
	public String getIdentifyId() {
		return identifyId;
	}
	public void setIdentifyId(String identifyId) {
		this.identifyId = identifyId;
	}
	public String getIdentifyId1() {
		return identifyId1;
	}
	public void setIdentifyId1(String identifyId1) {
		this.identifyId1 = identifyId1;
	}
	public MClassificationNumber getIdentifyId2() {
		return identifyId2;
	}
	public void setIdentifyId2(MClassificationNumber identifyId2) {
		this.identifyId2 = identifyId2;
	}
	public String getComValue() {
		return comValue;
	}
	public void setComValue(String comValue) {
		this.comValue = comValue;
	}
	public String getRunningNumber() {
		return runningNumber;
	}
	public void setRunningNumber(String runningNumber) {
		this.runningNumber = runningNumber;
	}
	public Timestamp getReceiveTime() {
		return receiveTime;
	}
	public void setReceiveTime(Timestamp receiveTime) {
		this.receiveTime = receiveTime;
	}
	
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public Long getQuantity() {
		return quantity;
	}
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public Boolean getIsPaperMoney() {
		return isPaperMoney;
	}
	public void setIsPaperMoney(Boolean isPaperMoney) {
		this.isPaperMoney = isPaperMoney;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	public Set<MJiabiExtendInfo> getJiabiExtendInfos() {
		return jiabiExtendInfos;
	}
	public void setJiabiExtendInfos(Set<MJiabiExtendInfo> jiabiExtendInfos) {
		this.jiabiExtendInfos = jiabiExtendInfos;
	}
	public Set<MJiabiBasePic> getJiabiBasePics() {
		return jiabiBasePics;
	}
	public void setJiabiBasePics(Set<MJiabiBasePic> jiabiBasePics) {
		this.jiabiBasePics = jiabiBasePics;
	}
	
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getPiaoyang_number() {
		return piaoyang_number;
	}
	public void setPiaoyang_number(String piaoyangNumber) {
		piaoyang_number = piaoyangNumber;
	}
	public Long getSmall_pic() {
		return small_pic;
	}
	public void setSmall_pic(Long smallPic) {
		small_pic = smallPic;
	}
	public String getCaseNo() {
		return caseNo;
	}
	public void setCaseNo(String caseNo) {
		this.caseNo = caseNo;
	}
	
	
}
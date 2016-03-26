package org.crimenetwork.neo4j.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.crimenetwork.neo4j.entity.relationship.CurrencySim;
import org.crimenetwork.neo4j.untils.ManualField;
import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;
import org.springframework.data.neo4j.annotation.RelatedToVia;

@NodeEntity
public class CounterfeitMoney{
	@GraphId
	@ManualField
	private Long id;
	
    @Indexed
	private Long fmid;	
	
    @ManualField
	private String currencyType;	
	
	@ManualField
	private String versionType;
	
	@ManualField
	private String categoryType;
	
	@Fetch
	@ManualField
	private String denomination;//假币面额
    
	@Indexed
	private String cfmid;
	
	private String guanzihao;
	
	private String identifyId;
	
	private String identifyId1;
	
	private String comValue;
	
	private String runningNumber;//流水号
		
	private Date receiveTime;
	
	private Date createTime;
	
	private Long quantity;//张数
	
	private String caseNo;//案件编号，没用	
	
	private Long total;//总额	
	
	private Boolean isPaperMoney;//是否纸币
	
	private String state;//状态，没用
	
	private String remark;//备注经常有案件编号，可以用regex匹配“A4116000000002011030004”
	
	private Long smallPic;//缩略图，没用
	
	@Indexed
	private String piaoyangNumber;
	
	@RelatedTo(type="EXTEND_INFO", direction=Direction.OUTGOING)
	@Fetch
	@ManualField
	private CurrencyExtendInfo currencyExtendInfo;//有两个属性可用
	
	@RelatedTo(type="RELATED_BY", direction=Direction.OUTGOING)
	@Fetch
	@ManualField
	private Set<CrimeCase> caseInfos = new HashSet<CrimeCase>(0);
	
	@RelatedToVia(type="SIMILAR", direction=Direction.OUTGOING)
	@Fetch
	@ManualField
    private Set<CurrencySim> similarCM = new HashSet<CurrencySim>(0);

	@Override
    public int hashCode() {
        return new HashCodeBuilder(17, 31). // two randomly chosen prime numbers
            // if deriving: appendSuper(super.hashCode()).
            append(fmid).
            append(piaoyangNumber).
            toHashCode();
    }
	
	@Override
	public boolean equals(Object obj) {

		if (!(obj instanceof CounterfeitMoney))
			return false;
		if (obj == this)
			return true;

		CounterfeitMoney rhs = (CounterfeitMoney) obj;
		if(rhs.caseInfos.size()!=this.caseInfos.size())
			return false;
		for(CrimeCase one:this.caseInfos){
			boolean notFround=true;
			for(CrimeCase two:rhs.caseInfos){
				if(one.getCaseId()!=null&&one.getCaseId().equals(two.getCaseId())){
					notFround=false;
					break;
				}
				else if(one.getcId().equals(two.getcId())){
					notFround=false;
					break;
				}
			}
			if(notFround) return false;
		}
		
		if(rhs.similarCM.size()!=this.similarCM.size())
			return false;
		
		
		return new EqualsBuilder().
		// if deriving: appendSuper(super.equals(obj)).
				append(fmid, rhs.fmid).append(piaoyangNumber, rhs.piaoyangNumber).isEquals();	
	}
	
	public void updateRelation(CounterfeitMoney newOne){
		for(CrimeCase two:newOne.caseInfos){
			boolean notFround=true;
			for(CrimeCase one:this.caseInfos){
				if(one.getCaseId()!=null&&one.getCaseId().equals(two.getCaseId())){
					notFround=false;
					break;
				}
				else if(one.getcId().equals(two.getcId())){
					notFround=false;
					break;
				}
			}
			if(notFround) {
				this.caseInfos.add(two);
			};
		}
	}
	
	public void deleteRepeatingData() {
		HashSet<String> flag = new HashSet<String>();
		ArrayList<CrimeCase> deleteList = new ArrayList<CrimeCase>();
		for (CrimeCase one : this.caseInfos) {
			String key;
			if (one.getCaseId() != null) {
				key = one.getCaseId();
			} else {
				key = one.getcId().toString();
			}

			if (flag.contains(key)) {
				deleteList.add(one);
			} else {
				flag.add(key);
			}
		}
		for(CrimeCase one :deleteList){
			this.caseInfos.remove(one);
		}
		
	}
	
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


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDenomination() {
		return denomination;
	}

	public void setDenomination(String denomination) {
		this.denomination = denomination;
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

	public Date getReceiveTime() {
		return receiveTime;
	}

	public void setReceiveTime(Date receiveTime) {
		this.receiveTime = receiveTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public String getCaseNo() {
		return caseNo;
	}

	public void setCaseNo(String caseNo) {
		this.caseNo = caseNo;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	

	public Long getSmallPic() {
		return smallPic;
	}

	public void setSmallPpic(Long smallPic) {
		this.smallPic = smallPic;
	}

	public String getPiaoyangNumber() {
		return piaoyangNumber;
	}

	public void setPiaoyangNumber(String piaoyangNumber) {
		this.piaoyangNumber = piaoyangNumber;
	}

	public CurrencyExtendInfo getCurrencyExtendInfo() {
		return currencyExtendInfo;
	}

	public void setCurrencyExtendInfo(CurrencyExtendInfo currencyExtendInfo) {
		this.currencyExtendInfo = currencyExtendInfo;
	}

	public Set<CrimeCase> getCaseInfos() {
		return caseInfos;
	}

	public void setCaseInfos(Set<CrimeCase> caseInfos) {
		this.caseInfos = caseInfos;
	}

	public Set<CurrencySim> getSimilarCM() {
		return similarCM;
	}

	public void setSimilarCM(Set<CurrencySim> similarCM) {
		this.similarCM = similarCM;
	}
	
	

}

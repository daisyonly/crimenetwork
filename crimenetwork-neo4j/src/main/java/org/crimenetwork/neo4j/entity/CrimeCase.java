package org.crimenetwork.neo4j.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.crimenetwork.neo4j.untils.ManualField;
import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

@NodeEntity
public class CrimeCase {

	@GraphId
	@ManualField
	private Long id;

	@ManualField
	@Indexed
	private Long cId;

	@Indexed
	private String caseId;

	private String caseName;

	private String registerUnit;// 立案单位

	private Date registerDate;

	private Float seizedAmount;

	@ManualField
	@RelatedTo(type = "INVOLVE", direction = Direction.OUTGOING)
	@Fetch
	private Set<SuspectInfo> suspects = new HashSet<SuspectInfo>(0);

	@ManualField
	@RelatedTo(type = "RELATED", direction = Direction.OUTGOING)
	@Fetch
	private Set<CounterfeitMoney> counterfeitMoneys = new HashSet<CounterfeitMoney>(
			0);

	private String briefInfo;

	@ManualField
	private String caseHappenLocation;
	
	
	private Date caseHappenTime;//案发时间
	
	private String caseHappenDetailAddress;//案发详细地址
	
	
	private String denLocation;//窝点详细地址
	
	private Date prosecutionTime;//起诉时间

	private Date firSecTrialTime;//一二审时间
	
	private String sceneSeizedDescription;//现场缴获
	
	private String seizedCcDescription;//缴获假币情况
	
	private String seizedTotalDescription;//交货金额
	
	@Override
    public int hashCode() {
        return new HashCodeBuilder(17, 31). // two randomly chosen prime numbers
            // if deriving: appendSuper(super.hashCode()).
            append(id).
            append(cId).
            append(caseId).
            toHashCode();
    }

	@Override
	public boolean equals(Object obj) {

		if (!(obj instanceof CrimeCase))
			return false;
		if (obj == this)
			return true;

		CrimeCase rhs = (CrimeCase) obj;
		if(rhs.counterfeitMoneys.size()!=this.counterfeitMoneys.size())
			return false;
		for(CounterfeitMoney one:this.counterfeitMoneys){
			boolean notFround=true;
			for(CounterfeitMoney two:rhs.counterfeitMoneys){
				if(one.getCfmid()!=null&&one.getCfmid().equals(two.getCfmid())){
					notFround=false;
					break;
				}else if(one.getFmid()!=null&&one.getFmid().equals(two.getFmid())){
					notFround=false;
					break;
				}
			}
			if(notFround) return false;
		}
		
		if(rhs.suspects.size()!=this.suspects.size())
			return false;
		for(SuspectInfo one:this.suspects){
			boolean notFround=true;
			for(SuspectInfo two:rhs.suspects){
				if(one.getSuspectId()!=null && one.getSuspectId().equals(two.getSuspectId())){
					notFround=false;
					break;
				}else if(one.getsId().equals(two.getsId())){
					notFround=false;
					break;
				}
			}
			if(notFround) return false;
		}
		
		return new EqualsBuilder().
		// if deriving: appendSuper(super.equals(obj)).
				append(id, rhs.id).append(cId, rhs.cId).append(caseId, rhs.caseId).isEquals();
		
	}
	public void updateRelation(CrimeCase newOne){
		for(CounterfeitMoney two:newOne.counterfeitMoneys){
			boolean notFround=true;
			for(CounterfeitMoney one:this.counterfeitMoneys){
				if(one.getCfmid()!=null&&one.getCfmid().equals(two.getCfmid())){
					notFround=false;
					break;
				}else if(one.getFmid()!=null&&one.getFmid().equals(two.getFmid())){
					notFround=false;
					break;
				}
			}
			if(notFround) this.counterfeitMoneys.add(two);
		}
		
		for(SuspectInfo two:newOne.suspects){
			boolean notFround=true;
			for(SuspectInfo one:this.suspects){
				if(one.getSuspectId()!=null && one.getSuspectId().equals(two.getSuspectId())){
					notFround=false;
					break;
				}else if(one.getsId().equals(two.getsId())){
					notFround=false;
					break;
				}
			}
			if(notFround) this.suspects.add(two);
		}
	}
	
	public void deleteRepeatingData() {
		HashSet<String> flag = new HashSet<String>();
		ArrayList<CounterfeitMoney> deleteList = new ArrayList<CounterfeitMoney>();
		for (CounterfeitMoney one : this.counterfeitMoneys) {
			String key;
			if (one.getPiaoyangNumber() != null) {
				key = one.getPiaoyangNumber();
			} else {
				key = one.getFmid().toString();
			}

			if (flag.contains(key)) {
				deleteList.add(one);
			} else {
				flag.add(key);
			}
		}
		for(CounterfeitMoney one :deleteList){
			this.counterfeitMoneys.remove(one);
		}
		
		flag.clear();
		ArrayList<SuspectInfo> deleteList2 = new ArrayList<SuspectInfo>();
		for (SuspectInfo one : this.suspects) {
			String key;
			if (one.getSuspectId() != null) {
				key = one.getSuspectId();
			} else {
				key = one.getsId().toString();
			}

			if (flag.contains(key)) {
				deleteList2.add(one);
			} else {
				flag.add(key);
			}
		}
		for(SuspectInfo one :deleteList2){
			this.suspects.remove(one);
		}
		
	}
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getcId() {
		return cId;
	}

	public void setcId(Long cId) {
		this.cId = cId;
	}

	public String getCaseId() {
		return caseId;
	}

	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}

	public String getCaseName() {
		return caseName;
	}

	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}

	public String getRegisterUnit() {
		return registerUnit;
	}

	public void setRegisterUnit(String registerUnit) {
		this.registerUnit = registerUnit;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public Float getSeizedAmount() {
		return seizedAmount;
	}

	public void setSeizedAmount(Float seizedAmount) {
		this.seizedAmount = seizedAmount;
	}

	public Set<SuspectInfo> getSuspects() {
		return suspects;
	}

	public void setSuspects(Set<SuspectInfo> suspects) {
		this.suspects = suspects;
	}

	public Set<CounterfeitMoney> getCounterfeitMoneys() {
		return counterfeitMoneys;
	}

	public void setCounterfeitMoneys(Set<CounterfeitMoney> counterfeitMoneys) {
		this.counterfeitMoneys = counterfeitMoneys;
	}

	public String getBriefInfo() {
		return briefInfo;
	}

	public void setBriefInfo(String briefInfo) {
		this.briefInfo = briefInfo;
	}

	public String getCaseHappenLocation() {
		return caseHappenLocation;
	}

	public void setCaseHappenLocation(String caseHappenLocation) {
		this.caseHappenLocation = caseHappenLocation;
	}

	public Date getCaseHappenTime() {
		return caseHappenTime;
	}

	public void setCaseHappenTime(Date caseHappenTime) {
		this.caseHappenTime = caseHappenTime;
	}

	public String getCaseHappenDetailAddress() {
		return caseHappenDetailAddress;
	}

	public void setCaseHappenDetailAddress(String caseHappenDetailAddress) {
		this.caseHappenDetailAddress = caseHappenDetailAddress;
	}

	public String getDenLocation() {
		return denLocation;
	}

	public void setDenLocation(String denLocation) {
		this.denLocation = denLocation;
	}

	public Date getProsecutionTime() {
		return prosecutionTime;
	}

	public void setProsecutionTime(Date prosecutionTime) {
		this.prosecutionTime = prosecutionTime;
	}

	public Date getFirSecTrialTime() {
		return firSecTrialTime;
	}

	public void setFirSecTrialTime(Date firSecTrialTime) {
		this.firSecTrialTime = firSecTrialTime;
	}

	public String getSceneSeizedDescription() {
		return sceneSeizedDescription;
	}

	public void setSceneSeizedDescription(String sceneSeizedDescription) {
		this.sceneSeizedDescription = sceneSeizedDescription;
	}

	public String getSeizedCcDescription() {
		return seizedCcDescription;
	}

	public void setSeizedCcDescription(String seizedCcDescription) {
		this.seizedCcDescription = seizedCcDescription;
	}

	public String getSeizedTotalDescription() {
		return seizedTotalDescription;
	}

	public void setSeizedTotalDescription(String seizedTotalDescription) {
		this.seizedTotalDescription = seizedTotalDescription;
	}
	
	

}

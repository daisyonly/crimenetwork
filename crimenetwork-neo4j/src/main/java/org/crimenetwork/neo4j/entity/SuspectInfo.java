package org.crimenetwork.neo4j.entity;

import java.util.ArrayList;
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
public class SuspectInfo {
	@GraphId
	@ManualField
	private Long id;

	@ManualField
	@Indexed
	private Long sId;

	@Indexed
	private String suspectId;

	private String name;

	private String idCardNumber;

	@ManualField
	private String nativeLocation;

	@ManualField
	private String gender;

	private String phoneNumber;

	@ManualField
	@RelatedTo(type = "INVOLVE_IN", direction = Direction.OUTGOING)
	@Fetch
	private Set<CrimeCase> cases = new HashSet<CrimeCase>(0);

	@ManualField
	private String nation;
	
	
	private String caseInfo;
	
	@ManualField
	private String accent;//口音
	
	private String locationCodeByCurrentAddress;//现住地址编码
	

	private String adress;//现住详细地址
	

	private String locationCodeByRegisteredResidence;//户籍编码
	

	private String registeredResidenceDetail;//户籍详细地址
	
	
	@ManualField
	private String occupation;
	
	@ManualField
	private String isLc;
	
	@ManualField
	private String  isReoffender;
	
	@ManualField
	private String  isHabitualOffender;
	
	
	private String qqNumber;
	
	private String bankAccount;
	
	private String handwritingNum;//笔记采集编号
	
	private String fingerprintNum;//指纹采集编号
	
	private String dnaNum;//DNA采集编号

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 31). // two randomly chosen prime numbers
				// if deriving: appendSuper(super.hashCode()).
				append(sId).append(suspectId).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {

		if (!(obj instanceof SuspectInfo))
			return false;
		if (obj == this)
			return true;

		SuspectInfo rhs = (SuspectInfo) obj;
		if (rhs.cases.size() != this.cases.size())
			return false;
		for (CrimeCase one : this.cases) {
			boolean notFround = true;
			for (CrimeCase two : rhs.cases) {
				if (one.getCaseId() != null
						&& one.getCaseId().equals(two.getCaseId())) {
					notFround = false;
					break;
				} else if (one.getcId().equals(two.getcId())) {
					notFround = false;
					break;
				}
			}
			if (notFround)
				return false;
		}

		return new EqualsBuilder()
				.
				// if deriving: appendSuper(super.equals(obj)).
				append(sId, rhs.sId).append(suspectId, rhs.suspectId)
				.isEquals();

	}

	public void deleteRepeatingData() {
		HashSet<String> flag = new HashSet<String>();
		ArrayList<CrimeCase> deleteList = new ArrayList<CrimeCase>();
		for (CrimeCase one : this.cases) {
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
			this.cases.remove(one);
		}

	}

	public void updateRelation(SuspectInfo newOne) {
		for (CrimeCase two : newOne.cases) {
			boolean notFround = true;
			for (CrimeCase one : this.cases) {
				if (one.getCaseId() != null
						&& one.getCaseId().equals(two.getCaseId())) {
					notFround = false;
					break;
				} else if (one.getcId().equals(two.getcId())) {
					notFround = false;
					break;
				}
			}
			if (notFround)
				this.cases.add(two);
		}
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public Long getsId() {
		return sId;
	}

	public void setsId(Long sId) {
		this.sId = sId;
	}

	public String getSuspectId() {
		return suspectId;
	}

	public void setSuspectId(String suspectId) {
		this.suspectId = suspectId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdCardNumber() {
		return idCardNumber;
	}

	public void setIdCardNumber(String idCardNumber) {
		this.idCardNumber = idCardNumber;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getNativeLocation() {
		return nativeLocation;
	}

	public void setNativeLocation(String nativeLocation) {
		this.nativeLocation = nativeLocation;
	}

	public Set<CrimeCase> getCases() {
		return cases;
	}

	public void setCases(Set<CrimeCase> cases) {
		this.cases = cases;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCaseInfo() {
		return caseInfo;
	}

	public void setCaseInfo(String caseInfo) {
		this.caseInfo = caseInfo;
	}

	public String getAccent() {
		return accent;
	}

	public void setAccent(String accent) {
		this.accent = accent;
	}

	public String getLocationCodeByCurrentAddress() {
		return locationCodeByCurrentAddress;
	}

	public void setLocationCodeByCurrentAddress(String locationCodeByCurrentAddress) {
		this.locationCodeByCurrentAddress = locationCodeByCurrentAddress;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getLocationCodeByRegisteredResidence() {
		return locationCodeByRegisteredResidence;
	}

	public void setLocationCodeByRegisteredResidence(
			String locationCodeByRegisteredResidence) {
		this.locationCodeByRegisteredResidence = locationCodeByRegisteredResidence;
	}

	public String getRegisteredResidenceDetail() {
		return registeredResidenceDetail;
	}

	public void setRegisteredResidenceDetail(String registeredResidenceDetail) {
		this.registeredResidenceDetail = registeredResidenceDetail;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getIsLc() {
		return isLc;
	}

	public void setIsLc(String isLc) {
		this.isLc = isLc;
	}

	public String getIsReoffender() {
		return isReoffender;
	}

	public void setIsReoffender(String isReoffender) {
		this.isReoffender = isReoffender;
	}

	public String getIsHabitualOffender() {
		return isHabitualOffender;
	}

	public void setIsHabitualOffender(String isHabitualOffender) {
		this.isHabitualOffender = isHabitualOffender;
	}

	public String getQqNumber() {
		return qqNumber;
	}

	public void setQqNumber(String qqNumber) {
		this.qqNumber = qqNumber;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String getHandwritingNum() {
		return handwritingNum;
	}

	public void setHandwritingNum(String handwritingNum) {
		this.handwritingNum = handwritingNum;
	}

	public String getFingerprintNum() {
		return fingerprintNum;
	}

	public void setFingerprintNum(String fingerprintNum) {
		this.fingerprintNum = fingerprintNum;
	}

	public String getDnaNum() {
		return dnaNum;
	}

	public void setDnaNum(String dnaNum) {
		this.dnaNum = dnaNum;
	}
	
	

}

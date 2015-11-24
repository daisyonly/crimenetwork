package org.crimenetwork.mongodb.entity.suspect;

import java.util.HashSet;
import java.util.Set;

import org.crimenetwork.mongodb.entity.AbstractDocument;
import org.crimenetwork.mongodb.entity.share.MLocation;
import org.crimenetwork.mongodb.utils.ManualField;
import org.springframework.data.mongodb.core.mapping.Document;
@Document
public class MSuspectBaseInfo extends AbstractDocument{

	@ManualField
	private Long sId;
	
	private String suspectId;
	
	private String name;
	
	private String idCardNumber;
	
	@ManualField
	private MLocation nativeLocation;
	
	@ManualField
	private String gender;
	
	private String phoneNumber;
	
	@ManualField
	private Set<SuspectCaseBaseInfo> cases = new HashSet<SuspectCaseBaseInfo>();

	@ManualField
	private String nation;
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
	public MLocation getNativeLocation() {
		return nativeLocation;
	}
	public void setNativeLocation(MLocation nativeLocation) {
		this.nativeLocation = nativeLocation;
	}
	public Set<SuspectCaseBaseInfo> getCases() {
		return cases;
	}
	public void setCases(Set<SuspectCaseBaseInfo> cases) {
		this.cases = cases;
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
	
}

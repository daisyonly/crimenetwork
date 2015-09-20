package org.crimenetwork.oracle.entity.cases;

import java.sql.Timestamp;

import org.crimenetwork.oracle.entity.EduLevel;
import org.crimenetwork.oracle.entity.currency.CurrencyType;
import org.crimenetwork.oracle.entity.share.CountryCode;
import org.crimenetwork.oracle.entity.share.LocationCode;
import org.crimenetwork.oracle.entity.share.UserInfo;
import org.crimenetwork.oracle.entity.suspect.Accent;
import org.crimenetwork.oracle.entity.suspect.Gender;
import org.crimenetwork.oracle.entity.suspect.Occupation;

/**
 * BlacklistInfo entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class BlacklistInfo implements java.io.Serializable {

	// Fieldsff

	private Long id;
	
	private UserInfo userInfo;
	
	private CurrencyType currencyType;//携带假币类别代码
	
	private Occupation occupation;
	private Gender gender;
	
	
	private Accent accent;
	
	private LocationCode locationCodeByHomeAddressLocation;
	
	private CountryCode countryCode;
	
	private LocationCode locationCodeByNativePlace;
	private String denominationType;
	
	private CrimeInfo crimeInfo;
	private String name;
	private String idCardNumber;
	private String alias;
	
	private Timestamp year;
	
	private EduLevel eduLevel;
	private String age;
	private String certificateNum;
	private String crimeName;
	private String crimeMeans;
	private String criminalGroup;
	private String counterfeitFeatures;
	private String counterfeitQuantity;
	private String homeAddressDetailAdress;
	private Timestamp detainedTime;
	private Timestamp arrestedTime;
	private String involvedAmount;
	private String processResults;
	private Timestamp createTime;
	private String userName;
	private String fillUnitId;
	private String fillUnitName;
	private String telphone;
	private String caseId;
	private String counterfeitTrail;
	private String suspectId;
	private Timestamp lastUpdateTime;
	private boolean isimportedexcel;
	private Long communicationInfo_cfid;
	private Long communication_cfid;
	private Long pic_cfid;
	private Long other_cfid;

	// Constructors

	/** default constructor */
	public BlacklistInfo() {
	}

	/** full constructor */
	public BlacklistInfo(UserInfo userInfo, CurrencyType currencyType,
			Occupation occupation, Gender gender,
			Accent accent,
			LocationCode locationCodeByHomeAddressLocation,
			CountryCode countryCode, LocationCode locationCodeByNativePlace,
			String denominationType, CrimeInfo crimeInfo,
			String name, String idCardNumber, String alias, Timestamp year,
			EduLevel eduLevel, String age, String certificateNum,
			String crimeName, String crimeMeans, String criminalGroup,
			String counterfeitFeatures, String counterfeitQuantity,
			String homeAddressDetailAdress, Timestamp detainedTime,
			Timestamp arrestedTime, String involvedAmount,
			String processResults, Timestamp createTime, String userName,
			String fillUnitId, String fillUnitName, String telphone,
			String caseId, String counterfeitTrail, String suspectId,
			Timestamp lastUpdateTime,boolean isimportedexcel,Long communicationInfo_cfid,
			Long communication_cfid,Long pic_cfid,Long other_cfid) {
		this.userInfo = userInfo;
		this.currencyType = currencyType;
		this.occupation = occupation;
		this.gender = gender;
		this.accent = accent;
		this.locationCodeByHomeAddressLocation = locationCodeByHomeAddressLocation;
		this.countryCode = countryCode;
		this.locationCodeByNativePlace = locationCodeByNativePlace;
		this.denominationType = denominationType;
		this.crimeInfo = crimeInfo;
		this.name = name;
		this.idCardNumber = idCardNumber;
		this.alias = alias;
		this.year = year;
		this.eduLevel = eduLevel;
		this.age = age;
		this.certificateNum = certificateNum;
		this.crimeName = crimeName;
		this.crimeMeans = crimeMeans;
		this.criminalGroup = criminalGroup;
		this.counterfeitFeatures = counterfeitFeatures;
		this.counterfeitQuantity = counterfeitQuantity;
		this.homeAddressDetailAdress = homeAddressDetailAdress;
		this.detainedTime = detainedTime;
		this.arrestedTime = arrestedTime;
		this.involvedAmount = involvedAmount;
		this.processResults = processResults;
		this.createTime = createTime;
		this.userName = userName;
		this.fillUnitId = fillUnitId;
		this.fillUnitName = fillUnitName;
		this.telphone = telphone;
		this.caseId = caseId;
		this.counterfeitTrail = counterfeitTrail;
		this.suspectId = suspectId;
		this.lastUpdateTime = lastUpdateTime;
		this.isimportedexcel=isimportedexcel;
		this.communicationInfo_cfid=communication_cfid;
		this.communication_cfid=communication_cfid;
		this.pic_cfid=pic_cfid;
		this.other_cfid=other_cfid;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserInfo getUserInfo() {
		return this.userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public CurrencyType getCurrencyType() {
		return this.currencyType;
	}

	public void setCurrencyType(CurrencyType currencyType) {
		this.currencyType = currencyType;
	}

	public Occupation getOccupation() {
		return this.occupation;
	}

	public void setOccupation(Occupation occupation) {
		this.occupation = occupation;
	}

	public Gender getGender() {
		return this.gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Accent getAccent() {
		return this.accent;
	}

	public void setAccent(Accent accent) {
		this.accent = accent;
	}

	public LocationCode getLocationCodeByHomeAddressLocation() {
		return this.locationCodeByHomeAddressLocation;
	}

	public void setLocationCodeByHomeAddressLocation(
			LocationCode locationCodeByHomeAddressLocation) {
		this.locationCodeByHomeAddressLocation = locationCodeByHomeAddressLocation;
	}

	public CountryCode getCountryCode() {
		return this.countryCode;
	}

	public void setCountryCode(CountryCode countryCode) {
		this.countryCode = countryCode;
	}

	public LocationCode getLocationCodeByNativePlace() {
		return this.locationCodeByNativePlace;
	}

	public void setLocationCodeByNativePlace(
			LocationCode locationCodeByNativePlace) {
		this.locationCodeByNativePlace = locationCodeByNativePlace;
	}
	public String getDenominationType() {
		return denominationType;
	}
	public void setDenominationType(String denominationType) {
		this.denominationType = denominationType;
	}
	public CrimeInfo getCrimeInfo() {
		return this.crimeInfo;
	}
	public void setCrimeInfo(CrimeInfo crimeInfo) {
		this.crimeInfo = crimeInfo;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdCardNumber() {
		return this.idCardNumber;
	}

	public void setIdCardNumber(String idCardNumber) {
		this.idCardNumber = idCardNumber;
	}

	public String getAlias() {
		return this.alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public Timestamp getYear() {
		return this.year;
	}

	public void setYear(Timestamp year) {
		this.year = year;
	}
	public EduLevel getEduLevel() {
		return eduLevel;
	}

	public void setEduLevel(EduLevel eduLevel) {
		this.eduLevel = eduLevel;
	}

	public String getAge() {
		return this.age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getCertificateNum() {
		return this.certificateNum;
	}

	public void setCertificateNum(String certificateNum) {
		this.certificateNum = certificateNum;
	}

	public String getCrimeName() {
		return this.crimeName;
	}

	public void setCrimeName(String crimeName) {
		this.crimeName = crimeName;
	}

	public String getCrimeMeans() {
		return this.crimeMeans;
	}

	public void setCrimeMeans(String crimeMeans) {
		this.crimeMeans = crimeMeans;
	}

	public String getCriminalGroup() {
		return this.criminalGroup;
	}

	public void setCriminalGroup(String criminalGroup) {
		this.criminalGroup = criminalGroup;
	}

	public String getCounterfeitFeatures() {
		return this.counterfeitFeatures;
	}

	public void setCounterfeitFeatures(String counterfeitFeatures) {
		this.counterfeitFeatures = counterfeitFeatures;
	}

	public String getCounterfeitQuantity() {
		return this.counterfeitQuantity;
	}

	public void setCounterfeitQuantity(String counterfeitQuantity) {
		this.counterfeitQuantity = counterfeitQuantity;
	}

	public String getHomeAddressDetailAdress() {
		return this.homeAddressDetailAdress;
	}

	public void setHomeAddressDetailAdress(String homeAddressDetailAdress) {
		this.homeAddressDetailAdress = homeAddressDetailAdress;
	}

	public Timestamp getDetainedTime() {
		return this.detainedTime;
	}

	public void setDetainedTime(Timestamp detainedTime) {
		this.detainedTime = detainedTime;
	}

	public Timestamp getArrestedTime() {
		return this.arrestedTime;
	}

	public void setArrestedTime(Timestamp arrestedTime) {
		this.arrestedTime = arrestedTime;
	}

	public String getInvolvedAmount() {
		return this.involvedAmount;
	}

	public void setInvolvedAmount(String involvedAmount) {
		this.involvedAmount = involvedAmount;
	}

	public String getProcessResults() {
		return this.processResults;
	}

	public void setProcessResults(String processResults) {
		this.processResults = processResults;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getFillUnitId() {
		return fillUnitId;
	}

	public void setFillUnitId(String fillUnitId) {
		this.fillUnitId = fillUnitId;
	}

	public String getFillUnitName() {
		return this.fillUnitName;
	}

	public void setFillUnitName(String fillUnitName) {
		this.fillUnitName = fillUnitName;
	}

	public String getTelphone() {
		return this.telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public String getCaseId() {
		return this.caseId;
	}

	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}

	public String getCounterfeitTrail() {
		return this.counterfeitTrail;
	}

	public void setCounterfeitTrail(String counterfeitTrail) {
		this.counterfeitTrail = counterfeitTrail;
	}

	public String getSuspectId() {
		return this.suspectId;
	}

	public void setSuspectId(String suspectId) {
		this.suspectId = suspectId;
	}

	public Timestamp getLastUpdateTime() {
		return this.lastUpdateTime;
	}

	public void setLastUpdateTime(Timestamp lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public boolean isIsimportedexcel() {
		return isimportedexcel;
	}

	public void setIsimportedexcel(boolean isimportedexcel) {
		this.isimportedexcel = isimportedexcel;
	}

	public Long getCommunicationInfo_cfid() {
		return communicationInfo_cfid;
	}

	public void setCommunicationInfo_cfid(Long communicationInfoCfid) {
		communicationInfo_cfid = communicationInfoCfid;
	}

	public Long getCommunication_cfid() {
		return communication_cfid;
	}

	public void setCommunication_cfid(Long communicationCfid) {
		communication_cfid = communicationCfid;
	}

	public Long getPic_cfid() {
		return pic_cfid;
	}

	public void setPic_cfid(Long picCfid) {
		pic_cfid = picCfid;
	}
	public Long getOther_cfid() {
		return other_cfid;
	}
	public void setOther_cfid(Long otherCfid) {
		other_cfid = otherCfid;
	}
}
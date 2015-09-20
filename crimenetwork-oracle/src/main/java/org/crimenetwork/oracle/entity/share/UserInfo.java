package org.crimenetwork.oracle.entity.share;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.crimenetwork.oracle.entity.suspect.Gender;

/**
 * UserInfo entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name="user_info", schema="system")
public class UserInfo implements java.io.Serializable {

	// Fields

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
	@SequenceGenerator(name="seq", sequenceName="SEQUENCE_1")
	@Column(name="user_id")
	private Long userId;//
	
	private Gender gender;//

	@Column(name="location_code")
	private LocationCode locationCode;

	private String policeNum;//警号
	private String username;//用户名
	private String password;
	private String trueName;//真是姓名
	private String unit;
	private String position;//职务
	private Timestamp birthday;
	private Timestamp lastLoginTime;
	private Boolean isLiaisonMan;
	private String officePhone;
	private String mobilePhone;
	
	
	private Set interactionInfos = new HashSet(0);
	
	private Set suspectInfos = new HashSet(0);
	
	private Set replyInfos = new HashSet(0);
	
	private Set infos = new HashSet(0);
	
	private Set currencyCharacterTrains = new HashSet(0);
	
	private Set blacklistInfos = new HashSet(0);
	
	private Set caseInfos = new HashSet(0);
	
	private Set jiabiBaseInfos = new HashSet(0);
	
	private Set commonLogs = new HashSet(0);

	// Constructors

	/** default constructor */
	public UserInfo() {
	}

	/** full constructor */
	public UserInfo(Gender gender, LocationCode locationCode,
			String policeNum, String username,
			String password, String trueName, String unit, String position,
			Timestamp birthday, Timestamp lastLoginTime, Boolean isLiaisonMan,String officePhone,String mobilePhone,
			Set interactionInfos,
			Set suspectInfos, Set replyInfos, Set userRoles, Set infos,
			Set currencyCharacterTrains, Set blacklistInfos, Set caseInfos,Set jiabiBaseInfos,Set commonLogs) {
		this.gender = gender;
		this.locationCode = locationCode;
		this.policeNum = policeNum;
		this.username = username;
		this.password = password;
		this.trueName = trueName;
		this.unit = unit;
		this.position = position;
		this.birthday = birthday;
		this.lastLoginTime = lastLoginTime;
		this.isLiaisonMan=isLiaisonMan;
		this.officePhone=officePhone;
		this.mobilePhone=mobilePhone;	
		this.interactionInfos = interactionInfos;
		this.suspectInfos = suspectInfos;
		this.replyInfos = replyInfos;
		this.infos = infos;
		this.currencyCharacterTrains = currencyCharacterTrains;
		this.blacklistInfos = blacklistInfos;
		this.caseInfos = caseInfos;
		this.jiabiBaseInfos = jiabiBaseInfos;
		this.commonLogs=commonLogs;
	}

	// Property accessors

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Gender getGender() {
		return this.gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public LocationCode getLocationCode() {
		return this.locationCode;
	}

	public void setLocationCode(LocationCode locationCode) {
		this.locationCode = locationCode;
	}

	

	public String getPoliceNum() {
		return this.policeNum;
	}

	public void setPoliceNum(String policeNum) {
		this.policeNum = policeNum;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTrueName() {
		return this.trueName;
	}

	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}

	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getPosition() {
		return this.position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Timestamp getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Timestamp birthday) {
		this.birthday = birthday;
	}

	public Timestamp getLastLoginTime() {
		return this.lastLoginTime;
	}

	public void setLastLoginTime(Timestamp lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public Set getInteractionInfos() {
		return this.interactionInfos;
	}

	public void setInteractionInfos(Set interactionInfos) {
		this.interactionInfos = interactionInfos;
	}

	public Set getSuspectInfos() {
		return this.suspectInfos;
	}

	public void setSuspectInfos(Set suspectInfos) {
		this.suspectInfos = suspectInfos;
	}

	public Set getReplyInfos() {
		return this.replyInfos;
	}

	public void setReplyInfos(Set replyInfos) {
		this.replyInfos = replyInfos;
	}


	

	public Set getInfos() {
		return this.infos;
	}

	public void setInfos(Set infos) {
		this.infos = infos;
	}

	public Set getCurrencyCharacterTrains() {
		return this.currencyCharacterTrains;
	}

	public void setCurrencyCharacterTrains(Set currencyCharacterTrains) {
		this.currencyCharacterTrains = currencyCharacterTrains;
	}

	public Set getBlacklistInfos() {
		return this.blacklistInfos;
	}

	public void setBlacklistInfos(Set blacklistInfos) {
		this.blacklistInfos = blacklistInfos;
	}

	public Set getCaseInfos() {
		return this.caseInfos;
	}

	public void setCaseInfos(Set caseInfos) {
		this.caseInfos = caseInfos;
	}

	public Set getJiabiBaseInfos() {
		return jiabiBaseInfos;
	}

	public void setJiabiBaseInfos(Set jiabiBaseInfos) {
		this.jiabiBaseInfos = jiabiBaseInfos;
	}

	public String getOfficePhone() {
		return officePhone;
	}

	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public Boolean getIsLiaisonMan() {
		return isLiaisonMan;
	}

	public void setIsLiaisonMan(Boolean isLiaisonMan) {
		this.isLiaisonMan = isLiaisonMan;
	}

	public Set getCommonLogs() {
		return commonLogs;
	}

	public void setCommonLogs(Set commonLogs) {
		this.commonLogs = commonLogs;
	}
	

}
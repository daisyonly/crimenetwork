package org.crimenetwork.mongodb.entity.share;

import java.sql.Timestamp;


import org.crimenetwork.mongodb.entity.suspect.Gender;



public class UserInfo{
	
	private Long userId;//
	
	
	private Gender gender;//


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
	
	
	// Constructors

	/** default constructor */
	public UserInfo() {
	}

	/** full constructor */
	public UserInfo(Gender gender, LocationCode locationCode,
			String policeNum, String username,
			String password, String trueName, String unit, String position,
			Timestamp birthday, Timestamp lastLoginTime, Boolean isLiaisonMan,String officePhone,String mobilePhone) {
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

}
package org.crimenetwork.oracle.entity.share;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="gender")
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
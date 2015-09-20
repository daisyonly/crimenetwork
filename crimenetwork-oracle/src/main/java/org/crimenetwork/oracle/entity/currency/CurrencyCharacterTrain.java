package org.crimenetwork.oracle.entity.currency;

import java.sql.Timestamp;

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

import org.crimenetwork.oracle.entity.share.UserInfo;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;



/**
 * CurrencyCharacterTrain entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="currency_character_train",schema="system")
public class CurrencyCharacterTrain{

	 @Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
	@SequenceGenerator(name="seq", sequenceName="SEQUENCE_1")
     private Long id;
	 @ManyToOne
 	 @JoinColumn(name="create_user")
     private UserInfo userInfo;
     @ManyToOne
 	 @JoinColumn(name="version")
     private VersionType versionType;
     @ManyToOne
     @JoinColumn(name="category")
     private CategoryType categoryType;
     @ManyToOne
     @JoinColumn(name="currency_Type")
     private CurrencyType currencyType;//货币类别
     @ManyToOne
     @JoinColumn(name="denomination")
     @NotFound(action=NotFoundAction.IGNORE)
     private DenominationType denominationType;
     private String currencyid;
     private String guanzihao;
     @Column(name="create_Time")
     private Timestamp createTime;
     @Column(name="is_real_money", precision=1, scale=0)
     private Boolean isRealMoney;
     @Column(name="cfid")
     private Long cfid;
     @Column(name="content")
     private String content;
     @Column(name="pic_cfid")
     private Long picCfid;
     @ManyToOne(fetch=FetchType.LAZY)
     @NotFound(action=NotFoundAction.IGNORE)
     @JoinColumn(name="identify")
     private ClassificationNumber identify;
     
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public UserInfo getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	
	public VersionType getVersionType() {
		return versionType;
	}
	public void setVersionType(VersionType versionType) {
		this.versionType = versionType;
	}
    public CategoryType getCategoryType() {
		return categoryType;
	}
	public void setCategoryType(CategoryType categoryType) {
		this.categoryType = categoryType;
	}
	public CurrencyType getCurrencyType() {
		return currencyType;
	}
	public void setCurrencyType(CurrencyType currencyType) {
		this.currencyType = currencyType;
	}
    public DenominationType getDenominationType() {
		return denominationType;
	}
	public void setDenominationType(DenominationType denominationType) {
		this.denominationType = denominationType;
	}
	public String getCurrencyid() {
		return currencyid;
	}
	public void setCurrencyid(String currencyid) {
		this.currencyid = currencyid;
	}
	public String getGuanzihao() {
		return guanzihao;
	}
	public void setGuanzihao(String guanzihao) {
		this.guanzihao = guanzihao;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public Boolean getIsRealMoney() {
		return isRealMoney;
	}
	public void setIsRealMoney(Boolean isRealMoney) {
		this.isRealMoney = isRealMoney;
	}
	public Long getCfid() {
		return cfid;
	}
	public void setCfid(Long cfid) {
		this.cfid = cfid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Long getPicCfid() {
		return picCfid;
	}
	public void setPicCfid(Long picCfid) {
		this.picCfid = picCfid;
	}
	public ClassificationNumber getIdentify() {
		return identify;
	}
	public void setIdentify(ClassificationNumber identify) {
		this.identify = identify;
	}


}
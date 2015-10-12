package org.crimenetwork.mongodb.entity.currency;


public class CurrencyCharacterTrain{
    
     private String versionType;
   
     private String categoryType;
    
     private String currencyType;//货币类别
    
     private MDenominationType denominationType;
     private String currencyid;
     private String guanzihao;
     
   
     private Boolean isRealMoney;
     
     private Long cfid;
     
     private String content;
     
     private Long picCfid;
     
     private MClassificationNumber identify;
     
	
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
	public String getCurrencyType() {
		return currencyType;
	}
	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}
    public MDenominationType getDenominationType() {
		return denominationType;
	}
	public void setDenominationType(MDenominationType denominationType) {
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
	public MClassificationNumber getIdentify() {
		return identify;
	}
	public void setIdentify(MClassificationNumber identify) {
		this.identify = identify;
	}


}
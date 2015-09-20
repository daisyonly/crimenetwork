package org.crimenetwork.oracle.entity.currency;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.crimenetwork.oracle.entity.share.IBasicCode;


/**
 * DenominationType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="jiabi_denomination_type",schema="system")
public class DenominationType implements IBasicCode{
	@Id
	private String code;
	@Column
	private String name;
	@Column
	private String description;
	@Column(name="category_code", length=20)
	private String categoryCode;
	@Column(name="version_code", length=20)
	private String versionCode;
	@Column
	private String value_moduleType;
	
	@OneToMany(mappedBy="denominationType" )
	private Set<JiabiBaseInfo> jiabiBaseInfos = new HashSet<JiabiBaseInfo>();

	@OneToMany(mappedBy="denominationType" )
	private Set<CurrencyCharacterTrain> currencyCharacterTrains = new HashSet<CurrencyCharacterTrain>();
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Set<JiabiBaseInfo> getJiabiBaseInfos() {
		return jiabiBaseInfos;
	}
	public void setJiabiBaseInfos(Set<JiabiBaseInfo> jiabiBaseInfos) {
		this.jiabiBaseInfos = jiabiBaseInfos;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getCategoryCode() {
		return categoryCode;
	}
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}
	public String getVersionCode() {
		return versionCode;
	}
	public void setVersionCode(String versionCode) {
		this.versionCode = versionCode;
	}
	
	public Set<CurrencyCharacterTrain> getCurrencyCharacterTrains() {
		return currencyCharacterTrains;
	}
	public void setCurrencyCharacterTrains(
			Set<CurrencyCharacterTrain> currencyCharacterTrains) {
		this.currencyCharacterTrains = currencyCharacterTrains;
	}
	public String getValue_moduleType() {
		return value_moduleType;
	}
	public void setValue_moduleType(String valueModuleType) {
		value_moduleType = valueModuleType;
	}
	
}
package org.crimenetwork.oracle.entity.currency;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.crimenetwork.oracle.entity.share.IBasicCode;


/**
 * JiabiCurrencyCategory entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="jiabi_category_type", schema="system")
@org.hibernate.annotations.Proxy(lazy = false)
public class CategoryType implements IBasicCode{

	@Id
	private String code;
	@Column
	private String name;
	@Column
	private String description;
	
	@OneToMany(mappedBy="categoryType",fetch=FetchType.LAZY)
	private Set<JiabiBaseInfo> jiabiBaseInfos = new HashSet<JiabiBaseInfo>();
	
	@OneToMany(mappedBy="categoryType",fetch=FetchType.LAZY)
	private Set<ClassificationNumber> classifications = new HashSet<ClassificationNumber>();
	
	@OneToMany(mappedBy="categoryType")
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
	
	public Set<ClassificationNumber> getClassifications() {
		return classifications;
	}
	public void setClassifications(Set<ClassificationNumber> classifications) {
		this.classifications = classifications;
	}
	public Set<CurrencyCharacterTrain> getCurrencyCharacterTrains() {
		return currencyCharacterTrains;
	}
	public void setCurrencyCharacterTrains(
			Set<CurrencyCharacterTrain> currencyCharacterTrains) {
		this.currencyCharacterTrains = currencyCharacterTrains;
	}

	
}
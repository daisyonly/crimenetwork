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
 * CurrencyType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="currency_type",schema="system")
public class CurrencyType implements IBasicCode{
	@Id
	private String code;
	@Column
	private String description;
	@OneToMany(mappedBy="currencyType")

	private Set<JiabiBaseInfo> jiabiBaseInfos = new HashSet<JiabiBaseInfo>();

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
//	public Set<JiabiBaseInfo> getJiabiBaseInfos() {
//		return jiabiBaseInfos;
//	}
//	public void setJiabiBaseInfos(Set<JiabiBaseInfo> jiabiBaseInfos) {
//		this.jiabiBaseInfos = jiabiBaseInfos;
//	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column
	private String name;
}
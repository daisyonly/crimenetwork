package org.crimenetwork.oracle.entity.jiabisim;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="jiabi_denomination_type",schema="system")
public class SimDenominationType{
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
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy="denominationType")
	private Set<SimJiabiBaseInfo> jiabiBaseInfos = new HashSet<SimJiabiBaseInfo>();

	
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
	public Set<SimJiabiBaseInfo> getJiabiBaseInfos() {
		return jiabiBaseInfos;
	}
	public void setJiabiBaseInfos(Set<SimJiabiBaseInfo> jiabiBaseInfos) {
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
	
	public String getValue_moduleType() {
		return value_moduleType;
	}
	public void setValue_moduleType(String valueModuleType) {
		value_moduleType = valueModuleType;
	}
	
}
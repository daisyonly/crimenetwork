package org.crimenetwork.oracle.entity.currency;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table(name="Jiabi_identify",schema="system")
@org.hibernate.annotations.Proxy(lazy = false)
public class ClassificationNumber {
	@Id
	private String code;
	@Column
	private String name;
	@Column(name="description")
	private String description;
	@Column
	private Long cfid;
	@Column(name="small_cfid")
	private Long smallCfid;
	@Column(name="small_cfid_400")
	private Long smallCfid400;
	@Column(name="small_cfid_96")
	private Long smallCfid96;
	@Column(name="back_cfid")
	private Long backCfid;
	@Column(name="back_small_cfid")
	private Long backSmallCfid;
	@Column(name="back_small_cfid_400")
	private Long backSmallCfid400;
	@Column(name="back_small_cfid_96")
	private Long backSmallCfid96;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="category_code")
	private CategoryType categoryType;
	@ManyToOne
	@JoinColumn(name="version_code")
	private VersionType versionType;
	@ManyToOne
	@JoinColumn(name="denomination_code")
	private DenominationType denominationType;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getCfid() {
		return cfid;
	}
	public void setCfid(Long cfid) {
		this.cfid = cfid;
	}
	public Long getSmallCfid() {
		return smallCfid;
	}
	public void setSmallCfid(Long smallCfid) {
		this.smallCfid = smallCfid;
	}
	public Long getSmallCfid400() {
		return smallCfid400;
	}
	public void setSmallCfid400(Long smallCfid400) {
		this.smallCfid400 = smallCfid400;
	}
	public Long getSmallCfid96() {
		return smallCfid96;
	}
	public void setSmallCfid96(Long smallCfid96) {
		this.smallCfid96 = smallCfid96;
	}
	public Long getBackCfid() {
		return backCfid;
	}
	public void setBackCfid(Long backCfid) {
		this.backCfid = backCfid;
	}
	public Long getBackSmallCfid() {
		return backSmallCfid;
	}
	public void setBackSmallCfid(Long backSmallCfid) {
		this.backSmallCfid = backSmallCfid;
	}
	public Long getBackSmallCfid400() {
		return backSmallCfid400;
	}
	public void setBackSmallCfid400(Long backSmallCfid400) {
		this.backSmallCfid400 = backSmallCfid400;
	}
	public Long getBackSmallCfid96() {
		return backSmallCfid96;
	}
	public void setBackSmallCfid96(Long backSmallCfid96) {
		this.backSmallCfid96 = backSmallCfid96;
	}
	public CategoryType getCategoryType() {
		return categoryType;
	}
	public void setCategoryType(CategoryType categoryType) {
		this.categoryType = categoryType;
	}	
	
	public VersionType getVersionType() {
		return versionType;
	}
	public void setVersionType(VersionType versionType) {
		this.versionType = versionType;
	}
	public DenominationType getDenominationType() {
		return denominationType;
	}
	public void setDenominationType(DenominationType denominationType) {
		this.denominationType = denominationType;
	}

}

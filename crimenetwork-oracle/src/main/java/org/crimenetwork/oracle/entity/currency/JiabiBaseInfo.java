package org.crimenetwork.oracle.entity.currency;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.crimenetwork.oracle.entity.cases.CaseBaseInfo;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;


@Entity
@Table(name="jiabi_base_info",schema="system")
//假币信息
public class JiabiBaseInfo{

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
	@SequenceGenerator(name="seq", sequenceName="SEQUENCE_1")
	private Long fmid;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="currency_type_id")
	private CurrencyType currencyType;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="version")
	private VersionType versionType;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="case_currency",
	joinColumns=@JoinColumn(name="fmid"),
	inverseJoinColumns=@JoinColumn(name="case_id"))
	private List<CaseBaseInfo> caseInfos;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="category ")
	private CategoryType categoryType;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="denomination")
	private DenominationType denominationType;//假币面额
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="data_from")
	private DataFrom dataFrom;//假币来源
	@Column
	private String cfmid;
	
	@Column
	private String guanzihao;
	@Column(name="identify_id")
	private String identifyId;
	@Column(name="identify_id1")
	private String identifyId1;
	@ManyToOne(fetch=FetchType.EAGER)
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name="identify_id2")
	private ClassificationNumber identifyId2;
	@Column(name="com_value")
	private String comValue;
	@Column(name="running_number")
	private String runningNumber;
	
	@Column(name="receive_time")
	private Date receiveTime;
	
	@Column(name="create_time")
	private Date createTime;
	@Column
	private Long quantity;
	@Column
	private String caseNo;
	
	@Column
	private Long total;//总额
	
	@Column(name="is_paper_money")
	private Boolean isPaperMoney;
	@Column
	private String state;
	@Column
	private String remark;
	@Column(name="small_pic")
	private Long smallPic;
	@Column(name="piaoyang_number")
	private String piaoyangNumber;
	
	@OneToMany(mappedBy="jiabiBaseInfo",fetch=FetchType.EAGER)
	private Set<JiabiExtendInfo> jiabiExtendInfos = new HashSet<JiabiExtendInfo>(0);
	
	@OneToMany(mappedBy="jiabiBaseInfo",fetch=FetchType.EAGER)
	private Set<JiabiBasePic> jiabiBasePics = new HashSet<JiabiBasePic>(0);
	
	
	@ManyToMany	(fetch=FetchType.EAGER)
	@JoinTable(name="forge_type_list",
	joinColumns=@JoinColumn(name="jiabi_base_info_id"),
	inverseJoinColumns=@JoinColumn(name="forge_type_id"))
	private Set<ForgeType> forgeTypes = new HashSet<ForgeType>(0);
	
	public Long getFmid() {
		return fmid;
	}
	public void setFmid(Long fmid) {
		this.fmid = fmid;
	}
	
	public List<CaseBaseInfo> getCaseInfos() {
		return caseInfos;
	}
	public void setCaseInfos(List<CaseBaseInfo> caseInfos) {
		this.caseInfos = caseInfos;
	}

	public CurrencyType getCurrencyType() {
		return currencyType;
	}
	public void setCurrencyType(CurrencyType currencyType) {
		this.currencyType = currencyType;
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
	
	public Set<ForgeType> getForgeTypes() {
		return forgeTypes;
	}
	public void setForgeTypes(Set<ForgeType> forgeTypes) {
		this.forgeTypes = forgeTypes;
	}
	public DenominationType getDenominationType() {
		return denominationType;
	}
	public void setDenominationType(DenominationType denominationType) {
		this.denominationType = denominationType;
	}
	public DataFrom getDataFrom() {
		return dataFrom;
	}
	public void setDataFrom(DataFrom dataFrom) {
		this.dataFrom = dataFrom;
	}
	public String getCfmid() {
		return cfmid;
	}
	public void setCfmid(String cfmid) {
		this.cfmid = cfmid;
	}
	public String getGuanzihao() {
		return guanzihao;
	}
	public void setGuanzihao(String guanzihao) {
		this.guanzihao = guanzihao;
	}
	public String getIdentifyId() {
		return identifyId;
	}
	public void setIdentifyId(String identifyId) {
		this.identifyId = identifyId;
	}
	public String getIdentifyId1() {
		return identifyId1;
	}
	public void setIdentifyId1(String identifyId1) {
		this.identifyId1 = identifyId1;
	}
	public ClassificationNumber getIdentifyId2() {
		return identifyId2;
	}
	public void setIdentifyId2(ClassificationNumber identifyId2) {
		this.identifyId2 = identifyId2;
	}
	public String getComValue() {
		return comValue;
	}
	public void setComValue(String comValue) {
		this.comValue = comValue;
	}
	public String getRunningNumber() {
		return runningNumber;
	}
	public void setRunningNumber(String runningNumber) {
		this.runningNumber = runningNumber;
	}
	public Date getReceiveTime() {
		return receiveTime;
	}
	public void setReceiveTime(Date receiveTime) {
		this.receiveTime = receiveTime;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Long getQuantity() {
		return quantity;
	}
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public Boolean getIsPaperMoney() {
		return isPaperMoney;
	}
	public void setIsPaperMoney(Boolean isPaperMoney) {
		this.isPaperMoney = isPaperMoney;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	public Set<JiabiExtendInfo> getJiabiExtendInfos() {
		return jiabiExtendInfos;
	}
	public void setJiabiExtendInfos(Set<JiabiExtendInfo> jiabiExtendInfos) {
		this.jiabiExtendInfos = jiabiExtendInfos;
	}
	public Set<JiabiBasePic> getJiabiBasePics() {
		return jiabiBasePics;
	}
	public void setJiabiBasePics(Set<JiabiBasePic> jiabiBasePics) {
		this.jiabiBasePics = jiabiBasePics;
	}
	
	
	
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public Long getSmallPic() {
		return smallPic;
	}
	public void setSmallPic(Long smallPic) {
		this.smallPic = smallPic;
	}
	public String getPiaoyangNumber() {
		return piaoyangNumber;
	}
	public void setPiaoyangNnumber(String piaoyangNumber) {
		this.piaoyangNumber = piaoyangNumber;
	}
	public String getCaseNo() {
		return caseNo;
	}
	public void setCaseNo(String caseNo) {
		this.caseNo = caseNo;
	}
	
	
}
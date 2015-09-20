package org.crimenetwork.oracle.entity.currency;

import java.sql.Timestamp;
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

import org.crimenetwork.oracle.entity.CaseCurrency;
import org.crimenetwork.oracle.entity.cases.CaseInfo;
import org.crimenetwork.oracle.entity.share.LocationCode;
import org.crimenetwork.oracle.entity.share.UserInfo;
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
	@ManyToOne
	@JoinColumn(name="den_location")
	private LocationCode locationCode;
	@ManyToOne
	@JoinColumn(name="currency_type_id")
	private CurrencyType currencyType;
	
	@ManyToOne
	@JoinColumn(name="version")
	private VersionType versionType;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="category ")
	private CategoryType categoryType;
	@ManyToOne
	@JoinColumn(name="denomination")
	private DenominationType denominationType;//假币面额
	@ManyToOne
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
	@ManyToOne(fetch=FetchType.LAZY)
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name="identify_id2")
	private ClassificationNumber identifyId2;
	@Column(name="com_value")
	private String comValue;
	@Column(name="running_number")
	private String runningNumber;
	
	@Column(name="receive_time")
	private Timestamp receiveTime;
	@ManyToOne
	@JoinColumn(name="create_user")
	private UserInfo createUser;
	@Column(name="create_time")
	private Timestamp createTime;
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
	@Column
	private Long small_pic;
	@Column
	private String piaoyang_number;
	
	@OneToMany(mappedBy="jiabiBaseInfo")
	private Set<JiabiExtendInfo> jiabiExtendInfos = new HashSet<JiabiExtendInfo>(0);
	@OneToMany(mappedBy="jiabiBaseInfo")
	private Set<JiabiBasePic> jiabiBasePics = new HashSet<JiabiBasePic>(0);
	
	@OneToMany(mappedBy="jiabiBaseInfo")
	private Set<CaseCurrency> caseCurrencies = new HashSet<CaseCurrency>(0);
	
	@ManyToMany
	@JoinTable(name="case_currency",
	joinColumns=@JoinColumn(name="fmid"),
	inverseJoinColumns=@JoinColumn(name="case_id"))
	private List<CaseInfo> caseInfos;
	@ManyToMany	
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
	public LocationCode getLocationCode() {
		return locationCode;
	}
	public void setLocationCode(LocationCode locationCode) {
		this.locationCode = locationCode;
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
	public Timestamp getReceiveTime() {
		return receiveTime;
	}
	public void setReceiveTime(Timestamp receiveTime) {
		this.receiveTime = receiveTime;
	}
	public UserInfo getCreateUser() {
		return createUser;
	}
	public void setCreateUser(UserInfo createUser) {
		this.createUser = createUser;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
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
	
	public Set<CaseCurrency> getCaseCurrencies() {
		return caseCurrencies;
	}
	public void setCaseCurrencies(Set<CaseCurrency> caseCurrencies) {
		this.caseCurrencies = caseCurrencies;
	}
	
	public List<CaseInfo> getCaseInfos() {
		return caseInfos;
	}
	public void setCaseInfos(List<CaseInfo> caseInfos) {
		this.caseInfos = caseInfos;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getPiaoyang_number() {
		return piaoyang_number;
	}
	public void setPiaoyang_number(String piaoyangNumber) {
		piaoyang_number = piaoyangNumber;
	}
	public Long getSmall_pic() {
		return small_pic;
	}
	public void setSmall_pic(Long smallPic) {
		small_pic = smallPic;
	}
	public String getCaseNo() {
		return caseNo;
	}
	public void setCaseNo(String caseNo) {
		this.caseNo = caseNo;
	}
	
	
}
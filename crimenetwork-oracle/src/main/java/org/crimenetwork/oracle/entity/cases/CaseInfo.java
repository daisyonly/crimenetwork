package org.crimenetwork.oracle.entity.cases;


import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.crimenetwork.oracle.entity.currency.CurrencyType;
import org.crimenetwork.oracle.entity.currency.JiabiBaseInfo;
import org.crimenetwork.oracle.entity.share.CountryCode;
import org.crimenetwork.oracle.entity.share.LocationCode;
import org.crimenetwork.oracle.entity.share.UserInfo;
import org.crimenetwork.oracle.entity.suspect.SuspectInfo;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;


@Entity
@Table(name="case_info", schema="system")
//案件信息
public class CaseInfo{
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
	@SequenceGenerator(name="seq", sequenceName="SEQUENCE_1")
	private Long id;
	
	@Column(name="case_id", length=50)
	private String caseId;
	
	@Column(name="case_name", length=100)
	private String caseName;
	
	@ManyToOne
	@JoinColumn(name="investigation_phase")
	//侦查工作阶段
	private CasePhase casePhase;
	
	
	@ManyToOne(targetEntity=LocationCode.class)
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name="case_happen_location")
	private LocationCode locationCode;//案发地点
	@Column(name="turn_over_unit", length=50)
	private String turnOverUnit;//移送单位
	@Column(name="undertaking_person", length=50)
	private String undertakingPerson;//负责人
	@Column(name="turn_over_unit_phone", length=50)
	private String turnOverUnitPhone;//电话
	@Column(name="case_happen_time")
	private Date caseHappenTime;//案发时间
	@Column(name="case_happen_detail_adress", length=200)
	private String caseHappenDetailAddress;//案发详细地址
	@Column(name="register_unit", length=200)
	private String registerUnit;//立案单位
	
	@Column(name="register_date", length=7)
	private Date registerDate;
	@ManyToOne
	@JoinColumn(name="case_period")
	private CasePeriod casePeriod;//案发时间段
	@ManyToOne
	@JoinColumn(name="crime_subject")
	private CrimeSubject crimeSubject;//犯案主体	
	@Column(name="is_involving_foreign_country", precision=1, scale=0)
	private Boolean isInvolvingForeignCountry;//涉外
	@Column(name="is_home_abroad_collude", precision=1, scale=0)
	private Boolean isHomeAbroadCollude;//内外勾结
	@Column(name="is_entry_crime", precision=1, scale=0)
	private Boolean isEntryCrime;//入境犯罪
	
	@Column(name="solve_time", length=7)
	private Date solveTime;	//破案时间
	@ManyToOne
	@JoinColumn(name="solve_period")
	private CasePeriod solvePeriod;
	@ManyToOne
	@JoinColumn(name="solve_type")
	private SolveType solveType;	
	@Column(name="involved_number",precision=10, scale=0)
	private Long involvedNumber;//涉案人数
	@Column(name="seized_number",precision=10, scale=0)
	private Long seizedNumber;//抓获人数
	@Column(name="at_large_number",precision=10, scale=0)
	private Long atLargeNumber;//未收缴
	@Column(name="transaction_price_ratio", length=50)
	private String transactionPriceRatio;//交易比价
	
	@Column(name="den_location_dettail", length=50)
	private String denLocation;//窝点详细地址
	@Column(name="prosecution_time", length=7)
	private Date prosecutionTime;//起诉时间
	@Column(name="fir_sec_trial_time", length=7)
	private Date firSecTrialTime;//一二审时间
	@Column(name="scene_Seized_Description", length=300)
	private String sceneSeizedDescription;//现场缴获
	@Column(name="seized_cc_description", length=300)
	private String seizedCcDescription;//缴获假币情况
	@Column(name="seized_total_description", length=300)
	private String seizedTotalDescription;//交货金额
	
	@Column(name="brief_info", length=300)
	private String briefInfo;//简要案情
	
	@Column(name="comprehensive_info", length=500)
	private String comprehensiveInfo;//详细案情
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private UserInfo userInfo;
	@Column(name="create_time", length=7)
	private Date createTime;
	
	private String AJZBRY;//主要办案人
	private String AJBARP;//办案人联系电话

	@Column(name="case_state", length=100)
	private String caseState;//办结状态
	
	@ManyToOne
	@JoinColumn(name="currency_type")
	private CurrencyType currencyType;//货币类别
	
	@ManyToOne
	@JoinColumn(name="crime_tool")
	private CommitTool crimeTool;//作案工具
	@Column(name="is_imported", precision=1, scale=0)
	private Boolean isImported;//是否经侦系统导入
	
	@Column(name="involved_amount", precision=10, scale=0)
	private Long involvedAmount;//涉案金额
	
	@ManyToOne
	@JoinColumn(name="involved_crime")
	private CrimeInfo involvedCrime;
	
	@ManyToOne
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name="case_type")
	private CaseType caseType;//案件类型
	@ManyToMany
	@JoinTable(name="commit_type_list",
		joinColumns=@JoinColumn(name="case_id"),
		inverseJoinColumns=@JoinColumn(name="commit_type_id"))//作案手段
	private List<CommitType> commitTypes = new ArrayList<CommitType>();
	
	@ManyToMany
	@JoinTable(name="case_suspect",
		joinColumns=@JoinColumn(name="case_id"),
		inverseJoinColumns=@JoinColumn(name="suspect_id")
	)
	private List<SuspectInfo> suspectInfos = new ArrayList<SuspectInfo>();
	
	@ManyToMany(fetch=FetchType.EAGER)//关闭懒加载
	@JoinTable(name="case_currency",
		joinColumns=@JoinColumn(name="case_id"),
		inverseJoinColumns=@JoinColumn(name="fmid"))
	private List<JiabiBaseInfo> jiabiBaseInfos = new ArrayList<JiabiBaseInfo>();
	@ElementCollection
	@CollectionTable(name="joint_field",joinColumns=@JoinColumn(name="case_id"))
	@Column(name="field_name")
	private List<String> fieldNames;
	@Column
	private String guanzihao;
	@Column(name="remark")
	private String remark;
	@Column(name="attachments")
	private String attachements;
	@Column(name="register_unit_phone")
	private String registerUnitPhone;

	private String sljjdw;//接警单位
	private String sljjry;//接警人员
	private String sljjsj;//接警时间
	private String sljjdd;//接警时间
	private String slslps;//接警批示
	private String  slshyj;//处理意见
	private String slccsh;//初查审核结果
	private String isfabu;//是否补立
	private String ajblyy;//补立原因
	private String ajlapz  ;//立案批准人	
	private String ajbayj;//办案单位意见
	private String ajldyj;//领导意见
	private String	ajsjgc;//案情涉及国家地区(中文)
	private String ajgjdx;//勾结对象	
	private String	ajcxrq;//撤销日期
	private String	ajcxyy ;//撤销原因
	private String ajpafc ;//破案方式(中文）
	private Double ajsazz;//涉案总价值(万元)	
	private Double ajssjz;//损失价值(万元)
	private Double ajwhss;//挽回损失价值(万元)
	private String nuuser;//最新修改人
	private Date intime;//最新修改时间	
	private String ajjsdw;
	private String uuid;
	@Column(name="case_happtime_end")
	private Date casehappTimeEnd;//案发时间的截止时间
	@Column(name="firSecTrialTime_End")
	private Date firSecTrialTimeEnd;//一、二审截止时间
	
	@Column(name="seized_amount")
	private Float seizedAmount;//收缴量
	
	public Float getSeizedAmount() {
		return seizedAmount;
	}
	public void setSeizedAmount(Float seizedAmount) {
		this.seizedAmount = seizedAmount;
	}
	public Date getCasehappTimeEnd() {
		return casehappTimeEnd;
	}
	public void setCasehappTimeEnd(Date casehappTimeEnd) {
		this.casehappTimeEnd = casehappTimeEnd;
	}
	public String getSljjdw() {
		return sljjdw;
	}
	public void setSljjdw(String sljjdw) {
		this.sljjdw = sljjdw;
	}
	public String getSljjry() {
		return sljjry;
	}
	public void setSljjry(String sljjry) {
		this.sljjry = sljjry;
	}
	public String getSljjsj() {
		return sljjsj;
	}
	public void setSljjsj(String sljjsj) {
		this.sljjsj = sljjsj;
	}
	public String getSljjdd() {
		return sljjdd;
	}
	public void setSljjdd(String sljjdd) {
		this.sljjdd = sljjdd;
	}
	public String getSlslps() {
		return slslps;
	}
	public void setSlslps(String slslps) {
		this.slslps = slslps;
	}
	public String getSlshyj() {
		return slshyj;
	}
	public void setSlshyj(String slshyj) {
		this.slshyj = slshyj;
	}
	public String getSlccsh() {
		return slccsh;
	}
	public void setSlccsh(String slccsh) {
		this.slccsh = slccsh;
	}
	public String getIsfabu() {
		return isfabu;
	}
	public void setIsfabu(String isfabu) {
		this.isfabu = isfabu;
	}
	public String getAjblyy() {
		return ajblyy;
	}
	public void setAjblyy(String ajblyy) {
		this.ajblyy = ajblyy;
	}
	public String getAjlapz() {
		return ajlapz;
	}
	public void setAjlapz(String ajlapz) {
		this.ajlapz = ajlapz;
	}
	public String getAjbayj() {
		return ajbayj;
	}
	public void setAjbayj(String ajbayj) {
		this.ajbayj = ajbayj;
	}
	public String getAjldyj() {
		return ajldyj;
	}
	public void setAjldyj(String ajldyj) {
		this.ajldyj = ajldyj;
	}
	public String getAjsjgc() {
		return ajsjgc;
	}
	public void setAjsjgc(String ajsjgc) {
		this.ajsjgc = ajsjgc;
	}
	public String getAjgjdx() {
		return ajgjdx;
	}
	public void setAjgjdx(String ajgjdx) {
		this.ajgjdx = ajgjdx;
	}
	public String getAjcxrq() {
		return ajcxrq;
	}
	public void setAjcxrq(String ajcxrq) {
		this.ajcxrq = ajcxrq;
	}
	public String getAjcxyy() {
		return ajcxyy;
	}
	public void setAjcxyy(String ajcxyy) {
		this.ajcxyy = ajcxyy;
	}
	public String getAjpafc() {
		return ajpafc;
	}
	public void setAjpafc(String ajpafc) {
		this.ajpafc = ajpafc;
	}
	public Double getAjsazz() {
		return ajsazz;
	}
	public void setAjsazz(Double ajsazz) {
		this.ajsazz = ajsazz;
	}
	public Double getAjssjz() {
		return ajssjz;
	}
	public void setAjssjz(Double ajssjz) {
		this.ajssjz = ajssjz;
	}
	public Double getAjwhss() {
		return ajwhss;
	}
	public void setAjwhss(Double ajwhss) {
		this.ajwhss = ajwhss;
	}
	public String getNuuser() {
		return nuuser;
	}
	public void setNuuser(String nuuser) {
		this.nuuser = nuuser;
	}
	public Date getIntime() {
		return intime;
	}
	public void setIntime(Date intime) {
		this.intime = intime;
	}
	public String getAjjsdw() {
		return ajjsdw;
	}
	public void setAjjsdw(String ajjsdw) {
		this.ajjsdw = ajjsdw;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getRegisterUnitPhone() {
		return registerUnitPhone;
	}
	public void setRegisterUnitPhone(String registerUnitPhone) {
		this.registerUnitPhone = registerUnitPhone;
	}
	public String getAttachements() {
		return attachements;
	}
	public void setAttachements(String attachements) {
		this.attachements = attachements;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getGuanzihao() {
		return guanzihao;
	}
	public void setGuanzihao(String guanzihao) {
		this.guanzihao = guanzihao;
	}
	public List<String> getFieldNames() {
		return fieldNames;
	}
	public void setFieldNames(List<String> fieldNames) {
		this.fieldNames = fieldNames;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCaseId() {
		return caseId;
	}
	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}
	public String getCaseName() {
		return caseName;
	}
	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}
	public CasePhase getCasePhase() {
		return casePhase;
	}
	public void setCasePhase(CasePhase casePhase) {
		this.casePhase = casePhase;
	}
	
	public LocationCode getLocationCode() {
		return locationCode;
	}
	public void setLocationCode(LocationCode locationCode) {
		this.locationCode = locationCode;
	}
	public String getTurnOverUnit() {
		return turnOverUnit;
	}
	public void setTurnOverUnit(String turnOverUnit) {
		this.turnOverUnit = turnOverUnit;
	}
	public String getUndertakingPerson() {
		return undertakingPerson;
	}
	public void setUndertakingPerson(String undertakingPerson) {
		this.undertakingPerson = undertakingPerson;
	}
	public String getTurnOverUnitPhone() {
		return turnOverUnitPhone;
	}
	public void setTurnOverUnitPhone(String turnOverUnitPhone) {
		this.turnOverUnitPhone = turnOverUnitPhone;
	}
	public Date getCaseHappenTime() {
		return caseHappenTime;
	}
	public void setCaseHappenTime(Date caseHappenTime) {
		this.caseHappenTime = caseHappenTime;
	}
	public String getCaseHappenDetailAddress() {
		return caseHappenDetailAddress;
	}
	public void setCaseHappenDetailAddress(String caseHappenDetailAddress) {
		this.caseHappenDetailAddress = caseHappenDetailAddress;
	}
	public String getRegisterUnit() {
		return registerUnit;
	}
	public void setRegisterUnit(String registerUnit) {
		this.registerUnit = registerUnit;
	}
	public Date getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}
	public CasePeriod getCasePeriod() {
		return casePeriod;
	}
	public void setCasePeriod(CasePeriod casePeriod) {
		this.casePeriod = casePeriod;
	}
	public CrimeSubject getCrimeSubject() {
		return crimeSubject;
	}
	public void setCrimeSubject(CrimeSubject crimeSubject) {
		this.crimeSubject = crimeSubject;
	}
	public Boolean getIsInvolvingForeignCountry() {
		return isInvolvingForeignCountry;
	}
	public void setIsInvolvingForeignCountry(Boolean isInvolvingForeignCountry) {
		this.isInvolvingForeignCountry = isInvolvingForeignCountry;
	}
	public Boolean getIsHomeAbroadCollude() {
		return isHomeAbroadCollude;
	}
	public void setIsHomeAbroadCollude(Boolean isHomeAbroadCollude) {
		this.isHomeAbroadCollude = isHomeAbroadCollude;
	}
	public Boolean getIsEntryCrime() {
		return isEntryCrime;
	}
	public void setIsEntryCrime(Boolean isEntryCrime) {
		this.isEntryCrime = isEntryCrime;
	}
	
	public Date getSolveTime() {
		return solveTime;
	}
	public void setSolveTime(Date solveTime) {
		this.solveTime = solveTime;
	}
	public CasePeriod getSolvePeriod() {
		return solvePeriod;
	}
	public void setSolvePeriod(CasePeriod solvePeriod) {
		this.solvePeriod = solvePeriod;
	}
	public SolveType getSolveType() {
		return solveType;
	}
	public void setSolveType(SolveType solveType) {
		this.solveType = solveType;
	}
	public Long getInvolvedNumber() {
		return involvedNumber;
	}
	public void setInvolvedNumber(Long involvedNumber) {
		this.involvedNumber = involvedNumber;
	}
	public Long getSeizedNumber() {
		return seizedNumber;
	}
	public void setSeizedNumber(Long seizedNumber) {
		this.seizedNumber = seizedNumber;
	}
	public Long getAtLargeNumber() {
		return atLargeNumber;
	}
	public void setAtLargeNumber(Long atLargeNumber) {
		this.atLargeNumber = atLargeNumber;
	}
	public String getTransactionPriceRatio() {
		return transactionPriceRatio;
	}
	public void setTransactionPriceRatio(String transactionPriceRatio) {
		this.transactionPriceRatio = transactionPriceRatio;
	}

	public String getDenLocation() {
		return denLocation;
	}
	public void setDenLocation(String denLocation) {
		this.denLocation = denLocation;
	}
	public Date getProsecutionTime() {
		return prosecutionTime;
	}
	public void setProsecutionTime(Date prosecutionTime) {
		this.prosecutionTime = prosecutionTime;
	}
	public Date getFirSecTrialTime() {
		return firSecTrialTime;
	}
	public void setFirSecTrialTime(Date firSecTrialTime) {
		this.firSecTrialTime = firSecTrialTime;
	}
	public String getSceneSeizedDescription() {
		return sceneSeizedDescription;
	}
	public void setSceneSeizedDescription(String sceneSeizedDescription) {
		this.sceneSeizedDescription = sceneSeizedDescription;
	}
	public String getSeizedCcDescription() {
		return seizedCcDescription;
	}
	public void setSeizedCcDescription(String seizedCcDescription) {
		this.seizedCcDescription = seizedCcDescription;
	}
	public String getSeizedTotalDescription() {
		return seizedTotalDescription;
	}
	public void setSeizedTotalDescription(String seizedTotalDescription) {
		this.seizedTotalDescription = seizedTotalDescription;
	}
	public String getBriefInfo() {
		return briefInfo;
	}
	public void setBriefInfo(String briefInfo) {
		this.briefInfo = briefInfo;
	}
	public String getComprehensiveInfo() {
		return comprehensiveInfo;
	}
	public void setComprehensiveInfo(String comprehensiveInfo) {
		this.comprehensiveInfo = comprehensiveInfo;
	}
	public UserInfo getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getCaseState() {
		return caseState;
	}
	public void setCaseState(String caseState) {
		this.caseState = caseState;
	}
	public CurrencyType getCurrencyType() {
		return currencyType;
	}
	public void setCurrencyType(CurrencyType currencyType) {
		this.currencyType = currencyType;
	}
	public CommitTool getCrimeTool() {
		return crimeTool;
	}
	public void setCrimeTool(CommitTool crimeTool) {
		this.crimeTool = crimeTool;
	}
	public Boolean getIsImported() {
		return isImported;
	}
	public void setIsImported(Boolean isImported) {
		this.isImported = isImported;
	}
	public Long getInvolvedAmount() {
		return involvedAmount;
	}
	public void setInvolvedAmount(Long involvedAmount) {
		this.involvedAmount = involvedAmount;
	}
	public CrimeInfo getInvolvedCrime() {
		return involvedCrime;
	}
	public void setInvolvedCrime(CrimeInfo involvedCrime) {
		this.involvedCrime = involvedCrime;
	}
	public CaseType getCaseType() {
		return caseType;
	}
	public void setCaseType(CaseType caseType) {
		this.caseType = caseType;
	}
	
	public List<CommitType> getCommitTypes() {
		return commitTypes;
	}
	public void setCommitTypes(List<CommitType> commitTypes) {
		this.commitTypes = commitTypes;
	}
	public List<JiabiBaseInfo> getJiabiBaseInfos() {
		return jiabiBaseInfos;
	}
	public void setJiabiBaseInfos(List<JiabiBaseInfo> jiabiBaseInfos) {
		this.jiabiBaseInfos = jiabiBaseInfos;
	}
	public List<SuspectInfo> getSuspectInfos() {
		return suspectInfos;
	}
	public void setSuspectInfos(List<SuspectInfo> suspectInfos) {
		this.suspectInfos = suspectInfos;
	}
	
	public String getAJZBRY() {
		return AJZBRY;
	}
	public void setAJZBRY(String aJZBRY) {
		AJZBRY = aJZBRY;
	}
	public String getAJBARP() {
		return AJBARP;
	}
	public void setAJBARP(String aJBARP) {
		AJBARP = aJBARP;
	}
	
	public Date getFirSecTrialTimeEnd() {
		return firSecTrialTimeEnd;
	}
	public void setFirSecTrialTimeEnd(Date firSecTrialTimeEnd) {
		this.firSecTrialTimeEnd = firSecTrialTimeEnd;
	}
	
	public List<?> getJointValue(Class<?> jointHelperClass,String fieldName) {
		List<?> group = null;
		if(jointHelperClass.equals(SuspectInfo.class)){
			group = this.getSuspectInfos();
		}
		if(jointHelperClass.equals(JiabiBaseInfo.class)){
			group = this.getJiabiBaseInfos();
		}
		if(group==null) return null;
		List<Object> values = new ArrayList<Object>();
		for(Object instance:group){
			try {
				Field field = instance.getClass().getDeclaredField(fieldName);
				field.setAccessible(true);
				Object value = field.get(instance);
				values.add(value);
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return values;
	}
}
package org.crimenetwork.oracle.entity.suspect;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.crimenetwork.oracle.entity.EduLevel;
import org.crimenetwork.oracle.entity.ProcessMode;
import org.crimenetwork.oracle.entity.Sif;
import org.crimenetwork.oracle.entity.cases.CaseInfo;
import org.crimenetwork.oracle.entity.share.CountryCode;
import org.crimenetwork.oracle.entity.share.LocationCode;
import org.crimenetwork.oracle.entity.share.UserInfo;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;


@Entity
@Table(name="suspect_info",schema="system")
public class SuspectInfo{
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
	@SequenceGenerator(name="seq", sequenceName="SEQUENCE_1")	
	private long id;
	
	@ManyToOne
	@JoinColumn(name="process_mode")
	@NotFound(action=NotFoundAction.IGNORE)
	//处理方式
	private ProcessMode processMode;
	@ManyToOne
	@JoinColumn(name="current_state")
	@NotFound(action=NotFoundAction.IGNORE)
	private CurrentState currentState;
	
	@ManyToOne
	@JoinColumn(name="current_address")
	@NotFound(action=NotFoundAction.IGNORE)//现住地址
	private LocationCode locationCodeByCurrentAddress;
	
	@ManyToOne
	@JoinColumn(name="accent")
	@NotFound(action=NotFoundAction.IGNORE)
	private Accent accent;
	
	@ManyToOne
	@JoinColumn(name="nationality")
	@NotFound(action=NotFoundAction.IGNORE)
	private CountryCode countryCode;
	@ManyToOne
	@JoinColumn(name="marriage_state")
	@NotFound(action=NotFoundAction.IGNORE)
	private MarriageState marriageState;
	
	@ManyToOne
	@JoinColumn(name="native_place")
	@NotFound(action=NotFoundAction.IGNORE)
	private LocationCode locationCodeByNativePlace;
	
	@ManyToOne
	@JoinColumn(name="registered_residence")
	@NotFound(action=NotFoundAction.IGNORE)
	private LocationCode locationCodeByRegisteredResidence;
	@ManyToOne
	@JoinColumn(name="body_type")
	@NotFound(action=NotFoundAction.IGNORE)
	private BodyType bodyType;
	@ManyToOne
	@JoinColumn(name="gender")
	@NotFound(action=NotFoundAction.IGNORE)
	private Gender gender;
	@ManyToOne
	@JoinColumn(name="blood_type")
	@NotFound(action=NotFoundAction.IGNORE)
	private BloodType bloodType;
	
	@ManyToOne
	@JoinColumn(name="face_type")
	@NotFound(action=NotFoundAction.IGNORE)
	private FaceType faceType;
	
	@Column(name="suspect_id")
	private String suspectId;
	
	@Column	
	private String name;
	@Column
	private String alias;
	@Column
	private String agname;
	
	
	@Column
	//private String year;
	//birthday
	private Date year;
	@ManyToOne
	@JoinColumn(name="edu_level")
	@NotFound(action=NotFoundAction.IGNORE)
	private EduLevel eduLevel;
	//@Column
	//private String work;
	@ManyToOne
	@JoinColumn(name="work")
	@NotFound(action=NotFoundAction.IGNORE)
	private Occupation occupation;
	@Column
	private String unit;
	@Column
	private String position;
	@Column(name="registered_residence_detail")
	private String registeredResidenceDetail;
	@Column
	private String adress;
	
	@Column(name="id_card_number")
	private String idCardNumber;//身份证号
	@Column(name="certificate1_number")
	private String certificate1Number;
	@Column(name="certificate2_number")
	private String certificate2Number;
	@ManyToOne
	@JoinColumn(name="nation")
	@NotFound(action=NotFoundAction.IGNORE)
	private Nation nation;
	@Column
	private String telphone;
	@Column
	private String mobile;
	@ManyToOne
	@JoinColumn(name="is_foreigner")//是否是境外人员
	@NotFound(action=NotFoundAction.IGNORE)
	private Sif isForeigner;
	@Column(name="handwriting_num")
	private String handwritingNum;
	@Column(name="fingerprint_num")
	private String fingerprintNum;
	@Column(name="dna_num")
	private String dnaNum;
	
	@ManyToOne
	@JoinColumn(name="is_lc")//是否流窜
	@NotFound(action=NotFoundAction.IGNORE)
	private Sif isLc;
	
	@ManyToOne
	@JoinColumn(name="is_reoffender")//是否累犯
	@NotFound(action=NotFoundAction.IGNORE)
	private Sif  isReoffender;
	
	@ManyToOne
	@JoinColumn(name="is_habitual_offender")//是否惯犯
	@NotFound(action=NotFoundAction.IGNORE)
	private Sif  isHabitualOffender;
	
	@Column(name="capture_time")
	private Date captureTime;
	@Column(name="criminal_group")
	private String criminalGroup;
	
	//涉案情况
	@Column(name="case_info")
	private String caseInfo;
	
	@Column(name="create_time")
	private Date createTime;
	
	@Column(name="phone_number")
	private String phoneNumber;
	
	@Column(name="qq_number")
	private String qqNumber;
	
	@Column(name="bank_account")
	private String bankAccount;
	
	@Column(name="other")
	private String other;
	
	@Column(name="is_imported")
	private Boolean isImported;
	
	@Column(name="phone_book")
	private String phoneBook;
	
	@Column(name="call_records_path")
	private String callRecordsPath;
	
	@Column
	private String status;
	
	@Column
	private String specialstatus;
	
	@Column
	private String height;
	
	@Column(name="death_date")
	private Date deathDate;
	@Column(name="criminal_group_name")
	private String criminalGroupName;
	
	@Column(name="cfid")
    private Long cfid;
	
	@Column(name="remark")
    private String remark;
	
	private String sentenced_time;//服刑时间,判刑情况
	
	private String sentenceInfo;//服刑情况
	@ManyToMany
	@JoinTable(name="case_suspect",
			joinColumns=@JoinColumn(name="suspect_id"),
			inverseJoinColumns=@JoinColumn(name="case_id")
		)
	private List<CaseInfo> caseInfos = new ArrayList<CaseInfo>();
	
	@ManyToOne
	@JoinColumn(name="user_id")
    private UserInfo userInfo;
	
	@Column
	private String specialty;//专长
	@Column
	private String special_features;//特殊特征
	@Column
	private String tbbjxx;//标记中文
	@Column
	private String tszcxx;//专长中文
	@Column
	private String tsclfs;//处理方式中文
	@Column
	private String xrtztz;//体表标记
	@Column
	private Date xrzprq;//照片录入时间
	@Column
	private String nuuser;//最新修改人
	@Column(name="other_contact")
	private String otherContact;//其他联系方式
	@Column(name="other_cfid")
    private Long otherCfid;//其他文件附件
	@Column(name="year_end")//出生时间
	private Date yearEnd;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public ProcessMode getProcessMode() {
		return processMode;
	}
	public void setProcessMode(ProcessMode processMode) {
		this.processMode = processMode;
	}
	public CurrentState getCurrentState() {
		return currentState;
	}
	public void setCurrentState(CurrentState currentState) {
		this.currentState = currentState;
	}
	public LocationCode getLocationCodeByCurrentAddress() {
		return locationCodeByCurrentAddress;
	}
	public void setLocationCodeByCurrentAddress(
			LocationCode locationCodeByCurrentAddress) {
		this.locationCodeByCurrentAddress = locationCodeByCurrentAddress;
	}
	public Accent getAccent() {
		return accent;
	}
	public void setAccent(Accent accent) {
		this.accent = accent;
	}
	public CountryCode getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(CountryCode countryCode) {
		this.countryCode = countryCode;
	}
	public MarriageState getMarriageState() {
		return marriageState;
	}
	public void setMarriageState(MarriageState marriageState) {
		this.marriageState = marriageState;
	}
	public LocationCode getLocationCodeByNativePlace() {
		return locationCodeByNativePlace;
	}
	public void setLocationCodeByNativePlace(LocationCode locationCodeByNativePlace) {
		this.locationCodeByNativePlace = locationCodeByNativePlace;
	}
	public LocationCode getLocationCodeByRegisteredResidence() {
		return locationCodeByRegisteredResidence;
	}
	public void setLocationCodeByRegisteredResidence(
			LocationCode locationCodeByRegisteredResidence) {
		this.locationCodeByRegisteredResidence = locationCodeByRegisteredResidence;
	}
	public BodyType getBodyType() {
		return bodyType;
	}
	public void setBodyType(BodyType bodyType) {
		this.bodyType = bodyType;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public BloodType getBloodType() {
		return bloodType;
	}
	public void setBloodType(BloodType bloodType) {
		this.bloodType = bloodType;
	}
	
	public FaceType getFaceType() {
		return faceType;
	}
	public void setFaceType(FaceType faceType) {
		this.faceType = faceType;
	}
	public String getSuspectId() {
		return suspectId;
	}
	public void setSuspectId(String suspectId) {
		this.suspectId = suspectId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getAgname() {
		return agname;
	}
	public void setAgname(String agname) {
		this.agname = agname;
	}

	public EduLevel getEduLevel() {
		return eduLevel;
	}
	public void setEduLevel(EduLevel eduLevel) {
		this.eduLevel = eduLevel;
	}

	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getRegisteredResidenceDetail() {
		return registeredResidenceDetail;
	}
	public void setRegisteredResidenceDetail(String registeredResidenceDetail) {
		this.registeredResidenceDetail = registeredResidenceDetail;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public String getIdCardNumber() {
		return idCardNumber;
	}
	public void setIdCardNumber(String idCardNumber) {
		this.idCardNumber = idCardNumber;
	}
	public String getCertificate1Number() {
		return certificate1Number;
	}
	public void setCertificate1Number(String certificate1Number) {
		this.certificate1Number = certificate1Number;
	}
	public String getCertificate2Number() {
		return certificate2Number;
	}
	public void setCertificate2Number(String certificate2Number) {
		this.certificate2Number = certificate2Number;
	}
	public Nation getNation() {
		return nation;
	}
	public void setNation(Nation nation) {
		this.nation = nation;
	}
	public String getTelphone() {
		return telphone;
	}
	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getHandwritingNum() {
		return handwritingNum;
	}
	public void setHandwritingNum(String handwritingNum) {
		this.handwritingNum = handwritingNum;
	}
	public String getFingerprintNum() {
		return fingerprintNum;
	}
	public void setFingerprintNum(String fingerprintNum) {
		this.fingerprintNum = fingerprintNum;
	}
	public String getDnaNum() {
		return dnaNum;
	}
	public void setDnaNum(String dnaNum) {
		this.dnaNum = dnaNum;
	}
	public Date getCaptureTime() {
		return captureTime;
	}
	public void setCaptureTime(Date captureTime) {
		this.captureTime = captureTime;
	}
	public String getCriminalGroup() {
		return criminalGroup;
	}
	public void setCriminalGroup(String criminalGroup) {
		this.criminalGroup = criminalGroup;
	}
	public String getCaseInfo() {
		return caseInfo;
	}
	public void setCaseInfo(String caseInfo) {
		this.caseInfo = caseInfo;
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
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getQqNumber() {
		return qqNumber;
	}
	public void setQqNumber(String qqNumber) {
		this.qqNumber = qqNumber;
	}
	public String getBankAccount() {
		return bankAccount;
	}
	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}
	public String getOther() {
		return other;
	}
	public void setOther(String other) {
		this.other = other;
	}
	public Boolean isImported() {
		return isImported;
	}
	public void setImported(Boolean isImported) {
		this.isImported = isImported;
	}
	public String getPhoneBook() {
		return phoneBook;
	}
	public void setPhoneBook(String phoneBook) {
		this.phoneBook = phoneBook;
	}
	public String getCallRecordsPath() {
		return callRecordsPath;
	}
	public void setCallRecordsPath(String callRecordsPath) {
		this.callRecordsPath = callRecordsPath;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSpecialstatus() {
		return specialstatus;
	}
	public void setSpecialstatus(String specialstatus) {
		this.specialstatus = specialstatus;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}

	/*public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}*/
	
	public Date getYear() {
		return year;
	}
	public Date getDeathDate() {
		return deathDate;
	}
	public void setDeathDate(Date deathDate) {
		this.deathDate = deathDate;
	}
	public void setYear(Date year) {
		this.year = year;
	}
	public String getCriminalGroupName() {
		return criminalGroupName;
	}
	public void setCriminalGroupName(String criminalGroupName) {
		this.criminalGroupName = criminalGroupName;
	}
	
	public Long getCfid() {
		return cfid;
	}
	public void setCfid(Long cfid) {
		this.cfid = cfid;
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
	public Boolean getIsImported() {
		return isImported;
	}
	public void setIsImported(Boolean isImported) {
		this.isImported = isImported;
	}

	public String getSentenced_time() {
		return sentenced_time;
	}
	public void setSentenced_time(String sentencedTime) {
		sentenced_time = sentencedTime;
	}
	public String getSentenceInfo() {
		return sentenceInfo;
	}
	public void setSentenceInfo(String sentenceInfo) {
		this.sentenceInfo = sentenceInfo;
	}

	public String getSpecialty() {
		return specialty;
	}
	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}
	public String getSpecial_features() {
		return special_features;
	}
	public void setSpecial_features(String specialFeatures) {
		special_features = specialFeatures;
	}
	public String getTbbjxx() {
		return tbbjxx;
	}
	public void setTbbjxx(String tbbjxx) {
		this.tbbjxx = tbbjxx;
	}
	public String getTszcxx() {
		return tszcxx;
	}
	public void setTszcxx(String tszcxx) {
		this.tszcxx = tszcxx;
	}
	public String getTsclfs() {
		return tsclfs;
	}
	public void setTsclfs(String tsclfs) {
		this.tsclfs = tsclfs;
	}
	public String getXrtztz() {
		return xrtztz;
	}
	public void setXrtztz(String xrtztz) {
		this.xrtztz = xrtztz;
	}
	public Date getXrzprq() {
		return xrzprq;
	}
	public void setXrzprq(Date xrzprq) {
		this.xrzprq = xrzprq;
	}
	public String getNuuser() {
		return nuuser;
	}
	public void setNuuser(String nuuser) {
		this.nuuser = nuuser;
	}
	public String getOtherContact() {
		return otherContact;
	}
	public void setOtherContact(String otherContact) {
		this.otherContact = otherContact;
	}
	public Long getOtherCfid() {
		return otherCfid;
	}
	public void setOtherCfid(Long otherCfid) {
		this.otherCfid = otherCfid;
	}
	public Occupation getOccupation() {
		return occupation;
	}
	public void setOccupation(Occupation occupation) {
		this.occupation = occupation;
	}
	public Date getYearEnd() {
		return yearEnd;
	}
	public void setYearEnd(Date yearEnd) {
		this.yearEnd = yearEnd;
	}
	public Sif getIsForeigner() {
		return isForeigner;
	}
	public void setIsForeigner(Sif isForeigner) {
		this.isForeigner = isForeigner;
	}
	public Sif getIsLc() {
		return isLc;
	}
	public void setIsLc(Sif isLc) {
		this.isLc = isLc;
	}
	public Sif getIsReoffender() {
		return isReoffender;
	}
	public void setIsReoffender(Sif isReoffender) {
		this.isReoffender = isReoffender;
	}
	public Sif getIsHabitualOffender() {
		return isHabitualOffender;
	}
	public void setIsHabitualOffender(Sif isHabitualOffender) {
		this.isHabitualOffender = isHabitualOffender;
	}
	
}
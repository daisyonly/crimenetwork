package org.crimenetwork.oracle.entity.suspect;

import java.util.Date;
import java.util.HashSet;
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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.crimenetwork.oracle.entity.cases.CaseBaseInfo;
import org.crimenetwork.oracle.entity.share.CountryCode;
import org.crimenetwork.oracle.entity.share.Location;
import org.crimenetwork.oracle.entity.share.Sif;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name="suspect_info",schema="SYSTEM")
public class SuspectBaseInfo {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
	@SequenceGenerator(name="seq", sequenceName="SEQUENCE_1")
	private Long id;
	@Column(name="suspect_id")
	private String suspectId;
	@Column
	private String name;//姓名
	
	@Column
	private String alias;//曾用名
	
	@Column
	private String agname;//绰号
	@Column(name="id_card_number")
	private String idCardNumber;//身份证号码
	@ManyToOne
	@JoinColumn(name="native_place")
	@NotFound(action=NotFoundAction.IGNORE)
	private Location nativeLocation;//籍贯
	@ManyToOne
	@JoinColumn(name="gender")
	@NotFound(action=NotFoundAction.IGNORE)
	private Gender gender;//性别
	@Column(name="phone_number")
	private String phoneNumber;//电话号码
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name="case_suspect",
		joinColumns=@JoinColumn(name="suspect_id"),
		inverseJoinColumns=@JoinColumn(name="case_id")
	)	
	private Set<CaseBaseInfo> cases = new HashSet<CaseBaseInfo>();
	
	@ManyToOne
	@JoinColumn(name="nation")
	@NotFound(action=NotFoundAction.IGNORE)
	private Nation nation;//民族
	
	@Column(name="case_info")
	private String caseInfo;//涉案信息
	
	@ManyToOne
	@JoinColumn(name="accent")
	@NotFound(action=NotFoundAction.IGNORE)
	private Accent accent;//口音
	
	
	@Column(name="current_address")
	@NotFound(action=NotFoundAction.IGNORE)
	private String locationCodeByCurrentAddress;//现住地址编码
	
	@Column
	private String adress;//现住详细地址
	
	@Column(name="registered_residence")
	@NotFound(action=NotFoundAction.IGNORE)
	private String locationCodeByRegisteredResidence;//户籍编码
	
	@Column(name="registered_residence_detail")
	private String registeredResidenceDetail;//户籍详细地址
	
	
	@ManyToOne
	@JoinColumn(name="work")
	@NotFound(action=NotFoundAction.IGNORE)
	private Occupation occupation;//职业
	
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
	
	@Column(name="qq_number")
	private String qqNumber;//qq号码
	@Column(name="bank_account")
	private String bankAccount;//银行账号
	
	
	@Column(name="handwriting_num")
	private String handwritingNum;//笔记采集编号
	@Column(name="fingerprint_num")
	private String fingerprintNum;//指纹采集编号
	@Column(name="dna_num")
	private String dnaNum;//DNA采集编号
	
	
	@ManyToOne
	@JoinColumn(name="body_type")
	@NotFound(action=NotFoundAction.IGNORE)
	private BodyType bodyType;//体型
	
	@ManyToOne
	@JoinColumn(name="marriage_state")
	@NotFound(action=NotFoundAction.IGNORE)
	private MarriageState marriageState;
	
	@ManyToOne
	@JoinColumn(name="nationality")
	@NotFound(action=NotFoundAction.IGNORE)
	private CountryCode countryCode;//国籍
	
	@ManyToOne
	@JoinColumn(name="blood_type")
	@NotFound(action=NotFoundAction.IGNORE)
	private BloodType bloodType;
	
	@ManyToOne
	@JoinColumn(name="face_type")
	@NotFound(action=NotFoundAction.IGNORE)
	private FaceType faceType;
	
	@ManyToOne
	@JoinColumn(name="certificate1_type")
	@NotFound(action=NotFoundAction.IGNORE)
	private CertificateType certificateTypeByCertificate1Type;
	
	@Column(name="certificate1_number")
	private String certificate1Number;
	@Column(name="certificate2_number")
	private String certificate2Number;
	
	@ManyToOne
	@JoinColumn(name="is_foreigner")//是否是境外人员
	@NotFound(action=NotFoundAction.IGNORE)
	private Sif isForeigner;//是否是境外人员
	
	
	@Column(name="capture_time")
	private Date captureTime;
	@Column(name="criminal_group")
	private String criminalGroup;
	
	@Column
	private String status;//身份
	@Column
	private String specialstatus;//特殊身份
	@Column
	private String height;//身高
	
	public Nation getNation() {
		return nation;
	}
	public void setNation(Nation nation) {
		this.nation = nation;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getIdCardNumber() {
		return idCardNumber;
	}
	public void setIdCardNumber(String idCardNumber) {
		this.idCardNumber = idCardNumber;
	}
	public Location getNativeLocation() {
		return nativeLocation;
	}
	public void setNativeLocation(Location nativeLocation) {
		this.nativeLocation = nativeLocation;
	}
	public Set<CaseBaseInfo> getCases() {
		return cases;
	}
	public void setCases(Set<CaseBaseInfo> cases) {
		this.cases = cases;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
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
	public String getCaseInfo() {
		return caseInfo;
	}
	public void setCaseInfo(String caseInfo) {
		this.caseInfo = caseInfo;
	}
	public Accent getAccent() {
		return accent;
	}
	public void setAccent(Accent accent) {
		this.accent = accent;
	}
	public String getLocationCodeByCurrentAddress() {
		return locationCodeByCurrentAddress;
	}
	public void setLocationCodeByCurrentAddress(String locationCodeByCurrentAddress) {
		this.locationCodeByCurrentAddress = locationCodeByCurrentAddress;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public String getLocationCodeByRegisteredResidence() {
		return locationCodeByRegisteredResidence;
	}
	public void setLocationCodeByRegisteredResidence(
			String locationCodeByRegisteredResidence) {
		this.locationCodeByRegisteredResidence = locationCodeByRegisteredResidence;
	}
	public String getRegisteredResidenceDetail() {
		return registeredResidenceDetail;
	}
	public void setRegisteredResidenceDetail(String registeredResidenceDetail) {
		this.registeredResidenceDetail = registeredResidenceDetail;
	}
	public Occupation getOccupation() {
		return occupation;
	}
	public void setOccupation(Occupation occupation) {
		this.occupation = occupation;
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
	public BodyType getBodyType() {
		return bodyType;
	}
	public void setBodyType(BodyType bodyType) {
		this.bodyType = bodyType;
	}
	public MarriageState getMarriageState() {
		return marriageState;
	}
	public void setMarriageState(MarriageState marriageState) {
		this.marriageState = marriageState;
	}
	public CountryCode getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(CountryCode countryCode) {
		this.countryCode = countryCode;
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
	public CertificateType getCertificateTypeByCertificate1Type() {
		return certificateTypeByCertificate1Type;
	}
	public void setCertificateTypeByCertificate1Type(
			CertificateType certificateTypeByCertificate1Type) {
		this.certificateTypeByCertificate1Type = certificateTypeByCertificate1Type;
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
	public Sif getIsForeigner() {
		return isForeigner;
	}
	public void setIsForeigner(Sif isForeigner) {
		this.isForeigner = isForeigner;
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
	
	@Override
	public String toString(){
		SuspectStatueCode codeMap=SuspectStatueCode.getAnInstance();
        if(this.getCaseInfo()!=null){
        	this.setCaseInfo(this.getCaseInfo().replaceAll("\r\n", " "));
        }
    	StringBuilder sb=new StringBuilder();
    	sb.append(this.getId()).append(",")
    	  .append(this.getSuspectId()).append(",")
    	  .append(this.getName()).append(",")
    	  .append(this.getAlias()).append(",")
    	  .append(this.getAgname()).append(",")
    	  .append(this.getIdCardNumber()).append(",")
    	  .append(this.getNativeLocation()).append(",")
    	  .append(this.getGender()).append(",")
    	  .append(this.getPhoneNumber()).append(",")
    	  .append(this.getNation()).append(",")
    	  .append("\"").append(this.getCaseInfo()).append("\"").append(",")
    	  .append(this.getAccent()).append(",")
    	  .append(this.getLocationCodeByCurrentAddress()).append(",")
    	  .append(this.getAdress()).append(",")
    	  .append(this.getLocationCodeByRegisteredResidence()).append(",")
    	  .append(this.getRegisteredResidenceDetail()).append(",")
    	  .append(this.getOccupation()).append(",")
    	  .append(this.getIsLc()).append(",")
    	  .append(this.getIsReoffender()).append(",")
    	  .append(this.getIsHabitualOffender()).append(",")
    	  .append(this.getQqNumber()).append(",")
    	  .append(this.getBankAccount()).append(",")
    	  .append(this.getHandwritingNum()).append(",")
    	  .append(this.getFingerprintNum()).append(",")
    	  .append(this.getDnaNum()).append(",")
    	  .append(this.getBodyType()).append(",")
    	  .append(this.getMarriageState()).append(",")
    	  .append(this.getCountryCode()).append(",")
    	  .append(this.getBloodType()).append(",")
    	  .append(this.getFaceType()).append(",")
    	  .append(this.getCertificateTypeByCertificate1Type()).append(",")
    	  .append(this.getCertificate1Number()).append(",")
    	  .append(this.getCertificate2Number()).append(",")
    	  .append(this.getIsForeigner()).append(",")
    	  .append(this.getCaptureTime()).append(",")
    	  .append(this.getCriminalGroup()).append(",")
    	  .append(codeMap.getStatusByCode(this.getStatus())).append(",")
    	  .append(codeMap.getSpecialstatusByCode(this.getSpecialstatus())).append(",")
    	  .append(this.getHeight());
    	  
    	return sb.toString();
	}
	
	
	
	
}

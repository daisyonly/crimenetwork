package org.crimenetwork.oracle.entity.jiabisim;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.crimenetwork.oracle.entity.currency.DenominationType;
import org.crimenetwork.oracle.entity.currency.JiabiBasePic;


@Entity
@Table(name="jiabi_base_info",schema="system")
//假币信息
public class SimJiabiBaseInfo{

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
	@SequenceGenerator(name="seq", sequenceName="SEQUENCE_1")
	private Long fmid;

	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="denomination")
	private DenominationType denominationType;//假币面额
	
	@Column
	private String cfmid;
	
	@Column
	private String guanzihao;
	@Column(name="identify_id")
	private String identifyId;
	@Column(name="identify_id1")
	private String identifyId1;

	@Column(name="com_value")
	private String comValue;
	@Column(name="running_number")
	private String runningNumber;
	
	@Column(name="receive_time")
	private Timestamp receiveTime;
	
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
	private Set<JiabiBasePic> jiabiBasePics = new HashSet<JiabiBasePic>(0);
	
	
	public Long getFmid() {
		return fmid;
	}
	public void setFmid(Long fmid) {
		this.fmid = fmid;
	}
	
	public DenominationType getDenominationType() {
		return denominationType;
	}
	public void setDenominationType(DenominationType denominationType) {
		this.denominationType = denominationType;
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
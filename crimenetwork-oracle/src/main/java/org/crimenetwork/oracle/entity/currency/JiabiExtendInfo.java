package org.crimenetwork.oracle.entity.currency;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@SuppressWarnings("serial")
@Entity
@Table(name="jiabi_extend_info",schema="system")
public class JiabiExtendInfo implements java.io.Serializable {

	// Fields
	@Id
	private Long jeiid;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="fmid")
	private JiabiBaseInfo jiabiBaseInfo;
	
	private String thick;
	private Double white;
	private Double quantity;
	private Double angle;
	private String fibre;//纤维种类
	
	@Column(name="fluore_color")
	private String fluoreColor;//光波下颜色
	
	@Column(name="back_fluore_Color")
	private String backFluoreColor;//背面团花荧光颜色
	
	@Column(name="is_netline")
	private Boolean isNetline;//网纹有无
	
	@Column(name="is_safeline")
	private Boolean isSafeline;//安全线有无
	
	@Column(name="is_color_fibreline")
	private Boolean isColorFibreline;//彩色纤维丝有无
	
	@Column(name="is_fluore_fibreline")
	private Boolean isFluoreFibreline;//荧光纤维丝有无
	
	@Column(name="is_printink")
	private Boolean isPrintink;//反光油墨有无
	
	@Column(name="is_waterprint")
	private Boolean isWaterprint;//水印有无
	
	@Column(name="is_guanzihao_inteval")
	private Boolean isGuanzihaoInteval;//冠字号间距相等否
	
	@Column(name="is_aoban_tezheng")
	private Boolean isAobanTezheng;//凹版特征有无
	
	@Column(name="is_fluore")
	private Boolean isFluore;//荧光100有无
	
	@Column(name="is_light_ink")
	private Boolean isLightInk;//光变油墨有无
	
	@Column(name="safeline_word")
	private String safelineWord;//安全线烫印文字
	
	@Column(name="safeline_length")
	private String safelineLength;
	
	@Column(name="safeline_width")
	private String safelineWidth;

	/** default constructor */
	public JiabiExtendInfo() {
	}
	
	public Long getJeiid() {
		return this.jeiid;
	}

	public void setJeiid(Long jeiid) {
		this.jeiid = jeiid;
	}

	public JiabiBaseInfo getJiabiBaseInfo() {
		return this.jiabiBaseInfo;
	}

	public void setJiabiBaseInfo(JiabiBaseInfo jiabiBaseInfo) {
		this.jiabiBaseInfo = jiabiBaseInfo;
	}

	public String getThick() {
		return this.thick;
	}

	public void setThick(String thick) {
		this.thick = thick;
	}

	public Double getWhite() {
		return this.white;
	}

	public void setWhite(Double white) {
		this.white = white;
	}

	public Double getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public Double getAngle() {
		return this.angle;
	}

	public void setAngle(Double angle) {
		this.angle = angle;
	}

	public String getFibre() {
		return this.fibre;
	}

	public void setFibre(String fibre) {
		this.fibre = fibre;
	}

	public String getFluoreColor() {
		return this.fluoreColor;
	}

	public void setFluoreColor(String fluoreColor) {
		this.fluoreColor = fluoreColor;
	}

	public String getBackFluoreColor() {
		return this.backFluoreColor;
	}

	public void setBackFluoreColor(String backFluoreColor) {
		this.backFluoreColor = backFluoreColor;
	}

	public Boolean getIsNetline() {
		return this.isNetline;
	}

	public void setIsNetline(Boolean isNetline) {
		this.isNetline = isNetline;
	}

	public Boolean getIsSafeline() {
		return this.isSafeline;
	}

	public void setIsSafeline(Boolean isSafeline) {
		this.isSafeline = isSafeline;
	}

	public Boolean getIsColorFibreline() {
		return this.isColorFibreline;
	}

	public void setIsColorFibreline(Boolean isColorFibreline) {
		this.isColorFibreline = isColorFibreline;
	}

	public Boolean getIsFluoreFibreline() {
		return this.isFluoreFibreline;
	}

	public void setIsFluoreFibreline(Boolean isFluoreFibreline) {
		this.isFluoreFibreline = isFluoreFibreline;
	}

	public Boolean getIsPrintink() {
		return this.isPrintink;
	}

	public void setIsPrintink(Boolean isPrintink) {
		this.isPrintink = isPrintink;
	}

	public Boolean getIsWaterprint() {
		return this.isWaterprint;
	}

	public void setIsWaterprint(Boolean isWaterprint) {
		this.isWaterprint = isWaterprint;
	}

	public Boolean getIsGuanzihaoInteval() {
		return this.isGuanzihaoInteval;
	}

	public void setIsGuanzihaoInteval(Boolean isGuanzihaoInteval) {
		this.isGuanzihaoInteval = isGuanzihaoInteval;
	}

	public Boolean getIsAobanTezheng() {
		return this.isAobanTezheng;
	}

	public void setIsAobanTezheng(Boolean isAobanTezheng) {
		this.isAobanTezheng = isAobanTezheng;
	}

	public Boolean getIsFluore() {
		return this.isFluore;
	}

	public void setIsFluore(Boolean isFluore) {
		this.isFluore = isFluore;
	}

	public Boolean getIsLightInk() {
		return this.isLightInk;
	}

	public void setIsLightInk(Boolean isLightInk) {
		this.isLightInk = isLightInk;
	}

	public String getSafelineWord() {
		return this.safelineWord;
	}

	public void setSafelineWord(String safelineWord) {
		this.safelineWord = safelineWord;
	}

	public String getSafelineLength() {
		return this.safelineLength;
	}

	public void setSafelineLength(String safelineLength) {
		this.safelineLength = safelineLength;
	}

	public String getSafelineWidth() {
		return this.safelineWidth;
	}

	public void setSafelineWidth(String safelineWidth) {
		this.safelineWidth = safelineWidth;
	}

}
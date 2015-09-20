package org.crimenetwork.oracle.entity;

import org.crimenetwork.oracle.entity.cases.CaseInfo;
import org.crimenetwork.oracle.entity.currency.JiabiBaseInfo;

/**
 * CaseCurrency entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class CaseCurrency implements java.io.Serializable {

	// Fields

	private CaseCurrencyId id;
	private JiabiBaseInfo jiabiBaseInfo;
	private CaseInfo caseInfo;

	// Constructors

	/** default constructor */
	public CaseCurrency() {
	}

	/** minimal constructor */
	public CaseCurrency(CaseCurrencyId id) {
		this.id = id;
	}

	/** full constructor */
	public CaseCurrency(CaseCurrencyId id, JiabiBaseInfo jiabiBaseInfo,
			CaseInfo caseInfo) {
		this.id = id;
		this.jiabiBaseInfo = jiabiBaseInfo;
		this.caseInfo = caseInfo;
	}

	// Property accessors

	public CaseCurrencyId getId() {
		return this.id;
	}

	public void setId(CaseCurrencyId id) {
		this.id = id;
	}

	public JiabiBaseInfo getJiabiBaseInfo() {
		return this.jiabiBaseInfo;
	}

	public void setJiabiBaseInfo(JiabiBaseInfo jiabiBaseInfo) {
		this.jiabiBaseInfo = jiabiBaseInfo;
	}

	public CaseInfo getCaseInfo() {
		return this.caseInfo;
	}

	public void setCaseInfo(CaseInfo caseInfo) {
		this.caseInfo = caseInfo;
	}

}
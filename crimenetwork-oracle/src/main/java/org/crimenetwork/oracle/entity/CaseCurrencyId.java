package org.crimenetwork.oracle.entity;

import org.crimenetwork.oracle.entity.cases.CaseInfo;
import org.crimenetwork.oracle.entity.currency.JiabiBaseInfo;

/**
 * CaseCurrencyId entity. @author MyEclipse Persistence Tools
 */

public class CaseCurrencyId implements java.io.Serializable {

	// Fields

	private JiabiBaseInfo jiabiBaseInfo;
	private CaseInfo caseInfo;

	// Constructors

	/** default constructor */
	public CaseCurrencyId() {
	}

	/** full constructor */
	public CaseCurrencyId(JiabiBaseInfo jiabiBaseInfo, CaseInfo caseInfo) {
		this.jiabiBaseInfo = jiabiBaseInfo;
		this.caseInfo = caseInfo;
	}

	// Property accessors

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

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof CaseCurrencyId))
			return false;
		CaseCurrencyId castOther = (CaseCurrencyId) other;

		return ((this.getJiabiBaseInfo() == castOther.getJiabiBaseInfo()) || (this
				.getJiabiBaseInfo() != null
				&& castOther.getJiabiBaseInfo() != null && this
				.getJiabiBaseInfo().equals(castOther.getJiabiBaseInfo())))
				&& ((this.getCaseInfo() == castOther.getCaseInfo()) || (this
						.getCaseInfo() != null
						&& castOther.getCaseInfo() != null && this
						.getCaseInfo().equals(castOther.getCaseInfo())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getJiabiBaseInfo() == null ? 0 : this.getJiabiBaseInfo()
						.hashCode());
		result = 37 * result
				+ (getCaseInfo() == null ? 0 : this.getCaseInfo().hashCode());
		return result;
	}

}
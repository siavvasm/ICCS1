/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author George Digkas <digasgeo@gmail.com>
 */
@Embeddable
public class SDK4EDQualityGateMethodIssuesPK implements Serializable {

	private static final long serialVersionUID = 1L;
	@Basic(optional = false)
	@Column(name = "sdk4ed_quality_gate_method_id")
	private int sdk4edQualityGateMethodId;
	@Basic(optional = false)
	@Column(name = "rule")
	private String rule;
	@Basic(optional = false)
	@Column(name = "line")
	private int line;

	public SDK4EDQualityGateMethodIssuesPK() {
	}

	public SDK4EDQualityGateMethodIssuesPK(int sdk4edQualityGateMethodId, String rule, int line) {
		this.sdk4edQualityGateMethodId = sdk4edQualityGateMethodId;
		this.rule = rule;
		this.line = line;
	}

	public int getSdk4edQualityGateMethodId() {
		return sdk4edQualityGateMethodId;
	}

	public void setSdk4edQualityGateMethodId(int sdk4edQualityGateMethodId) {
		this.sdk4edQualityGateMethodId = sdk4edQualityGateMethodId;
	}

	public String getRule() {
		return rule;
	}

	public void setRule(String rule) {
		this.rule = rule;
	}

	public int getLine() {
		return line;
	}

	public void setLine(int line) {
		this.line = line;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (int) sdk4edQualityGateMethodId;
		hash += (rule != null ? rule.hashCode() : 0);
		hash += (int) line;
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof SDK4EDQualityGateMethodIssuesPK)) {
			return false;
		}
		SDK4EDQualityGateMethodIssuesPK other = (SDK4EDQualityGateMethodIssuesPK) object;
		if (this.sdk4edQualityGateMethodId != other.sdk4edQualityGateMethodId) {
			return false;
		}
		if ((this.rule == null && other.rule != null) || (this.rule != null && !this.rule.equals(other.rule))) {
			return false;
		}
		if (this.line != other.line) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.digkas.git.domain.Sdk4edQualityGateMethodIssuesPK[ sdk4edQualityGateMethodId=" + sdk4edQualityGateMethodId + ", rule=" + rule + ", line=" + line + " ]";
	}

}

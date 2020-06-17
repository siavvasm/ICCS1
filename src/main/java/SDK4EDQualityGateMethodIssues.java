/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author George Digkas <digasgeo@gmail.com>
 */
@Entity
@Table(name = "sdk4ed_quality_gate_method_issues")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "SDK4EDQualityGateMethodIssues.findAll", query = "SELECT s FROM SDK4EDQualityGateMethodIssues s"),
	@NamedQuery(name = "SDK4EDQualityGateMethodIssues.findBySdk4edQualityGateMethodId", query = "SELECT s FROM SDK4EDQualityGateMethodIssues s WHERE s.sdk4edQualityGateMethodIssuesPK.sdk4edQualityGateMethodId = :sdk4edQualityGateMethodId"),
	@NamedQuery(name = "SDK4EDQualityGateMethodIssues.findByRule", query = "SELECT s FROM SDK4EDQualityGateMethodIssues s WHERE s.sdk4edQualityGateMethodIssuesPK.rule = :rule"),
	@NamedQuery(name = "SDK4EDQualityGateMethodIssues.findByLine", query = "SELECT s FROM SDK4EDQualityGateMethodIssues s WHERE s.sdk4edQualityGateMethodIssuesPK.line = :line"),
	@NamedQuery(name = "SDK4EDQualityGateMethodIssues.findByEffort", query = "SELECT s FROM SDK4EDQualityGateMethodIssues s WHERE s.effort = :effort")})
public class SDK4EDQualityGateMethodIssues implements Serializable {

	private static final long serialVersionUID = 1L;
	@EmbeddedId
	protected SDK4EDQualityGateMethodIssuesPK sdk4edQualityGateMethodIssuesPK;
	@Basic(optional = false)
	@Column(name = "effort")
	private int effort;
	@JoinColumn(name = "sdk4ed_quality_gate_method_id", referencedColumnName = "id", insertable = false, updatable = false)
	@ManyToOne(optional = false)
	private SDK4EDQualityGateMethodContribution sdk4edQualityGateMethodContribution;

	public SDK4EDQualityGateMethodIssues() {
	}

	public SDK4EDQualityGateMethodIssues(SDK4EDQualityGateMethodIssuesPK sdk4edQualityGateMethodIssuesPK) {
		this.sdk4edQualityGateMethodIssuesPK = sdk4edQualityGateMethodIssuesPK;
	}

	public SDK4EDQualityGateMethodIssues(SDK4EDQualityGateMethodIssuesPK sdk4edQualityGateMethodIssuesPK, int effort) {
		this.sdk4edQualityGateMethodIssuesPK = sdk4edQualityGateMethodIssuesPK;
		this.effort = effort;
	}

	public SDK4EDQualityGateMethodIssues(int sdk4edQualityGateMethodId, String rule, int line) {
		this.sdk4edQualityGateMethodIssuesPK = new SDK4EDQualityGateMethodIssuesPK(sdk4edQualityGateMethodId, rule, line);
	}

	public SDK4EDQualityGateMethodIssues(int sdk4edQualityGateMethodId, String rule, int line, int effort) {
		this.sdk4edQualityGateMethodIssuesPK = new SDK4EDQualityGateMethodIssuesPK(sdk4edQualityGateMethodId, rule, line);
		this.effort = effort;
	}

	public SDK4EDQualityGateMethodIssuesPK getSdk4edQualityGateMethodIssuesPK() {
		return sdk4edQualityGateMethodIssuesPK;
	}

	public void setSdk4edQualityGateMethodIssuesPK(SDK4EDQualityGateMethodIssuesPK sdk4edQualityGateMethodIssuesPK) {
		this.sdk4edQualityGateMethodIssuesPK = sdk4edQualityGateMethodIssuesPK;
	}

	public int getEffort() {
		return effort;
	}

	public void setEffort(int effort) {
		this.effort = effort;
	}

	public SDK4EDQualityGateMethodContribution getSDK4EDQualityGateMethodContribution() {
		return sdk4edQualityGateMethodContribution;
	}

	public void setSdk4edQualityGateMethodContribution(SDK4EDQualityGateMethodContribution sdk4edQualityGateMethodContribution) {
		this.sdk4edQualityGateMethodContribution = sdk4edQualityGateMethodContribution;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (sdk4edQualityGateMethodIssuesPK != null ? sdk4edQualityGateMethodIssuesPK.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof SDK4EDQualityGateMethodIssues)) {
			return false;
		}
		SDK4EDQualityGateMethodIssues other = (SDK4EDQualityGateMethodIssues) object;
		if ((this.sdk4edQualityGateMethodIssuesPK == null && other.sdk4edQualityGateMethodIssuesPK != null) || (this.sdk4edQualityGateMethodIssuesPK != null && !this.sdk4edQualityGateMethodIssuesPK.equals(other.sdk4edQualityGateMethodIssuesPK))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.digkas.git.domain.Sdk4edQualityGateMethodIssues[ sdk4edQualityGateMethodIssuesPK=" + sdk4edQualityGateMethodIssuesPK + " ]";
	}

}

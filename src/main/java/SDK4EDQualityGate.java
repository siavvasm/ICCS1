

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author George Digkas <digasgeo@gmail.com>
 */
@Entity
@Table(name = "sdk4ed_quality_gate")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "SDK4EDQualityGate.findAll", query = "SELECT s FROM SDK4EDQualityGate s"),
	@NamedQuery(name = "SDK4EDQualityGate.findById", query = "SELECT s FROM SDK4EDQualityGate s WHERE s.id = :id"),
	@NamedQuery(name = "SDK4EDQualityGate.findByBaseSha", query = "SELECT s FROM SDK4EDQualityGate s WHERE s.baseSha = :baseSha"),
	@NamedQuery(name = "SDK4EDQualityGate.findByTargetSha", query = "SELECT s FROM SDK4EDQualityGate s WHERE s.targetSha = :targetSha"),
	@NamedQuery(name = "SDK4EDQualityGate.findByTargetCommitTime", query = "SELECT s FROM SDK4EDQualityGate s WHERE s.targetCommitTime = :targetCommitTime"), })
public class SDK4EDQualityGate implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;
	@Basic(optional = false)
	@Column(name = "base_sha")
	private String baseSha;
	@Basic(optional = false)
	@Column(name = "target_sha")
	private String targetSha;
	@Basic(optional = false)
	@Column(name = "target_commit_time")
	private int targetCommitTime;
	// @Max(value=?) @Min(value=?)//if you know range of your decimal fields
	// consider using these annotations to enforce field validation
	// @Column(name = "sqale_index")
	// private BigDecimal sqaleIndex;
	// @Column(name = "ncloc")
	// private BigDecimal ncloc;
	@JoinColumn(name = "project_id", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Project projectId;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "sdk4edQualityGateId")
	private Collection<SDK4EDQualityGateMethodContribution> sdk4edQualityGateMethodContributionCollection;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "sdk4edQualityGate")
	private Collection<SDK4EDQualityGateMetrics> sdk4edQualityGateMetricsCollection;

	public SDK4EDQualityGate() {
	}

	public SDK4EDQualityGate(Integer id) {
		this.id = id;
	}

	public SDK4EDQualityGate(Integer id, String baseSha, String targetSha, int targetCommitTime) {
		this.id = id;
		this.baseSha = baseSha;
		this.targetSha = targetSha;
		this.targetCommitTime = targetCommitTime;
	}

	public SDK4EDQualityGate(String baseSha, String targetSha, int targetCommitTime, Project projectId) {
		this.baseSha = baseSha;
		this.targetSha = targetSha;
		this.targetCommitTime = targetCommitTime;
		this.projectId = projectId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBaseSha() {
		return baseSha;
	}

	public void setBaseSha(String baseSha) {
		this.baseSha = baseSha;
	}

	public String getTargetSha() {
		return targetSha;
	}

	public void setTargetSha(String targetSha) {
		this.targetSha = targetSha;
	}

	public int getTargetCommitTime() {
		return targetCommitTime;
	}

	public void setTargetCommitTime(int targetCommitTime) {
		this.targetCommitTime = targetCommitTime;
	}

	// public BigDecimal getSqaleIndex() {
	// return sqaleIndex;
	// }
	//
	// public void setSqaleIndex(BigDecimal sqaleIndex) {
	// this.sqaleIndex = sqaleIndex;
	// }
	//
	// public BigDecimal getNcloc() {
	// return ncloc;
	// }
	//
	// public void setNcloc(BigDecimal ncloc) {
	// this.ncloc = ncloc;
	// }

	public Project getProjectId() {
		return projectId;
	}

	public void setProjectId(Project projectId) {
		this.projectId = projectId;
	}

	@XmlTransient
	public Collection<SDK4EDQualityGateMethodContribution> getSdk4edQualityGateMethodContributionCollection() {
		return sdk4edQualityGateMethodContributionCollection;
	}

	public void setSdk4edQualityGateMethodContributionCollection(
			Collection<SDK4EDQualityGateMethodContribution> sdk4edQualityGateMethodContributionCollection) {
		this.sdk4edQualityGateMethodContributionCollection = sdk4edQualityGateMethodContributionCollection;
	}

	@XmlTransient
	public Collection<SDK4EDQualityGateMetrics> getSdk4edQualityGateMetricsCollection() {
		return sdk4edQualityGateMetricsCollection;
	}

	public void setSdk4edQualityGateMetricsCollection(
			Collection<SDK4EDQualityGateMetrics> sdk4edQualityGateMetricsCollection) {
		this.sdk4edQualityGateMetricsCollection = sdk4edQualityGateMetricsCollection;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof SDK4EDQualityGate)) {
			return false;
		}
		SDK4EDQualityGate other = (SDK4EDQualityGate) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "SDK4EDQualityGate [id=" + id + ", baseSha=" + baseSha + ", targetSha=" + targetSha
				+ ", targetCommitTime=" + targetCommitTime + ", projectId=" + projectId.getId() + "]";
	}

	// @Override
	// public String toString() {
	// return "com.digkas.git.domain.Sdk4edQualityGate[ id=" + id + " ]";
	// }

}

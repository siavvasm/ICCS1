

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "sonarqube_live_measures")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "SonarqubeLiveMeasures.findAll", query = "SELECT s FROM SonarqubeLiveMeasures s"),
	@NamedQuery(name = "SonarqubeLiveMeasures.findById", query = "SELECT s FROM SonarqubeLiveMeasures s WHERE s.id = :id"),
	@NamedQuery(name = "SonarqubeLiveMeasures.findByValue", query = "SELECT s FROM SonarqubeLiveMeasures s WHERE s.value = :value"),
	@NamedQuery(name = "SonarqubeLiveMeasures.findByVariation", query = "SELECT s FROM SonarqubeLiveMeasures s WHERE s.variation = :variation"),
	@NamedQuery(name = "SonarqubeLiveMeasures.findByPath", query = "SELECT s FROM SonarqubeLiveMeasures s WHERE s.path = :path"),
	@NamedQuery(name = "SonarqubeLiveMeasures.findByScope", query = "SELECT s FROM SonarqubeLiveMeasures s WHERE s.scope = :scope"),
	@NamedQuery(name = "SonarqubeLiveMeasures.findByQualifier", query = "SELECT s FROM SonarqubeLiveMeasures s WHERE s.qualifier = :qualifier") })
public class SonarqubeLiveMeasures implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;
	@Basic(optional = false)
	@Column(name = "uuid")
	private String uuid;
	// @Max(value=?) @Min(value=?)//if you know range of your decimal fields
	// consider using these annotations to enforce field validation
	@Column(name = "value")
	private BigDecimal value;
	@Column(name = "variation")
	private BigDecimal variation;
	@Column(name = "path")
	private String path;
	@Column(name = "scope")
	private String scope;
	@Column(name = "qualifier")
	private String qualifier;
	@JoinColumn(name = "commit_id", referencedColumnName = "id")
	@ManyToOne(optional = false, fetch=FetchType.LAZY)
	private Commit commitId;
	@JoinColumn(name = "metric_id", referencedColumnName = "id")
	@ManyToOne(optional = false, fetch=FetchType.LAZY)
	private Metrics metricId;

	public SonarqubeLiveMeasures() {
	}

	public SonarqubeLiveMeasures(Integer id) {
		this.id = id;
	}

	public SonarqubeLiveMeasures(LiveMeasures liveMeasure, String path, String scope, String qualifier, Commit commitId, Metrics metricId) {
		this.uuid = liveMeasure.getUuid();
		this.value = liveMeasure.getValue();
		this.variation = liveMeasure.getVariation();
		this.path = path;
		this.scope = scope;
		this.qualifier = qualifier;
		this.commitId = commitId;
		this.metricId = metricId;
	}

	public SonarqubeLiveMeasures(String uuid, BigDecimal value, BigDecimal variation, String path, String scope, String qualifier, Commit commitId, Metrics metricId) {
		this.uuid = uuid;
		this.value = value;
		this.variation = variation;
		this.path = path;
		this.scope = scope;
		this.qualifier = qualifier;
		this.commitId = commitId;
		this.metricId = metricId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public BigDecimal getVariation() {
		return variation;
	}

	public void setVariation(BigDecimal variation) {
		this.variation = variation;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getQualifier() {
		return qualifier;
	}

	public void setQualifier(String qualifier) {
		this.qualifier = qualifier;
	}

	public Commit getCommitId() {
		return commitId;
	}

	public void setCommitId(Commit commitId) {
		this.commitId = commitId;
	}

	public Metrics getMetricId() {
		return metricId;
	}

	public void setMetricId(Metrics metricId) {
		this.metricId = metricId;
	}

//	@Override
//	public int hashCode() {
//		int hash = 0;
//		hash += (id != null ? id.hashCode() : 0);
//		return hash;
//	}
//
//	@Override
//	public boolean equals(Object object) {
//		// TODO: Warning - this method won't work in the case the id fields are not set
//		if (!(object instanceof SonarqubeLiveMeasures)) {
//			return false;
//		}
//		SonarqubeLiveMeasures other = (SonarqubeLiveMeasures) object;
//		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
//			return false;
//		}
//		return true;
//	}
	

	@Override
	public String toString() {
		return "SonarqubeLiveMeasures [id=" + id + ", uuid=" + uuid + ", value=" + value + ", variation=" + variation
				+ ", path=" + path + ", scope=" + scope + ", qualifier=" + qualifier + ", commitId=" + commitId.getCommitTime()
				+ ", metricId=" + metricId.getId() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((commitId == null) ? 0 : commitId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((metricId == null) ? 0 : metricId.hashCode());
		result = prime * result + ((path == null) ? 0 : path.hashCode());
		result = prime * result + ((qualifier == null) ? 0 : qualifier.hashCode());
		result = prime * result + ((scope == null) ? 0 : scope.hashCode());
		result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		result = prime * result + ((variation == null) ? 0 : variation.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SonarqubeLiveMeasures other = (SonarqubeLiveMeasures) obj;
		if (commitId == null) {
			if (other.commitId != null)
				return false;
		} else if (!commitId.equals(other.commitId))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (metricId == null) {
			if (other.metricId != null)
				return false;
		} else if (!metricId.equals(other.metricId))
			return false;
		if (path == null) {
			if (other.path != null)
				return false;
		} else if (!path.equals(other.path))
			return false;
		if (qualifier == null) {
			if (other.qualifier != null)
				return false;
		} else if (!qualifier.equals(other.qualifier))
			return false;
		if (scope == null) {
			if (other.scope != null)
				return false;
		} else if (!scope.equals(other.scope))
			return false;
		if (uuid == null) {
			if (other.uuid != null)
				return false;
		} else if (!uuid.equals(other.uuid))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		if (variation == null) {
			if (other.variation != null)
				return false;
		} else if (!variation.equals(other.variation))
			return false;
		return true;
	}

//	@Override
//	public String toString() {
//		return "com.digkas.git.domain.SonarqubeLiveMeasures[ id=" + id + " ]";
//	}
	
	

}

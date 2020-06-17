

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
@Table(name = "sdk4ed_quality_gate_method_contribution")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "SDK4EDQualityGateMethodContribution.findAll", query = "SELECT s FROM SDK4EDQualityGateMethodContribution s"),
	@NamedQuery(name = "SDK4EDQualityGateMethodContribution.findById", query = "SELECT s FROM SDK4EDQualityGateMethodContribution s WHERE s.id = :id"),
	@NamedQuery(name = "SDK4EDQualityGateMethodContribution.findByClassPath", query = "SELECT s FROM SDK4EDQualityGateMethodContribution s WHERE s.classPath = :classPath"),
	@NamedQuery(name = "SDK4EDQualityGateMethodContribution.findByMethodName", query = "SELECT s FROM SDK4EDQualityGateMethodContribution s WHERE s.methodName = :methodName"),
	@NamedQuery(name = "SDK4EDQualityGateMethodContribution.findByClassifier", query = "SELECT s FROM SDK4EDQualityGateMethodContribution s WHERE s.classifier = :classifier"),
	@NamedQuery(name = "SDK4EDQualityGateMethodContribution.findByContribution", query = "SELECT s FROM SDK4EDQualityGateMethodContribution s WHERE s.contribution = :contribution") })
public class SDK4EDQualityGateMethodContribution implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;
	@Basic(optional = false)
	@Column(name = "class_path")
	private String classPath;
	@Basic(optional = false)
	@Column(name = "method_name")
	private String methodName;
	@Basic(optional = false)
	@Column(name = "classifier")
	private String classifier;
	@Basic(optional = false)
	@Column(name = "contribution")
	private float contribution;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "sdk4edQualityGateMethodContribution")
	private Collection<SDK4EDQualityGateMethodIssues> sdk4edQualityGateMethodIssuesCollection;
	@JoinColumn(name = "sdk4ed_quality_gate_id", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private SDK4EDQualityGate sdk4edQualityGateId;

	public SDK4EDQualityGateMethodContribution() {
	}

	public SDK4EDQualityGateMethodContribution(Integer id) {
		this.id = id;
	}

	public SDK4EDQualityGateMethodContribution(Integer id, String classPath, String methodName, String classifier, float contribution) {
		this.id = id;
		this.classPath = classPath;
		this.methodName = methodName;
		this.classifier = classifier;
		this.contribution = contribution;
	}

	public SDK4EDQualityGateMethodContribution(String classPath, String methodName, String classifier, float contribution, SDK4EDQualityGate sdk4edQualityGateId) {
		this.classPath = classPath;
		this.methodName = methodName;
		this.classifier = classifier;
		this.contribution = contribution;
		this.sdk4edQualityGateId = sdk4edQualityGateId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getClassPath() {
		return classPath;
	}

	public void setClassPath(String classPath) {
		this.classPath = classPath;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getClassifier() {
		return classifier;
	}

	public void setClassifier(String classifier) {
		this.classifier = classifier;
	}

	public float getContribution() {
		return contribution;
	}

	public void setContribution(float contribution) {
		this.contribution = contribution;
	}

	@XmlTransient
	public Collection<SDK4EDQualityGateMethodIssues> getSdk4edQualityGateMethodIssuesCollection() {
		return sdk4edQualityGateMethodIssuesCollection;
	}

	public void setSdk4edQualityGateMethodIssuesCollection(Collection<SDK4EDQualityGateMethodIssues> sdk4edQualityGateMethodIssuesCollection) {
		this.sdk4edQualityGateMethodIssuesCollection = sdk4edQualityGateMethodIssuesCollection;
	}

	public SDK4EDQualityGate getSdk4edQualityGateId() {
		return sdk4edQualityGateId;
	}

	public void setSdk4edQualityGateId(SDK4EDQualityGate sdk4edQualityGateId) {
		this.sdk4edQualityGateId = sdk4edQualityGateId;
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
		if (!(object instanceof SDK4EDQualityGateMethodContribution)) {
			return false;
		}
		SDK4EDQualityGateMethodContribution other = (SDK4EDQualityGateMethodContribution) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.digkas.git.domain.Sdk4edQualityGateMethodContribution[ id=" + id + " ]";
	}

}

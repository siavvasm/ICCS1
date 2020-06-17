

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author George Digkas <digasgeo@gmail.com>
 */
@Entity
@Table(name = "project")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "Project.findAll", query = "SELECT p FROM Project p"),
	@NamedQuery(name = "Project.findById", query = "SELECT p FROM Project p WHERE p.id = :id"),
	@NamedQuery(name = "Project.findByName", query = "SELECT p FROM Project p WHERE p.name = :name"),
	@NamedQuery(name = "Project.findByKee", query = "SELECT p FROM Project p WHERE p.kee = :kee"),
	@NamedQuery(name = "Project.findByProjectUuid", query = "SELECT p FROM Project p WHERE p.projectUuid = :projectUuid") })
public class Project implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;
	@Basic(optional = false)
	@Column(name = "name")
	private String name;
	@Column(name = "kee")
	private String kee;
	@Column(name = "project_uuid")
	private String projectUuid;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "projectId", fetch=FetchType.LAZY)
	private Collection<SDK4EDQualityGate> sdk4edQualityGateCollection;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "projectId", fetch=FetchType.LAZY)
	private Set<Commit> commitCollection;
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "project")
	private ProjectDetails projectDetails;

	public Project() {}

	public Project(String name, String kee) {
		this.name = name;
		this.kee = kee;
	}

	public Project(String name, String kee, ProjectDetails projectDetails) {
		this.name = name;
		this.kee = kee;
		this.projectDetails = projectDetails;
	}

	public Project(Integer id) {
		this.id = id;
	}

	public Project(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKee() {
		return kee;
	}

	public void setKee(String kee) {
		this.kee = kee;
	}

	public String getProjectUuid() {
		return projectUuid;
	}

	public void setProjectUuid(String projectUuid) {
		this.projectUuid = projectUuid;
	}

	@XmlTransient
	public Collection<SDK4EDQualityGate> getSdk4edQualityGateCollection() {
		return sdk4edQualityGateCollection;
	}

	public void setSdk4edQualityGateCollection(Collection<SDK4EDQualityGate> sdk4edQualityGateCollection) {
		this.sdk4edQualityGateCollection = sdk4edQualityGateCollection;
	}

	@XmlTransient
	public Collection<Commit> getCommitCollection() {
		return commitCollection;
	}

	public void setCommitCollection(Set<Commit> commitCollection) {
		this.commitCollection = commitCollection;
	}

	public ProjectDetails getProjectDetails() {
		return projectDetails;
	}

	public Project setProjectDetails(ProjectDetails projectDetails) {
		this.projectDetails = projectDetails;
		return this;
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
		if (!(object instanceof Project)) {
			return false;
		}
		Project other = (Project) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.digkas.git.domain.Project[ id=" + id + " ]";
	}

}

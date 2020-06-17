/**
 * 
 */


import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author George Digkas <digasgeo@gmail.com>
 *
 */
@Entity
@Table(name = "project_details")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "ProjectDetails.findAll", query = "SELECT p FROM ProjectDetails p"),
	@NamedQuery(name = "ProjectDetails.findByProjectId", query = "SELECT p FROM ProjectDetails p WHERE p.projectId = :projectId"),
	@NamedQuery(name = "ProjectDetails.findByGitUri", query = "SELECT p FROM ProjectDetails p WHERE p.gitUri = :gitUri"),
	@NamedQuery(name = "ProjectDetails.findByLanguages", query = "SELECT p FROM ProjectDetails p WHERE p.languages = :languages"),
	@NamedQuery(name = "ProjectDetails.findByBuildTool", query = "SELECT p FROM ProjectDetails p WHERE p.buildTool = :buildTool"),
	@NamedQuery(name = "ProjectDetails.findByUsername", query = "SELECT p FROM ProjectDetails p WHERE p.username = :username"),
	@NamedQuery(name = "ProjectDetails.findByPassword", query = "SELECT p FROM ProjectDetails p WHERE p.password = :password"),
	@NamedQuery(name = "ProjectDetails.findByOauth", query = "SELECT p FROM ProjectDetails p WHERE p.oauth = :oauth") })
public class ProjectDetails implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@Column(name = "project_id")
	private Integer projectId;
	@Column(name = "git_uri")
	private String gitUri;
	@Column(name = "languages")
	private String languages;
	@Column(name = "build_tool")
	private String buildTool;
	@Column(name = "username")
	private String username;
	@Column(name = "password")
	private String password;
	@Column(name = "oauth")
	private String oauth;
	@JoinColumn(name = "project_id", referencedColumnName = "id", insertable = false, updatable = false)
	@OneToOne(optional = false)
	private Project project;

	public ProjectDetails() {
	}

	public ProjectDetails(Integer projectId) {
		this.projectId = projectId;
	}

	public ProjectDetails(String gitUri, String languages) {
		this.gitUri = gitUri;
		this.languages = languages;
	}

	public ProjectDetails(Project project, String gitUri, String languages) {
		this.project = project;
		this.projectId = project.getId();
		this.gitUri = gitUri;
		this.languages = languages;
	}

	public ProjectDetails(Integer projectId, String gitUri, String languages) {
		this.projectId = projectId;
		this.gitUri = gitUri;
		this.languages = languages;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public ProjectDetails setProjectId(Integer projectId) {
		this.projectId = projectId;
		return this;
	}

	public String getGitUri() {
		return gitUri;
	}

	public ProjectDetails setGitUri(String gitUri) {
		this.gitUri = gitUri;
		return this;
	}

	public String getLanguages() {
		return languages;
	}

	public ProjectDetails setLanguages(String languages) {
		this.languages = languages;
		return this;
	}

	public String getBuildTool() {
		return buildTool;
	}

	public ProjectDetails setBuildTool(String buildTool) {
		this.buildTool = buildTool;
		return this;
	}

	public String getUsername() {
		return username;
	}

	public ProjectDetails setUsername(String username) {
		this.username = username;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public ProjectDetails setPassword(String password) {
		this.password = password;
		return this;
	}

	public String getOauth() {
		return oauth;
	}

	public ProjectDetails setOauth(String oauth) {
		this.oauth = oauth;
		return this;
	}

	public Project getProject() {
		return project;
	}

	public ProjectDetails setProject(Project project) {
		this.project = project;
		return this;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (projectId != null ? projectId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof ProjectDetails)) {
			return false;
		}
		ProjectDetails other = (ProjectDetails) object;
		if ((this.projectId == null && other.projectId != null)
				|| (this.projectId != null && !this.projectId.equals(other.projectId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "ProjectDetails [projectId=" + projectId + ", gitUri=" + gitUri + ", languages=" + languages
				+ ", username=" + username + ", password=" + password + ", oauth=" + oauth + "]";
	}

	//	@Override
	//	public String toString() {
	//		return "com.digkas.git.domain.ProjectDetails[ projectId=" + projectId + " ]";
	//	}

}

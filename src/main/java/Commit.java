

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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.eclipse.jgit.revwalk.RevCommit;

/**
 *
 * @author George Digkas <digasgeo@gmail.com>
 */
@Entity
@Table(name = "commit")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "Commit.findAll", query = "SELECT c FROM Commit c"),
	@NamedQuery(name = "Commit.findById", query = "SELECT c FROM Commit c WHERE c.id = :id"),
	@NamedQuery(name = "Commit.findBySha", query = "SELECT c FROM Commit c WHERE c.sha = :sha"),
	@NamedQuery(name = "Commit.findByCommitTime", query = "SELECT c FROM Commit c WHERE c.commitTime = :commitTime"),
	@NamedQuery(name = "Commit.findByAuthorEmail", query = "SELECT c FROM Commit c WHERE c.authorEmail = :authorEmail"),
	@NamedQuery(name = "Commit.findByCommitterEmail", query = "SELECT c FROM Commit c WHERE c.committerEmail = :committerEmail") })
public class Commit implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;
	@Basic(optional = false)
	@Column(name = "sha")
	private String sha;
	@Basic(optional = false)
	@Column(name = "commit_time")
	private int commitTime;
	@Column(name = "author_email")
	private String authorEmail;
	@Column(name = "committer_email")
	private String committerEmail;
	@JoinTable(name = "commits_history", joinColumns = {
			@JoinColumn(name = "commit_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "previous_commit_id", referencedColumnName = "id") })
	@ManyToMany
	private Set<Commit> commitCollection;
	@ManyToMany(mappedBy = "commitCollection", fetch=FetchType.LAZY)
	private Set<Commit> commitCollection1;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "commitId", fetch=FetchType.LAZY)
	private Set<SonarqubeIssues> sonarqubeIssuesCollection;
	@JoinColumn(name = "project_id", referencedColumnName = "id")
	@ManyToOne(optional = false, fetch=FetchType.LAZY)
	private Project projectId;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "commitId")
	private Set<Method> methodCollection;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "commitId", fetch=FetchType.LAZY)
	private Set<CommitFiles> commitFilesCollection;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "commitId", fetch=FetchType.LAZY)
	private Set<SonarqubeLiveMeasures> sonarqubeLiveMeasuresCollection;

	public Commit() {
	}

	public Commit(Integer id) {
		this.id = id;
	}

	public Commit(Integer id, String sha, int commitTime) {
		this.id = id;
		this.sha = sha;
		this.commitTime = commitTime;
	}

	public Commit(String sha, int commitTime, Project projectId, String authorEmail, String committerEmail) {
		this.sha = sha;
		this.commitTime = commitTime;
		this.projectId = projectId;
		this.authorEmail = authorEmail;
		this.committerEmail = committerEmail;
	}

	public Commit(RevCommit rc, Project gitProject) {
		this.sha = rc.getName();
		this.commitTime = rc.getCommitTime();
		this.projectId = gitProject;
		this.authorEmail = rc.getAuthorIdent().getEmailAddress();
		this.committerEmail = rc.getCommitterIdent().getEmailAddress();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSha() {
		return sha;
	}

	public void setSha(String sha) {
		this.sha = sha;
	}

	public int getCommitTime() {
		return commitTime;
	}

	public void setCommitTime(int commitTime) {
		this.commitTime = commitTime;
	}

	public String getAuthorEmail() {
		return authorEmail;
	}

	public void setAuthorEmail(String authorEmail) {
		this.authorEmail = authorEmail;
	}

	public String getCommitterEmail() {
		return committerEmail;
	}

	public void setCommitterEmail(String committerEmail) {
		this.committerEmail = committerEmail;
	}

	@XmlTransient
	public Collection<Commit> getCommitCollection() {
		return commitCollection;
	}

	public void setCommitCollection(Set<Commit> commitCollection) {
		this.commitCollection = commitCollection;
	}

	@XmlTransient
	public Collection<Commit> getCommitCollection1() {
		return commitCollection1;
	}

	public void setCommitCollection1(Set<Commit> commitCollection1) {
		this.commitCollection1 = commitCollection1;
	}

	@XmlTransient
	public Collection<SonarqubeIssues> getSonarqubeIssuesCollection() {
		return sonarqubeIssuesCollection;
	}

	public void setSonarqubeIssuesCollection(Set<SonarqubeIssues> sonarqubeIssuesCollection) {
		this.sonarqubeIssuesCollection = sonarqubeIssuesCollection;
	}

	public Project getProjectId() {
		return projectId;
	}

	public void setProjectId(Project projectId) {
		this.projectId = projectId;
	}

	@XmlTransient
	public Collection<Method> getMethodCollection() {
		return methodCollection;
	}

	public void setMethodCollection(Set<Method> methodCollection) {
		this.methodCollection = methodCollection;
	}

	@XmlTransient
	public Collection<CommitFiles> getCommitFilesCollection() {
		return commitFilesCollection;
	}

	public void setCommitFilesCollection(Set<CommitFiles> commitFilesCollection) {
		this.commitFilesCollection = commitFilesCollection;
	}

	@XmlTransient
	public Collection<SonarqubeLiveMeasures> getSonarqubeLiveMeasuresCollection() {
		return sonarqubeLiveMeasuresCollection;
	}

	public void setSonarqubeLiveMeasuresCollection(Set<SonarqubeLiveMeasures> sonarqubeLiveMeasuresCollection) {
		this.sonarqubeLiveMeasuresCollection = sonarqubeLiveMeasuresCollection;
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
		if (!(object instanceof Commit)) {
			return false;
		}
		Commit other = (Commit) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.digkas.git.domain.Commit[ id=" + id + " ]";
	}

}

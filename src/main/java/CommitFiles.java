

import java.io.Serializable;

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
@Table(name = "commit_files")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "CommitFiles.findAll", query = "SELECT c FROM CommitFiles c"),
	@NamedQuery(name = "CommitFiles.findById", query = "SELECT c FROM CommitFiles c WHERE c.id = :id"),
	@NamedQuery(name = "CommitFiles.findByOldFilePath", query = "SELECT c FROM CommitFiles c WHERE c.oldFilePath = :oldFilePath"),
	@NamedQuery(name = "CommitFiles.findByNewFilePath", query = "SELECT c FROM CommitFiles c WHERE c.newFilePath = :newFilePath"),
	@NamedQuery(name = "CommitFiles.findByChangeType", query = "SELECT c FROM CommitFiles c WHERE c.changeType = :changeType") })
public class CommitFiles implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;
	@Column(name = "old_file_path")
	private String oldFilePath;
	@Column(name = "new_file_path")
	private String newFilePath;
	@Column(name = "change_type")
	private String changeType;
	@JoinColumn(name = "commit_id", referencedColumnName = "id")
	@ManyToOne(optional = false, fetch=FetchType.LAZY)
	private Commit commitId;

	public CommitFiles() {
	}

	public CommitFiles(Integer id) {
		this.id = id;
	}

	public CommitFiles(String oldFilePath, String newFilePath, String changeType, Commit commitId) {
		this.oldFilePath = oldFilePath;
		this.newFilePath = newFilePath;
		this.changeType = changeType;
		this.commitId = commitId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOldFilePath() {
		return oldFilePath;
	}

	public void setOldFilePath(String oldFilePath) {
		this.oldFilePath = oldFilePath;
	}

	public String getNewFilePath() {
		return newFilePath;
	}

	public void setNewFilePath(String newFilePath) {
		this.newFilePath = newFilePath;
	}

	public String getChangeType() {
		return changeType;
	}

	public void setChangeType(String changeType) {
		this.changeType = changeType;
	}

	public Commit getCommitId() {
		return commitId;
	}

	public void setCommitId(Commit commitId) {
		this.commitId = commitId;
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
		if (!(object instanceof CommitFiles)) {
			return false;
		}
		CommitFiles other = (CommitFiles) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "CommitFiles [oldFilePath=" + oldFilePath + ", newFilePath=" + newFilePath + ", changeType=" + changeType + "]" +"\n";
	}

	//	@Override
	//	public String toString() {
	//		return "com.digkas.git.domain.CommitFiles[ id=" + id + " ]";
	//	}

}

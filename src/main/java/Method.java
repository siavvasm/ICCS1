

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author George Digkas <digasgeo@gmail.com>
 */
@Entity
@Table(name = "method")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "Method.findAll", query = "SELECT m FROM Method m"),
	@NamedQuery(name = "Method.findById", query = "SELECT m FROM Method m WHERE m.id = :id"),
	@NamedQuery(name = "Method.findByPath", query = "SELECT m FROM Method m WHERE m.path = :path"),
	@NamedQuery(name = "Method.findByName", query = "SELECT m FROM Method m WHERE m.name = :name"),
	@NamedQuery(name = "Method.findByStartPos", query = "SELECT m FROM Method m WHERE m.startPos = :startPos"),
	@NamedQuery(name = "Method.findByEndPos", query = "SELECT m FROM Method m WHERE m.endPos = :endPos"),
	@NamedQuery(name = "Method.findByStartLine", query = "SELECT m FROM Method m WHERE m.startLine = :startLine"),
	@NamedQuery(name = "Method.findByEndLine", query = "SELECT m FROM Method m WHERE m.endLine = :endLine"),
	@NamedQuery(name = "Method.findByBodyLines", query = "SELECT m FROM Method m WHERE m.bodyLines = :bodyLines"),
	@NamedQuery(name = "Method.findByClassifier", query = "SELECT m FROM Method m WHERE m.classifier = :classifier"),
	@NamedQuery(name = "Method.findByHashCode", query = "SELECT m FROM Method m WHERE m.hashCode = :hashCode") })
public class Method implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;
	@Basic(optional = false)
	@Column(name = "path")
	private String path;
	@Basic(optional = false)
	@Column(name = "name")
	private String name;
	@Basic(optional = false)
	@Column(name = "start_pos")
	private int startPos;
	@Basic(optional = false)
	@Column(name = "end_pos")
	private int endPos;
	@Column(name = "start_line")
	private Integer startLine;
	@Column(name = "end_line")
	private Integer endLine;
	@Basic(optional = false)
	@Column(name = "body_lines")
	private int bodyLines;
	@Basic(optional = false)
	@Column(name = "classifier")
	private String classifier;
	@Basic(optional = false)
	@Column(name = "hash_code")
	private String hashCode;
	@JoinColumn(name = "commit_id", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Commit commitId;
	@OneToMany(mappedBy = "oldMethodId", fetch=FetchType.LAZY)
	private Collection<Method> methodCollection;
	@JoinColumn(name = "old_method_id", referencedColumnName = "id")
	@ManyToOne(fetch = FetchType.LAZY)
	private Method oldMethodId;

	public Method() {
	}

	public Method(Integer id) {
		this.id = id;
	}

	public Method(Integer id, String path, String name, int startPos, int endPos, int bodyLines, String classifier, String hashCode) {
		this.id = id;
		this.path = path;
		this.name = name;
		this.startPos = startPos;
		this.endPos = endPos;
		this.bodyLines = bodyLines;
		this.classifier = classifier;
		this.hashCode = hashCode;
	}

	public Method(String path, String name, int startPos, int endPos, Integer startLine, Integer endLine, Commit commitId, int bodyLines, String hashCode) {
		this.path = path;
		this.name = name;
		this.startPos = startPos;
		this.endPos = endPos;
		this.startLine = startLine;
		this.endLine = endLine;
		this.commitId = commitId;
		this.bodyLines = bodyLines;
		this.hashCode = hashCode;
	}

	public Method(String path, String name, int startPos, int endPos, Integer startLine, Integer endLine, Commit commitId, int bodyLines, String hashCode, Method oldMethodId) {
		this.path = path;
		this.name = name;
		this.startPos = startPos;
		this.endPos = endPos;
		this.startLine = startLine;
		this.endLine = endLine;
		this.commitId = commitId;
		this.bodyLines = bodyLines;
		this.hashCode = hashCode;
		this.oldMethodId = oldMethodId;
	}

	public Method(String path, String name, int startPos, int endPos, Integer startLine, Integer endLine, int bodyLines, String classifier, Commit commitId, String hashCode) {
		this.path = path;
		this.name = name;
		this.startPos = startPos;
		this.endPos = endPos;
		this.startLine = startLine;
		this.endLine = endLine;
		this.bodyLines = bodyLines;
		this.classifier = classifier;
		this.commitId = commitId;
		this.hashCode = hashCode;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStartPos() {
		return startPos;
	}

	public void setStartPos(int startPos) {
		this.startPos = startPos;
	}

	public int getEndPos() {
		return endPos;
	}

	public void setEndPos(int endPos) {
		this.endPos = endPos;
	}

	public Integer getStartLine() {
		return startLine;
	}

	public void setStartLine(Integer startLine) {
		this.startLine = startLine;
	}

	public Integer getEndLine() {
		return endLine;
	}

	public void setEndLine(Integer endLine) {
		this.endLine = endLine;
	}

	public int getBodyLines() {
		return bodyLines;
	}

	public void setBodyLines(int bodyLines) {
		this.bodyLines = bodyLines;
	}

	public String getClassifier() {
		return classifier;
	}

	public void setClassifier(String classifier) {
		this.classifier = classifier;
	}

	public String getHashCode() {
		return hashCode;
	}

	public void setHashCode(String hashCode) {
		this.hashCode = hashCode;
	}

	public Commit getCommitId() {
		return commitId;
	}

	public void setCommitId(Commit commitId) {
		this.commitId = commitId;
	}

	@XmlTransient
	public Collection<Method> getMethodCollection() {
		return methodCollection;
	}

	public void setMethodCollection(Set<Method> methodCollection) {
		this.methodCollection = methodCollection;
	}

	public Method getOldMethodId() {
		return oldMethodId;
	}

	public void setOldMethodId(Method oldMethodId) {
		this.oldMethodId = oldMethodId;
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
		if (!(object instanceof Method)) {
			return false;
		}
		Method other = (Method) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	//	@Override
	//	public int hashCode() {
	//		final int prime = 31;
	//		int result = 1;
	//		result = prime * result + bodyLines;
	//		result = prime * result + ((classifier == null) ? 0 : classifier.hashCode());
	//		result = prime * result + ((commitId == null) ? 0 : commitId.hashCode());
	//		result = prime * result + ((endLine == null) ? 0 : endLine.hashCode());
	//		result = prime * result + endPos;
	//		result = prime * result + ((hashCode == null) ? 0 : hashCode.hashCode());
	//		result = prime * result + ((id == null) ? 0 : id.hashCode());
	//		result = prime * result + ((methodCollection == null) ? 0 : methodCollection.hashCode());
	//		result = prime * result + ((name == null) ? 0 : name.hashCode());
	//		result = prime * result + ((oldMethodId == null) ? 0 : oldMethodId.hashCode());
	//		result = prime * result + ((path == null) ? 0 : path.hashCode());
	//		result = prime * result + ((startLine == null) ? 0 : startLine.hashCode());
	//		result = prime * result + startPos;
	//		return result;
	//	}
	//
	//	@Override
	//	public boolean equals(Object obj) {
	//		if (this == obj)
	//			return true;
	//		if (obj == null)
	//			return false;
	//		if (getClass() != obj.getClass())
	//			return false;
	//		Method other = (Method) obj;
	//		if (bodyLines != other.bodyLines)
	//			return false;
	//		if (classifier == null) {
	//			if (other.classifier != null)
	//				return false;
	//		} else if (!classifier.equals(other.classifier))
	//			return false;
	//		if (commitId == null) {
	//			if (other.commitId != null)
	//				return false;
	//		} else if (!commitId.equals(other.commitId))
	//			return false;
	//		if (endLine == null) {
	//			if (other.endLine != null)
	//				return false;
	//		} else if (!endLine.equals(other.endLine))
	//			return false;
	//		if (endPos != other.endPos)
	//			return false;
	//		if (hashCode == null) {
	//			if (other.hashCode != null)
	//				return false;
	//		} else if (!hashCode.equals(other.hashCode))
	//			return false;
	//		if (id == null) {
	//			if (other.id != null)
	//				return false;
	//		} else if (!id.equals(other.id))
	//			return false;
	//		if (methodCollection == null) {
	//			if (other.methodCollection != null)
	//				return false;
	//		} else if (!methodCollection.equals(other.methodCollection))
	//			return false;
	//		if (name == null) {
	//			if (other.name != null)
	//				return false;
	//		} else if (!name.equals(other.name))
	//			return false;
	//		if (oldMethodId == null) {
	//			if (other.oldMethodId != null)
	//				return false;
	//		} else if (!oldMethodId.equals(other.oldMethodId))
	//			return false;
	//		if (path == null) {
	//			if (other.path != null)
	//				return false;
	//		} else if (!path.equals(other.path))
	//			return false;
	//		if (startLine == null) {
	//			if (other.startLine != null)
	//				return false;
	//		} else if (!startLine.equals(other.startLine))
	//			return false;
	//		if (startPos != other.startPos)
	//			return false;
	//		return true;
	//	}

	@Override
	public String toString() {
		return "com.digkas.git.domain.Method[ id=" + id + " ]";
	}

}



import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Type;

/**
 *
 * @author George Digkas <digasgeo@gmail.com>
 */
@Entity
@Table(name = "issues")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "Issues.findAll", query = "SELECT i FROM Issues i"),
	@NamedQuery(name = "Issues.findById", query = "SELECT i FROM Issues i WHERE i.id = :id"),
	@NamedQuery(name = "Issues.findByKee", query = "SELECT i FROM Issues i WHERE i.kee = :kee"),
	@NamedQuery(name = "Issues.findByRuleId", query = "SELECT i FROM Issues i WHERE i.ruleId = :ruleId"),
	@NamedQuery(name = "Issues.findBySeverity", query = "SELECT i FROM Issues i WHERE i.severity = :severity"),
	@NamedQuery(name = "Issues.findByManualSeverity", query = "SELECT i FROM Issues i WHERE i.manualSeverity = :manualSeverity"),
	@NamedQuery(name = "Issues.findByMessage", query = "SELECT i FROM Issues i WHERE i.message = :message"),
	@NamedQuery(name = "Issues.findByLine", query = "SELECT i FROM Issues i WHERE i.line = :line"),
	@NamedQuery(name = "Issues.findByGap", query = "SELECT i FROM Issues i WHERE i.gap = :gap"),
	@NamedQuery(name = "Issues.findByStatus", query = "SELECT i FROM Issues i WHERE i.status = :status"),
	@NamedQuery(name = "Issues.findByResolution", query = "SELECT i FROM Issues i WHERE i.resolution = :resolution"),
	@NamedQuery(name = "Issues.findByChecksum", query = "SELECT i FROM Issues i WHERE i.checksum = :checksum"),
	@NamedQuery(name = "Issues.findByReporter", query = "SELECT i FROM Issues i WHERE i.reporter = :reporter"),
	@NamedQuery(name = "Issues.findByAssignee", query = "SELECT i FROM Issues i WHERE i.assignee = :assignee"),
	@NamedQuery(name = "Issues.findByAuthorLogin", query = "SELECT i FROM Issues i WHERE i.authorLogin = :authorLogin"),
	@NamedQuery(name = "Issues.findByActionPlanKey", query = "SELECT i FROM Issues i WHERE i.actionPlanKey = :actionPlanKey"),
	@NamedQuery(name = "Issues.findByIssueAttributes", query = "SELECT i FROM Issues i WHERE i.issueAttributes = :issueAttributes"),
	@NamedQuery(name = "Issues.findByEffort", query = "SELECT i FROM Issues i WHERE i.effort = :effort"),
	@NamedQuery(name = "Issues.findByCreatedAt", query = "SELECT i FROM Issues i WHERE i.createdAt = :createdAt"),
	@NamedQuery(name = "Issues.findByUpdatedAt", query = "SELECT i FROM Issues i WHERE i.updatedAt = :updatedAt"),
	@NamedQuery(name = "Issues.findByIssueCreationDate", query = "SELECT i FROM Issues i WHERE i.issueCreationDate = :issueCreationDate"),
	@NamedQuery(name = "Issues.findByIssueUpdateDate", query = "SELECT i FROM Issues i WHERE i.issueUpdateDate = :issueUpdateDate"),
	@NamedQuery(name = "Issues.findByIssueCloseDate", query = "SELECT i FROM Issues i WHERE i.issueCloseDate = :issueCloseDate"),
	@NamedQuery(name = "Issues.findByTags", query = "SELECT i FROM Issues i WHERE i.tags = :tags"),
	@NamedQuery(name = "Issues.findByComponentUuid", query = "SELECT i FROM Issues i WHERE i.componentUuid = :componentUuid"),
	@NamedQuery(name = "Issues.findByProjectUuid", query = "SELECT i FROM Issues i WHERE i.projectUuid = :projectUuid"),
	@NamedQuery(name = "Issues.findByIssueType", query = "SELECT i FROM Issues i WHERE i.issueType = :issueType"),
	@NamedQuery(name = "Issues.findByFromHotspot", query = "SELECT i FROM Issues i WHERE i.fromHotspot = :fromHotspot") })
public class Issues implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Long id;
	@Basic(optional = false)
	@Column(name = "kee")
	private String kee;
	@Column(name = "rule_id")
	private Integer ruleId;
	@Column(name = "severity")
	private String severity;
	@Basic(optional = false)
	@Column(name = "manual_severity")
	private boolean manualSeverity;
	@Column(name = "message")
	private String message;
	@Column(name = "line")
	private Integer line;
	// @Max(value=?) @Min(value=?)//if you know range of your decimal fields
	// consider using these annotations to enforce field validation
	@Column(name = "gap")
	private BigDecimal gap;
	@Column(name = "status")
	private String status;
	@Column(name = "resolution")
	private String resolution;
	@Column(name = "checksum")
	private String checksum;
	@Column(name = "reporter")
	private String reporter;
	@Column(name = "assignee")
	private String assignee;
	@Column(name = "author_login")
	private String authorLogin;
	@Column(name = "action_plan_key")
	private String actionPlanKey;
	@Column(name = "issue_attributes")
	private String issueAttributes;
	@Column(name = "effort")
	private Integer effort;
	@Column(name = "created_at", columnDefinition="int8")
	private BigInteger createdAt;
	@Column(name = "updated_at", columnDefinition="int8")
	private BigInteger updatedAt;
	@Column(name = "issue_creation_date", columnDefinition="int8")
	private BigInteger issueCreationDate;
	@Column(name = "issue_update_date", columnDefinition="int8")
	private BigInteger issueUpdateDate;
	@Column(name = "issue_close_date", columnDefinition="int8")
	private BigInteger issueCloseDate;
	@Column(name = "tags")
	private String tags;
	@Column(name = "component_uuid")
	private String componentUuid;
	@Column(name = "project_uuid")
	private String projectUuid;
	@Lob
	@Type(type="org.hibernate.type.BinaryType")
	@Column(name = "locations", columnDefinition="bytea")
	private byte[] locations;
	@Column(name = "issue_type")
	private Short issueType;
	@Column(name = "from_hotspot")
	private Boolean fromHotspot;

	public Issues() {
	}

	public Issues(Long id) {
		this.id = id;
	}

	public Issues(Long id, String kee, boolean manualSeverity) {
		this.id = id;
		this.kee = kee;
		this.manualSeverity = manualSeverity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getKee() {
		return kee;
	}

	public void setKee(String kee) {
		this.kee = kee;
	}

	public Integer getRuleId() {
		return ruleId;
	}

	public void setRuleId(Integer ruleId) {
		this.ruleId = ruleId;
	}

	public String getSeverity() {
		return severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
	}

	public boolean getManualSeverity() {
		return manualSeverity;
	}

	public void setManualSeverity(boolean manualSeverity) {
		this.manualSeverity = manualSeverity;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getLine() {
		return line;
	}

	public void setLine(Integer line) {
		this.line = line;
	}

	public BigDecimal getGap() {
		return gap;
	}

	public void setGap(BigDecimal gap) {
		this.gap = gap;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getResolution() {
		return resolution;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	public String getChecksum() {
		return checksum;
	}

	public void setChecksum(String checksum) {
		this.checksum = checksum;
	}

	public String getReporter() {
		return reporter;
	}

	public void setReporter(String reporter) {
		this.reporter = reporter;
	}

	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	public String getAuthorLogin() {
		return authorLogin;
	}

	public void setAuthorLogin(String authorLogin) {
		this.authorLogin = authorLogin;
	}

	public String getActionPlanKey() {
		return actionPlanKey;
	}

	public void setActionPlanKey(String actionPlanKey) {
		this.actionPlanKey = actionPlanKey;
	}

	public String getIssueAttributes() {
		return issueAttributes;
	}

	public void setIssueAttributes(String issueAttributes) {
		this.issueAttributes = issueAttributes;
	}

	public Integer getEffort() {
		return effort;
	}

	public void setEffort(Integer effort) {
		this.effort = effort;
	}

	public BigInteger getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(BigInteger createdAt) {
		this.createdAt = createdAt;
	}

	public BigInteger getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(BigInteger updatedAt) {
		this.updatedAt = updatedAt;
	}

	public BigInteger getIssueCreationDate() {
		return issueCreationDate;
	}

	public void setIssueCreationDate(BigInteger issueCreationDate) {
		this.issueCreationDate = issueCreationDate;
	}

	public BigInteger getIssueUpdateDate() {
		return issueUpdateDate;
	}

	public void setIssueUpdateDate(BigInteger issueUpdateDate) {
		this.issueUpdateDate = issueUpdateDate;
	}

	public BigInteger getIssueCloseDate() {
		return issueCloseDate;
	}

	public void setIssueCloseDate(BigInteger issueCloseDate) {
		this.issueCloseDate = issueCloseDate;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getComponentUuid() {
		return componentUuid;
	}

	public void setComponentUuid(String componentUuid) {
		this.componentUuid = componentUuid;
	}

	public String getProjectUuid() {
		return projectUuid;
	}

	public void setProjectUuid(String projectUuid) {
		this.projectUuid = projectUuid;
	}

	public byte[] getLocations() {
		return locations;
	}

	public void setLocations(byte[] locations) {
		this.locations = locations;
	}

	public Short getIssueType() {
		return issueType;
	}

	public void setIssueType(Short issueType) {
		this.issueType = issueType;
	}

	public Boolean getFromHotspot() {
		return fromHotspot;
	}

	public void setFromHotspot(Boolean fromHotspot) {
		this.fromHotspot = fromHotspot;
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
		if (!(object instanceof Issues)) {
			return false;
		}
		Issues other = (Issues) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.digkas.sonarqube.domain.Issues[ id=" + id + " ]";
	}

}

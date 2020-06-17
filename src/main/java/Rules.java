

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author George Digkas <digasgeo@gmail.com>
 */
@Entity
@Table(name = "rules")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "Rules.findAll", query = "SELECT r FROM Rules r"),
	@NamedQuery(name = "Rules.findById", query = "SELECT r FROM Rules r WHERE r.id = :id"),
	@NamedQuery(name = "Rules.findByName", query = "SELECT r FROM Rules r WHERE r.name = :name"),
	@NamedQuery(name = "Rules.findByPluginRuleKey", query = "SELECT r FROM Rules r WHERE r.pluginRuleKey = :pluginRuleKey"),
	@NamedQuery(name = "Rules.findByPluginConfigKey", query = "SELECT r FROM Rules r WHERE r.pluginConfigKey = :pluginConfigKey"),
	@NamedQuery(name = "Rules.findByPluginName", query = "SELECT r FROM Rules r WHERE r.pluginName = :pluginName"),
	@NamedQuery(name = "Rules.findByDescription", query = "SELECT r FROM Rules r WHERE r.description = :description"),
	@NamedQuery(name = "Rules.findByPriority", query = "SELECT r FROM Rules r WHERE r.priority = :priority"),
	@NamedQuery(name = "Rules.findByTemplateId", query = "SELECT r FROM Rules r WHERE r.templateId = :templateId"),
	@NamedQuery(name = "Rules.findByStatus", query = "SELECT r FROM Rules r WHERE r.status = :status"),
	@NamedQuery(name = "Rules.findByLanguage", query = "SELECT r FROM Rules r WHERE r.language = :language"),
	@NamedQuery(name = "Rules.findByDefRemediationFunction", query = "SELECT r FROM Rules r WHERE r.defRemediationFunction = :defRemediationFunction"),
	@NamedQuery(name = "Rules.findByDefRemediationGapMult", query = "SELECT r FROM Rules r WHERE r.defRemediationGapMult = :defRemediationGapMult"),
	@NamedQuery(name = "Rules.findByDefRemediationBaseEffort", query = "SELECT r FROM Rules r WHERE r.defRemediationBaseEffort = :defRemediationBaseEffort"),
	@NamedQuery(name = "Rules.findByGapDescription", query = "SELECT r FROM Rules r WHERE r.gapDescription = :gapDescription"),
	@NamedQuery(name = "Rules.findBySystemTags", query = "SELECT r FROM Rules r WHERE r.systemTags = :systemTags"),
	@NamedQuery(name = "Rules.findByIsTemplate", query = "SELECT r FROM Rules r WHERE r.isTemplate = :isTemplate"),
	@NamedQuery(name = "Rules.findByDescriptionFormat", query = "SELECT r FROM Rules r WHERE r.descriptionFormat = :descriptionFormat"),
	@NamedQuery(name = "Rules.findByCreatedAt", query = "SELECT r FROM Rules r WHERE r.createdAt = :createdAt"),
	@NamedQuery(name = "Rules.findByUpdatedAt", query = "SELECT r FROM Rules r WHERE r.updatedAt = :updatedAt"),
	@NamedQuery(name = "Rules.findByRuleType", query = "SELECT r FROM Rules r WHERE r.ruleType = :ruleType"),
	@NamedQuery(name = "Rules.findByPluginKey", query = "SELECT r FROM Rules r WHERE r.pluginKey = :pluginKey"),
	@NamedQuery(name = "Rules.findByScope", query = "SELECT r FROM Rules r WHERE r.scope = :scope"),
	@NamedQuery(name = "Rules.findByIsExternal", query = "SELECT r FROM Rules r WHERE r.isExternal = :isExternal"),
	@NamedQuery(name = "Rules.findBySecurityStandards", query = "SELECT r FROM Rules r WHERE r.securityStandards = :securityStandards"),
	@NamedQuery(name = "Rules.findByIsAdHoc", query = "SELECT r FROM Rules r WHERE r.isAdHoc = :isAdHoc") })
public class Rules implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;
	@Column(name = "name")
	private String name;
	@Basic(optional = false)
	@Column(name = "plugin_rule_key")
	private String pluginRuleKey;
	@Column(name = "plugin_config_key")
	private String pluginConfigKey;
	@Basic(optional = false)
	@Column(name = "plugin_name")
	private String pluginName;
	@Column(name = "description")
	private String description;
	@Column(name = "priority")
	private Integer priority;
	@Column(name = "template_id")
	private Integer templateId;
	@Column(name = "status")
	private String status;
	@Column(name = "language")
	private String language;
	@Column(name = "def_remediation_function")
	private String defRemediationFunction;
	@Column(name = "def_remediation_gap_mult")
	private String defRemediationGapMult;
	@Column(name = "def_remediation_base_effort")
	private String defRemediationBaseEffort;
	@Column(name = "gap_description")
	private String gapDescription;
	@Column(name = "system_tags")
	private String systemTags;
	@Basic(optional = false)
	@Column(name = "is_template")
	private boolean isTemplate;
	@Column(name = "description_format")
	private String descriptionFormat;
	@Column(name = "created_at", columnDefinition="int8")
	private BigInteger createdAt;
	@Column(name = "updated_at", columnDefinition="int8")
	private BigInteger updatedAt;
	@Column(name = "rule_type")
	private Short ruleType;
	@Column(name = "plugin_key")
	private String pluginKey;
	@Basic(optional = false)
	@Column(name = "scope")
	private String scope;
	@Basic(optional = false)
	@Column(name = "is_external")
	private boolean isExternal;
	@Column(name = "security_standards")
	private String securityStandards;
	@Basic(optional = false)
	@Column(name = "is_ad_hoc")
	private boolean isAdHoc;

	public Rules() {
	}

	public Rules(Integer id) {
		this.id = id;
	}

	public Rules(Integer id, String pluginRuleKey, String pluginName, boolean isTemplate, String scope,
			boolean isExternal, boolean isAdHoc) {
		this.id = id;
		this.pluginRuleKey = pluginRuleKey;
		this.pluginName = pluginName;
		this.isTemplate = isTemplate;
		this.scope = scope;
		this.isExternal = isExternal;
		this.isAdHoc = isAdHoc;
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

	public String getPluginRuleKey() {
		return pluginRuleKey;
	}

	public void setPluginRuleKey(String pluginRuleKey) {
		this.pluginRuleKey = pluginRuleKey;
	}

	public String getPluginConfigKey() {
		return pluginConfigKey;
	}

	public void setPluginConfigKey(String pluginConfigKey) {
		this.pluginConfigKey = pluginConfigKey;
	}

	public String getPluginName() {
		return pluginName;
	}

	public void setPluginName(String pluginName) {
		this.pluginName = pluginName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Integer getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Integer templateId) {
		this.templateId = templateId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getDefRemediationFunction() {
		return defRemediationFunction;
	}

	public void setDefRemediationFunction(String defRemediationFunction) {
		this.defRemediationFunction = defRemediationFunction;
	}

	public String getDefRemediationGapMult() {
		return defRemediationGapMult;
	}

	public void setDefRemediationGapMult(String defRemediationGapMult) {
		this.defRemediationGapMult = defRemediationGapMult;
	}

	public String getDefRemediationBaseEffort() {
		return defRemediationBaseEffort;
	}

	public void setDefRemediationBaseEffort(String defRemediationBaseEffort) {
		this.defRemediationBaseEffort = defRemediationBaseEffort;
	}

	public String getGapDescription() {
		return gapDescription;
	}

	public void setGapDescription(String gapDescription) {
		this.gapDescription = gapDescription;
	}

	public String getSystemTags() {
		return systemTags;
	}

	public void setSystemTags(String systemTags) {
		this.systemTags = systemTags;
	}

	public boolean getIsTemplate() {
		return isTemplate;
	}

	public void setIsTemplate(boolean isTemplate) {
		this.isTemplate = isTemplate;
	}

	public String getDescriptionFormat() {
		return descriptionFormat;
	}

	public void setDescriptionFormat(String descriptionFormat) {
		this.descriptionFormat = descriptionFormat;
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

	public Short getRuleType() {
		return ruleType;
	}

	public void setRuleType(Short ruleType) {
		this.ruleType = ruleType;
	}

	public String getPluginKey() {
		return pluginKey;
	}

	public void setPluginKey(String pluginKey) {
		this.pluginKey = pluginKey;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public boolean getIsExternal() {
		return isExternal;
	}

	public void setIsExternal(boolean isExternal) {
		this.isExternal = isExternal;
	}

	public String getSecurityStandards() {
		return securityStandards;
	}

	public void setSecurityStandards(String securityStandards) {
		this.securityStandards = securityStandards;
	}

	public boolean getIsAdHoc() {
		return isAdHoc;
	}

	public void setIsAdHoc(boolean isAdHoc) {
		this.isAdHoc = isAdHoc;
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
		if (!(object instanceof Rules)) {
			return false;
		}
		Rules other = (Rules) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.digkas.sonarqube.domain.Rules[ id=" + id + " ]";
	}

}

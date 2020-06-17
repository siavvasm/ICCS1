

import java.io.Serializable;
import java.math.BigDecimal;

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
@Table(name = "metrics")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "Metrics.findAll", query = "SELECT m FROM Metrics m"),
	@NamedQuery(name = "Metrics.findById", query = "SELECT m FROM Metrics m WHERE m.id = :id"),
	@NamedQuery(name = "Metrics.findByName", query = "SELECT m FROM Metrics m WHERE m.name = :name"),
	@NamedQuery(name = "Metrics.findByDescription", query = "SELECT m FROM Metrics m WHERE m.description = :description"),
	@NamedQuery(name = "Metrics.findByDirection", query = "SELECT m FROM Metrics m WHERE m.direction = :direction"),
	@NamedQuery(name = "Metrics.findByDomain", query = "SELECT m FROM Metrics m WHERE m.domain = :domain"),
	@NamedQuery(name = "Metrics.findByShortName", query = "SELECT m FROM Metrics m WHERE m.shortName = :shortName"),
	@NamedQuery(name = "Metrics.findByQualitative", query = "SELECT m FROM Metrics m WHERE m.qualitative = :qualitative"),
	@NamedQuery(name = "Metrics.findByValType", query = "SELECT m FROM Metrics m WHERE m.valType = :valType"),
	@NamedQuery(name = "Metrics.findByUserManaged", query = "SELECT m FROM Metrics m WHERE m.userManaged = :userManaged"),
	@NamedQuery(name = "Metrics.findByEnabled", query = "SELECT m FROM Metrics m WHERE m.enabled = :enabled"),
	@NamedQuery(name = "Metrics.findByWorstValue", query = "SELECT m FROM Metrics m WHERE m.worstValue = :worstValue"),
	@NamedQuery(name = "Metrics.findByBestValue", query = "SELECT m FROM Metrics m WHERE m.bestValue = :bestValue"),
	@NamedQuery(name = "Metrics.findByOptimizedBestValue", query = "SELECT m FROM Metrics m WHERE m.optimizedBestValue = :optimizedBestValue"),
	@NamedQuery(name = "Metrics.findByHidden", query = "SELECT m FROM Metrics m WHERE m.hidden = :hidden"),
	@NamedQuery(name = "Metrics.findByDeleteHistoricalData", query = "SELECT m FROM Metrics m WHERE m.deleteHistoricalData = :deleteHistoricalData"),
	@NamedQuery(name = "Metrics.findByDecimalScale", query = "SELECT m FROM Metrics m WHERE m.decimalScale = :decimalScale") })
public class Metrics implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;
	@Basic(optional = false)
	@Column(name = "name")
	private String name;
	@Column(name = "description")
	private String description;
	@Basic(optional = false)
	@Column(name = "direction")
	private int direction;
	@Column(name = "domain")
	private String domain;
	@Column(name = "short_name")
	private String shortName;
	@Basic(optional = false)
	@Column(name = "qualitative")
	private boolean qualitative;
	@Column(name = "val_type")
	private String valType;
	@Column(name = "user_managed")
	private Boolean userManaged;
	@Column(name = "enabled")
	private Boolean enabled;
	// @Max(value=?) @Min(value=?)//if you know range of your decimal fields
	// consider using these annotations to enforce field validation
	@Column(name = "worst_value")
	private BigDecimal worstValue;
	@Column(name = "best_value")
	private BigDecimal bestValue;
	@Column(name = "optimized_best_value")
	private Boolean optimizedBestValue;
	@Column(name = "hidden")
	private Boolean hidden;
	@Column(name = "delete_historical_data")
	private Boolean deleteHistoricalData;
	@Column(name = "decimal_scale")
	private Integer decimalScale;

	public Metrics() {
	}

	public Metrics(Integer id) {
		this.id = id;
	}

	public Metrics(Integer id, String name, int direction, boolean qualitative) {
		this.id = id;
		this.name = name;
		this.direction = direction;
		this.qualitative = qualitative;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public boolean getQualitative() {
		return qualitative;
	}

	public void setQualitative(boolean qualitative) {
		this.qualitative = qualitative;
	}

	public String getValType() {
		return valType;
	}

	public void setValType(String valType) {
		this.valType = valType;
	}

	public Boolean getUserManaged() {
		return userManaged;
	}

	public void setUserManaged(Boolean userManaged) {
		this.userManaged = userManaged;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public BigDecimal getWorstValue() {
		return worstValue;
	}

	public void setWorstValue(BigDecimal worstValue) {
		this.worstValue = worstValue;
	}

	public BigDecimal getBestValue() {
		return bestValue;
	}

	public void setBestValue(BigDecimal bestValue) {
		this.bestValue = bestValue;
	}

	public Boolean getOptimizedBestValue() {
		return optimizedBestValue;
	}

	public void setOptimizedBestValue(Boolean optimizedBestValue) {
		this.optimizedBestValue = optimizedBestValue;
	}

	public Boolean getHidden() {
		return hidden;
	}

	public void setHidden(Boolean hidden) {
		this.hidden = hidden;
	}

	public Boolean getDeleteHistoricalData() {
		return deleteHistoricalData;
	}

	public void setDeleteHistoricalData(Boolean deleteHistoricalData) {
		this.deleteHistoricalData = deleteHistoricalData;
	}

	public Integer getDecimalScale() {
		return decimalScale;
	}

	public void setDecimalScale(Integer decimalScale) {
		this.decimalScale = decimalScale;
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
		if (!(object instanceof Metrics)) {
			return false;
		}
		Metrics other = (Metrics) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.digkas.sonarqube.domain.Metrics[ id=" + id + " ]";
	}

}

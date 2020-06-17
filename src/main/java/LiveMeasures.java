

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author George Digkas <digasgeo@gmail.com>
 */
@Entity
@Table(name = "live_measures")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "LiveMeasures.findAll", query = "SELECT l FROM LiveMeasures l"),
	@NamedQuery(name = "LiveMeasures.findByUuid", query = "SELECT l FROM LiveMeasures l WHERE l.uuid = :uuid"),
	@NamedQuery(name = "LiveMeasures.findByProjectUuid", query = "SELECT l FROM LiveMeasures l WHERE l.projectUuid = :projectUuid"),
	@NamedQuery(name = "LiveMeasures.findByComponentUuid", query = "SELECT l FROM LiveMeasures l WHERE l.componentUuid = :componentUuid"),
	@NamedQuery(name = "LiveMeasures.findByMetricId", query = "SELECT l FROM LiveMeasures l WHERE l.metricId = :metricId"),
	@NamedQuery(name = "LiveMeasures.findByValue", query = "SELECT l FROM LiveMeasures l WHERE l.value = :value"),
	@NamedQuery(name = "LiveMeasures.findByTextValue", query = "SELECT l FROM LiveMeasures l WHERE l.textValue = :textValue"),
	@NamedQuery(name = "LiveMeasures.findByVariation", query = "SELECT l FROM LiveMeasures l WHERE l.variation = :variation"),
	@NamedQuery(name = "LiveMeasures.findByUpdateMarker", query = "SELECT l FROM LiveMeasures l WHERE l.updateMarker = :updateMarker"),
	@NamedQuery(name = "LiveMeasures.findByCreatedAt", query = "SELECT l FROM LiveMeasures l WHERE l.createdAt = :createdAt"),
	@NamedQuery(name = "LiveMeasures.findByUpdatedAt", query = "SELECT l FROM LiveMeasures l WHERE l.updatedAt = :updatedAt") })
public class LiveMeasures implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@Column(name = "uuid")
	private String uuid;
	@Basic(optional = false)
	@Column(name = "project_uuid")
	private String projectUuid;
	@Basic(optional = false)
	@Column(name = "component_uuid")
	private String componentUuid;
	@Basic(optional = false)
	@Column(name = "metric_id")
	private int metricId;
	// @Max(value=?) @Min(value=?)//if you know range of your decimal fields
	// consider using these annotations to enforce field validation
	@Column(name = "value")
	private BigDecimal value;
	@Column(name = "text_value")
	private String textValue;
	@Column(name = "variation")
	private BigDecimal variation;
	@Lob
	@Column(name = "measure_data", columnDefinition="bytea")
	private byte[] measureData;
	@Column(name = "update_marker")
	private String updateMarker;
	@Basic(optional = false)
	@Column(name = "created_at")
	private long createdAt;
	@Basic(optional = false)
	@Column(name = "updated_at")
	private long updatedAt;

	public LiveMeasures() {
	}

	public LiveMeasures(String uuid) {
		this.uuid = uuid;
	}

	public LiveMeasures(String uuid, String projectUuid, String componentUuid, int metricId, long createdAt,
			long updatedAt) {
		this.uuid = uuid;
		this.projectUuid = projectUuid;
		this.componentUuid = componentUuid;
		this.metricId = metricId;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getProjectUuid() {
		return projectUuid;
	}

	public void setProjectUuid(String projectUuid) {
		this.projectUuid = projectUuid;
	}

	public String getComponentUuid() {
		return componentUuid;
	}

	public void setComponentUuid(String componentUuid) {
		this.componentUuid = componentUuid;
	}

	public int getMetricId() {
		return metricId;
	}

	public void setMetricId(int metricId) {
		this.metricId = metricId;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public String getTextValue() {
		return textValue;
	}

	public void setTextValue(String textValue) {
		this.textValue = textValue;
	}

	public BigDecimal getVariation() {
		return variation;
	}

	public void setVariation(BigDecimal variation) {
		this.variation = variation;
	}

	public byte[] getMeasureData() {
		return measureData;
	}

	public void setMeasureData(byte[] measureData) {
		this.measureData = measureData;
	}

	public String getUpdateMarker() {
		return updateMarker;
	}

	public void setUpdateMarker(String updateMarker) {
		this.updateMarker = updateMarker;
	}

	public long getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(long createdAt) {
		this.createdAt = createdAt;
	}

	public long getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(long updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (uuid != null ? uuid.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof LiveMeasures)) {
			return false;
		}
		LiveMeasures other = (LiveMeasures) object;
		if ((this.uuid == null && other.uuid != null) || (this.uuid != null && !this.uuid.equals(other.uuid))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.digkas.sonarqube.domain.LiveMeasures[ uuid=" + uuid + " ]";
	}

}

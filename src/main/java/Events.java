

import java.io.Serializable;

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
@Table(name = "events")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "Events.findAll", query = "SELECT e FROM Events e"),
	@NamedQuery(name = "Events.findById", query = "SELECT e FROM Events e WHERE e.id = :id"),
	@NamedQuery(name = "Events.findByName", query = "SELECT e FROM Events e WHERE e.name = :name"),
	@NamedQuery(name = "Events.findByCategory", query = "SELECT e FROM Events e WHERE e.category = :category"),
	@NamedQuery(name = "Events.findByDescription", query = "SELECT e FROM Events e WHERE e.description = :description"),
	@NamedQuery(name = "Events.findByEventData", query = "SELECT e FROM Events e WHERE e.eventData = :eventData"),
	@NamedQuery(name = "Events.findByEventDate", query = "SELECT e FROM Events e WHERE e.eventDate = :eventDate"),
	@NamedQuery(name = "Events.findByCreatedAt", query = "SELECT e FROM Events e WHERE e.createdAt = :createdAt"),
	@NamedQuery(name = "Events.findByComponentUuid", query = "SELECT e FROM Events e WHERE e.componentUuid = :componentUuid"),
	@NamedQuery(name = "Events.findByAnalysisUuid", query = "SELECT e FROM Events e WHERE e.analysisUuid = :analysisUuid"),
	@NamedQuery(name = "Events.findByUuid", query = "SELECT e FROM Events e WHERE e.uuid = :uuid") })
public class Events implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;
	@Column(name = "name")
	private String name;
	@Column(name = "category")
	private String category;
	@Column(name = "description")
	private String description;
	@Column(name = "event_data")
	private String eventData;
	@Basic(optional = false)
	@Column(name = "event_date")
	private long eventDate;
	@Basic(optional = false)
	@Column(name = "created_at")
	private long createdAt;
	@Basic(optional = false)
	@Column(name = "component_uuid")
	private String componentUuid;
	@Basic(optional = false)
	@Column(name = "analysis_uuid")
	private String analysisUuid;
	@Basic(optional = false)
	@Column(name = "uuid")
	private String uuid;

	public Events() {
	}

	public Events(Integer id) {
		this.id = id;
	}

	public Events(Integer id, long eventDate, long createdAt, String componentUuid, String analysisUuid, String uuid) {
		this.id = id;
		this.eventDate = eventDate;
		this.createdAt = createdAt;
		this.componentUuid = componentUuid;
		this.analysisUuid = analysisUuid;
		this.uuid = uuid;
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEventData() {
		return eventData;
	}

	public void setEventData(String eventData) {
		this.eventData = eventData;
	}

	public long getEventDate() {
		return eventDate;
	}

	public void setEventDate(long eventDate) {
		this.eventDate = eventDate;
	}

	public long getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(long createdAt) {
		this.createdAt = createdAt;
	}

	public String getComponentUuid() {
		return componentUuid;
	}

	public void setComponentUuid(String componentUuid) {
		this.componentUuid = componentUuid;
	}

	public String getAnalysisUuid() {
		return analysisUuid;
	}

	public void setAnalysisUuid(String analysisUuid) {
		this.analysisUuid = analysisUuid;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
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
		if (!(object instanceof Events)) {
			return false;
		}
		Events other = (Events) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.digkas.sonarqube.domain.Events[ id=" + id + " ]";
	}

}

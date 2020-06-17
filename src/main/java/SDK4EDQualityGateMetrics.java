

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name = "sdk4ed_quality_gate_metrics")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "SDK4EDQualityGateMetrics.findAll", query = "SELECT s FROM SDK4EDQualityGateMetrics s"),
	@NamedQuery(name = "SDK4EDQualityGateMetrics.findBySdk4edQualityGateId", query = "SELECT s FROM SDK4EDQualityGateMetrics s WHERE s.sdk4edQualityGateMetricsPK.sdk4edQualityGateId = :sdk4edQualityGateId"),
	@NamedQuery(name = "SDK4EDQualityGateMetrics.findBySqaleIndex", query = "SELECT s FROM SDK4EDQualityGateMetrics s WHERE s.sdk4edQualityGateMetricsPK.sqaleIndex = :sqaleIndex"),
	@NamedQuery(name = "SDK4EDQualityGateMetrics.findByNcloc", query = "SELECT s FROM SDK4EDQualityGateMetrics s WHERE s.sdk4edQualityGateMetricsPK.ncloc = :ncloc") })
public class SDK4EDQualityGateMetrics implements Serializable {

	private static final long serialVersionUID = 1L;
	@EmbeddedId
	protected SDK4EDQualityGateMetricsPK sdk4edQualityGateMetricsPK;
	@JoinColumn(name = "sdk4ed_quality_gate_id", referencedColumnName = "id", insertable = false, updatable = false)
	@ManyToOne(optional = false)
	private SDK4EDQualityGate sdk4edQualityGate;

	public SDK4EDQualityGateMetrics() {
	}

	public SDK4EDQualityGateMetrics(SDK4EDQualityGateMetricsPK sdk4edQualityGateMetricsPK) {
		this.sdk4edQualityGateMetricsPK = sdk4edQualityGateMetricsPK;
	}

	public SDK4EDQualityGateMetrics(int sdk4edQualityGateId, BigDecimal sqaleIndex, BigDecimal ncloc) {
		this.sdk4edQualityGateMetricsPK = new SDK4EDQualityGateMetricsPK(sdk4edQualityGateId, sqaleIndex, ncloc);
	}

	public SDK4EDQualityGateMetricsPK getSdk4edQualityGateMetricsPK() {
		return sdk4edQualityGateMetricsPK;
	}

	public void setSdk4edQualityGateMetricsPK(SDK4EDQualityGateMetricsPK sdk4edQualityGateMetricsPK) {
		this.sdk4edQualityGateMetricsPK = sdk4edQualityGateMetricsPK;
	}

	public SDK4EDQualityGate getSdk4edQualityGate() {
		return sdk4edQualityGate;
	}

	public void setSdk4edQualityGate(SDK4EDQualityGate sdk4edQualityGate) {
		this.sdk4edQualityGate = sdk4edQualityGate;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (sdk4edQualityGateMetricsPK != null ? sdk4edQualityGateMetricsPK.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof SDK4EDQualityGateMetrics)) {
			return false;
		}
		SDK4EDQualityGateMetrics other = (SDK4EDQualityGateMetrics) object;
		if ((this.sdk4edQualityGateMetricsPK == null && other.sdk4edQualityGateMetricsPK != null)
				|| (this.sdk4edQualityGateMetricsPK != null
				&& !this.sdk4edQualityGateMetricsPK.equals(other.sdk4edQualityGateMetricsPK))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.digkas.git.domain.Sdk4edQualityGateMetrics[ sdk4edQualityGateMetricsPK="
				+ sdk4edQualityGateMetricsPK + " ]";
	}

}

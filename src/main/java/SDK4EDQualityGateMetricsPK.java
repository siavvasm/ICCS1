

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author George Digkas <digasgeo@gmail.com>
 */
@Embeddable
public class SDK4EDQualityGateMetricsPK implements Serializable {

	private static final long serialVersionUID = 1L;
	@Basic(optional = false)
	@Column(name = "sdk4ed_quality_gate_id")
	private int sdk4edQualityGateId;
	// @Max(value=?) @Min(value=?)//if you know range of your decimal fields
	// consider using these annotations to enforce field validation
	@Basic(optional = false)
	@Column(name = "sqale_index")
	private BigDecimal sqaleIndex;
	@Basic(optional = false)
	@Column(name = "ncloc")
	private BigDecimal ncloc;

	public SDK4EDQualityGateMetricsPK() {
	}

	public SDK4EDQualityGateMetricsPK(BigDecimal sqaleIndex, BigDecimal ncloc) {
		this.sqaleIndex = sqaleIndex;
		this.ncloc = ncloc;
	}

	public SDK4EDQualityGateMetricsPK(int sdk4edQualityGateId, BigDecimal sqaleIndex, BigDecimal ncloc) {
		this.sdk4edQualityGateId = sdk4edQualityGateId;
		this.sqaleIndex = sqaleIndex;
		this.ncloc = ncloc;
	}

	public int getSdk4edQualityGateId() {
		return sdk4edQualityGateId;
	}

	public void setSdk4edQualityGateId(int sdk4edQualityGateId) {
		this.sdk4edQualityGateId = sdk4edQualityGateId;
	}

	public BigDecimal getSqaleIndex() {
		return sqaleIndex;
	}

	public void setSqaleIndex(BigDecimal sqaleIndex) {
		this.sqaleIndex = sqaleIndex;
	}

	public BigDecimal getNcloc() {
		return ncloc;
	}

	public void setNcloc(BigDecimal ncloc) {
		this.ncloc = ncloc;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (int) sdk4edQualityGateId;
		hash += (sqaleIndex != null ? sqaleIndex.hashCode() : 0);
		hash += (ncloc != null ? ncloc.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof SDK4EDQualityGateMetricsPK)) {
			return false;
		}
		SDK4EDQualityGateMetricsPK other = (SDK4EDQualityGateMetricsPK) object;
		if (this.sdk4edQualityGateId != other.sdk4edQualityGateId) {
			return false;
		}
		if ((this.sqaleIndex == null && other.sqaleIndex != null)
				|| (this.sqaleIndex != null && !this.sqaleIndex.equals(other.sqaleIndex))) {
			return false;
		}
		if ((this.ncloc == null && other.ncloc != null) || (this.ncloc != null && !this.ncloc.equals(other.ncloc))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.digkas.git.domain.Sdk4edQualityGateMetricsPK[ sdk4edQualityGateId=" + sdk4edQualityGateId
				+ ", sqaleIndex=" + sqaleIndex + ", ncloc=" + ncloc + " ]";
	}

}

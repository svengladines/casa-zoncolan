package be.occam.zoncolan.domain.heat.honeywell;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class LocationStatus {
	
	protected String locationId;
	protected List<GateWay> gateWays;
	
	public String getLocationId() {
		return locationId;
	}
	
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	
	@JsonProperty(value="gateways")
	public List<GateWay> gateWays() {
		return this.gateWays;
	}
	
}

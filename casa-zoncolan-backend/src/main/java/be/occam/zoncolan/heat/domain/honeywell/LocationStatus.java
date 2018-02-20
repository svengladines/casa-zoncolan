package be.occam.zoncolan.heat.domain.honeywell;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

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

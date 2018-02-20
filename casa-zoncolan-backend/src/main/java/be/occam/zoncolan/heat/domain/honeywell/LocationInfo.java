package be.occam.zoncolan.heat.domain.honeywell;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author sven
 *
 */
public class LocationInfo {
	
	protected String locationId;
	protected List<GateWay> gateWays;
	
	@JsonProperty(value="locationId")
	public String getLocationId() {
		return this.locationId;
	}

}

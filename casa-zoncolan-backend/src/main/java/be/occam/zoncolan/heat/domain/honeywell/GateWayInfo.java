package be.occam.zoncolan.heat.domain.honeywell;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GateWayInfo {
	
	protected String gateWayId;
	
	@JsonProperty(value="gatewayId")
	public String getGateWayId() {
		return this.gateWayId;
	}

}

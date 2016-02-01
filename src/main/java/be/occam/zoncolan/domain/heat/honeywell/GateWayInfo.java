package be.occam.zoncolan.domain.heat.honeywell;

import org.codehaus.jackson.annotate.JsonProperty;

public class GateWayInfo {
	
	protected String gateWayId;
	
	@JsonProperty(value="gatewayId")
	public String getGateWayId() {
		return this.gateWayId;
	}

}

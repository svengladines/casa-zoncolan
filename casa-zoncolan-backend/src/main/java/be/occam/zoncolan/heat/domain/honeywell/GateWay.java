package be.occam.zoncolan.heat.domain.honeywell;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;


public class GateWay {
	
	protected Client client;
	protected List<TemperatureControlSystem> temperatureControlSystems;
	
	public GateWay() {
	}
	
	public GateWay setClient(Client client) {
		this.client = client;
		return this;
	}

	@JsonProperty(value="temperatureControlSystems") 
	public List<TemperatureControlSystem> temperatureControlSystems() {
		return this.temperatureControlSystems;
	}
	
	

}

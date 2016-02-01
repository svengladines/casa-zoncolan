package be.occam.zoncolan.domain.heat.honeywell;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

import be.occam.zoncolan.domain.heat.Client;

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

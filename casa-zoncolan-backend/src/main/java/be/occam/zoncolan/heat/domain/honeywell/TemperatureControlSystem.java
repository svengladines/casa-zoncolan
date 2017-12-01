package be.occam.zoncolan.heat.domain.honeywell;

import java.util.ArrayList;
import java.util.List;


public class TemperatureControlSystem {
	
	protected Client client;
	protected String systemId;
	protected List<Zone> zones;
	
	public TemperatureControlSystem() {
		this.zones = new ArrayList<Zone>();
	}

	public String getSystemId() {
		return systemId;
	}

	public TemperatureControlSystem setSystemId( String systemId ) {
		this.systemId = systemId;
		return this;
	}

	public Client getClient() {
		return client;
	}

	public TemperatureControlSystem setClient(Client client) {
		this.client = client;
		return this;
	}

	public List<Zone> getZones() {
		return zones;
	}
	
}

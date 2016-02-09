package be.occam.zoncolan.domain.heat.honeywell;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 *  {
      "zoneId": "1486393",
      "temperatureStatus": {
        "temperature": 14.5,
        "isAvailable": true
      },
      "activeFaults": [],
      "setpointStatus": {
        "targetHeatTemperature": 6.0000,
        "setpointMode": "PermanentOverride"
      },
      "name": "Thermostat"
  }
 * @author sven
 *
 */
public class Zone {
	
	protected String zoneId;
	protected TemperatureStatus temperatureStatus;
	protected SetPointStatus setPointStatus;
	protected String name;
	protected String[] activeFaults;

	@JsonProperty(value="zoneId")
	public String getZoneId() {
		return this.zoneId;
	}

	@JsonProperty(value="temperatureStatus")
	public TemperatureStatus getTemperatureStatus() {
		return temperatureStatus;
	}

	@JsonProperty(value="setpointStatus")
	public SetPointStatus getSetPointStatus() {
		return setPointStatus;
	}

	@JsonProperty(value="name")
	public String getName() {
		return name;
	}

	@JsonProperty(value="activeFaults")
	public String[] getActiveFaults() {
		return this.activeFaults;
	}	

}

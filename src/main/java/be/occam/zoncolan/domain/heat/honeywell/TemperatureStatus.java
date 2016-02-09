package be.occam.zoncolan.domain.heat.honeywell;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 *  {
      "temperatureStatus": {
        "temperature": 14.5,
        "isAvailable": true
      },
  }
 * @author sven
 *
 */
public class TemperatureStatus {
	
	protected Float temperature;
	protected Boolean isAvailable;

	@JsonProperty(value="temperature")
	public Float getTemperature() {
		return this.temperature;
	}

	@JsonProperty(value="isAvailable")
	public Boolean getIsAvailable() {
		return this.isAvailable;
	}	

}

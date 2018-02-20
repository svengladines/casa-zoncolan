package be.occam.zoncolan.heat.domain.honeywell;

import com.fasterxml.jackson.annotation.JsonProperty;

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

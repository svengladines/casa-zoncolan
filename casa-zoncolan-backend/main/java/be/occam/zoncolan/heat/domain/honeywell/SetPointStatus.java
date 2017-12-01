package be.occam.zoncolan.heat.domain.honeywell;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 *  {
      "setpointStatus": {
        "targetHeatTemperature": 6.0000,
        "setpointMode": "PermanentOverride"
      },
      "name": "Thermostat"
  }
 * @author sven
 *
 */
public class SetPointStatus {
	
	public static enum SetPointMode {
		PermanentOverride;
	}
	
	protected Double targetHeatTemperature;
	protected SetPointMode setPointMode;

	@JsonProperty(value="targetHeatTemperature")
	public Double getTargetHeatTemperature() {
		return this.targetHeatTemperature;
	}

	@JsonProperty(value="setpointMode")
	public SetPointMode getSetPointMode() {
		return this.setPointMode;
	}

	public void setTargetHeatTemperature(Double targetHeatTemperature) {
		this.targetHeatTemperature = targetHeatTemperature;
	}

	public void setSetPointMode(SetPointMode setPointMode) {
		this.setPointMode = setPointMode;
	}
	
}

package be.occam.zoncolan.web.dto;

public class ThermostatStatusDTO {
	
	protected Float currentTemperature;
	protected Double targetTemperature;
	
	public Float getCurrentTemperature() {
		return currentTemperature;
	}
	
	public ThermostatStatusDTO setCurrentTemperature( Float currentTemperature ) {
		this.currentTemperature = currentTemperature;
		return this;
	}
	
	public Double getTargetTemperature() {
		return targetTemperature;
	}
	
	public ThermostatStatusDTO setTargetTemperature( Double targetTemperature ) {
		this.targetTemperature = targetTemperature;
		return this;
	}

}

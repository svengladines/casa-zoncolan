package be.occam.zoncolan.web.dto;

public class ThermostatStatusDTO {
	
	protected Float currentTemperature;
	protected Float targetTemperature;
	
	public Float getCurrentTemperature() {
		return currentTemperature;
	}
	
	public ThermostatStatusDTO setCurrentTemperature( Float currentTemperature ) {
		this.currentTemperature = currentTemperature;
		return this;
	}
	
	public Float getTargetTemperature() {
		return targetTemperature;
	}
	
	public ThermostatStatusDTO setTargetTemperature( Float targetTemperature ) {
		this.targetTemperature = targetTemperature;
		return this;
	}

}

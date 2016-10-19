package be.occam.zoncolan.heat.web.dto;

public class ThermostatDTO {
	
	protected String id;
	protected Float currentTemperature;
	protected Float targetTemperature;
	
	public Float getCurrentTemperature() {
		return currentTemperature;
	}
	
	public ThermostatDTO setCurrentTemperature( Float currentTemperature ) {
		this.currentTemperature = currentTemperature;
		return this;
	}
	
	public Float getTargetTemperature() {
		return targetTemperature;
	}
	
	public ThermostatDTO setTargetTemperature( Float targetTemperature ) {
		this.targetTemperature = targetTemperature;
		return this;
	}

}

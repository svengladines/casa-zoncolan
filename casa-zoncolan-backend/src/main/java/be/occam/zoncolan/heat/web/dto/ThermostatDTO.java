package be.occam.zoncolan.heat.web.dto;

import be.occam.zoncolan.heat.domain.Thermostat;

public class ThermostatDTO {
	
	protected String id;
	protected Float currentTemperature;
	protected Float targetTemperature;
	
	public String getID() {
		return id;
	}

	public void setID(String id) {
		this.id = id;
	}
	
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
	
	public static ThermostatDTO from( Thermostat f ) {
		
		ThermostatDTO t
			= new ThermostatDTO();
		t.setID( f.getID() );
		t.setCurrentTemperature( f.getCurrentTemperature() );
		t.setTargetTemperature( f.getTargetTemperature() );
		return t;
	}

}

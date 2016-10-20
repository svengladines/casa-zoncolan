package be.occam.zoncolan.heat.domain;

import be.occam.zoncolan.heat.web.dto.ThermostatDTO;


public class Thermostat {

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
	
	public Thermostat setCurrentTemperature( Float currentTemperature ) {
		this.currentTemperature = currentTemperature;
		return this;
	}
	
	public Float getTargetTemperature() {
		return targetTemperature;
	}
	
	public Thermostat setTargetTemperature( Float targetTemperature ) {
		this.targetTemperature = targetTemperature;
		return this;
	}
	
	public static Thermostat from( ThermostatDTO f ) {
		
		Thermostat t
			= new Thermostat();
		t.setID( f.getID() );
		t.setCurrentTemperature( f.getCurrentTemperature() );
		t.setTargetTemperature( f.getTargetTemperature() );
		return t;
	}

}

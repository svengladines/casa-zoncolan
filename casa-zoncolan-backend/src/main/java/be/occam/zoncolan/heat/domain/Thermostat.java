package be.occam.zoncolan.heat.domain;

import be.occam.zoncolan.heat.web.dto.ThermostatDTO;


public class Thermostat {
	
	public static Float TEMP_OFF = 14.5F;
	public static Double TEMP_OFF_DOUBLE = 14.5D;
	
	public static Float TEMP_ON = 18.5F;
	public static Double TEMP_ON_DOUBLE = 18.5D;

	protected String id;
	protected Float currentTemperature;
	protected Float targetTemperature;
	protected Status status;
	
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
	
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
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

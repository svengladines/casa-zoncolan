package be.occam.zoncolan.domain.service.impl;

import be.occam.zoncolan.heat.domain.Status;
import be.occam.zoncolan.heat.domain.Thermostat;
import be.occam.zoncolan.heat.domain.people.Actor;
import be.occam.zoncolan.heat.domain.service.ThermostatService;

public class ThermostatServiceForAcme extends ThermostatService {

	@Override
	public boolean goTo(Float temperature) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Float feel(Actor actor) {
		return 18F;
	}

	@Override
	public Float readTarget(Actor actor) {
		return 19F;
	}

	@Override
	public Status turnOn(Actor actor) {
		return Status.on;
	}

	@Override
	public Status turnOff(Actor actor) {
		return Status.off;
	}

	@Override
	public Thermostat ping(Actor actor) {
		Thermostat thermostat
			= new Thermostat();
		thermostat.setCurrentTemperature( this.feel(actor));
		thermostat.setTargetTemperature( this.readTarget( actor) );
		
		return thermostat;
	}
	
}

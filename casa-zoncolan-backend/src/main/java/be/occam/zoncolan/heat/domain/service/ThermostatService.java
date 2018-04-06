package be.occam.zoncolan.heat.domain.service;

import be.occam.zoncolan.heat.domain.Status;
import be.occam.zoncolan.heat.domain.Thermostat;
import be.occam.zoncolan.heat.domain.people.Actor;
import be.occam.zoncolan.heat.web.dto.ThermostatDTO;

public abstract class ThermostatService {
	
	public ThermostatDTO retrieve( String id, Actor actor ){
		
		ThermostatDTO dto
			= new ThermostatDTO();
		
		Thermostat thermostat
			= this.ping( actor );
		
		thermostat.setID( id );
		// thermostat.setCurrentTemperature( this.feel( actor ) );
		// thermostat.setTargetTemperature( this.readTarget( actor ) );
		
		// Float target
			// = this.readTarget( actor );
		
		thermostat.setStatus( thermostat.getTargetTemperature().equals( Thermostat.TEMP_OFF ) ? Status.off : Status.on );
		
		dto = ThermostatDTO.from( thermostat );
		return dto;
		
	}
	
	public ThermostatDTO updateStatus( String id, Status status, Actor actor ){
		
		ThermostatDTO dto
			= new ThermostatDTO();
		
		if ( Status.on.equals( status ) ){
		
			dto.setStatus( this.turnOn( actor ) );
			
		}
		else if ( Status.off.equals( status ) ){
		
			dto.setStatus( this.turnOff( actor ) );
			
		}
		
		// dto.setID( id );
		//dto.setCurrentTemperature( this.feel( actor ) );
		//dto.setTargetTemperature( this.readTarget( actor ) );
		
		return dto;
		
	}
	
	public abstract boolean goTo( Float temperature );
	
	public abstract Thermostat ping( Actor actor );
	public abstract Float feel( Actor actor );
	public abstract Float readTarget( Actor actor );
	
	public abstract Status turnOn( Actor actor );
	public abstract Status turnOff( Actor actor );

}

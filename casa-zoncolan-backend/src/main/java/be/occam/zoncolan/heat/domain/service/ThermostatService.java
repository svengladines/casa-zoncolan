package be.occam.zoncolan.heat.domain.service;

import javax.annotation.Resource;

import be.occam.zoncolan.heat.domain.people.Actor;
import be.occam.zoncolan.heat.domain.people.HeatMan;
import be.occam.zoncolan.heat.web.dto.ThermostatDTO;

public class ThermostatService {
	
	@Resource
	HeatMan heatMan;

	public ThermostatDTO retrieve( String id, Actor actor ){
		
		ThermostatDTO dto
			= new ThermostatDTO();
		
		dto.setID( id );
		dto.setCurrentTemperature( this.heatMan.feel( actor ) );
		dto.setTargetTemperature( this.heatMan.readTarget( actor ) );
		
		return dto;
		
	}
	
	public ThermostatDTO off( String id, Actor actor ){
		
		ThermostatDTO dto
			= new ThermostatDTO();
		
		this.heatMan.turnOff( actor );
		
		// dto.setID( id );
		//dto.setCurrentTemperature( this.heatMan.feel( actor ) );
		//dto.setTargetTemperature( this.heatMan.readTarget( actor ) );
		
		return dto;
		
	}

}

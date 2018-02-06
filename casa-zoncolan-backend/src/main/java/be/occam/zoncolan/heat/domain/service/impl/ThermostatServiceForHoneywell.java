package be.occam.zoncolan.heat.domain.service.impl;

import java.text.DecimalFormat;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import be.occam.zoncolan.heat.domain.Status;
import be.occam.zoncolan.heat.domain.Thermostat;
import be.occam.zoncolan.heat.domain.honeywell.Client;
import be.occam.zoncolan.heat.domain.honeywell.LocationStatus;
import be.occam.zoncolan.heat.domain.honeywell.Schedule;
import be.occam.zoncolan.heat.domain.honeywell.Zone;
import be.occam.zoncolan.heat.domain.people.Actor;
import be.occam.zoncolan.heat.domain.service.ThermostatService;

public class ThermostatServiceForHoneywell extends ThermostatService {
	
	protected final Logger logger 
	= LoggerFactory.getLogger( this.getClass() );

	@Resource
	protected Client client;

	@Override
	public boolean goTo(Float temperature) {
		return false;
	}

@Override
public Thermostat ping( Actor actor ) {
	
	Thermostat thermostat
		= new Thermostat();
	
	LocationStatus status 
		= client( actor ).connect().account().locations().first().fetchStatus();
		
	Logger logger
		= LoggerFactory.getLogger( this.getClass() );
	
	logger.info( "status : [{}]", status );
	
	logger.info( "gateways : [{}]", status.gateWays() );
	
	Float currentTemperature
		= status.gateWays().get( 0 ).temperatureControlSystems().get( 0 ).getZones().get( 0 ).getTemperatureStatus().getTemperature();
	
	logger.info( "current temperature is [{}]", currentTemperature );
	
	thermostat.setCurrentTemperature( currentTemperature );
	
	Float currentTarget
		= toFloat( status.gateWays().get( 0 ).temperatureControlSystems().get( 0 ).getZones().get( 0 ).getSetPointStatus().getTargetHeatTemperature() ) ;

	logger.info( "target temperature is [{}]", currentTarget );
	
	thermostat.setTargetTemperature( currentTarget );
	
	return thermostat;
	
}

@Override
public Float feel( Actor actor ) {
	
	LocationStatus status 
		= client( actor ).connect().account().locations().first().fetchStatus();
		
	Logger logger
		= LoggerFactory.getLogger( this.getClass() );
	
	logger.info( "status : [{}]", status );
	
	logger.info( "gateways : [{}]", status.gateWays() );
	
	Float currentTemperature
		= status.gateWays().get( 0 ).temperatureControlSystems().get( 0 ).getZones().get( 0 ).getTemperatureStatus().getTemperature();
	
	logger.info( "current temperature is [{}]", currentTemperature );
	
	return currentTemperature;
	
}

protected Client client( Actor actor ) {
	
	return this.client;
	
}

@Override
public Float readTarget(Actor actor) {
	

	LocationStatus status 
		= client( actor ).connect().account().locations().first().fetchStatus();
	
	Float currentTarget
		= toFloat( status.gateWays().get( 0 ).temperatureControlSystems().get( 0 ).getZones().get( 0 ).getSetPointStatus().getTargetHeatTemperature() ) ;

	logger.info( "target temperature is [{}]", currentTarget );
	
	return currentTarget;
	
}

protected Float toFloat( Double d ) {
	
	try {
		return new DecimalFormat("#").parse( d.toString() ).floatValue();
	}
	catch( Exception e ) {
		throw new RuntimeException(e );
	}
	
}

protected Schedule schedule( Actor actor ) {
	
	LocationStatus status 
		= client( actor ).connect().account().locations().first().fetchStatus();
	
	Zone zone = status.gateWays().get( 0 ).temperatureControlSystems().get( 0 ).getZones().get( 0 );
	
	return this.client.getZoneSchedule( zone.getZoneId() );
	
}

@Override
public Status turnOn(Actor actor) {

	logger.info( "[{}], turn on heating", actor.getUserID() );
	
	
	LocationStatus status 
		= client( actor ).connect().account().locations().first().fetchStatus();

	Zone zone = status.gateWays().get( 0 ).temperatureControlSystems().get( 0 ).getZones().get( 0 );
	
	logger.info( "[{}], zone ID is [{}]", actor.getUserID(), zone.getZoneId() );

	//client ( actor ).putHeatPoint( zone.getZoneId(), 5.0D );
	Zone z = client ( actor ).getZone( zone.getZoneId() );
	
	zone.getSetPointStatus().setTargetHeatTemperature( 18.5D );
	
	client ( actor ).putZone( zone.getZoneId(), zone );
	
	return Status.on;
		
}


	@Override
	public Status turnOff(Actor actor) {
	
		logger.info( "[{}], turn off heat", actor.getUserID() );
		
		
		LocationStatus status 
			= client( actor ).connect().account().locations().first().fetchStatus();
	
		Zone zone = status.gateWays().get( 0 ).temperatureControlSystems().get( 0 ).getZones().get( 0 );
		
		logger.info( "[{}], zone ID is [{}]", actor.getUserID(), zone.getZoneId() );
	
		//client ( actor ).putHeatPoint( zone.getZoneId(), 5.0D );
		Zone z = client ( actor ).getZone( zone.getZoneId() );
		
		zone.getSetPointStatus().setTargetHeatTemperature( Thermostat.TEMP_OFF_DOUBLE );
		
		client ( actor ).putZone( zone.getZoneId(), zone );
		
		return Status.on;
			
	}


}

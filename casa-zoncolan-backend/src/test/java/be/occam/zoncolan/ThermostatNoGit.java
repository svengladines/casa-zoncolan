package be.occam.zoncolan;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import be.occam.zoncolan.heat.domain.honeywell.Client;
import be.occam.zoncolan.heat.domain.honeywell.LocationStatus;

public class ThermostatNoGit {
	
	protected final Logger logger
		= LoggerFactory.getLogger( this.getClass() );
	
	public static void main( String[] args ) {
		
		Client t
			= new Client( "sven.gladines@telenet.be", "honeyW3ll.16" );
		
		// t.connect().account().locations().first().gateWays().get( 0 ).temperatureControlSystems().get( 0 );
		
		LocationStatus status 
			= t.connect().account().locations().first().fetchStatus();
		
		Logger logger
			= LoggerFactory.getLogger( ThermostatNoGit.class );
		
		logger.info( "status : [{}]", status );
		
		logger.info( "gateways : [{}]", status.gateWays() );
		
		Float currentTemperature
			= status.gateWays().get( 0 ).temperatureControlSystems().get( 0 ).getZones().get( 0 ).getTemperatureStatus().getTemperature();
		
		Double currentTarget
			= status.gateWays().get( 0 ).temperatureControlSystems().get( 0 ).getZones().get( 0 ).getSetPointStatus().getTargetHeatTemperature();
		
		logger.info( "current temperature is [{}], target temperature is [{}]", currentTemperature, currentTarget );
		
	}

}

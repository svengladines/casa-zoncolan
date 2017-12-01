package be.occam.zoncolan.heat.domain.honeywell;

import java.text.DecimalFormat;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import be.occam.zoncolan.heat.domain.people.Actor;
import be.occam.zoncolan.heat.domain.people.HeatMan;

public class HeatManForHoneyWell extends HeatMan {
	
	protected final Logger logger 
		= LoggerFactory.getLogger( this.getClass() );
	
	@Resource
	protected Client client;

	@Override
	public boolean goTo(Float temperature) {
		return false;
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
		
		this.client.setUserName( actor.getUserID() );
		this.client.setPassWord( actor.getPassWord() );
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
	public void turnOff(Actor actor) {

		logger.info( "[{}], turn off heat", actor.getUserID() );
		
		
		LocationStatus status 
			= client( actor ).connect().account().locations().first().fetchStatus();
	
		Zone zone = status.gateWays().get( 0 ).temperatureControlSystems().get( 0 ).getZones().get( 0 );
		
		logger.info( "[{}], zone ID is [{}]", actor.getUserID(), zone.getZoneId() );

		//client ( actor ).putHeatPoint( zone.getZoneId(), 5.0D );
		Zone z = client ( actor ).getZone( zone.getZoneId() );
		
		zone.getSetPointStatus().setTargetHeatTemperature( 5.0D );
		
		client ( actor ).putZone( zone.getZoneId(), zone );
			
	}
	

}

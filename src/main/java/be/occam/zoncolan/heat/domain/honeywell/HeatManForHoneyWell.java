package be.occam.zoncolan.heat.domain.honeywell;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import be.occam.zoncolan.heat.domain.people.HeatMan;

public class HeatManForHoneyWell extends HeatMan {
	
	@Resource
	protected Client client;

	@Override
	public boolean goTo(Float temperature) {
		return false;
	}

	@Override
	public Float feel() {
		
		LocationStatus status 
			= client.connect().account().locations().first().fetchStatus();
			
		Logger logger
			= LoggerFactory.getLogger( this.getClass() );
		
		logger.info( "status : [{}]", status );
		
		logger.info( "gateways : [{}]", status.gateWays() );
		
		Float currentTemperature
			= status.gateWays().get( 0 ).temperatureControlSystems().get( 0 ).getZones().get( 0 ).getTemperatureStatus().getTemperature();
		
		logger.info( "current temperature is [{}]", currentTemperature );
		
		return currentTemperature;
		
	}

}

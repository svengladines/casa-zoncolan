package be.occam.zoncolan.domain.heat;

import static be.occam.utils.spring.context.Context.getBean;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import be.occam.zoncolan.domain.scenario.PullAndPublishTemperatureScenario;

public class ThermostatPullAndPublishNoGit {
	
	protected static final Logger logger
		= LoggerFactory.getLogger( ThermostatPullAndPublishNoGit.class );
	
	@Test
	public void doesItSmoke() {
		
		AnnotationConfigApplicationContext context	
			= new AnnotationConfigApplicationContext( "be.occam.zoncolan.application.config" );
		
		 PullAndPublishTemperatureScenario scenario
			= getBean( context, "pullAndPublishTemperatureScenario" );

		// scenario.setUserName( "sven.gladines@telenet.be" );
		// scenario.setPassWord( "honeyW3ll.16" ); 
		
		scenario.execute( "sven.gladines@telenet.be", "honeyW3ll.16" );
		
	}

}

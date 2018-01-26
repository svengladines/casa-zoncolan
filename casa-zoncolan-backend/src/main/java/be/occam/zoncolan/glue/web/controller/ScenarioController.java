package be.occam.zoncolan.glue.web.controller;

import static be.occam.utils.spring.web.Controller.response;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import be.occam.zoncolan.glue.domain.scenario.PullAndPublishTemperatureScenario;

@Controller
@RequestMapping(value="/scenarios/{id}")
public class ScenarioController {
	
	private final Logger logger 
		= LoggerFactory.getLogger( ScenarioController.class );
	
	@Resource
	PullAndPublishTemperatureScenario pullAndPublishTemperatureScenario;
	
	@RequestMapping( method = { RequestMethod.GET } )
	@ResponseBody
	public ResponseEntity<String> get( 
			@PathVariable("id") String id, 
			@RequestParam(required=false) String x,
			@RequestParam(required=false) String y ) {
		
		try {
			
			if ( "pullAndPublishTemperatureScenario".equals( id ) ) {
				
				this.pullAndPublishTemperatureScenario.execute( x, y );
				
				return response( "success" , HttpStatus.OK );
			}
			else {
				return response( String.format( "scenario [%] not supported", id ), HttpStatus.NOT_FOUND );
			}
			
		}
		catch( Exception e ) {
			logger.warn( "scenario execution failed", e );
			return response( e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR );
		}
			
	}
		
}

package be.occam.zoncolan.heat.web.controller;

import static be.occam.utils.spring.web.Controller.response;
import static be.occam.zoncolan.heat.web.util.WebUtil.actor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import be.occam.zoncolan.heat.domain.Status;
import be.occam.zoncolan.heat.domain.people.Actor;
import be.occam.zoncolan.heat.domain.service.ThermostatService;
import be.occam.zoncolan.heat.web.dto.ThermostatDTO;

@RequestMapping(value="/heating/cron")
public class CronController {
	
	private final Logger logger 
		= LoggerFactory.getLogger( CronController.class );
	
	@Resource
	ThermostatService thermostatService;
	
	@RequestMapping( value="turn-on", method = { RequestMethod.GET } )
	@ResponseBody
	// @CrossOrigin(origins = "http://www.debrodders.be")
	public ResponseEntity<Status> turnOn( 
			HttpServletRequest httpServletRequest ) {
		
		Actor actor
			= actor( httpServletRequest );
		
		logger.info( "[cron] turning heating on" );
		
		Status status 
			= Status.on;
		
		ThermostatDTO thermostatDTO
			= this.thermostatService.updateStatus( "zoncolan", status, actor );
		
		logger.info( "target temperature now is [{}]", thermostatDTO.getTargetTemperature() );
		
		return response( thermostatDTO.getStatus() , HttpStatus.OK );
			
	}
	
	@RequestMapping( value="turn-off", method = { RequestMethod.GET } )
	@ResponseBody
	// @CrossOrigin(origins = "http://www.debrodders.be")
	public ResponseEntity<Status> turnOff( 
			HttpServletRequest httpServletRequest ) {
		
		Actor actor
			= actor( httpServletRequest );
		
		logger.info( "[cron] turning heating off" );
		
		Status status 
			= Status.off;
		
		ThermostatDTO thermostatDTO
			= this.thermostatService.updateStatus( "zoncolan", status, actor );
		
		logger.info( "target temperature now is [{}]", thermostatDTO.getTargetTemperature() );
		
		return response( thermostatDTO.getStatus() , HttpStatus.OK );
			
	}
	
}

package be.occam.zoncolan.heat.web.controller;

import static be.occam.utils.spring.web.Controller.response;
import static be.occam.zoncolan.heat.web.util.WebUtil.actor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import be.occam.zoncolan.heat.domain.Status;
import be.occam.zoncolan.heat.domain.people.Actor;
import be.occam.zoncolan.heat.domain.service.ThermostatService;
import be.occam.zoncolan.heat.web.dto.ThermostatDTO;

@RequestMapping(value="/heating")
public class ThermostatController {
	
	private final Logger logger 
		= LoggerFactory.getLogger( ThermostatController.class );
	
	@Resource
	ThermostatService thermostatService;
	
	@RequestMapping( method = { RequestMethod.GET } )
	@ResponseBody
	@CrossOrigin(origins = "http://www.debrodders.be")
	public ResponseEntity<ThermostatDTO> retrieve( 
			HttpServletRequest httpServletRequest ) {
		
		logger.info( "test new version" );
		
		Actor actor
			= actor( httpServletRequest );
		
		ThermostatDTO thermostatDTO
			= this.thermostatService.retrieve( "zoncolan", actor );
		
		logger.info( "temperature is [{}]", thermostatDTO.getCurrentTemperature() );
		
		return response( thermostatDTO , HttpStatus.OK );
			
	}
	
	@RequestMapping( value="/status", method = { RequestMethod.PUT } )
	@ResponseBody
	@CrossOrigin(origins = "http://www.debrodders.be")
	public ResponseEntity<Status> putStatus( 
			@RequestBody Status status,
			HttpServletRequest httpServletRequest ) {
		
		Actor actor
			= actor( httpServletRequest );
		
		logger.info( "turning heating [{}]", status );
		
		ThermostatDTO thermostatDTO
			= this.thermostatService.updateStatus( "zoncolan", status, actor );
		
		logger.info( "target temperature is [{}]", thermostatDTO.getTargetTemperature() );
		
		return response( thermostatDTO.getStatus() , HttpStatus.OK );
			
	}
	
}

package be.occam.zoncolan.heat.web.controller;

import static be.occam.utils.spring.web.Controller.response;
import static be.occam.zoncolan.heat.web.util.WebUtil.actor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import be.occam.zoncolan.heat.domain.people.Actor;
import be.occam.zoncolan.heat.domain.service.ThermostatService;
import be.occam.zoncolan.heat.web.dto.ThermostatDTO;

@Controller
@RequestMapping(value="/heat/thermostats/{id}")
public class ThermostatController {
	
	private final Logger logger 
		= LoggerFactory.getLogger( ThermostatController.class );
	
	@Resource
	ThermostatService thermostatService;
	
	@RequestMapping( method = { RequestMethod.GET } )
	@ResponseBody
	public ResponseEntity<ThermostatDTO> retrieve( 
			@PathVariable("id") String id, 
			HttpServletRequest httpServletRequest ) {
		
		Actor actor
			= actor( httpServletRequest );
		
		ThermostatDTO thermostatDTO
			= this.thermostatService.retrieve( id, actor );
		
		logger.info( "temperature is [{}]", thermostatDTO.getCurrentTemperature() );
		
		return response( thermostatDTO , HttpStatus.OK );
			
	}
	
	@RequestMapping( value="off", method = { RequestMethod.GET } )
	@ResponseBody
	public ResponseEntity<ThermostatDTO> off( 
			@PathVariable("id") String id, 
			HttpServletRequest httpServletRequest ) {
		
		Actor actor
			= actor( httpServletRequest );
		
		ThermostatDTO thermostatDTO
			= this.thermostatService.off( id, actor );
		
		logger.info( "temperature is [{}]", thermostatDTO.getCurrentTemperature() );
		
		return response( thermostatDTO , HttpStatus.OK );
			
	}
		
}

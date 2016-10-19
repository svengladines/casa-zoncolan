package be.occam.zoncolan.heat.web.controller;

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

import be.occam.zoncolan.heat.domain.service.ThermostatService;
import be.occam.zoncolan.heat.web.dto.ThermostatDTO;

@Controller
@RequestMapping(value="/heat/thermostat/{id}")
public class ThermostatController {
	
	private final Logger logger 
		= LoggerFactory.getLogger( ThermostatController.class );
	
	@Resource
	ThermostatService thermostatService;
	
	@RequestMapping( method = { RequestMethod.GET } )
	@ResponseBody
	public ResponseEntity<ThermostatDTO> get( 
			@PathVariable("id") String id, 
			@RequestParam(required=false) String x,
			@RequestParam(required=false) String y ) {
		
		return response( "success" , HttpStatus.OK );
			
	}
		
}

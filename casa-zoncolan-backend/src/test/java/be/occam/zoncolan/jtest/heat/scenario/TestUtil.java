package be.occam.zoncolan.jtest.heat.scenario;

import static be.occam.utils.spring.web.Client.getJSON;
import static be.occam.utils.spring.web.Client.putJSON;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import be.occam.utils.javax.Utils;
import be.occam.utils.spring.web.Result;
import be.occam.zoncolan.heat.domain.Status;
import be.occam.zoncolan.heat.web.dto.ThermostatDTO;
import be.occam.zoncolan.heat.web.util.Headers;

public class TestUtil {
	
	protected static String heatingURL;
	
	public static void heatingURL( String url ) {
		heatingURL = url;
	}
	
	public static String heatingURL( ) {
		return heatingURL;
	}
	
	public static class RidesResult extends Result<ThermostatDTO[]> {
		
	};
	
	public static Map<String,String> as( String userId ) {

		Map<String,String> headers
			= new HashMap<String, String>();

		headers.put( Headers.ACTOR, userId );

		return headers;

	}

	public static ThermostatDTO turnOn( Map<String,String> headers ) {
		
		ThermostatDTO thermostat
			= new ThermostatDTO();

		thermostat.setStatus( Status.on );

		ThermostatDTO[] r
			= new ThermostatDTO[] {};
	
		ResponseEntity<ThermostatDTO> response
			= (ResponseEntity<ThermostatDTO>) putJSON( heatingURL, thermostat,  headers );

		return response.getBody();
		
		
	}
	
	public static Status turnOff( ) {
		
		Map<String,String> headers
			= new HashMap<String, String>();
		
		return turnOff( headers );
		
	}

	
	public static Status turnOff( Map<String,String> headers ) {
		
		String heatingStatusURL
			= new StringBuilder( heatingURL ).append( "/status" ).toString();
		
		Status offStatus
			= Status.off;
	
		ResponseEntity<Status> response
			= (ResponseEntity<Status>) putJSON( heatingStatusURL, offStatus,  headers );

		return response.getBody();
		
		
	}
	
	public static ThermostatDTO read( ) {
		
		Map<String,String> headers
			= Utils.map();
		
		return read( headers );
		
	}
	
	public static ThermostatDTO read( Map<String,String> headers ) {
		
		ResponseEntity<ThermostatDTO> response
			= getJSON( heatingURL, be.occam.zoncolan.heat.web.dto.ThermostatDTO.class, headers );
		
		return response.getBody();
		
	}
	
	public static Long oneHour = Long.valueOf( 60 * 60 * 1000);
	public static Long oneMinute = Long.valueOf( 60 * 1000 );

}

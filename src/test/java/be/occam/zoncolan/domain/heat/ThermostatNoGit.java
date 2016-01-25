package be.occam.zoncolan.domain.heat;

import static be.occam.utils.spring.web.Client.postMultiPart;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;

public class ThermostatNoGit {
	
	protected final Logger logger
		= LoggerFactory.getLogger( this.getClass() );
	
	public static void main( String[] args ) {
		
		Thermostat t
			= new Thermostat( "sven.gladines@telenet.be", "honeyW3ll.16" );
		
		t.connect();
		
	}

}

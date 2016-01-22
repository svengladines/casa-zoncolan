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

public class Thermostat {
	
	protected final Logger logger
		= LoggerFactory.getLogger( this.getClass() );
	
	protected final String host
		= "tccna.honeywell.com";
	
	protected final String basePath
		= "/WebAPI/emea/api/v1/";
	
	protected final String tokenPath
		= "/Auth/OAuth/Token";
	
	protected final String grantTypeField
		= "grant_type";
	
    protected final String grantType
    	= "password";
    
    protected final String scopeField
    	= "scope";
    
    protected final String scope
    	= "EMEA-V1-Basic EMEA-V1-Anonymous EMEA-V1-Get-Current-User-Account EMEA-V1-Contractor-Connections";
    
    protected final String appID
    	= "2ff150b4-a385-40d5-8899-5c6d88d2cbc2";
    
    protected final String userNameField
    	= "username";
    
    protected final String passWordField
		= "password";
    
    protected final String userName
    	= "sven.gladines@telenet.be";
    
    protected final String passWord
    	= "honeyWell.16";
    
	
	public void connect() {
		
		String url
			= new StringBuilder("https://").append( this.host ).append( this.tokenPath ).toString();
		
		
		try {
		
			ResponseEntity<String> loginResponse
				= postMultiPart( url, String.class, this.fields(), this.headers() );
			
			logger.info( "login response code: {} ", loginResponse.getStatusCode() );
			logger.info( "login response body: {} ", loginResponse.getBody() );
			
			URI uri 
				= loginResponse.getHeaders().getLocation();
			
			logger.info( "login response location header: {} ", uri.toString() );
		}
		catch ( HttpClientErrorException e) {
			try {
				String x 
					= new String( e.getResponseBodyAsByteArray(), "utf-8" );
				logger.info( new String( x ) );
			}
			catch( UnsupportedEncodingException ignore ) {}
			
		} 
		
	}
	
	protected Map<String,String> headers() {
		
		Map<String,String> headers
			= new HashMap<String,String>();
		
	    headers.put("User-Agent", "RestSharp 104.4.0.0");
	    headers.put("Content-Type", "application/x-www-form-urlencoded");
	    headers.put("applicationId", this.appID );

		return headers;
		
	}
	
	protected MultiValueMap<String,Object> fields() {
		
		MultiValueMap<String,Object> fields
			= new LinkedMultiValueMap<String,Object>();
		
		fields.add( this.userNameField, this.userName );
		fields.add( this.passWordField, this.passWord );
		
		fields.add( this.grantTypeField , this.grantType );
		fields.add( this.scopeField, this.scope );
		
		return fields;
		
	}
	
	public static void main( String[] args ) {
		
		Thermostat t
			= new Thermostat();
		
		t.connect();
		
	}

}

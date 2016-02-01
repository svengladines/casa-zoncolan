package be.occam.zoncolan.domain.heat;

import static be.occam.utils.spring.web.Client.getJSON;
import static be.occam.utils.spring.web.Client.postMultiPart;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;

import be.occam.zoncolan.domain.heat.honeywell.AccessToken;
import be.occam.zoncolan.domain.heat.honeywell.Account;
import be.occam.zoncolan.domain.heat.honeywell.Location;
import be.occam.zoncolan.domain.heat.honeywell.LocationStatus;
import be.occam.zoncolan.domain.heat.honeywell.Locations;

public class Client {
	
	protected final Logger logger
		= LoggerFactory.getLogger( this.getClass() );
	
	protected final String host
		= "tccna.honeywell.com";
	
	protected final String basePath
		= "/WebAPI/emea/api/v1";
	
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
    
    protected final String userName;
    
    protected final String passWord;
    
    protected AccessToken accessToken;
    
    protected Account account;
    
    protected Locations locations;
    
    protected final ObjectMapper objectMapper;
    
    
    public Client( String userName, String passWord ) {
    	
    	this.userName = userName;
    	this.passWord = passWord;
    	this.objectMapper = new ObjectMapper();
    	this.objectMapper.configure( org.codehaus.jackson.map.DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false );
    	
    }
	
	public Client connect() {
		
		String url
			= new StringBuilder("https://").append( this.host ).append( this.tokenPath ).toString();
		
		
		try {
		
			ResponseEntity<String> loginResponse
				= postMultiPart( url, String.class, this.fields(), this.headers() );
			
			logger.info( "login response code: {} ", loginResponse.getStatusCode() );
			logger.info( "login response body: {} ", loginResponse.getBody() );
			
			URI uri 
				= loginResponse.getHeaders().getLocation();

			if ( uri != null ) {
				logger.info( "login response location header: {} ", uri.toString() );
			}	
			else {
				
				String responseJSON
					= loginResponse.getBody();
				
				logger.info( "json = [{}]", responseJSON );
				
				this.accessToken = this.objectMapper.reader( AccessToken.class ).readValue( responseJSON );
				
				logger.info( "access_token = [{}]", this.accessToken );
				
			}
		}
		catch ( HttpClientErrorException e) {
			try {
				String x 
					= new String( e.getResponseBodyAsByteArray(), "utf-8" );
				logger.info( new String( x ) );
			}
			catch( UnsupportedEncodingException ignore ) {}
			
		} catch (JsonProcessingException e) {
			logger.warn( "could not parse JSON response", e );
		} catch (IOException e) {
			logger.warn( "could not process JSON response", e );
		} 
		
		return this;
		
	}
	
	public Client account() {
		
		String url
			= new StringBuilder("https://").append( this.host ).append( this.basePath ).append( "/userAccount" ).toString();
		
		
		try {
		
			ResponseEntity<String> getResponse
				= getJSON( url, String.class, this.headers() );
			
			logger.info( "account GET response code: {} ", getResponse.getStatusCode() );
			logger.info( "account GET response body: {} ", getResponse.getBody() );
			
			String responseJSON
					= getResponse.getBody();
				
			logger.info( "json = [{}]", responseJSON );
			
			this.account = this.objectMapper.reader( Account.class ).readValue( responseJSON );
			
			logger.info( "user id is [{}]", this.account.getUserId() );
			
		}
		catch ( HttpClientErrorException e) {
			try {
				String x 
					= new String( e.getResponseBodyAsByteArray(), "utf-8" );
				logger.info( new String( x ) );
			}
			catch( UnsupportedEncodingException ignore ) {}
		} catch (JsonProcessingException e) {
			logger.warn( "could not parse JSON response", e );
		} catch (IOException e) {
			logger.warn( "could not process JSON response", e );
		} 
		
		return this;
		
	}
	
	public Locations locations() {
		
		String url
			= new StringBuilder("https://").append( this.host ).append( this.basePath ).append( "/location/installationInfo?userId={userId}&includeTemperatureControlSystems=True" ).toString();
		
		try {
		
			ResponseEntity<String> getResponse
				= getJSON( url, String.class, this.headers(), this.account.getUserId() );
			
			logger.info( "account GET response code: {} ", getResponse.getStatusCode() );
			logger.info( "account GET response body: {} ", getResponse.getBody() );
			
			String responseJSON
					= getResponse.getBody();
				
			logger.info( "json = [{}]", responseJSON );
			
			Location[] locations = this.objectMapper.reader( Location[].class ).readValue( responseJSON );
			
			this.locations 
				= new Locations( this, locations );
			
			return this.locations;
			
		}
		catch ( HttpClientErrorException e) {
			try {
				String x 
					= new String( e.getResponseBodyAsByteArray(), "utf-8" );
				logger.info( new String( x ) );
			}
			catch( UnsupportedEncodingException ignore ) {}
		} catch (JsonProcessingException e) {
			logger.warn( "could not parse JSON response", e );
		} catch (IOException e) {
			logger.warn( "could not process JSON response", e );
		} 
		
		return null;
		
	}
	
	public Client location( ) {
		
		String id
			= this.locations.first().getLocationInfo().getLocationId();
		
		String url
			= new StringBuilder("https://").append( this.host ).append( this.basePath ).append( "/location/" ).append( id ).append("/installationInfo" ).toString();
		
		try {
		
			ResponseEntity<String> getResponse
				= getJSON( url, String.class, this.headers(), this.account.getUserId() );
			
			logger.info( "account GET response code: {} ", getResponse.getStatusCode() );
			logger.info( "account GET response body: {} ", getResponse.getBody() );
			
			String responseJSON
					= getResponse.getBody();
				
			logger.info( "json = [{}]", responseJSON );
			
			// this.account = this.objectMapper.reader( Account.class ).readValue( responseJSON );
			
			// logger.info( "user id is [{}]", this.account.getUserId() );
			
		}
		catch ( HttpClientErrorException e) {
			try {
				String x 
					= new String( e.getResponseBodyAsByteArray(), "utf-8" );
				logger.info( new String( x ) );
			}
			catch( UnsupportedEncodingException ignore ) {}
		} /*catch (JsonProcessingException e) {
			logger.warn( "could not parse JSON response", e );
		} catch (IOException e) {
			logger.warn( "could not process JSON response", e );
		} */
		
		return this;
		
	}
	
	protected Map<String,String> headers() {
		
		Map<String,String> headers
			= new HashMap<String,String>();
		
	    headers.put("User-Agent", "RestSharp 104.4.0.0");
	    headers.put("Content-Type", "application/x-www-form-urlencoded");
	    headers.put("Accept", "application/json");
	    headers.put("applicationId", this.appID );
	    
	    if ( this.accessToken == null ) { 
	    	headers.put("Authorization", "Basic MmZmMTUwYjQtYTM4NS00MGQ1LTg4OTktNWM2ZDg4ZDJjYmMyOjZGODhCOTgwLUI5OTUtNDUxRC04RTJBLTY2REMyQkNCRDU3MQ==" );
	    }
	    else {
	    	headers.put("Authorization", String.format( "bearer %s", this.accessToken.getAccessToken() ) );
	    }

		return headers;
		
	}
	
	public StringBuilder basePath() {
		
		StringBuilder b
			= new StringBuilder("https://")
				.append( this.host )
				.append( this.basePath );

		return b;
		
	}
	
	public LocationStatus getLocationStatus( String locationId ) {
		
		String url
			= this.basePath().append("/location/{locationId}/status").toString();
		
		try {
		
			ResponseEntity<String> getResponse
				= getJSON( url, String.class, this.headers(), locationId );
			
			logger.info( "location.status GET response code: {} ", getResponse.getStatusCode() );
			logger.info( "location.status GET response body: {} ", getResponse.getBody() );
			
			String responseJSON
					= getResponse.getBody();
				
			logger.info( "json = [{}]", responseJSON );
			
			// Location[] locations = this.objectMapper.reader( Location[].class ).readValue( responseJSON );
			
			// this.locations 
			//	= new Locations( this, locations );
			
			return null;
			
		}
		catch ( HttpClientErrorException e) {
			try {
				String x 
					= new String( e.getResponseBodyAsByteArray(), "utf-8" );
				logger.info( new String( x ) );
			}
			catch( UnsupportedEncodingException ignore ) {}
		} /* catch (JsonProcessingException e) {
			logger.warn( "could not parse JSON response", e );
		} catch (IOException e) {
			logger.warn( "could not process JSON response", e );
		} */
		
		return null;
		
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

}
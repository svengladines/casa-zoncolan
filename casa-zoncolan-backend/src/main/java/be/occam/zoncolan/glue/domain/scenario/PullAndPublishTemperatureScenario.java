package be.occam.zoncolan.glue.domain.scenario;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import be.occam.zoncolan.glue.domain.people.MailMan;
import be.occam.zoncolan.heat.domain.honeywell.Client;
import be.occam.zoncolan.heat.domain.honeywell.LocationStatus;
import be.occam.zoncolan.heat.web.dto.ThermostatDTO;
import freemarker.template.Configuration;
import freemarker.template.Template;

public class PullAndPublishTemperatureScenario extends Scenario {
	
	protected final Logger logger
		= LoggerFactory.getLogger( this.getClass() );
	
	@Resource
	protected Client client;
	
	@Resource
	protected MailMan mailMan;
	
	@Resource
	protected JavaMailSender javaMailSender;

	
	@Override
	public void execute( String... parameters ) {
		
		// PULL
		this.client.setUserName( parameters[ 0 ] );
		this.client.setPassWord( parameters[ 1 ] );
		
		LocationStatus status 
			= client.connect().account().locations().first().fetchStatus();
	
		Logger logger
			= LoggerFactory.getLogger( this.getClass() );
		
		logger.info( "status : [{}]", status );
		
		logger.info( "gateways : [{}]", status.gateWays() );
		
		Float currentTemperature
			= status.gateWays().get( 0 ).temperatureControlSystems().get( 0 ).getZones().get( 0 ).getTemperatureStatus().getTemperature();
		
		Double targetTemperature
			= status.gateWays().get( 0 ).temperatureControlSystems().get( 0 ).getZones().get( 0 ).getSetPointStatus().getTargetHeatTemperature();
		
		logger.info( "current temperature is [{}], target temperature is [{}]", currentTemperature, targetTemperature );
		
		ThermostatDTO dto
			= new ThermostatDTO().setCurrentTemperature( currentTemperature ).setTargetTemperature( toFloat( targetTemperature) );
		
		MimeMessage message
			= this.formatThermostateOffMessage( "/templates/thermostat-status.tmpl", dto, "sven.gladines@gmail.com", "katrien.belmans@gmail.com" );

		if ( message != null ) {
			
			mailMan.deliver( message );
			
		}
		
	}
	
	public PullAndPublishTemperatureScenario setUserName( String userName ) {
		this.client.setUserName( userName );
		return this;
	}
	
	public PullAndPublishTemperatureScenario setPassWord( String passWord ) {
		this.client.setPassWord( passWord );
		return this;
	}
	
	protected MimeMessage formatThermostateOffMessage( String templateID, ThermostatDTO status, String... recipients ) {
		
		MimeMessage message
			= null;
			
		Configuration cfg 
			= new Configuration();
		
		try {
			
			InputStream tis
				= this.getClass().getResourceAsStream( templateID );
			
			Template template 
				= new Template( templateID, new InputStreamReader( tis ), cfg );
			
			Map<String, Object> model = new HashMap<String, Object>();
					
			model.put( "status", status );
			model.put( "sx", ( status.getTargetTemperature() > status.getCurrentTemperature() ) ? "aan" : "af" );
			
			StringWriter bodyWriter 
				= new StringWriter();
			
			template.process( model , bodyWriter );
			
			bodyWriter.flush();
				
			message = this.javaMailSender.createMimeMessage( );
			// SGL| GAE does not support multipart_mode_mixed_related (default, when flag true is set)
			MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_NO, "utf-8");
				
			helper.setFrom( "sven.gladines@gmail.com" );
			helper.setTo( recipients );
			helper.setSubject( "Casa Zoncolan verwarmingsrapport" );
				
			String text
				= bodyWriter.toString();
				
			logger.info( "email text is [{}]", text );
				
			helper.setText(text, true);
			
		}
		catch( Exception e ) {
			logger.warn( "could not create e-mail", e );
			throw new RuntimeException( e );
		}
		
		return message;
    	
    }
	
	protected Float toFloat( Double d ) {
		
		try {
			return new DecimalFormat("#").parse( d.toString() ).floatValue();
		}
		catch( Exception e ) {
			throw new RuntimeException(e );
		}
		
	}
	
}

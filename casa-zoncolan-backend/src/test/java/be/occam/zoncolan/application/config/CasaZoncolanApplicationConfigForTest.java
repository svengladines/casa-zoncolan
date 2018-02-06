package be.occam.zoncolan.application.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import be.occam.utils.spring.configuration.ConfigurationProfiles;

@Configuration
public class CasaZoncolanApplicationConfigForDev {
	
	@Profile( { ConfigurationProfiles.DEV } )
	public static class DbConfigForDev {
		
	}
	
	@Configuration
	@Profile( { ConfigurationProfiles.DEV } ) 
	public static class ConfigForDevelopment {
		
	}
	
}
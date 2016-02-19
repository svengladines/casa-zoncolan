package be.occam.zoncolan.application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.google.apphosting.api.ApiProxy;

import be.occam.utils.spring.configuration.ConfigurationProfiles;
import be.occam.zoncolan.web.util.DataGuard;
import be.occam.zoncolan.web.util.DevGuard;

@Configuration
public class CasaZoncolanApplicationConfigForDev {
	
	@Profile( { ConfigurationProfiles.DEV } )
	public static class DbConfigForDev {
		
	}
	
	@Configuration
	@Profile( { ConfigurationProfiles.DEV } ) 
	public static class ConfigForDevelopment {
		
		@Bean
		DataGuard dataGuard( LocalServiceTestHelper helper ) {
			
			return new DevGuard( ApiProxy.getCurrentEnvironment() );
			
		}
		
	}
	
}
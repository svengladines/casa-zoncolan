package be.occam.zoncolan.application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import be.occam.utils.spring.configuration.ConfigurationProfiles;
import be.occam.zoncolan.domain.service.impl.ThermostatServiceForAcme;
import be.occam.zoncolan.heat.domain.service.ThermostatService;

@Configuration
public class CasaZoncolanApplicationConfigForTest {
	
	@Configuration
	@Profile( { ConfigurationProfiles.TEST } ) 
	public static class DomainConfigForTest {
		
		@Bean
		public ThermostatService thermostatService() {
			return new ThermostatServiceForAcme();
		}
		
		
	}
	
}
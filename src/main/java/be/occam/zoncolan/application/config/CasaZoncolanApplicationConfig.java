package be.occam.zoncolan.application.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import be.occam.utils.ftp.FTPClient;
import be.occam.utils.spring.configuration.ConfigurationProfiles;
import be.occam.zoncolan.domain.heat.Client;
import be.occam.zoncolan.domain.people.MailMan;
import be.occam.zoncolan.domain.scenario.PullAndPublishTemperatureScenario;
import be.occam.zoncolan.web.util.DataGuard;
import be.occam.zoncolan.web.util.NoopGuard;

@Configuration
@EnableTransactionManagement
public class CasaZoncolanApplicationConfig {
	
	final static Logger logger
		= LoggerFactory.getLogger( CasaZoncolanApplicationConfig.class );

	final static String BASE_PKG 
		= "be.occam.zoncolan";
	
	public final static String EMAIL_ADDRESS 
		= "sven.gladines@gmail.com";
	
	static class propertiesConfigurer {
		
		@Bean
		@Scope("singleton")
		public static PropertySourcesPlaceholderConfigurer propertiesConfig() {
			return new PropertySourcesPlaceholderConfigurer();
		}
		
	}
	
	@Configuration
	@Profile({ConfigurationProfiles.PRODUCTION,ConfigurationProfiles.DEV})
	// @Import( PirlewietAppEngineConfig.class )
	static class RepositoryConfigForProduction {
	
	}
	
	@Configuration
	public static class BeansConfigShared {
		
		@Bean
		public FTPClient ftpClient() {
			return new FTPClient( "94.198.164.46", "pirlewietbe", "d;giTaal.15");
		}
		
		@Bean
		public PullAndPublishTemperatureScenario pullAndPublishTemperatureScenario() {
			return new PullAndPublishTemperatureScenario();
		}
		
		@Bean
		Client client() {
			return new Client();
		}
		
		@Bean
		public MailMan mailMan() {
			return new MailMan();
		}
		
		@Bean
		public JavaMailSender javaMailSender () {
			
			JavaMailSenderImpl sender
				= new JavaMailSenderImpl();
			return sender;
			
		}
		
	}
	
	@Configuration
	@Profile({ConfigurationProfiles.PRODUCTION})
	static class DomainConfigForProduction {
		
		@Bean
		DataGuard dataGuard() {
			
			return new NoopGuard();
			
		}
	
	}
	
}
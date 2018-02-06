package be.occam.zoncolan.jtest.heat.scenario;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import be.occam.test.jtest.JTest;
import be.occam.utils.spring.configuration.ConfigurationProfiles;
import be.occam.zoncolan.application.boot.ZoncolanApplication;
import be.occam.zoncolan.heat.web.dto.ThermostatDTO;

/**
 * Reini updates his location twice, Krikke notices the location change
 * 
 * @author sven
 *
 */
@RunWith( SpringRunner.class )
@SpringBootTest(classes={ZoncolanApplication.class})
@ActiveProfiles(ConfigurationProfiles.TEST)
public class TestReadHeatingStatusBoot {
	
	protected String heatingURL;
	
	public TestReadHeatingStatusBoot() {
		
	}
	
	@Before
	public void setUp() {
		TestUtil.heatingURL ( new StringBuilder( "http://localhost:8080/api" ).append( "/heating" ).toString() );
	}
	
	@Test
	public void doesItSmoke() {
		
		ThermostatDTO thermostat
			= TestUtil.read( );
		
		assertNotNull( thermostat );
		
	}
	

}

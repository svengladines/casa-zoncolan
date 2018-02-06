package be.occam.zoncolan.jtest.heat.scenario;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import be.occam.test.jtest.JTest;
import be.occam.zoncolan.heat.web.dto.ThermostatDTO;

/**
 * Reini updates his location twice, Krikke notices the location change
 * 
 * @author sven
 *
 */
public class TestReadHeatingStatus extends JTest {
	
	protected String heatingURL;
	
	public TestReadHeatingStatus() {
		
		super("/casa-zoncolan");
		this.forcePort = 8086;
		
	}
	
	@Before
	public void setUp() {
		super.setUp();
		TestUtil.heatingURL ( new StringBuilder( super.baseAPIURL() ).append( "/heating" ).toString() );
	}
	
	@Test
	public void doesItSmoke() {
		
		ThermostatDTO thermostat
			= TestUtil.read( );
		
		assertNotNull( thermostat );
		
		logger.info( "target temperature is [{}]", thermostat.getCurrentTemperature() );
		
	}
	

}

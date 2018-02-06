package be.occam.zoncolan.jtest.heat.scenario;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import be.occam.test.jtest.JTest;
import be.occam.zoncolan.heat.domain.Status;
import be.occam.zoncolan.heat.domain.Thermostat;
import be.occam.zoncolan.heat.web.dto.ThermostatDTO;

public class TestTurnHeatingOff extends JTest {
	
	protected String heatingURL;
	
	public TestTurnHeatingOff() {
		
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
		
		Status newStatus
			= TestUtil.turnOff( );
		
		assertNotNull( newStatus );
		
		assertEquals( Status.off, newStatus );
		
		
		
	}
	

}

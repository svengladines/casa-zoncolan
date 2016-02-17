package be.occam.zoncolan.domain.scenario;

import javax.annotation.Resource;

import be.pirlewiet.registrations.web.util.DataGuard;

public abstract class Scenario {
	
	public abstract void execute( String... parameters );
	
}

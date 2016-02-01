package be.occam.zoncolan.domain.heat.honeywell;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import be.occam.zoncolan.domain.heat.Client;

public class TemperatureControlSystems {
	
	protected Client client;
	protected List<TemperatureControlSystem> temperatureControlSystems;
	
	public TemperatureControlSystems() {
		this.temperatureControlSystems 
			= new ArrayList<TemperatureControlSystem>();
	}
	
	public TemperatureControlSystems( List<TemperatureControlSystem> temperatureControlSystems ) {
		this();
		this.temperatureControlSystems.addAll( temperatureControlSystems );
	}
	
	public TemperatureControlSystems( Client client ) {
		this.temperatureControlSystems = new LinkedList<TemperatureControlSystem>();
	}
	
	public TemperatureControlSystems( Client client, TemperatureControlSystem... gateWays ) {
		
		this( client );
		
		for ( TemperatureControlSystem temperatureControlSystem : temperatureControlSystems ) {
			
			this.temperatureControlSystems.add( temperatureControlSystem.setClient( client ) );
			
		}
		
	}
	
	public TemperatureControlSystem first() {
		
		return this.temperatureControlSystems.get( 0 );
		
		
	}
	
	public List<TemperatureControlSystem> list() {
		
		return this.temperatureControlSystems;
		
		
	}
	

}

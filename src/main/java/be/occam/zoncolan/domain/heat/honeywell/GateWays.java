package be.occam.zoncolan.domain.heat.honeywell;

import java.util.LinkedList;
import java.util.List;

import be.occam.zoncolan.domain.heat.Client;

public class GateWays {
	
	protected Client client;
	protected List<GateWay> gateWays;
	
	public GateWays( Client client ) {
		this.gateWays = new LinkedList<GateWay>();
	}
	
	public GateWays( Client client, GateWay... gateWays ) {
		
		this( client );
		
		for ( GateWay gateWay : gateWays ) {
			
			this.gateWays.add( gateWay.setClient( client ) );
			
		}
		
	}
	
	public GateWay first() {
		
		return this.gateWays.get( 0 );
		
		
	}
	
	public List<GateWay> list() {
		
		return this.gateWays;
		
		
	}

}

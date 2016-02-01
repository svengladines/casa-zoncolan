package be.occam.zoncolan.domain.heat.honeywell;

import java.util.LinkedList;
import java.util.List;

import be.occam.zoncolan.domain.heat.Client;

public class Locations {
	
	protected Client client;
	protected List<Location> locations;
	
	public Locations( Client client ) {
		this.locations = new LinkedList<Location>();
	}
	
	public Locations( Client client, Location... locations ) {
		
		this( client );
		
		for ( Location location : locations ) {
			
			this.locations.add( location.setClient( client ) );
			
		}
		
	}
	
	public Location first() {
		
		return this.locations.get( 0 );
		
		
	}
	

}

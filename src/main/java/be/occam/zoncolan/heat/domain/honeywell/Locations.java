package be.occam.zoncolan.heat.domain.honeywell;

import java.util.LinkedList;
import java.util.List;


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

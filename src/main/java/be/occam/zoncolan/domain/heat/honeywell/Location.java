package be.occam.zoncolan.domain.heat.honeywell;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import be.occam.zoncolan.domain.heat.Client;

/**
 * @author sven
 *
 */
public class Location {
	
	protected final Logger logger
		= LoggerFactory.getLogger( this.getClass() );
	
	protected Client client;
	protected List<GateWay> gateWays;
	protected LocationInfo locationInfo;
	
	@JsonIgnore
	protected LocationStatus status;
	
	public Location() {
		
	}
	
	public Client getClient() {
		return client;
	}

	public Location setClient(Client client) {
		this.client = client;
		return this;
	}
	
	@JsonProperty(value="gateways")
	public List<GateWay> gateWays() {
		return this.gateWays;
	}

	@JsonProperty(value="locationInfo")
	public LocationInfo getLocationInfo() {
		return locationInfo;
	}

	public LocationStatus getStatus() {
		return this.client.getLocationStatus( this.locationInfo.getLocationId() );
	}
	
}

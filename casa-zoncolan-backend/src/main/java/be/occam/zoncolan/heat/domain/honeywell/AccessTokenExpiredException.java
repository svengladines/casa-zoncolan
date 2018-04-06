package be.occam.zoncolan.heat.domain.honeywell;

public class AccessTokenExpiredException extends RuntimeException {
	
	public AccessTokenExpiredException(String message ) {
		super( message );
	}

}

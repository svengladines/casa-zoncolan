package be.occam.zoncolan.heat.domain.honeywell;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * {scope=EMEA-V1-Basic EMEA-V1-Anonymous},
 * {access_token=UmxDgJUcS8ex2nrFs7Sv4-mtV1NrZv0J8VoJ9GOld-sgGLLwWylQntJLWfhIYme_VVPljLQYkAXzN5c8Ld_nQKUkAfjoMALMJQzu8fhkJQ_ltE4gIcEA9Uf8NtqvSjf_Fi0wgVG-9hA5VrlEP7YEN2HVK6VtKpjlZG20tKTVRd-SGJLobLL70jHz3pXxVQT_eMPmd64jCVNEfNSBUUUqJTzMIcLAha8yE112kBSl9q9zTKcJ3GEh5LGz1SrOOOBDD_MPvwpz9y6dKAorpy91Be0JL7wQdW4k1VlexcZCnJ_jXwvvvUfMGeNARz-jP5BejIMhuJ23N_XsR2x9xayNNwruVpW6DFUvSJ-Qez_KINhTBbVOSDCDC_x0oRGbJuJQRuQK1-wawOXmFczi89P3NrQwseCWLPdHpix-YzZeSR6qk1cSaZwXU-BGt_jKSupXJEUg1kBQ6wFse02lebg8XPhCY3-r6bpqFhUJXInz9gYCqRqBn03s_S0WW7ee4C7hTkue6Muk8GPKbWJfX-4X_Qq5deM
 *, expires_in=3599}
 * @author sven
 *
 */
public class AccessToken {
	
	protected String scope;
	protected String accessToken;
	protected int expiresIn;
	protected String tokenType;
	protected String refreshToken;

	@JsonProperty(value="access_token")
	public String getAccessToken() {
		return this.accessToken;
	}
	
	@JsonProperty(value="scope")
	public String getScope() {
		return this.scope;
	} 
	
	@JsonProperty(value="expires_in")
	public int expiresIn() {
		return this.expiresIn;
	}

	@JsonProperty(value="token_type")
	public String getTokenType() {
		return tokenType;
	}

	@JsonProperty(value="refresh_token")
	public String getRefreshToken() {
		return refreshToken;
	}
	
	@Override
	public String toString() {
		
		return new StringBuilder( "{accessToken=" ).append( this.accessToken ).append(",expiresIn=" ).append( this.expiresIn ).append( "}" ).toString();
		
	}
	
	

}

package be.occam.zoncolan.domain.heat.honeywell;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * "userId": "1175639",
  "username": "sven.gladines@telenet.be",
  "firstname": "Sven",
  "lastname": "Gladines",
  "streetAddress": "Vertrijksestraat",
  "city": "Neervelp",
  "postcode": "3370",
  "country": "Belgium",
  "language": "nlNL"
 * @author sven
 *
 */
public class Schedule {
	
	protected String userId;
	protected String userName;
	protected String firstName;
	protected String lastName;
	protected String streetAddress;
	protected String city;
	protected String postCode;
	protected String country;
	protected String language;

	@JsonProperty(value="userId")
	public String getUserId() {
		return this.userId;
	}

	@JsonProperty(value="username")
	public String getUserName() {
		return userName;
	}

	@JsonProperty(value="firstname")
	public String getFirstName() {
		return firstName;
	}

	@JsonProperty(value="lastname")
	public String getLastName() {
		return lastName;
	}

	@JsonProperty(value="streetAddress")
	public String getStreetAddress() {
		return streetAddress;
	}

	@JsonProperty(value="city")
	public String getCity() {
		return city;
	}

	@JsonProperty(value="postcode")
	public String getPostCode() {
		return postCode;
	}

	@JsonProperty(value="country")
	public String getCountry() {
		return country;
	}

	@JsonProperty(value="language")
	public String getLanguage() {
		return language;
	}
	
	
	

}

package api.booking;

import static io.restassured.RestAssured.given;

import api.BaseAPI;
import io.restassured.http.ContentType;

/**
* 
* Implementation of UpdateBooking PUT Service
*/

public class DeleteBooking extends BaseAPI {	
	String apiPath = "/booking";
	int bookingId;
	String authToken;

	public DeleteBooking(String baseURI) {
		super(baseURI);
	}

	public void setBookingId(int bookingId) {
		this.apiPath = String.format("%s/%s", this.apiPath, bookingId);
	}
	
	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

	@Override
	protected void createRequest() {
		requestSpecBuilder.setBaseUri(baseURI);
		requestSpecBuilder.setBasePath(apiPath);
		requestSpecBuilder.addParam("id", bookingId); //setting of path parameter is not working, so apiPath has formed using string.format()
		requestSpecBuilder.setContentType(ContentType.JSON);
		requestSpecBuilder.addCookie("token", this.authToken);
		requestSpecification = requestSpecBuilder.build();
	}

	@Override
	protected void executeRequest() {
		apiResponse = given().spec(requestSpecification).delete();
	}

	@Override
	protected void validateResponse() {
		responseSpecBuilder.expectStatusCode(expectedStatusCode);
		responseSpecification = responseSpecBuilder.build();
		apiResponse.then().spec(responseSpecification);
	}
}
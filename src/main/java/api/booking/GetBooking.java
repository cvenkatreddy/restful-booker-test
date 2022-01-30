package api.booking;

import static io.restassured.RestAssured.given;

import api.BaseAPI;

/**
* 
* Implementation of GetBooking GET Service
* 
*/

public class GetBooking extends BaseAPI {
	String apiPath = "/booking";
	int bookingId;

	public GetBooking(String baseURI) {
		super(baseURI);
	}

	public void setBookingId(int bookingId) {
		this.apiPath = String.format("%s/%s", this.apiPath, bookingId);
	}

	@Override
	protected void createRequest() {
		requestSpecBuilder.setBaseUri(baseURI);
		requestSpecBuilder.setBasePath(apiPath);
		requestSpecBuilder.addParam("id", bookingId); //setting of path parameter is not working, so apiPath has formed using string.format()
		requestSpecification = requestSpecBuilder.build();
	}

	@Override
	protected void executeRequest() {
		apiResponse = given().spec(requestSpecification).get();
	}

	@Override
	protected void validateResponse() {
		responseSpecBuilder.expectStatusCode(expectedStatusCode);
		responseSpecification = responseSpecBuilder.build();
		apiResponse.then().spec(responseSpecification);
	}
}
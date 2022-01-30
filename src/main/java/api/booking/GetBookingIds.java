package api.booking;

import static io.restassured.RestAssured.given;

import api.BaseAPI;

/**
* 
* Implementation of GetBooking GET Service
*/

public class GetBookingIds extends BaseAPI {
	String apiPath = "/booking";
	int bookingId;

	public GetBookingIds(String baseURI) {
		super(baseURI);
	}

	@Override
	protected void createRequest() {
		requestSpecBuilder.setBaseUri(baseURI);
		requestSpecBuilder.setBasePath(apiPath);
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
package api.booking;

import static io.restassured.RestAssured.given;

import org.json.JSONObject;

import api.BaseAPI;
import env.ApplicationProperties;
import env.Environment;
import io.restassured.http.ContentType;
import pojo.Booking;

/**
* 
* Implementation of CreateBooking POST Service
* 
*/

public class CreateBooking extends BaseAPI {
	ApplicationProperties appProps = Environment.INSTANCE.getApplicationProperties();
	String apiPath = "/booking";
	Booking requestBody;

	public CreateBooking(String baseURI) {
		super(baseURI);
	}
	
	public void setRequestBody(Booking booking) {
		this.requestBody = booking;
	}
	
	@Override
	protected void createRequest() {
		JSONObject requestBody = new JSONObject(this.requestBody);
		
		requestSpecBuilder.setBaseUri(baseURI);
		requestSpecBuilder.setBasePath(apiPath);
		requestSpecBuilder.setContentType(ContentType.JSON);
		requestSpecBuilder.setBody(requestBody.toString());
		requestSpecification = requestSpecBuilder.build();
	}

	@Override
	protected void executeRequest() {
		apiResponse = given().spec(requestSpecification).post();
	}

	@Override
	protected void validateResponse() {
		responseSpecBuilder.expectStatusCode(expectedStatusCode);
		responseSpecification = responseSpecBuilder.build();
	}
}

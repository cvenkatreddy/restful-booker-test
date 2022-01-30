package api.auth;

import static io.restassured.RestAssured.given;

import org.json.JSONObject;

import api.BaseAPI;
import env.ApplicationProperties;
import env.Environment;
import io.restassured.http.ContentType;

/**
* 
* Implementation of CreateToken POST Service
* 
*/
public class CreateToken extends BaseAPI {
	ApplicationProperties appProps = Environment.INSTANCE.getApplicationProperties();
	String apiPath = "/auth";

	public CreateToken(String baseURI) {
		super(baseURI);
	}

	@Override
	protected void createRequest() {
		JSONObject requestParams = new JSONObject();
		requestParams.put("username", appProps.getUsername()); 
		requestParams.put("password", appProps.getPassword());

		requestSpecBuilder.setBaseUri(baseURI);
		requestSpecBuilder.setBasePath(apiPath);
		requestSpecBuilder.setContentType(ContentType.JSON);
		requestSpecBuilder.setBody(requestParams.toString());
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
		apiResponse.then().spec(responseSpecification);
	}
}

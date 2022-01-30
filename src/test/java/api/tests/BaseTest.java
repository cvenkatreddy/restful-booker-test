package api.tests;

import org.testng.annotations.BeforeSuite;

import api.auth.CreateToken;
import env.ApplicationProperties;
import env.Environment;
import pojo.Token;

public class BaseTest {
	ApplicationProperties appProps = Environment.INSTANCE.getApplicationProperties();
	Token token;

	@BeforeSuite()
	public void setAuthToken() throws Exception {
		CreateToken postAuth = new CreateToken(appProps.getBaseURL());
		postAuth.setExpectedStatusCode(200);
		postAuth.perform();
		this.token = postAuth.getAPIResponseAsPOJO(Token.class);
	}
}

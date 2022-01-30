package api.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.booking.CreateBooking;
import api.booking.DeleteBooking;
import api.booking.GetBooking;
import api.booking.UpdateBooking;
import env.ApplicationProperties;
import env.Environment;
import pojo.Booking;
import pojo.BookingDetail;


public class BookingTest extends BaseTest {
	ApplicationProperties appProps = Environment.INSTANCE.getApplicationProperties();
	Booking requestBody;
	
	@Test(description = "CreateBooking Service Test")
	public void createBooking() throws Exception {
		requestBody = new Booking();
		CreateBooking createBookingRequest = new CreateBooking(appProps.getBaseURL());
		createBookingRequest.setRequestBody(requestBody);
		createBookingRequest.setExpectedStatusCode(200);
		createBookingRequest.perform();

		BookingDetail createBookingResponse = createBookingRequest.getAPIResponseAsPOJO(BookingDetail.class);
		Assert.assertEquals(requestBody, createBookingResponse.getBooking());
	}

	@Test(description = "GetBooking Service Test")
	public void getBooking() throws Exception {
		Booking requestBody = new Booking();
		CreateBooking createBookingRequest = new CreateBooking(appProps.getBaseURL());
		createBookingRequest.setExpectedStatusCode(200);
		createBookingRequest.setRequestBody(requestBody);
		createBookingRequest.perform();

		BookingDetail createBookingResponse = createBookingRequest.getAPIResponseAsPOJO(BookingDetail.class);
		Assert.assertEquals(requestBody, createBookingResponse.getBooking());

		GetBooking getBookingRequest = new GetBooking(appProps.getBaseURL());
		getBookingRequest.setBookingId(createBookingResponse.getBookingid());
		getBookingRequest.setExpectedStatusCode(200);
		getBookingRequest.perform();

		Booking getBookingDetails = getBookingRequest.getAPIResponseAsPOJO(Booking.class);
		Assert.assertEquals(requestBody, getBookingDetails);

	}

	@Test(description = "UpdateBooking Service Test")
	public void updateBooking() throws Exception {
		requestBody = new Booking();
		CreateBooking createBookingRequest = new CreateBooking(appProps.getBaseURL());
		createBookingRequest.setExpectedStatusCode(200);
		createBookingRequest.setRequestBody(requestBody);
		createBookingRequest.perform();

		BookingDetail createBookingResponse = createBookingRequest.getAPIResponseAsPOJO(BookingDetail.class);
		Assert.assertEquals(requestBody, createBookingResponse.getBooking());

		UpdateBooking updateBookingRequest = new UpdateBooking(appProps.getBaseURL());
		Booking newReqquestBody = new Booking();
		updateBookingRequest.setBookingId(createBookingResponse.getBookingid());
		updateBookingRequest.setAuthToken(token.getToken());
		updateBookingRequest.setRequestBody(newReqquestBody);
		updateBookingRequest.setExpectedStatusCode(200);
		updateBookingRequest.perform();

		Booking updateBookingResponse = updateBookingRequest.getAPIResponseAsPOJO(Booking.class);

		GetBooking getBookingRequest = new GetBooking(appProps.getBaseURL());
		getBookingRequest.setBookingId(createBookingResponse.getBookingid());
		getBookingRequest.setExpectedStatusCode(200);
		getBookingRequest.perform();

		Booking getBookingResponse = getBookingRequest.getAPIResponseAsPOJO(Booking.class);
		Assert.assertEquals(updateBookingResponse, getBookingResponse);

	}

	@Test(description = "DeleteBooking Service Test")
	public void deleteBooking() throws Exception {
		Booking requestBody = new Booking();
		CreateBooking createBookingRequest = new CreateBooking(appProps.getBaseURL());
		createBookingRequest.setExpectedStatusCode(200);
		createBookingRequest.setRequestBody(requestBody);
		createBookingRequest.perform();

		BookingDetail createBookingResponse = createBookingRequest.getAPIResponseAsPOJO(BookingDetail.class);
		Assert.assertEquals(requestBody, createBookingResponse.getBooking());
		

		DeleteBooking deleteBooking = new DeleteBooking(appProps.getBaseURL());
		deleteBooking.setAuthToken(token.getToken());
		deleteBooking.setBookingId(createBookingResponse.getBookingid());
		deleteBooking.setExpectedStatusCode(201); // We can't use 201 status code for delete, either 404 or 200 is the standard code for delete
		deleteBooking.perform();

		GetBooking getBookingRequest = new GetBooking(appProps.getBaseURL());
		getBookingRequest.setBookingId(createBookingResponse.getBookingid());
		getBookingRequest.setExpectedStatusCode(404);
		getBookingRequest.perform();
	}
}
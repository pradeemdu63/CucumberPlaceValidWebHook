package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.TestData;
import resources.Utils;
import resources.httpResources;

public class stepDefs extends Utils {

	RequestSpecification placereq;
	ResponseSpecification respSpec;
	Response response;
	TestData data = new TestData();
	String jsonString;
	httpResources httpres;
	public static String placeId;

	@Given("Add place payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException {

		respSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		placereq = given().spec(requestSpec()) // since inherited the utils class
				.body(data.addPlace(name, language, address));

	}

	@When("User calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String method) {

		httpres = httpResources.valueOf(resource);
		if (method.equalsIgnoreCase("POST")) {
			response = placereq.when().post(httpres.getResource());
		} else if (method.equalsIgnoreCase("GET")) {
			response = placereq.when().get(httpres.getResource());
		} 
	}

	@Then("the API call is success with status code {int}")
	public void the_api_call_is_success_with_status_code(Integer int1) {

		assertEquals(200, response.getStatusCode());
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String expectedValue) {

		jsonString = rawJson(response, keyValue);
		assertEquals(jsonString, expectedValue);
	}

	@Then("verify place_id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String name, String resource) {

		placeId = rawJson(response, "place_id");
		placereq = placereq.queryParam("place_id", placeId);
		user_calls_with_http_request(resource, "GET");

		String actualName = rawJson(response, "name");
		assertEquals(actualName, name);

	}

	@Given("Deleteplace payload")
	public void deleteplace_payload() throws IOException {
		
		placereq = given().spec(requestSpec())
						  .body(data.deletePayload(placeId));
		
	}

}

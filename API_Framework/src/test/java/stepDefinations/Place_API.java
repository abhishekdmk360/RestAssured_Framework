package stepDefinations;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.Location;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class Place_API extends Utils {
	
	RequestSpecification request;
	ResponseSpecification responseSpec;
	Response response;
	static String placeID;
	TestDataBuild data = new TestDataBuild();
	
	@Given("User has Add Place Payload with {string} {string} {string}")
	public void addPlacePayload(String name, String language, String address) throws IOException
	{
		request = given().spec(requestSpecification()).body(data.addPlacePayload(name, language, address));
	}
	
	@When("User calls {string} with {string} http request")
	public void callAPI(String resource, String request_type)
	{
		APIResources resourceAPI = APIResources.valueOf(resource);
		//responseSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		if(request_type.equalsIgnoreCase("POST"))
			response =	request.when().post(resourceAPI.getResource()).then().extract().response();
		else if(request_type.equalsIgnoreCase("GET"))
			response =	request.when().get(resourceAPI.getResource()).then().extract().response();
	}
	
	@Then("API call is success with status code {int}")
	public void checkStatusCode(int expectedCode)
	{
		assertEquals(expectedCode, response.getStatusCode());
	}
	
	@Then("{string} in response body is {string}")
	public void validateResponseKeyValue(String key, String expectedValue)
	{
		assertEquals(expectedValue, getJsonPath_WithKey(response, key));
		
	}
	
	@Then("Verify PlaceId created maps to {string} using {string}")
	public void verifyPlaceID_Name(String expectedName, String resource) throws IOException
	{
		placeID = getJsonPath_WithKey(response, "place_id");
		request = given().spec(requestSpecification()).queryParam("place_id", placeID);
		APIResources resourceAPI = APIResources.valueOf(resource);
		callAPI(resource, "GET");
		String actualName = getJsonPath_WithKey(response, "name");
		assertEquals(expectedName, actualName);
	}
	
	@Given("User has Delete Place Payload")
	public void deletePlacePayload() throws IOException
	{
		request = given().spec(requestSpecification()).body(data.deletePlacePayload(placeID));
	}

}

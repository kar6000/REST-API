package RestAssured.RestAssured;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class WeatherGetTests {

	@Test
	public void getWeatherDetailsTest() {
		// 1. define the base URL
		// http://restapi.demoqa.com/utilities/weather/city
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";

		// 2. Define the http request
		RequestSpecification httpRequest = RestAssured.given();

		// 3. execute the request
		Response response = httpRequest.request(Method.GET, "/Delhi");
		System.out.println(response);

		// 4.Get the Response Body(convert the response to JSON String)
		String responseBody = response.getBody().asString();
		System.out.println(responseBody);

		// validate the key or value
		Assert.assertEquals(responseBody.contains("Delhi"), true);

		// 5. Get the status code and validate it
		int statusCode = response.getStatusCode();
		System.out.println("Status code is: " + statusCode);

		// 6. validate the Response code
		Assert.assertEquals(statusCode, 200);

		// 7. print status line
		String statusLine = response.getStatusLine();
		System.out.println("Status line is: " + statusLine);

		// 8. Get response headers(Headers are key:value pair)
		Headers headers = response.getHeaders();
		System.out.println("Headers are: \n" + headers);

		// 9.Get the key value using JSON path
		JsonPath jsonPathvalue = response.jsonPath();

		String city = jsonPathvalue.get("City");
		System.out.println("The value of city is: " + city);

		String temp = jsonPathvalue.get("Temperature");
		System.out.println("The value of city is: " + temp);

		String humidity = jsonPathvalue.get("Humidity");
		System.out.println("The value of city is: " + humidity);

		String WeatherDescriptioncity = jsonPathvalue.get("WeatherDescription");
		System.out.println("The value of city is: " + WeatherDescriptioncity);

		String WindSpeed = jsonPathvalue.get("WindSpeed");
		System.out.println("The value of city is: " + WindSpeed);

		String WindDirectionDegree = jsonPathvalue.get("WindDirectionDegree");
		System.out.println("The value of city is: " + WindDirectionDegree);

	}

	@Test
	public void getWeatherIncorrectCity() {
		// 1. define the base URL
		// http://restapi.demoqa.com/utilities/weather/city
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";

		// 2. Define the http request
		RequestSpecification httpRequest = RestAssured.given();

		// 3. execute the request
		Response response = httpRequest.request(Method.GET, "/test123");
		System.out.println(response);

		// 4.Get the Response Body(convert the response to JSON String)
		String responseBody = response.getBody().asString();
		System.out.println(responseBody);

		// validate the key or value
		Assert.assertEquals(responseBody.contains("internal error"), true);

		// 5. Get the status code and validate it
		int statusCode = response.getStatusCode();
		System.out.println("Status code is: " + statusCode);

		// 6. validate the Response code
		Assert.assertEquals(statusCode, 400);

	}

}

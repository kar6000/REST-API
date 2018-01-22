package RestAssured.RestAssured;

import org.json.simple.JSONObject;
import org.junit.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestCustomerPostcall2 {
	@Test
	public void getWeatherDetailsTest() {
		// 1. define the base URL
		RestAssured.baseURI = "http://restapi.demoqa.com/customer/";

		// 2.define http request
		RequestSpecification httpRequest = RestAssured.given();

		for (int i = 0; i < 10; i++) {
			// 3.Create a JSON object with all the fields
			JSONObject requestJson = new JSONObject();

			String fName = "ips1" + i;
			String lName = "kar3" + i;
			String uName = "ikar3" + i;
			String pWord = "12345" + i;
			String eMail = "test1567" + i;
			// System.out.println(fName);

			requestJson.put("FirstName", fName);
			requestJson.put("LastName", lName);
			requestJson.put("UserName", uName);
			requestJson.put("Password", pWord);
			requestJson.put("Email", eMail);

			// 4. add header
			httpRequest.header("Content-Type", "application/json");

			// 5.add the JSON payload to the body of request
			httpRequest.body(requestJson.toJSONString());

			// 6. Post the request and get the response
			Response response = httpRequest.post("/register");

			// 7.get response body and print to console
			String responseBody = response.getBody().asString();
			System.out.println("Response body is: " + responseBody);
			
			//8. Get status code and validate it
			int statusCode =response.statusCode();
			System.out.println("The status code is: "+statusCode);
			//Validate the Status code
			//Assert.assertEquals(statusCode, 200);	
			
			//9. Get status line			
			String statusLine = response.getStatusLine();
			System.out.println("The status line is: "+statusLine);
			
			//10. Get the Headers
			//Headers headers =response.getHeaders();
			//System.out.println(headers);
			
			System.out.println("Headers are: \n "+response.getHeaders());
			
			String contentType=response.getHeader("Content-Type");
			System.out.println("The value of content type header is :"+contentType);
			
			String contentLength=response.getHeader("Content-Length");
			System.out.println("The value of Content-length is: "+contentLength);
		}

	}

}

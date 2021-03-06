package TestClasses;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONStreamAware;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;
import org.junit.Ignore;
import org.junit.Test;

import Links.FilesPaths;
import Links.URLs;
import Utils.JSONUtils;
import enums.HTTPMethod;
import enums.HTTPRequestsContentTypes;
import requestHandling.HandleRequestReponse;
import requestHandling.RestClientHandler;


public class TestGetRestAPI {

	@Ignore
	@Test
	public void TestReqRes()  {
		try
		{
		// 1. connect to server and open connection (get HttpURLConnection object)
		HttpURLConnection connection = RestClientHandler.connectServer(URLs.ReqResAPI, HTTPMethod.GET,
				HTTPRequestsContentTypes.JSON);
		
		//2. Send Post Request
		RestClientHandler.sendGet(connection, "", HTTPRequestsContentTypes.JSON);
		assertTrue("unable to connect to webservice", connection.getResponseCode() == 200);
		// 3. reading response using input stream
		String response = RestClientHandler.readResponse(connection);
		System.out.println(response);
		assertTrue("Data is empty", !response.equals(""));
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}

	}
	
	@Ignore
	@Test
	public void TestRestClientHandler() throws IOException {
		// 1. connect to server and open connection (get HttpURLConnection object)
		HttpURLConnection connection = RestClientHandler.connectServer(URLs.BOOKING, HTTPMethod.GET,
				HTTPRequestsContentTypes.JSON);
		// 2. validate if the connection is successfully openned
		System.out.println("connection.getResponseCode() : " + connection.getResponseCode());
		assertTrue("unable to connect to webservice", connection.getResponseCode() == 200);
		// 3. reading response using input stream
		String response = RestClientHandler.readResponse(connection);
		System.out.println(response);
		assertTrue("Data is empty", !response.equals(""));

	}
	@Ignore
	@Test
	public void testCreatBooking() throws Exception {
		// 1. Open Connection --- HttpURLConnection
		HttpURLConnection connection = RestClientHandler.connectServer(URLs.BOOKING, HTTPMethod.POST,
				HTTPRequestsContentTypes.JSON);
		// 2. Prepare Json Object
		String resquestJSONObject = JSONUtils.readJSONObjectFromFile(FilesPaths.NewBookingJSONFile);
		// 3. Post Request
		RestClientHandler.sendPost(connection, resquestJSONObject, HTTPRequestsContentTypes.JSON);
		// 4. Reading Response
		String response = RestClientHandler.readResponse(connection);
		System.out.println(response);
		// 5. convert String to JSON
		JSONObject jsonObject = (JSONObject) JSONUtils.convertStringToJSON(response);
		String bookingid = jsonObject.get("bookingid").toString();
		String additionalneeds = ((JSONObject) jsonObject.get("booking")).get("additionalneeds").toString();

		String checkin = ((JSONObject) (((JSONObject) jsonObject.get("booking")).get("bookingdates"))).get("checkin")
				.toString();
		System.out.println("bookingid=" + bookingid);
		System.out.println("additionalneeds=" + additionalneeds);
		System.out.println("checkin=" + checkin);

		// 6. validation data jsonObject==response
		// (https://restful-booker.herokuapp.com/booking/id)
	}

	
	
	
	
	
	
	@Test
	public void testGetExistingUser() throws Exception {
		// 1. Open Connection --- HttpURLConnection
		String url = URLs.ReqResAPI+"/2";
		HttpURLConnection connection = RestClientHandler.connectServer(url, HTTPMethod.GET,
				HTTPRequestsContentTypes.JSON);
		// 2. validate if the connection is successfully openned
		System.out.println("connection.getResponseCode() : " + connection.getResponseCode());
		System.out.println("connection.getResponseCode() : " + connection.getResponseCode());
		assertTrue("The user exists", connection.getResponseCode() == 200);
		// 3. reading response using input stream
		String response = RestClientHandler.readResponse(connection);
		System.out.println(response);
		assertTrue("Data is empty", !response.equals(""));
	}

	@Test
	public void testGetNotExistingUser() throws Exception {
		// 1. Open Connection --- HttpURLConnection
		String url = URLs.ReqResAPI+"/10";
		HttpURLConnection connection = RestClientHandler.connectServer(url, HTTPMethod.GET,
				HTTPRequestsContentTypes.JSON);
		// 2. validate if the connection is successfully openned
		System.out.println("connection.getResponseCode() : " + connection.getResponseCode());
		assertTrue( connection.getResponseCode() == 404);
		
	}
	
	@Test
	public void testGetInvalidInput() throws Exception {
		// 1. Open Connection --- HttpURLConnection
		String url = URLs.ReqResAPI+"/!#1";
		HttpURLConnection connection = RestClientHandler.connectServer(url, HTTPMethod.GET,
				HTTPRequestsContentTypes.JSON);
		// 2. validate if the connection is successfully openned
		System.out.println("connection.getResponseCode() : " + connection.getResponseCode());
		assertTrue("UNable to connect the webservice", connection.getResponseCode() == 200);
		
	}

//	@Test
//	public void TestPostData() throws Exception
//	{
//		//1. Get HttpURLConnection
//		HttpURLConnection connection= RestClientHandler.connectServer(URLs.BOOKING, HTTPMethod.POST , HTTPRequestsContentTypes.JSON);
//		
//		//2. Read JSON file		
//		String json=JSONUtils.readJSONObjectFromFile(FilesPaths.CreatBookingJSONFile);
//		
//		//3.sent post request
//		RestClientHandler.sendPost(connection, json, HTTPRequestsContentTypes.JSON);
//		
//		//4. Read Response
//		String response=RestClientHandler.readResponse(connection);
//		System.out.println(response);
//		//5. convert string to json object 
//		JSONObject obj= (JSONObject) JSONUtils.convertStringToJSON(response);
//		System.out.println(obj.get("bookingid"));
//	}
	
	

}

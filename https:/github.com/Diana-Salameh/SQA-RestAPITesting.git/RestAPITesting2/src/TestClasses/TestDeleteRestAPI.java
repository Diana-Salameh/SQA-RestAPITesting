package TestClasses;

import static org.junit.Assert.*;

import java.net.HttpURLConnection;

import org.junit.Test;

import Links.URLs;
import enums.HTTPMethod;
import enums.HTTPRequestsContentTypes;
import requestHandling.RestClientHandler;

public class TestDeleteRestAPI {

	@Test
	public void testDeleteUser() throws Exception {
		// 1. connect to server and open connection (get HttpURLConnection object)
		String Url = URLs.ReqResAPI + "1";
		System.out.println(Url);
		HttpURLConnection connection = RestClientHandler.connectServer(Url, HTTPMethod.DELETE,
				HTTPRequestsContentTypes.JSON);
		// 2. Send DELETE request
		RestClientHandler.sendDelete(connection, "", HTTPRequestsContentTypes.JSON);
		// 3. validate if the connection is successfully openned
		System.out.println("connection.getResponseCode() : " + connection.getResponseCode());
		assertTrue("unable to connect to the webservice", connection.getResponseCode() == 201);
		// 4. reading response using input stream
		String response = RestClientHandler.readResponse(connection);
		System.out.println(response);
	
	}
	public void testDeleteNotExistingUser() throws Exception {
		// 1. connect to server and open connection (get HttpURLConnection object)
		String Url = URLs.ReqResAPI + "7";
		System.out.println(Url);
		HttpURLConnection connection = RestClientHandler.connectServer(Url, HTTPMethod.DELETE,
				HTTPRequestsContentTypes.JSON);
		// 2. Send DELETE request
		RestClientHandler.sendDelete(connection, "", HTTPRequestsContentTypes.JSON);
		// 3. validate if the connection is successfully openned
		System.out.println("connection.getResponseCode() : " + connection.getResponseCode());
		assertTrue("unable to connect to the webservice", connection.getResponseCode() == 201);
		// 4. reading response using input stream
		String response = RestClientHandler.readResponse(connection);
		System.out.println(response);
	
	}

}

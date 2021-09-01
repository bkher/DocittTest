package com.qa.test;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.BaseClass;
import com.qa.utils.commonutiles;

import qa.client.restClient;

public class Weathertest extends BaseClass {

	BaseClass base;
	String envUrl;
	restClient restmethods;
	CloseableHttpResponse closeableHttpResponse;
	commonutiles utilitobj;
	String station1ID;
	String station2ID;
	String DeleteStation1ID;
	String DeleteStation2ID;

	@BeforeMethod
	public void setup() {
		base = new BaseClass();
		envUrl = prop.getProperty("url");

	}

	@Test(priority = 1, enabled = true)

	public void attemptToRegisterWeatherStationWithoutAPIkey() throws ClientProtocolException, IOException {

		String url = envUrl + "/data/3.0/stations";
		restmethods = new restClient();

		HashMap<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Authorization", "0b2d252f7ff55e253f0f8496f8d8ae54");

		String payload = "{\r\n" + "  \"external_id\": \"SF_TEST001\",\r\n"
				+ "  \"name\": \"San Francisco Test Station\",\r\n" + "  \"latitude\": 37.76,\r\n"
				+ "  \"longitude\": -122.43,\r\n" + "  \"altitude\": 150\r\n" + "}";

		closeableHttpResponse = restmethods.post(url, payload, headers);
		int ststuscode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("attemptToRegisterWeatherStationWithoutAPIkey --- " + ststuscode);
//		 assertEquals(ststuscode, STATUS_RESPONSE_CODE_401, "status code is not 401 No Content");

		String Responsestring = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
		JSONObject Responsejson = new JSONObject(Responsestring);
		System.out.println("Response json from API..." + Responsejson);

		String message = utilitobj.getvlueByJpath(Responsejson, "message");
		System.out.println("Error message is " + message);
		assertEquals(message, "Invalid API key. Please see http://openweathermap.org/faq#error401 for more info.",
				"Getting different messsage");

	}

	@Test(priority = 2, enabled = true)

	public void Registerstation1() throws ClientProtocolException, IOException {

		String url = envUrl + "/data/3.0/stations?appid=0b2d252f7ff55e253f0f8496f8d8ae54";
		restmethods = new restClient();

		HashMap<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		String payload = "{\r\n" + "  \"external_id\": \"DEMO_TEST001\",\r\n"
				+ "  \"name\" :\"interview Station 1\",\r\n" + "  \"latitude\": 33.33,\r\n"
				+ "  \"longitude\": -111.43,\r\n" + "   \"altitude\": 444 \r\n" + "}";

		closeableHttpResponse = restmethods.post(url, payload, headers);
		int ststuscode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status code of Registerstation1 --- " + ststuscode);
//		 assertEquals(ststuscode, STATUS_RESPONSE_CODE_201, "status code is not 201 No Content");
		String Responsestring = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
		JSONObject Responsejson = new JSONObject(Responsestring);
		System.out.println("Response json from API..." + Responsejson);
		station1ID = utilitobj.getvlueByJpath(Responsejson, "ID");
		System.out.println("station1 ID is " + station1ID);

	}

	@Test(priority = 3, enabled = true)
	public void Registerstation2() throws ClientProtocolException, IOException {

		String url = envUrl + "/data/3.0/stations?appid=0b2d252f7ff55e253f0f8496f8d8ae54";
		restmethods = new restClient();

		HashMap<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		String payload = "{\r\n" + "  \"external_id\": \"Interview1 \",\r\n"
				+ "  \"name\" :\"interview Station 2\",\r\n" + "  \"latitude\": 33.44,\r\n"
				+ "  \"longitude\": -12.44,\r\n" + "   \"altitude\": 444 \r\n" + "}";

		closeableHttpResponse = restmethods.post(url, payload, headers);
		int ststuscode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status code of Registerstation2 --- " + ststuscode);
//		 assertEquals(ststuscode, STATUS_RESPONSE_CODE_201, "status code is not 201 No Content");
		String Responsestring = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
		JSONObject Responsejson = new JSONObject(Responsestring);
		System.out.println("Response json from API..." + Responsejson);

		station2ID = utilitobj.getvlueByJpath(Responsejson, "ID");
		System.out.println("station2 ID is " + station2ID);

	}

	@Test(priority = 4, enabled = true)
	public void Validatestation1Data() throws ClientProtocolException, IOException {

		String url = envUrl + "/data/3.0/stations?station_id=" + station1ID + "&appid=0b2d252f7ff55e253f0f8496f8d8ae54";
		restmethods = new restClient();

		HashMap<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");

		closeableHttpResponse = restmethods.get(url, headers);
		int ststuscode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status code of Validatestation1Data --- " + ststuscode);
		// assertEquals(ststuscode, STATUS_RESPONSE_CODE_200, "status code is not 200 No
		// Content");
		String Responsestring = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");

		JSONArray jsonArray1 = new JSONArray(Responsestring);
		JSONObject jObj1 = jsonArray1.getJSONObject(0);
		System.out.println("Station 1 response : " + jObj1);
		DeleteStation1ID = jObj1.getString("id");

		assertEquals(jObj1.getDouble("longitude"), -111.43, "longitude is not same as we added");
		assertEquals(jObj1.getDouble("latitude"), 33.33, "latitude is not same as we added");
		assertEquals(jObj1.getInt("altitude"), 444, "altitude is not same as we added");
		assertEquals(jObj1.getString("external_id"), "DEMO_TEST001", "external_id is not same as we added");
		assertEquals(jObj1.getString("name"), "interview Station 1", "Name is not same as we added");

	}

	@Test(priority = 5, enabled = true)
	public void Validatestation2Data() throws ClientProtocolException, IOException {

		String url = envUrl + "/data/3.0/stations?station_id=" + station2ID + "&appid=0b2d252f7ff55e253f0f8496f8d8ae54";
		restmethods = new restClient();

		HashMap<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");

		closeableHttpResponse = restmethods.get(url, headers);
		int ststuscode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status code of Validatestation2Data --- " + ststuscode);
		assertEquals(ststuscode, STATUS_RESPONSE_CODE_200, "status code is not 200 No Content");
		String Responsestring = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");

		JSONArray jsonArray = new JSONArray(Responsestring);
		JSONObject jObj = jsonArray.getJSONObject(1);
		System.out.println("Station 2 response : " + jObj);
		DeleteStation2ID = jObj.getString("id");

		assertEquals(jObj.getString("name"), "interview Station 2", "Name is not same as we added");
		assertEquals(jObj.getDouble("longitude"), -12.44, "longitude is not same as we added");
		assertEquals(jObj.getDouble("latitude"), 33.44, "latitude is not same as we added");
		assertEquals(jObj.getInt("altitude"), 444, "altitude is not same as we added");
		assertEquals(jObj.getString("external_id"), "Interview1 ", "external_id is not same as we added");

	}

	@Test(priority = 6, enabled = true)
	public void Deletestation1Data() throws ClientProtocolException, IOException {

		String url = envUrl + "/data/3.0/stations/" + DeleteStation1ID + "?appid=0b2d252f7ff55e253f0f8496f8d8ae54";
		restmethods = new restClient();

		HashMap<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");

		closeableHttpResponse = restmethods.delete(url, headers);
		int ststuscode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status code of Deletestation1Data --- " + ststuscode);
		assertEquals(ststuscode, 204, "status code is not 204 No Content");

	}

	@Test(priority = 7, enabled = true)
	public void Deletestation2Data() throws ClientProtocolException, IOException {

		String url = envUrl + "/data/3.0/stations/" + DeleteStation2ID + "?appid=0b2d252f7ff55e253f0f8496f8d8ae54";
		restmethods = new restClient();

		HashMap<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");

		closeableHttpResponse = restmethods.delete(url, headers);
		int ststuscode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status code of Deletestation2Data --- " + ststuscode);
		assertEquals(ststuscode, 204, "status code is not 204 No Content");

	}

	@Test(priority = 8, enabled = true)
	public void AgainDeletetation1Data() throws ClientProtocolException, IOException {

		String url = envUrl + "/data/3.0/stations/" + DeleteStation1ID + "?appid=0b2d252f7ff55e253f0f8496f8d8ae54";
		restmethods = new restClient();

		HashMap<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");

		closeableHttpResponse = restmethods.delete(url, headers);
		int ststuscode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status code of AgainDeletetation1Data --- " + ststuscode);
		assertEquals(ststuscode, 404, "status code is not 404 No Content");

		String Responsestring = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
		JSONObject Responsejson = new JSONObject(Responsestring);
		System.out.println("Response json from API..." + Responsejson);

		String message = utilitobj.getvlueByJpath(Responsejson, "message");
		// System.out.println("Error message oftation 1 is "+message);
		assertEquals(message, "Station not found", "Getting different messsage");
	}

	@Test(priority = 9, enabled = true)
	public void AgainDeletetation2Data() throws ClientProtocolException, IOException {

		String url = envUrl + "/data/3.0/stations/" + DeleteStation2ID + "?appid=0b2d252f7ff55e253f0f8496f8d8ae54";
		restmethods = new restClient();

		HashMap<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");

		closeableHttpResponse = restmethods.delete(url, headers);
		int ststuscode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status code of AgainDeletetation2Data --- " + ststuscode);
		assertEquals(ststuscode, 404, "status code is not 404 No Content");

		String Responsestring = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
		JSONObject Responsejson = new JSONObject(Responsestring);
		System.out.println("Response json from API..." + Responsejson);

		String message = utilitobj.getvlueByJpath(Responsejson, "message");
		// System.out.println("Error message of station 2 is "+message);
		assertEquals(message, "Station not found", "Getting different messsage");
	}

}

package com.Automation.framework.testcases;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;
import org.testng.annotations.Test;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
public class TC_003_Replace_Domain {

	DateFormat dateFormat1 = new SimpleDateFormat("MMdd");
	Date date1 = new Date();
	String dateFormatted1 = dateFormat1.format(date1);
	int ran = 100 + (int) (Math.random() * ((10000 - 100) + 1));
	String NewDomain = "automationqa" + ran + dateFormatted1;

	private static final String BASE_PATH = "base_Url/";

	@Test
	public void contextLoads() {
	}

	@Test(priority = 1)
	public void Replace_Domain() throws Exception {
		String endpointString = "api/v1/client-domains";
		String apiEndpointString = BASE_PATH + endpointString;

		CloseableHttpClient client = HttpClients.createDefault();
		HttpPut httpPut = new HttpPut(apiEndpointString);

		httpPut.setHeader("accept", "application/json");
		httpPut.setHeader("Content-Type", "application/json");
		httpPut.setHeader("authorization",
				"Bearer ..dshfgjdsbfjsdbjfsdf");
		System.out.println("New Domain is " + NewDomain);
		String json = "{\"domains\":[{\"old\": \"www.yopmail.com\",\"new\": \"www." + NewDomain + ".com\"}]}";
		StringEntity entity = new StringEntity(json);
		httpPut.setEntity(entity);

		CloseableHttpResponse response = client.execute(httpPut);
		System.out.println("API Response now " + response);
		try {
			Assert.isTrue(response.getStatusLine().getStatusCode() == 200, "Status code is not 200");
		} catch (AssertionError e) {
			System.out.println(response.getStatusLine().getStatusCode());
		}

		InputStream instream = response.getEntity().getContent();
		String result = convertStreamToString(instream);
		JSONObject jsonObject = new JSONObject(result);
		System.out.println("Test Response: " + jsonObject);
		String DeviceType = jsonObject.get("message").toString();

		if (DeviceType.contains("Domain updated successfully")) {
			System.out.println("Domain updated successfully");
		} else if (DeviceType.contains("No data found")) {
			System.out.println("Domain does not existed.");
		} else {
			System.out.println("Something went wrong. API is not working.");
		}
		client.close();
	}

	private static String convertStreamToString(InputStream is) {

		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();

		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}

}

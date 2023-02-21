package com.traveltimeaware.app.util.maps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.traveltimeaware.app.util.PropertiesLoader;

public abstract class BingMapsRequest {
	
	private static String KEY = null;
	
	private static void init() throws IOException {
		if(KEY == null)
			KEY = PropertiesLoader.loadProperties("application-local.properties").getProperty("bing.maps.token");
	}
	
	public static String send(MapsURL request) throws IOException {
		init();
		request = request.param("key", KEY);
		
		URL url = new URL(request.toString());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		
		return getResponse(conn);
	}

	private static String getResponse(HttpURLConnection conn) throws IOException {
		Reader streamReader = null;
		if (conn.getResponseCode() > 299) {
			streamReader = new InputStreamReader(conn.getErrorStream());
		} else {
			streamReader = new InputStreamReader(conn.getInputStream());
		}

		BufferedReader in = new BufferedReader(streamReader);
		String inputLine;
		StringBuffer content = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			content.append(inputLine);
		}
		
		in.close();
		conn.disconnect();
		
		return content.toString();
	}
}
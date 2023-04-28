package com.traveltimeaware.app.map;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

public abstract class BingMapsRequest {

	@Autowired
	private static Environment env;
	
	private BingMapsRequest() {
		throw new IllegalStateException("Utility class");
	}

	private static String KEY = null;

	private static void init() throws IOException {
		if(KEY == null)
			KEY = env.getProperty("bing.maps.token");
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
		StringBuilder content = new StringBuilder();
		while ((inputLine = in.readLine()) != null) {
			content.append(inputLine);
		}

		in.close();
		conn.disconnect();

		return content.toString();
	}
}

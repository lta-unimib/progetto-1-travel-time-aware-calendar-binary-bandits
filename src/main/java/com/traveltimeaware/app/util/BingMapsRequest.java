package com.traveltimeaware.app.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application-local.properties")
public class BingMapsRequest {
	
	@Value("${bing.maps.token}")
	private String KEY;
	
	public BingMapsRequest() {
		
	}
	
	public String send(MapsURL request) throws IOException {
		request = request.param("key", KEY);
		
		URL url = new URL(request.toString());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		
		return getResponse(conn);
	}

	private String getResponse(HttpURLConnection conn) throws IOException {
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
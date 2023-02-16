package com.traveltimeaware.app.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.web.util.UriBuilder;

public class BingMapsRequest {
	private static final String KEY = "Ak3neTD8b8APq3gOBYmpT09W2LaGZS-fK1UFvyjhF2X7lUeeCYfytch0rX2I4tVo";
	private String base = "http://dev.virtualearth.net/REST/V1";
	private HttpURLConnection conn;

	public static class Builder {
		private final String path; // something like /path1/path2/path3
		private Map<String, String> params;

		public Builder(String path) {
			this.params = new HashMap<>();
			this.path = path;
		}

		public Builder param(String key, String value) {
			this.params.put(key, value);
			return this;
		}

		public BingMapsRequest build() throws IOException {
			return new BingMapsRequest(this);
		}
	}

	private BingMapsRequest(Builder builder) throws IOException {
		URL url = new URL();
		conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");

		builder.params.put("key", KEY);
		base += "?";
		for(Entry<String, String> entry : builder.params.entrySet()) {
			base += entry.getKey() + " = " + entry.getValue() + "&";
		}
		
		System.out.println(conn);
	}

	public String getResponse() throws IOException {
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

package com.traveltimeaware.app.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class BingMapsRequest {
	private static final String KEY = "123";
	private String request = "http://dev.virtualearth.net/REST/V1/";
	private HttpURLConnection conn;

	public static class Builder {
		private final String path;
		private final Map<String, String> params;

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
		request += builder.path;
		URL url = new URL(request);
		conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setDoOutput(true);

		builder.params.put("key", KEY);
		DataOutputStream out = new DataOutputStream(conn.getOutputStream());
		out.writeBytes(ParameterStringBuilder.getParamsString(builder.params));
		out.flush();
		out.close();
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

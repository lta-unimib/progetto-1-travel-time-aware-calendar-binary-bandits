package com.traveltimeaware.app.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.glassfish.jaxb.core.api.impl.NameConverter.Standard;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

public class BingMapsRequest {
	
	private static String KEY = "Ak3neTD8b8APq3gOBYmpT09W2LaGZS-fK1UFvyjhF2X7lUeeCYfytch0rX2I4tVo";
	
	private String base;
	private HttpURLConnection conn;

	public static class Builder {
		private String path;
		private Map<String, String> params;

		public Builder(String path) {
			this.params = new HashMap<>();
			this.path = path;
		}

		public Builder param(String key, String value) {
			this.params.put(
					URLEncoder.encode(key, StandardCharsets.UTF_8),
					URLEncoder.encode(value, StandardCharsets.UTF_8));
			
			return this;
		}

		public BingMapsRequest build() throws IOException {
			return new BingMapsRequest(this);
		}
	}

	private BingMapsRequest(Builder builder) {
		base = "http://dev.virtualearth.net/REST/V1" + builder.path + "?"; 
		
		builder.params.put("key", KEY);
		for(Entry<String, String> entry : builder.params.entrySet()) {
			base += entry.getKey() + "=" + entry.getValue() + "&";
		}
		
		if(base == null || base.length() == 0) {											//per rimuovere l'ultimo carattere della stringa[&]{
			return;
		} else {
			base = (base.substring(0, base.length() - 1));
		}
		
		System.out.println(base);
	}
	
	public void sendRequest() throws IOException {
		URL url = new URL(base);
		conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
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
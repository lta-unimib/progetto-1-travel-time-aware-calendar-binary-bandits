package com.traveltimeaware.app.map;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

public class MapsURL {
	private static final String BASE = "http://dev.virtualearth.net/REST/V1";
	private final Map<String, String> params = new HashMap<>();
	
	private String path;
	
	public MapsURL() {
		this("");
	}
	
	public MapsURL(String path) {
		this.path = path;
	}
	
	public MapsURL param(String key, String value) {
		this.params.put(
				URLEncoder.encode(key, StandardCharsets.UTF_8),
				URLEncoder.encode(value, StandardCharsets.UTF_8));
		
		return this;
	}
	
	@Override
	public String toString() {
		String url = BASE + path + "?";
		for(Entry<String, String> entry : params.entrySet()) {
			url += entry.getKey() + "=" + entry.getValue() + "&";
		}
		
		if(url != null && url.length() != 0) {
			url = (url.substring(0, url.length() - 1));
		}
		
		return url;
	}

	@Override
	public int hashCode() {
		return Objects.hash(params, path);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MapsURL other = (MapsURL) obj;
		return Objects.equals(params, other.params) && Objects.equals(path, other.path);
	}
	
	
}

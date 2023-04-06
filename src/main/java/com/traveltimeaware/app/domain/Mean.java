package com.traveltimeaware.app.domain;

public enum Mean {
	CAR("Car"),
	FOOT("Foot"),
	PUBLIC_TRANSPORT("Public Transport");
	
	private final String value;
	
	private Mean(String value) {
		this.value = value;
	}
}

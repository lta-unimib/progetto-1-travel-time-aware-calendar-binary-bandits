package com.traveltimeaware.app.domain;

public class Schedule {
	
	private Event event;
	private TravelTime travel;
	
	public Schedule(Event event, TravelTime travel) {
		this.event = event;
		this.travel = travel;
	}

	public Event getEvent() {
		return event;
	}

	public TravelTime getTravel() {
		return travel;
	}
}

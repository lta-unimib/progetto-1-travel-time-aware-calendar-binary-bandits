package com.traveltimeaware.app.domain;

import java.util.Date;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class Day {
	private final Set<Meeting> meetings;
	private final Date day;

	public Day() {
		this(new Date());
	}

	public Day(Date day) {
		if (day == null)
			throw new NullPointerException("Date is null");
		this.day = day;
		
		ConcurrentHashMap<Meeting, Object> eventsMap = new ConcurrentHashMap<>();
		this.meetings = eventsMap.keySet();
	}
	
	public Meeting getMeeting() {
		return null;
	}
	
	public void removeMeeting() {}
	
}

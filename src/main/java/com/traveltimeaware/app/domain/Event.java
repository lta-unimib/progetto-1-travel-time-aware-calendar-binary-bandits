package com.traveltimeaware.app.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Event implements Comparable<Event> {
	
	protected LocalDateTime startEvent = LocalDateTime.MIN;
	protected LocalDateTime endEvent = LocalDateTime.MAX;
	
	public Event(LocalDateTime start, LocalDateTime end) {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");  
		
		this.startEvent.format(format); 
		this.endEvent.format(format); 
		
		setStart(start);
		setEnd(end);
	}
	
	public LocalDateTime getStart() {
		return startEvent;
	}

	public void setStart(LocalDateTime start) {
		if(start.compareTo(this.endEvent) > 0)
			throw new IllegalArgumentException("Event end < start");
		this.startEvent = start;
	}

	public LocalDateTime getEnd() {
		return endEvent;
	}

	public void setEnd(LocalDateTime end) {
		if(end.compareTo(this.startEvent) < 0)
			throw new IllegalArgumentException("Event end < start");
		this.endEvent = end;
	}
	
	@Override
	public int compareTo(Event o) {
		if(o == null)
			throw new NullPointerException("Day is uncomparable, is null");
		return this.startEvent.compareTo(o.endEvent);
	}
}

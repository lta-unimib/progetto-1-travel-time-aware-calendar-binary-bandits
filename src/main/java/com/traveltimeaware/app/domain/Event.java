package com.traveltimeaware.app.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Event implements Comparable<Event> {
	
	protected LocalDateTime startEvent = LocalDateTime.MIN;
	protected LocalDateTime endEvent = LocalDateTime.MAX;
	
	protected Event(LocalDateTime start, LocalDateTime end) {
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

	@Override
	public int hashCode() {
		return Objects.hash(endEvent, startEvent);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Event other = (Event) obj;
		return Objects.equals(endEvent, other.endEvent) && Objects.equals(startEvent, other.startEvent);
	}
	
	
}

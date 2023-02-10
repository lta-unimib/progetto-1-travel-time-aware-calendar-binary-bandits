package com.traveltimeaware.app.domain;

import java.util.Date;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class Day implements Comparable<Day> {
	
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
	
	public Date getDayDate() {
		return this.day;
	}
	
	public Meeting getMeeting() {
		return null;
	}
	
	public void removeMeeting() {}

	@Override
	public String toString() {
		return "Day [day=" + day + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(day);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Day other = (Day) obj;
		return Objects.equals(day, other.day);
	}

	@Override
	public int compareTo(Day o) {
		if(o == null)
			throw new NullPointerException("Day is uncomparable, is null");
		return this.day.compareTo(o.day);
	}
	
	
}

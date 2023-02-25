package com.traveltimeaware.app.domain;

import java.text.ParseException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Calendar {
	
	private final Set<Day> days;
	
	public Calendar() throws ParseException {
		this.days = ConcurrentHashMap.newKeySet();
	}

	public void addDay(Day d) {
		if(validateInput(d) && days.add(d) != true)
			throw new IllegalArgumentException("Element already exist");
	}
	
	public void removeDay(Date d) {
		if(validateInput(new Day(d)) && days.remove(new Day(d)) != true) 
			throw new IllegalArgumentException("Element doesn't exist");
	}
	
	public void updateDay(Day d) {
		removeDay(d.getDayDate());
		addDay(d);
	}
	
	public Day getDay(Date d) {		
		if(validateInput(new Day(d)) && !days.contains(new Day(d)))
			throw new IllegalArgumentException("Element doesn't already exist");
		
		Day found = null;
		for(Day day : days) {
			if(day.equals(new Day(d))) {
				found = day;
				break;
			}
		}
		
		return found;
	}
	
	private boolean validateInput(Day d) {
		if(d == null)
			throw new NullPointerException("Date is null");
		
		if(!days.contains(d))
			throw new IllegalArgumentException("Element doesn't already exist");
		
		return true;
	}
	
	// return ordered Set
	public Set<Day> getCalendar() {
		return (new TreeSet<>(days));
	}
	
}

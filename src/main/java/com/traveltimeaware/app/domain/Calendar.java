package com.traveltimeaware.app.domain;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Calendar {
	
	private final Map<Date, Day> days;
	private final Date today;
	
	public Calendar() throws ParseException {
		this.days = new ConcurrentHashMap<>();
		
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		this.today = formatter.parse(formatter.format(new Date()));
	}
	
	public void addDay(Day d) {
		if(d == null)
			throw new NullPointerException("Day is null");
		
		Day added = days.putIfAbsent(d.getDayDate(), d);
		if(added == null)
			throw new IllegalArgumentException("Key already exist");
	}
	
	public Day removeDay(Date d) {
		if(d == null)
			throw new NullPointerException("Date is null");
		
		Day removed = days.remove(d);
		if(removed == null) 
			throw new IllegalArgumentException("Key doesn't exist");
		
		return removed;
	}
	
	public void updateDay(Day d) {
		if(!days.containsKey(d.getDayDate()))
			throw new IllegalArgumentException("Key doesn't exist");
		
		days.put(d.getDayDate(), d);
	}
	
	public Day getDay(Date d) {
		if(!days.containsKey(d))
			throw new IllegalArgumentException("Key doesn't exist");
		
		return days.get(d);
	}
	
	// return ordered List by keys
	public Set<Day> getCalendar() {
		Map<Date, Day> orderedDays = new TreeMap<>(days);
		return new HashSet<Day>(orderedDays.values());
	}
	
}

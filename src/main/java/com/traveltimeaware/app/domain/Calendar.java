package com.traveltimeaware.app.domain;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Calendar {
	// Thread safe HashSet
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
		days.putIfAbsent(d.getDayDate(), d);
	}
	
	public void removeDay(Date d) {
		if(d == null)
			throw new NullPointerException("Date is null");
		days.remove(d);
	}
	
	public void updateDay(Day d) {}
	
	// Two day are equals if they date are equals
	public Day getDay(Date d) {
		return null;
	}
	
	
}

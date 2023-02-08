package com.traveltimeaware.app.domain;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Calendar {
	// Thread safe HashSet
	private final Set<Day> days;
	private final Date today;
	
	public Calendar() throws ParseException {
		ConcurrentHashMap<Day, Object> daysMap = new ConcurrentHashMap<>();
		this.days = daysMap.keySet();
		
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		this.today = formatter.parse(formatter.format(new Date()));
	}
	
	public void addDay(Day d) {
		if(d != null) {
			days.add(d);
		}
	}
	
	public void removeDay(Day d) {
		if(!days.remove(d)) {
			//errore
		}
	}
	
	public void updateDay(Day d) {}
	
	// Two day are equals if they date are equals
	public Day getDay(Date d) {
		return null;
	}
	
	
}

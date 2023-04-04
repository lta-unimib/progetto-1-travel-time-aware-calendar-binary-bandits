package com.traveltimeaware.app.domain;

import java.util.Collections;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

import com.traveltimeaware.app.domain.profile.User;

public class Calendar {
	
	private SortedSet<Schedule> events;
	
	private User user;
	
	private static Calendar instance = null;
 	
	private Calendar (User user) {
		this.user = user;
		events = Collections.synchronizedSortedSet(new TreeSet<Schedule>());
	}
	
	public static synchronized Calendar getInstance(User user) {
		if(instance == null)
			instance = new Calendar(user);
		
		return instance;
	}
	
	public void add(Schedule s) {
		validate(s);
		
		if(events.contains(s))
			throw new IllegalArgumentException("Element already exist");
		
		events.add(s);
	}
	
	public void remove(Schedule s) {
		validate(s);
		
		if(!events.contains(s))
			throw new IllegalArgumentException("Element doesn't exist");
		
		events.remove(s);
	}
	
	public void update(Schedule prev, Schedule next) {
		events.remove(prev);
		events.add(next);
	}
	
	public Schedule get(Schedule s) {
		validate(s);
		
		if(!events.contains(s))
			throw new IllegalArgumentException("Element doesn't exist");
		
		Schedule found = null;
		for(Iterator<Schedule> iter = events.iterator(); iter.hasNext();) {
			Schedule next = iter.next();
			if(next.equals(s)) {
				found = s;
				break;
			}
		}
		
		return found;
	}
	
	private void validate(Schedule s) {
		if(s == null)
			throw new NullPointerException("Schedule is null");
	}
}

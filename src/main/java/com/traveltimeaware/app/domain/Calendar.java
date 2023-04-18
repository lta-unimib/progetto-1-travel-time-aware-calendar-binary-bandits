package com.traveltimeaware.app.domain;

import java.util.Collections;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

import com.traveltimeaware.app.domain.profile.User;

import jakarta.ejb.Singleton;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "calendars")
public class Calendar {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@OneToMany(mappedBy = "calendar")
	private SortedSet<Schedule> events;
	
	@OneToOne(mappedBy = "calendar")
	private User user;
	
	private static Calendar instance = null;
 	
	public Calendar () {
		events = Collections.synchronizedSortedSet(new TreeSet<Schedule>());
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

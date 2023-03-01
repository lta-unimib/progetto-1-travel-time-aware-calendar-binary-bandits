package com.traveltimeaware.app.domain;

import java.text.ParseException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import com.traveltimeaware.app.security.domain.User;

import jakarta.persistence.*;

@Entity
@Table(name = "calendars")
public class Calendar {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_email", referencedColumnName = "email")
    private User user;
	
	@OneToMany(mappedBy = "calendar")
	private Set<Day> days;

	public long getId() {
		return id;
	}
	
	public Calendar() throws ParseException {
		setDays(new HashSet<>());
	}

	public void addDay(Day d) {
		if(d == null || days.add(d) != true)
			throw new IllegalArgumentException("Element already exist");
	}
	
	public void removeDay(Date d) {
		if(d == null || days.remove(new Day(d)) != true) 
			throw new IllegalArgumentException("Element doesn't exist");
	}
	
	public void removeDay(Day d) {
		if(d == null || days.remove(d) != true) 
			throw new IllegalArgumentException("Element doesn't exist");
	}
	
	public void updateDay(Day d) {
		removeDay(d.getDay());
		addDay(d);
	}
	
	public Day getDay(Date d) {		
		if(d == null || !days.contains(new Day(d)))
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
	
	// return ordered Set
	public Set<Day> getDays() {
		return (new TreeSet<>(days));
	}
	
	private void setDays(Set<Day> days) {
		ConcurrentHashMap<Day, Object> daysMap = new ConcurrentHashMap<>();
		this.days = daysMap.keySet(days);
	}
	
}

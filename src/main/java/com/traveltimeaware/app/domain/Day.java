package com.traveltimeaware.app.domain;

import java.util.Date;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;

import jakarta.persistence.*;

@Entity
@Table(name = "days")
public class Day implements Comparable<Day> {
	
	@Id
	@Column(nullable = false, unique = true)
	private final Date day;
	
	@OneToMany(mappedBy = "day")
	private Set<Meeting> meetings;
	
	@ManyToOne
    @JoinColumn(name = "calendar_id", nullable = false)
	private Calendar calendar;

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
	
	public Date getDay() {
		return this.day;
	}
	
	public void addMeeting(Meeting m) {	
		if(m == null || !meetings.add(m))
			throw new IllegalArgumentException("Element already exist");
	}
	
	public void removeMeeting(Meeting m) {
		if(m == null || !meetings.remove(m)) 
			throw new IllegalArgumentException("Element doesn't exist");
	}
	
	public void updateMeeting(Meeting m) {
		removeMeeting(m);
		addMeeting(m);
	}
	
	public Set<Meeting> getMeetings(){
		return new TreeSet<>(meetings);
	}

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

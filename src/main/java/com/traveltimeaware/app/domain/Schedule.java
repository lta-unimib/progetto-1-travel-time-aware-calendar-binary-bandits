package com.traveltimeaware.app.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "schedules")
public class Schedule {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "event_id", referencedColumnName = "id")
	private Event event;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "travel_id", referencedColumnName = "id")
	private Travel travel;
	
	@ManyToOne
    @JoinColumn(name = "calendar_id", nullable = false)
	private Calendar calendar;

	public Calendar getCalendar() {
		return calendar;
	}
	
	public Long getId() {
		return id;
	}

	public void setCalendar(Calendar calendar) {
		this.calendar = calendar;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public void setTravel(Travel travel) {
		this.travel = travel;
	}

	public Event getEvent() {
		return event;
	}

	public Travel getTravel() {
		return travel;
	}
}

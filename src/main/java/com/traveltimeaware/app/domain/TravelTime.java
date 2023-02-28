package com.traveltimeaware.app.domain;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "travel_time")
public class TravelTime extends Event {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Enumerated(EnumType.ORDINAL)
	private Mean mean;
	private String startLocation;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "meeting_id", referencedColumnName = "id")
	private Meeting meeting;
	
	public TravelTime(LocalDateTime start, LocalDateTime end, Mean mean, String startLocation, String endLocation) {
		super(start, end);
		
		setMean(mean);
		setStartLocation(startLocation);
	}
	
	public long getId() {
		return id;
	}
	
	public Mean getMean() {
		return mean;
	}
	
	public void setMean(Mean mean) {
		this.mean = mean;
	}
	
	public String getStartLocation() {
		return startLocation;
	}
	
	public void setStartLocation(String startLocation) {
		if(startLocation == null)
			throw new IllegalArgumentException("Location not valid");
		this.startLocation = startLocation;
	}
}

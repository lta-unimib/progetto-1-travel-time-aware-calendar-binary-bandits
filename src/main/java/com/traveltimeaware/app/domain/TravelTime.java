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
	private Means means;
	private String startLocation;
	private String endLocation;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "meeting_id", referencedColumnName = "id")
	private Meeting meeting;
	
	public TravelTime(LocalDateTime start, LocalDateTime end, Means means, String startLocation, String endLocation) {
		super(start, end);
		
		setMeans(means);
		setStartLocation(startLocation);
		setEndLocation(endLocation);
	}
	
	public Means getMeans() {
		return means;
	}
	
	public void setMeans(Means means) {
		this.means = means;
	}
	
	public String getStartLocation() {
		return startLocation;
	}
	
	public void setStartLocation(String startLocation) {
		if(startLocation == null)
			throw new IllegalArgumentException("Location not valid");
		this.startLocation = startLocation;
	}
	
	public String getEndLocation() {
		return endLocation;
	}
	
	public void setEndLocation(String endLocation) {
		if(endLocation == null)
			throw new IllegalArgumentException("Location not valid");
		this.endLocation = endLocation;
	}
}

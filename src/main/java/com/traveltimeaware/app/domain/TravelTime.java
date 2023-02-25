package com.traveltimeaware.app.domain;

import java.time.LocalDateTime;

public class TravelTime extends Event {
	
	private Means means;
	private Location startLocation;
	private Location endLocation;
	
	public TravelTime(LocalDateTime start, LocalDateTime end, Means means, Location startLocation, Location endLocation) {
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
	
	public Location getStartLocation() {
		return startLocation;
	}
	
	public void setStartLocation(Location startLocation) {
		if(startLocation == null)
			throw new IllegalArgumentException("Location not valid");
		this.startLocation = startLocation;
	}
	
	public Location getEndLocation() {
		return endLocation;
	}
	
	public void setEndLocation(Location endLocation) {
		if(endLocation == null)
			throw new IllegalArgumentException("Location not valid");
		this.endLocation = endLocation;
	}
}

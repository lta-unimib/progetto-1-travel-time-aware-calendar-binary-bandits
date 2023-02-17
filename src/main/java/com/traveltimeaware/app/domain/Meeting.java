package com.traveltimeaware.app.domain;

public class Meeting extends Event implements Comparable<Meeting>{
	private String title;
    private String description;
    private Boolean repetition;
    private TravelTime travelTime;
    private Location location;
	
	public Meeting(String title, String description, Boolean repetition, TravelTime travelTime) {
		super();
		this.title = title;
		this.description = description;
		this.repetition = repetition;
		this.travelTime = travelTime;
	}
    
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Boolean getRepetition() {
		return repetition;
	}
	public void setRepetition(Boolean repetition) {
		this.repetition = repetition;
	}
	public TravelTime getTravelTime() {
		return travelTime;
	}
	public void setTravelTime(TravelTime travelTime) {
		this.travelTime = travelTime;
	}
    
	

	@Override
	public void addEvent() {
		//creare evento
		
	}

	// if endate < startdate --> this < o
	@Override
	public int compareTo(Meeting o) {
		return this.endDate.compareTo(o.startDate);
	}
}
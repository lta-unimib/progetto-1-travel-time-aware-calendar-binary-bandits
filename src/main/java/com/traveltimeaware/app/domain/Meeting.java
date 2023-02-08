package com.traveltimeaware.app.domain;

public class Meeting extends Event{
	
	public Meeting(String title, String description, Boolean repetion, TravelTime travelIme) {
		super();
		this.title = title;
		this.description = description;
		this.repetion = repetion;
		this.travelIme = travelIme;
	}

	private String title;
    private String description;
    private Boolean repetion;
    private TravelTime travelIme;
    
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
	public Boolean getRepetion() {
		return repetion;
	}
	public void setRepetion(Boolean repetion) {
		this.repetion = repetion;
	}
}

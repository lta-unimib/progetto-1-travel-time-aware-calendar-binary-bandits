package com.traveltimeaware.app.domain;

import java.time.LocalDateTime;

public class Meeting extends Event {

	private long id;
	private String title;
	private String description;
	private boolean repetition = false;
	private TravelTime travel;
	private Location location;

	public static class Builder {
		// Required
		private String title;
		private Location location;
		private LocalDateTime start;
		private LocalDateTime end;
		private TravelTime travel;
		
		// Optional
		private String description;
		private boolean repetition;

		public Builder(LocalDateTime start, LocalDateTime end, String title, Location location, TravelTime travel) {
			this.title = title;
			this.location = location;
			this.travel = travel;
			
			this.start = start;
			this.end = end;
		}
		
		public Builder description(String description) {
			this.description = description;
			return this;
		}
		
		public Builder repetition(boolean repetition) {
			this.repetition = repetition;
			return this;
		}
		
		public Meeting build() {
			return new Meeting(this);
		}
	}

	private Meeting(Builder builder) {
		super(builder.start, builder.end);
		
		setDescription(builder.description);
		setTitle(builder.title);
		setRepetition(builder.repetition);
		setLocation(builder.location);
		setTravelTime(builder.travel);
	}
	
	public long getId() {
		return id;
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

	public boolean getRepetition() {
		return repetition;
	}

	public void setRepetition(boolean repetition) {
		this.repetition = repetition;
	}

	public TravelTime getTravelTime() {
		return travel;
	}

	public void setTravelTime(TravelTime travelTime) {
		this.travel = travelTime;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
}

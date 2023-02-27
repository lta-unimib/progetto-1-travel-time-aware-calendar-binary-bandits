package com.traveltimeaware.app.domain;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.*;

@Entity
@Table(name = "meetings")
public class Meeting extends Event {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String title;
	private String description = "";
	private boolean repetition = false;
	
	@OneToOne(mappedBy = "meeting")
	private TravelTime travel;
	private String location;
	
	@ManyToOne
    @JoinColumn(name = "day_id", nullable = false)
	private Day day;

	public static class Builder {
		// Required
		private String title;
		private String location;
		private LocalDateTime start;
		private LocalDateTime end;
		private TravelTime travel;
		
		// Optional
		private String description;
		private boolean repetition;

		public Builder(LocalDateTime start, LocalDateTime end, String title, String location, TravelTime travel) {
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
	
	protected Meeting(){
		super(LocalDateTime.MIN, LocalDateTime.MAX);
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
		if(title == null || title.length() == 0)
			throw new IllegalArgumentException("Title not valid");
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		if(location == null)
			throw new IllegalArgumentException("Location not valid");
		this.location = location;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(description, id, location, repetition, title, travel);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Meeting other = (Meeting) obj;
		return Objects.equals(description, other.description) && id == other.id
				&& Objects.equals(location, other.location) && repetition == other.repetition
				&& Objects.equals(title, other.title) && Objects.equals(travel, other.travel);
	}
	
	
}

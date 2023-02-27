package com.traveltimeaware.app.domain;

import java.time.LocalDateTime;

import com.traveltimeaware.app.security.domain.User;

import jakarta.persistence.*;

@Entity
@Table(name = "days")
public class PreferenceEvent extends Event {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String name;
	private int minimalTime;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "email")
    private User user;
	
	public PreferenceEvent(LocalDateTime start, LocalDateTime end, String name, int minimalTime) {
		super(start, end);
		
		setMinimalTime(minimalTime);
		setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMinimalTime() {
		return minimalTime;
	}

	public void setMinimalTime(int minimalTime) {
		if(minimalTime <= 0)
			throw new IllegalArgumentException("Minimum time > 0");
		this.minimalTime = minimalTime;
	}

	public long getId() {
		return id;
	}
	
	
}

package com.traveltimeaware.app.domain.profile;

import java.util.Arrays;
import java.util.List;

import com.traveltimeaware.app.domain.Calendar;
import com.traveltimeaware.app.domain.Mean;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "account_id", referencedColumnName = "id")
	private Account account;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "calendar_id", referencedColumnName = "id")
	private Calendar calendar;
	
	@ElementCollection
	@Enumerated(EnumType.STRING)
	private List<Mean> preferendMeans;
	
	private String name;
	private String surname;
	
	public User(String name, String surname) {
		this.name = name;
		this.surname = surname;
		
		preferendMeans = Arrays.asList(Mean.values());
	}
	
	public String getName() {
		return name;
	}
	
	
	public String getSurname() {
		return surname;
	}
}

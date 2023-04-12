package com.traveltimeaware.app.domain.profile;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

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
	@JoinColumn(name = "calendar_id", referencedColumnName = "id")
	private Calendar calendar;
	
	@ElementCollection
	@Enumerated(EnumType.STRING)
	private List<Mean> preferendMeans;
	
	private String email;
	private String password;
	
	public User() {
		this("temp@email.tmp", "");
	}
	
	public User (String email, String password) {
		setEmail(email);
		setPassword(password);
		
		preferendMeans = Arrays.asList(Mean.values());
	}
	
	public void setEmail(String email) {
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";
		
		Pattern pat = Pattern.compile(emailRegex);
		if (email == null || !pat.matcher(email).matches())
			throw new IllegalArgumentException(emailRegex);
        
		this.email = email;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword() {
		return password;
	}
}

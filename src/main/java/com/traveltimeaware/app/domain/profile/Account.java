package com.traveltimeaware.app.domain.profile;

import java.util.regex.Pattern;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "accounts")
public class Account {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@OneToOne(mappedBy = "account")
	private User user;
	
	private String email;
	private String password;
	
	public Account (String email, String password, User user) {
		setEmail(email);
		setPassword(password);
		this.user = user;
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
	
	public User getUser() {
		return user;
	}
}

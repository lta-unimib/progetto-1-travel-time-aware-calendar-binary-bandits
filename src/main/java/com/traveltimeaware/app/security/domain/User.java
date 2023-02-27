package com.traveltimeaware.app.security.domain;

import com.traveltimeaware.app.domain.*;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

	@Id
	@Column(nullable = false, unique = true)
	private String email;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String surname;
	
	@Column(nullable = false, columnDefinition = "TINYINT", length = 1)
	private boolean enabled;
	
	@OneToOne(mappedBy = "user")
	private Calendar calendar;
	
	@OneToOne(mappedBy = "user")
	private PreferenceEvent preferenceEvent;
	
	@OneToOne(mappedBy = "user")
	private PreferenceMean preferenceMean;

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}
}

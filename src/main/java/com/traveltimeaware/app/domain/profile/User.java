package com.traveltimeaware.app.domain.profile;

import java.util.Collection;
import java.util.regex.Pattern;

import com.traveltimeaware.app.domain.Calendar;
import com.traveltimeaware.app.domain.Mean;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
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
	
	@Column(unique = true)
	private String email;
	
	@ElementCollection(targetClass = Mean.class)
	@JoinTable(name = "means", joinColumns = @JoinColumn(name = "id"))
	@Column(name = "pref_means", nullable = false)
	@Enumerated(EnumType.ORDINAL)
	Collection<Mean> prefMeans;
	
	private String password;
	
	public User() {
		this("temp@email.tmp", "");
	}
	
	public User (String email, String password) {
		setEmail(email);
		setPassword(password);
		
	}
	
	public Collection<Mean> getPreferedMeans(){
		return prefMeans;
	}
	
	public void setPreferedeMeans(Collection<Mean> prefMeans) {
		this.prefMeans = prefMeans;
	}
	
	public void setCalendar(Calendar calendar) {
		this.calendar = calendar;
	}
	
	public Calendar getCalendar() {
		return calendar;
	}
	
	public void setEmail(String email) {
		String emailRegex = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" 
		        + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
		
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

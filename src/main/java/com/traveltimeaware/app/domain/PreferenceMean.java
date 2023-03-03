package com.traveltimeaware.app.domain;

import java.util.ArrayList;
import java.util.List;

import com.traveltimeaware.app.security.domain.User;

import jakarta.persistence.*;

@Entity
@Table(name = "preference_means")
public class PreferenceMean {
	
	@Id
	@Column(nullable = false, unique = true)
	private long id;
	
	@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "join_mean_prefered", 
              joinColumns = @JoinColumn(name = "preference_id"),
              inverseJoinColumns = @JoinColumn(name = "mean_id"))
    @ElementCollection(targetClass = Mean.class)
	private List<Mean> preferedMeans;

	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_email", referencedColumnName = "email")
    private User user;
	
	public long getId() {
		return id;
	}
	
	public PreferenceMean() {
		preferedMeans = new ArrayList<>();
	}
	
	public List<Mean> getPreferedMeans() {
		return preferedMeans;
	}
	
	public boolean contain(Mean mean) {
		for(Mean m : preferedMeans) {
			if(m.equals(mean)) {
				return true;
			}
		}
		
		return false;
	}
	
	public void addMean(Mean m) {
		if(!preferedMeans.add(m))
			throw new IllegalArgumentException("Element already exist");
	}
	
	public void removeMean(Mean m) {
		if(!preferedMeans.remove(m)) 
			throw new IllegalArgumentException("Element doesn't exist");
	}
}

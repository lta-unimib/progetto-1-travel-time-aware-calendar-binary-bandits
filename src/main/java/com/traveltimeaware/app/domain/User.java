package com.traveltimeaware.app.domain;

import java.util.Objects;

public class User {
    private final String name;
    private final String surname;
    private String email;
    private String password;
    
    public User(String name, String surname, String email, String password) {
    	this.name = name;
    	this.surname = surname;
    	this.email = email;
    	this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void updateEmail(String email) {
    	this.email = email;
    }

	@Override
	public String toString() {
		return "User [name=" + name + ", surname=" + surname + ", email=" + email + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(email);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(email, other.email);
	}
    
    
}

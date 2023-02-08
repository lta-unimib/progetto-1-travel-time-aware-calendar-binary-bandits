package com.traveltimeaware.app.domain;

public class User {
    private final String name;
    private final String surname;
    private String email;
    private String password;
    
    public User (String name, String surname, String email, String password) {
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
}

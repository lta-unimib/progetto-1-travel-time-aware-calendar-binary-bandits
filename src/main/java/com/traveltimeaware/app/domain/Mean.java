package com.traveltimeaware.app.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "mean")
public enum Mean {	
    CAR,
    FOOT,
    PUBLIC_TRANSPORT;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "mean")
	private String mean;
}

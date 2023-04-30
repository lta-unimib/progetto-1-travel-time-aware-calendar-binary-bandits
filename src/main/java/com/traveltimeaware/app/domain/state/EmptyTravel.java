package com.traveltimeaware.app.domain.state;

import java.util.Date;

import com.traveltimeaware.app.domain.Travel;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "empty_travel")
public class EmptyTravel extends Travel{

	public EmptyTravel(Date start, Date end) {
		super(start, end);
	}
}

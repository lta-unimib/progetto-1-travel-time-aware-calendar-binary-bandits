package com.traveltimeaware.app.domain.state;

import java.util.Date;

import com.traveltimeaware.app.domain.Mean;
import com.traveltimeaware.app.domain.Travel;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Entity
@Table(name = "exist_travel")
public class ExistTravel extends Travel{

	@Enumerated(EnumType.ORDINAL)
	private Mean mean;
	
	public ExistTravel(Date start, Date end, Mean mean) {
		super(start, end);
		this.mean = mean;
	}

}

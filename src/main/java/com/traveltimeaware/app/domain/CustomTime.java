package com.traveltimeaware.app.domain;

import java.util.Date;

public class CustomTime extends Interval{

	private String title;
	
	public CustomTime(Date start, Date end) {
		super(start, end);
		// TODO Auto-generated constructor stub
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}

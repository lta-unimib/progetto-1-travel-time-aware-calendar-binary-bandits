package com.traveltimeaware.app.domain;

import java.util.Date;

public abstract class Event {
	protected Date startDate;
	protected Date endDate;
	
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public abstract void addEvent();

}

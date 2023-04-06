package com.traveltimeaware.app.domain;

import java.util.Date;
import java.util.Objects;

public class CustomTime extends Interval {

	private String title;
	
	public CustomTime(Date start, Date end) {
		super(start, end);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(title);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomTime other = (CustomTime) obj;
		return Objects.equals(title, other.title);
	}
	
	
}

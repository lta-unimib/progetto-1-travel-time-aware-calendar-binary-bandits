package com.traveltimeaware.app.domain;

import java.util.Date;
import java.util.Objects;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Interval implements Comparable<Interval>{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private Date start;

	private Date end;
	
	public Interval(Date start, Date end) {
		if(start.compareTo(end) > 0)
			throw new IllegalArgumentException("Start date > End date");
		
		this.start = start;
		this.end = end;
	}
	
	public Date getStart() {
		return start;
	}
	
	public Date getEnd() {
		return end;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setStart(Date start) {
		if(start.compareTo(this.end) > 0)
			throw new IllegalArgumentException("Start date > End date");
		
		this.start = start;
	}
	
	public void setEnd(Date end) {
		if(end.compareTo(this.start) < 0)
			throw new IllegalArgumentException("Start date < End date");
		
		this.end = end;
	}
	
	public boolean isDisjointed(Interval o) {
		if(end.before(o.start) || start.after(o.end)) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public int compareTo(Interval o) {
		if(isDisjointed(o)) {
			if(end.before(o.start)) {
				return -1;
			} else if (start.after(o.end)) {
				return 1;
			} else {
				return 0;
			}
		} else {
			throw new IllegalArgumentException("Interval aren't disjointed");
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(end, start);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Interval other = (Interval) obj;
		return Objects.equals(end, other.end) && Objects.equals(start, other.start);
	}
}

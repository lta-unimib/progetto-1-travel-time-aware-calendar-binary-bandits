package com.traveltimeaware.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.traveltimeaware.app.domain.Calendar;

public interface CalendarRepository extends JpaRepository<Calendar, Long>{
	
}

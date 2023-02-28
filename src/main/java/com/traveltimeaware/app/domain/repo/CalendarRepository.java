 package com.traveltimeaware.app.domain.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import com.traveltimeaware.app.domain.Calendar;

@Component
public interface CalendarRepository extends JpaRepository<Calendar, Long> {
	@Query("SELECT c FROM Calendar c WHERE c.user.email = ?1")
	public Calendar findByEmail(String userEmail);
}


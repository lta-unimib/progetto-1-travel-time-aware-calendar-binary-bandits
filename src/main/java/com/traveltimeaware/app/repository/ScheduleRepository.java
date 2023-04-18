package com.traveltimeaware.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.traveltimeaware.app.domain.Calendar;
import com.traveltimeaware.app.domain.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, Long>{
	@Query("SELECT s FROM Schedule s WHERE s.calendar = ?1")
    public List<Schedule> findByCalendar(Calendar calendar);
}

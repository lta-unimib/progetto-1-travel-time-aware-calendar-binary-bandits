package com.traveltimeaware.app.controller;

import java.util.*;
import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.traveltimeaware.app.domain.Calendar;
import com.traveltimeaware.app.domain.Event;
import com.traveltimeaware.app.domain.Schedule;
import com.traveltimeaware.app.domain.Travel;
import com.traveltimeaware.app.repository.CalendarRepository;
import com.traveltimeaware.app.repository.ScheduleRepository;

@RestController
public class CalendarController {
		
	@Autowired
	ScheduleRepository scheduleRepo;
	
	CalendarRepository calendarRepo;
	private Calendar calendar;
	
	@Autowired
	public CalendarController(CalendarRepository calendarRepo) {
		Optional<Calendar> c = calendarRepo.findById(1L);
		if (c.isPresent()){
			calendar = c.get();
		}
		
		this.calendarRepo = calendarRepo;
	}
	
	@GetMapping(value = "/calendar/events")
	public List<Schedule> getAllCalendarEvent() {
		List<Schedule> schedules = scheduleRepo.findByCalendar(calendar);
		
		return schedules;
	}
	
	@PostMapping(value = "/calendar/event/add")
	public void addEvent(@RequestBody Event event) {
		Schedule schedule = new Schedule();
		
		SecureRandom secureRand = new SecureRandom();
		Date start = new Date (secureRand.nextLong(event.getStart().getTime() - 10, event.getStart().getTime()));
		Travel travel = new Travel(start, event.getStart());
		
		schedule.setEvent(event);
		schedule.setTravel(travel);
		
		calendar.add(schedule);
		calendarRepo.save(calendar);
	}
}
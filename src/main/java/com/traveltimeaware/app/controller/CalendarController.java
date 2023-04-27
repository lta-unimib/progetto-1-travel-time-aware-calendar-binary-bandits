package com.traveltimeaware.app.controller;

import java.util.*;
import java.net.URI;
import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
	
	@GetMapping(value = "/calendar/event/get/{id}")
	public ResponseEntity<Schedule> getSchedule(@PathVariable("id") Long id) {
		Optional<Schedule> schedule = scheduleRepo.findById(id);
		
		Schedule found = null;
		if(schedule.isPresent()) {
			found = schedule.get();
		}
		
		if(found == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(found);
		}
	}
	
	@PostMapping(value = "/calendar/event/add")
	public ResponseEntity<Schedule> addEvent(@RequestBody Event event) {
		Schedule schedule = new Schedule();
		
		SecureRandom secureRand = new SecureRandom();
		Date start = new Date (secureRand.nextLong(event.getStart().getTime() - 10, event.getStart().getTime()));
		Travel travel = new Travel(start, event.getStart());
		
		schedule.setEvent(event);
		schedule.setTravel(travel);
		
		calendar.add(schedule);
		calendarRepo.save(calendar);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
		          .path("/calendar/event/get/{id}")
		          .buildAndExpand(schedule.getId())
		          .toUri();
		
		return ResponseEntity.created(uri).body(schedule);
	}
}
package com.traveltimeaware.app.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.traveltimeaware.app.domain.Calendar;
import com.traveltimeaware.app.domain.Event;
import com.traveltimeaware.app.domain.Schedule;
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
	public void getAllCalendarEvent() {
		
	}
	
	@PostMapping(value = "/calendar/event/add")
	public void addEvent(@RequestBody Event event) {
		
	}
	
	@PostMapping(value = "/calendar/means/set")
	public void setPreferedMeans(@RequestBody Map<String, Boolean> checkboxes) {
		
	}

}
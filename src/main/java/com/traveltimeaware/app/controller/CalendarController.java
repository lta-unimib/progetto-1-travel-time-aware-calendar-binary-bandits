package com.traveltimeaware.app.controller;

import java.util.*;
import java.net.URI;
import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.traveltimeaware.app.domain.Calendar;
import com.traveltimeaware.app.domain.Event;
import com.traveltimeaware.app.domain.Mean;
import com.traveltimeaware.app.domain.Schedule;
import com.traveltimeaware.app.domain.Travel;
import com.traveltimeaware.app.domain.profile.User;
import com.traveltimeaware.app.domain.state.EmptyTravel;
import com.traveltimeaware.app.domain.state.ExistTravel;
import com.traveltimeaware.app.repository.CalendarRepository;
import com.traveltimeaware.app.repository.ScheduleRepository;
import com.traveltimeaware.app.repository.UserRepository;

@RestController
public class CalendarController {
	
	@Autowired
	UserRepository userRepo;
	
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
		return scheduleRepo.findByCalendar(calendar);
	}
	
	@GetMapping(value = "/calendar/event/{id}")
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
	
	@DeleteMapping(value = "/caledar/event/{id}")
	public ResponseEntity<Schedule> deleteSchedule(@PathVariable("id") Long id){
		Optional<Schedule> schedule = scheduleRepo.findById(id);
		
		Schedule found = null;
		if(schedule.isPresent()) {
			found = schedule.get();
		}
		
		scheduleRepo.delete(found);
		
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping(value = "/calendar/event/")
	public ResponseEntity<Schedule> addEvent(@RequestBody Event event) {
		Schedule schedule = new Schedule();
		
		Travel travel = null;
		Event prev = calendar.getPrev(event);
		if(prev != null) {
			List<Mean> pref = new ArrayList<>(calendar.getUser().getPreferedMeans());
			if(prev.getEnd().getTime() - event.getStart().getTime() <= 1800000 && pref.contains(Mean.FOOT)) { // 30 min
				travel = new ExistTravel(prev.getEnd(), event.getStart(), Mean.FOOT);
			} else if (pref.contains(Mean.CAR)) {
				travel = new ExistTravel(prev.getEnd(), event.getStart(), Mean.CAR);
			} else if (pref.contains(Mean.PUBLIC_TRANPORT)) {
				travel = new ExistTravel(prev.getEnd(), event.getStart(), Mean.PUBLIC_TRANPORT);
			} else {
				travel = new EmptyTravel(event.getStart(), event.getEnd());
			}
		}
		
		schedule.setEvent(event);
		schedule.setTravel(travel);
		
		calendar.add(schedule);
		calendarRepo.save(calendar);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
		          .path("/calendar/event/{id}")
		          .buildAndExpand(schedule.getId())
		          .toUri();
		
		return ResponseEntity.created(uri).body(schedule);
	}
	
	@PostMapping(value = "/calendar/means/set")
	public void setPreferedMeans(@RequestBody Map<String, Boolean> means){
		List<Mean> uploaded = new ArrayList<>();
		
		if (means.get("publicTransport"))
			uploaded.add(Mean.PUBLIC_TRANPORT);
		
		if (means.get("foot"))
			uploaded.add(Mean.FOOT);
		
		if (means.get("car"))
			uploaded.add(Mean.CAR);
		
		User user = calendar.getUser();
		user.setPreferedeMeans(uploaded);
		
		userRepo.save(user);
	}
}
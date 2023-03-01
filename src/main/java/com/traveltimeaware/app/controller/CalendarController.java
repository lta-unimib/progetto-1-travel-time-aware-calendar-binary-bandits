package com.traveltimeaware.app.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.traveltimeaware.app.domain.*;
import com.traveltimeaware.app.domain.repo.CalendarRepository;
import com.traveltimeaware.app.security.CustomUserDetails;
import com.traveltimeaware.app.security.domain.User;

@Controller
public class CalendarController {
	
	@Autowired
	CalendarRepository calendarRepo;
	
	private Calendar active;
	private User logged;
	
	@GetMapping("/")
	public ModelAndView getHomepage() {		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		logged = ((CustomUserDetails) principal).getUser();
		
		String email = "";
		if (principal instanceof CustomUserDetails) {
			email = logged.getEmail();
		} else {
			email = principal.toString();
		}
		
		active = calendarRepo.findByEmail(email);
		
		JsonObject json = new JsonObject();
		for(Day d : active.getDays()) {
			String date = new SimpleDateFormat("dd-MM-yyyy").format(d.getDay());
			json.addProperty(date, d.getMeetings().size());
		}
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("calendar");
		mv.getModel().put("events", json.toString());
	
		return mv;
	}
	
	@GetMapping("/create/meeting")
	public ModelAndView getCreateMeetingPage() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("createMeeting");
		
		return mv;
	}
	
	@PostMapping("/create/meeting")
	public void addMeeting(@RequestBody Map<String, String> payload) {
		Date date = null;
		try {
			date = new SimpleDateFormat("dd/MM/yyyy").parse(payload.get("start"));
		} catch (ParseException e) {
			e.printStackTrace();
		}  
		
		Day day = active.getDay(date);
		
		List<Meeting> dayMeetings = new ArrayList<>(day.getMeetings());
		
		Meeting meeting;
		if(dayMeetings.size() == 0) { // primo meeting						
			LocalDateTime start = LocalDateTime.parse(payload.get("start"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
			LocalDateTime end = LocalDateTime.parse(payload.get("end"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
			meeting = new Meeting.Builder(start, end, payload.get("title"), payload.get("location"), null).build();
		} else {
			Meeting last = dayMeetings.get(dayMeetings.size() - 1);
			LocalDateTime start = LocalDateTime.parse(payload.get("start"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
			LocalDateTime end = LocalDateTime.parse(payload.get("end"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
			TravelTime travel = new TravelTime(last.getEnd(), start, Mean.PUBLIC_TRANSPORT, last.getLocation());
			meeting = new Meeting.Builder(start, end, payload.get("title"), payload.get("location"), null).build();
		}
		
		day.addMeeting(meeting);
		active.addDay(day);
		calendarRepo.save(active);
	}
	
	@GetMapping("preferences")
	public ModelAndView getPreferenceMeanPage() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("preferences");
		
		return mv;
	}
	
	@PostMapping("preferences")
	public void setPreferenceMean(@RequestBody String json) {
		ObjectMapper objectMapper = new ObjectMapper();
		String[] langs = null;
		
		try {
			langs = objectMapper.readValue(json, String[].class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		for(int i = 0; i < json.length(); i++) {
			switch(langs[i]) {
			case "PUBLIC_TRANSPORT":
				logged.getPreferenceMean().addMean(Mean.PUBLIC_TRANSPORT);
				
			case "CAR":
				logged.getPreferenceMean().addMean(Mean.CAR);
				
			case "FOOT":
				logged.getPreferenceMean().addMean(Mean.FOOT);
			}
		}
	}
}

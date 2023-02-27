package com.traveltimeaware.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.JsonObject;
import com.traveltimeaware.app.domain.Calendar;
import com.traveltimeaware.app.domain.Day;
import com.traveltimeaware.app.domain.repo.CalendarRepository;
import com.traveltimeaware.app.security.CustomUserDetails;

@Controller
public class CalendarController {
	
	@Autowired
	CalendarRepository calendarRepo;
	
	@GetMapping("/")
	public ModelAndView getHomepage() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		String email = "";
		if (principal instanceof CustomUserDetails) {
			email = ((CustomUserDetails) principal).getUsername();
		} else {
			email = principal.toString();
		}
		
		
		Calendar c = calendarRepo.findByEmail(email);
		
		JsonObject json = new JsonObject();
		for(Day d : c.getDays()) {
			json.addProperty(d.getDay().toString(), d.getMeetings().size());
		}
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("calendar");
		mv.getModel().put("days", json.toString());
	
		return mv;
	}
}

package com.traveltimeaware.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.traveltimeaware.app.domain.Calendar;
import com.traveltimeaware.app.domain.repo.CalendarRepository;
import com.traveltimeaware.app.security.domain.User;

@Controller
public class CalendarController {
	
	@Autowired
	CalendarRepository calendarRepo;
	
	@GetMapping("/")
	public ModelAndView getHomepage() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("calendar");
		// mv.getModel().put("data", "data");
		
		return mv;
	}
	
	@GetMapping("/provac")
	public ModelAndView tryed() {
		Calendar c = calendarRepo.findByEmail("user@email.com");
		
		System.out.print(c.getDays());
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("signup");
		mv.getModel().put("user", new User());
	
		return mv;
	}
}

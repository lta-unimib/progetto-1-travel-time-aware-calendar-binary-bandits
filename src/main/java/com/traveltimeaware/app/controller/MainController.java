package com.traveltimeaware.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/")
public class MainController {
	
	@GetMapping
	public ModelAndView getHomepage() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("calendar");
		// mv.getModel().put("data", "data");
		
		return mv;
	}
}

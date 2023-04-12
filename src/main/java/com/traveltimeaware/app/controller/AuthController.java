package com.traveltimeaware.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.traveltimeaware.app.domain.profile.User;
import com.traveltimeaware.app.repository.UserRepository;

@Controller
public class AuthController {

	@Autowired
	private UserRepository userRepo;
	
	@PostMapping("/register")
	public RedirectView processRegister(User user) {
	    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	    String encodedPassword = passwordEncoder.encode(user.getPassword());
	    user.setPassword(encodedPassword);
	     
	    userRepo.save(user);
	     
	    return new RedirectView("/login");
	}
	
	@GetMapping("/register")
	public ModelAndView getRegistrationPage() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("auth/registration");
		mv.getModel().put("user", new User());
		
		return mv;
	}
}

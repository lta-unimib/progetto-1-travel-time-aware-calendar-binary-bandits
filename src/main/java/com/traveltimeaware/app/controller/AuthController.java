package com.traveltimeaware.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.traveltimeaware.app.security.domain.User;
import com.traveltimeaware.app.security.repo.UserRepository;

@Controller
public class AuthController {
	
	@Autowired
    private UserRepository userRepo;
	
	/*
	@GetMapping("/users")
	public ModelAndView listUsers() {
	    List<User> listUsers = userRepo.findAll();
	    
		ModelAndView mv = new ModelAndView();
		mv.setViewName("users");
	    mv.getModel().put("listUsers", listUsers);
	     
	    return mv;
	} 
	*/
     
    @GetMapping("/register")
    public ModelAndView showRegistrationForm() {
    	ModelAndView mv = new ModelAndView();
		mv.setViewName("signup");
		mv.getModel().put("user", new User());
	
		return mv;
    }
    
    @PostMapping("/register")
    public boolean processRegister(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
         
        userRepo.save(user);
         
        return true;
    }
}

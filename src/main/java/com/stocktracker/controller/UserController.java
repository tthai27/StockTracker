package com.stocktracker.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.stocktracker.service.UserService;

@Controller
public class UserController {
	
	Logger logger =  Logger.getLogger(UserController.class);   
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login() {
		logger.info("login in");
		logger.info("size="+userService.getAllUsers().size());
		return "login";
	}
	
	
	
//
//	   @RequestMapping(value = "/accessdenied", method = RequestMethod.GET)
//	    public String loginerror(ModelMap model) {
//	        model.addAttribute("error", "true");
//	        return "403";
//	    }
	

	
}

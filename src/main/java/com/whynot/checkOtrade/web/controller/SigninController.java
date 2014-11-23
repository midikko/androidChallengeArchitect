package com.whynot.checkOtrade.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SigninController {

	@RequestMapping(value = "signin", method = RequestMethod.GET)
	public String signin() {
        return "signin/signin";
    }
	
	@RequestMapping(value = "signin", method = RequestMethod.POST)
	public String signinSubmit(@ModelAttribute Model model) {
		
        return "signin/signin";
    }
}

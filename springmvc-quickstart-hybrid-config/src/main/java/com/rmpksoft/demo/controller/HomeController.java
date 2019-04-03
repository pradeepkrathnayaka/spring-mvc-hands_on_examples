package com.rmpksoft.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	private final MessageSource messageSource;

	@Autowired
	public HomeController(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	@RequestMapping("/")
	public String welcome(Model model) {
		model.addAttribute("greeting", "Welcome to Web Store!");
		return "forward:/welcome/home";

	}

	@RequestMapping("/welcome/home")
	public String greeting(Model model) {
		return "home";
	}

}

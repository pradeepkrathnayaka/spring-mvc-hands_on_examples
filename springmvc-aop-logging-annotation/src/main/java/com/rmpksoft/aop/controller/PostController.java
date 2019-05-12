package com.rmpksoft.aop.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.rmpksoft.aop.model.Post;

@CrossOrigin
@Controller
@SessionAttributes("postBean")
public class PostController {
	
	final static private Logger logger = LoggerFactory.getLogger(PostController.class );
	
	@RequestMapping(value = "posts", method = RequestMethod.POST)
	public ModelAndView addPost(@ModelAttribute("postBean") Post post) {
		ModelAndView mav = new ModelAndView();
		return mav;
	}

}

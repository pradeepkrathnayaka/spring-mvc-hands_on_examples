package com.rmpksoft.aop.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.http.HttpStatus;

import com.rmpksoft.aop.model.Comment;
import com.rmpksoft.aop.service.CommentService;

@CrossOrigin
@Controller
@SessionAttributes("commentBean")
public class CommentController {
	
	final static private Logger logger = LoggerFactory.getLogger(CommentController.class );
	
	@Autowired
	private CommentService commentService;
	
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
    public String testView(Model model) {
        model.addAttribute("msg", "Spring test start!!");
        return "test-page";
    }
	
	/*@RequestMapping("/")
	public RedirectView home() {
		return new RedirectView("/comments");
	}*/
	
	@RequestMapping(value = "/comments", method = RequestMethod.GET)
	public @ResponseBody List<Comment> list(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Comment> commentList = new ArrayList<Comment>();
		commentList.add(new Comment(1L, "tetst"));
		
		return commentList;
	}
	
	
	/*@RequestMapping(value = "/comments", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("msg", "Spring test start!!");
        return "comment/list";
    }*/
	
	/*Create comment page*/
	@RequestMapping(value = "/comments/save", method = RequestMethod.GET)    
    public String createCommentPage(Model model){    
		model.addAttribute("comment", new Comment());  
        return "comment";   
    }    
	
	/*Save comment*/
	@RequestMapping(value = "/comments/save", method = RequestMethod.POST)
    public String saveComment(@ModelAttribute("comment") Comment comment) {
		try {
			commentService.save("");
		} catch (Exception e) {
			e.printStackTrace();
		}
        return "test-page";
    }
	
	/*Edit comment page*/
	@RequestMapping(value = "/comments/edit/{id}", method = RequestMethod.GET)
    public String editCommentPage(@PathVariable long id, Model model) {
		try {
			Comment comment = commentService.getCommentById(id);
			model.addAttribute("comment", comment);
		} catch (Exception e) {
			e.printStackTrace();
		} 
        return "comment/edit";
    }
	
	/*Save edited data*/
	@RequestMapping(value = "/comments/edit", method = RequestMethod.POST)
    public String saveEdit(@PathVariable int id, Model model) {
        model.addAttribute("msg", "Spring test start!!");
        return "comment/success";
    }
	
	
	@RequestMapping(value = "/comments/delete/{id}", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.NO_CONTENT)
    public String delete(@PathVariable long id) {
		try {
			commentService.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return "comment/success";
    }
	
	@RequestMapping(value = "/comments/{id}", method = RequestMethod.GET)
    public String findById(@PathVariable long id) {
        return "test-page";
    }

}

package edu.awieclawski.quiz.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
	@GetMapping({ "/", "/hello", "/welcome" })
	public String hello(Model model) {
		return "welcome";
	}
}

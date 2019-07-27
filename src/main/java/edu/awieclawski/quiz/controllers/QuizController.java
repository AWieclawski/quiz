package edu.awieclawski.quiz.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/quiz")
public class QuizController {

	
	@GetMapping(path = "/firststep")
	public String redirectToFirstStep(Model model) {
		return "redirect:/testtype/firststep";
	}

	@GetMapping(path = "/secondstep")
	public String redirectToSecondStep(Model model) {
		return "redirect:/difficultylevel/secondstep";
	}

}

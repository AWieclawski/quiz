package edu.awieclawski.quiz.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/quiz")
public class QuizController {

	@RequestMapping(path = "/firststep")
	public String redirectToFirstStep(Model model) {
		return "forward:/testtype/firststep";
	}

}

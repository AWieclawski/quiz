package edu.awieclawski.quiz.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.awieclawski.quiz.repositories.TestTypeRepository;

@Controller
@RequestMapping(path = "/quiz")
public class QuizController {

	@Autowired
	TestTypeRepository testTypeRepository;
	
	@GetMapping(path="firststep")
	public String presentTestTypes(Model model) {
		model.addAttribute("testTypes", testTypeRepository.findAll());
		return "quiz/stepfirst";
	}
	
	

}

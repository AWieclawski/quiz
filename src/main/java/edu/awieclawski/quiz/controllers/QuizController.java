package edu.awieclawski.quiz.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.awieclawski.quiz.repositories.DifficultyLevelRepository;
import edu.awieclawski.quiz.repositories.TestTypeRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Controller
@RequestMapping(path = "/quiz")
public class QuizController {
	
	private static final Logger logger = LogManager.getLogger(QuizController.class.getName());


	@Autowired
	TestTypeRepository testTypeRepository;

	@GetMapping(path = "firststep")
	public String presentTestTypes(Model model) {
		model.addAttribute("testTypes", testTypeRepository.findAll());
		return "quiz/stepfirst";
	}

	@PostMapping(path = "firststep")
	public String selectTestType(@ModelAttribute ("testType_Id") Long selectedTestTypeId, ModelMap model) {
		model.addAttribute("selectedTestType", testTypeRepository.findById(selectedTestTypeId).get());
		return "quiz/stepsecond";
	}
	
	@Autowired
	DifficultyLevelRepository difficultyLevelRepository;
	
	@GetMapping(path = "secondstep")
	public String presentDifficultyLevels(Model model) {
		model.addAttribute("difficultyLevels", difficultyLevelRepository.findAll());
        logger.info("difficultyLevels length" + difficultyLevelRepository.count());
		return "quiz/stepsecond";
	}
	
	
}

package edu.awieclawski.quiz.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.awieclawski.quiz.models.TestType;
import edu.awieclawski.quiz.models.DifficultyLevel;
import edu.awieclawski.quiz.models.Test;
import edu.awieclawski.quiz.repositories.TestRepository;

@Controller
@RequestMapping(path = "/test")
@SessionAttributes("sessionTest")
public class TestController {

	private static final Logger logger = LogManager.getLogger(TestController.class.getName());

	@Autowired
	private TestRepository testRepository;

	@ModelAttribute("sessionTest")
	public Test setUpTest() {
		return new Test();
	}

	@GetMapping(path = "/thirdstep")
	public String presentTests(
			@SessionAttribute("sessionTestType") TestType selectedTestType, 
			@SessionAttribute("sessionDifficultyLevel") DifficultyLevel selectedDifficultyLevel,
			ModelMap model) {
		model.addAttribute("sessionTestType", selectedTestType);
		logger.info(" ### sessionTestType get from session: " + selectedTestType.toString());
		model.addAttribute("selectedDifficultyLevel", selectedDifficultyLevel);
		logger.info(" ### selectedDifficultyLevel get from session: " + selectedDifficultyLevel.toString());
				model.addAttribute("tests", testRepository.findAll());
		/*
		 * TODO - establish query in TestController "from Test t where
		 * t.testType.testTypeId =:testType_Id and
		 * t.difficultyLevel.difficultyLevelId=:difficultyLevel_Id"
		 * 
		 * or successful retrieve session attributes sessionTestType & sessionDifficultyLevel
		 */
		logger.info(" $$$ testRepository count: " + testRepository.count());
		return "/quiz/stepthird";
	}

	@PostMapping(path = "/thirdstep")
	public String selectTest(
			@ModelAttribute("test_Id") Long selectedTestId,
			@ModelAttribute("sessionTest") Test sessionTest, 
			ModelMap model, 
			RedirectAttributes redirectAttributes) {
		sessionTest = testRepository.findById(selectedTestId).get();
		model.addAttribute("selectedTest", sessionTest);
		logger.info(" *** selectedTest set to session: " + sessionTest.toString());
		return "redirect:/quiz/exam";
	}
}

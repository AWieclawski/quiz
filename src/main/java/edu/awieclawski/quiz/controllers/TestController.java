package edu.awieclawski.quiz.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.awieclawski.quiz.models.DifficultyLevel;
import edu.awieclawski.quiz.models.Test;
import edu.awieclawski.quiz.models.TestType;
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
			@SessionAttribute("sessionDifficultyLevel") DifficultyLevel selectedDifficultyLevel,
			@SessionAttribute("sessionTestType") TestType selectedTestType, 
			Model model) {
		model.addAttribute("selectedDifficultyLevel", selectedDifficultyLevel);
		logger.info(" ### selectedDifficultyLevel: " + selectedDifficultyLevel.toString());
		model.addAttribute("sessionTestType", selectedTestType);
		logger.info(" ### sessionTestType: " + selectedTestType.toString());
		model.addAttribute("tests", testRepository.findAll());
/*				.findByTestTypeAndDifficultyLevel(selectedTestType,selectedDifficultyLevel));
*				TODO - establish query in TestController
*				"from Test t 
*				where t.testType.testTypeId =:testType_Id 
*				and t.difficultyLevel.difficultyLevelId=:difficultyLevel_Id"
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
		sessionTest = testRepository
				.findById(selectedTestId).get();
		model.addAttribute("selectedTest", sessionTest);
		logger.info(" *** selectedTest: " + sessionTest.toString());
		return "redirect:/quiz/exam";
	}
}

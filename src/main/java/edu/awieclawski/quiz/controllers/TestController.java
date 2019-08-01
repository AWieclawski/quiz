package edu.awieclawski.quiz.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.awieclawski.quiz.models.TestType;
import edu.awieclawski.quiz.models.DifficultyLevel;
import edu.awieclawski.quiz.models.Test;
import edu.awieclawski.quiz.repositories.TestRepository;

@Controller
@RequestMapping(path = "/test")
public class TestController {

	private static final Logger logger = LogManager.getLogger(TestController.class.getName());

	@Autowired
	private TestRepository testRepository;

	@GetMapping(path = "/thirdstep")
	public String presentTests(
			HttpServletRequest request, 
			ModelMap model) {
		HttpSession session = request.getSession(false);
		TestType selectedTestType = (TestType) session.getAttribute("sessionTestType");
		model.addAttribute("selectedTestType", selectedTestType);
		logger.info(" ### sessionTestType get from session: " + selectedTestType.toString());
		DifficultyLevel selectedDifficultyLevel = 
				(DifficultyLevel) session.getAttribute("sessionDifficultyLevel");
		model.addAttribute("selectedDifficultyLevel", selectedDifficultyLevel);
		logger.info(" ### selectedDifficultyLevel get from session: " 
		+ selectedDifficultyLevel.toString());
		model.addAttribute("tests", testRepository
//		.findByTestTypeAndDifficultyLevel(selectedTestType,selectedDifficultyLevel));
		.findAll());
		/*
		 * TODO - establish query in TestController "from Test t where
		 * t.testType.testTypeId =:testType_Id and
		 * t.difficultyLevel.difficultyLevelId=:difficultyLevel_Id"
		 */
		logger.info(" $$$ testRepository count: " + testRepository.count());
		return "/quiz/stepthird";
	}

	@PostMapping(path = "/thirdstep")
	public String selectTest(
			@ModelAttribute("test_Id") Long selectedTestId,
			ModelMap model, 
			HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		HttpSession session = request.getSession(false);
		Test selectedTest = testRepository.findById(selectedTestId).get();
		session.setAttribute("sessionTest", selectedTest);
		logger.info(" *** sessionTest set to session: " + selectedTest.toString());
		return "redirect:/quiz/exam";
	}
}

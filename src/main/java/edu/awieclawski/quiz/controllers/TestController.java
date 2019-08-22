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
	private String infoMessageInit = "OK. Selected: ";
	private String errorMessageInit = "Select ";
	private String resultsName = "tests";
	private String resultName = "test";

	@Autowired
	private TestRepository testRepository;

	@GetMapping(path = "/thirdstep")
	public String presentTests(
			@ModelAttribute("errorMessage") String errorMessageReceived,
			@ModelAttribute("infoMessage") String infoMessageReceived,
			HttpServletRequest request, 
			ModelMap model) {
		HttpSession session = request.getSession(false);
		model.addAttribute("errorMessageToDisplay", errorMessageReceived);
		model.addAttribute("infoMessageToDisplay", infoMessageReceived);
		TestType selectedTestType = (TestType) session.getAttribute("sessionTestType");
		model.addAttribute("selectedTestType", selectedTestType);
		DifficultyLevel selectedDifficultyLevel = (DifficultyLevel) session.getAttribute("sessionDifficultyLevel");
		model.addAttribute("selectedDifficultyLevel", selectedDifficultyLevel);
		Iterable<Test> resultsThatMeetSelectedCriteria = testRepository
				.findTestsByTestTypeAndDifficultyLevel(selectedTestType, selectedDifficultyLevel);
		model.addAttribute("results", resultsThatMeetSelectedCriteria);
		model.addAttribute("resultsName", resultsName);
		model.addAttribute("resultName", resultName);
		return "/quiz/stepthird";
	}

	@PostMapping(path = "/thirdstep")
	public String selectTest(
			@ModelAttribute("errorMessage") String errorMessage,
			@ModelAttribute("infoMessage") String infoMessage,
			ModelMap model, 
			HttpServletRequest request, 
			RedirectAttributes redirectAttributes) {
		HttpSession session = request.getSession(false);
		//Reset session attributes after backward in http browser
		session.removeAttribute("thisTestQuestionSetsProxyList");
		session.removeAttribute("thisTestQuestionSet");
		session.removeAttribute("thisTestSelectionsMap");
		
		String selectedTestIdToString = null;
		selectedTestIdToString = request.getParameter("submittedTest_Id");

		if (selectedTestIdToString != null) {
			Long selectedTestId = Long.valueOf(selectedTestIdToString);
			Test selectedTest = testRepository.findById(selectedTestId).get();
			session.setAttribute("sessionTest", selectedTest);
			logger.info(" *** sessionTest set to session: " + selectedTest.toString());
			infoMessage = infoMessageInit.concat(selectedTest.getTestName());
			redirectAttributes.addFlashAttribute("infoMessage", infoMessage);
			return "redirect:/quiz/exam";
//			return "redirect:/questionset/exam";
		} else {
			errorMessage = errorMessageInit.concat(resultName).concat("!");
			logger.info(" ^^^ errorMessage flash redirect: " + errorMessage);
			redirectAttributes.addFlashAttribute("errorMessage", errorMessage);
			return "redirect:/quiz/thirdstep";
		}
	}
}

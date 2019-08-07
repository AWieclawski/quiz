package edu.awieclawski.quiz.controllers;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.awieclawski.quiz.models.QuestionSet;
import edu.awieclawski.quiz.models.Test;
import edu.awieclawski.quiz.repositories.QuestionSetRepository;


@Controller
@RequestMapping(path = "/questionset")
public class QuestionSetController {

	private static final Logger logger = LogManager.getLogger(QuestionSetController.class.getName());
	private String infoMessageInit = "OK. Selected: ";
	private String errorMessageInit = "Must select ";
	private String resultsName = "question sets";
	private String resultName = "guestion set";

	@Autowired
	private QuestionSetRepository questionSetRepository;

	@GetMapping(path = "/exam")
	public String presentQuestionSets(
			@ModelAttribute("errorMessage") String errorMessageReceived,
			@ModelAttribute("infoMessage") String infoMessageReceived, 
			HttpServletRequest request, ModelMap model) {
		model.addAttribute("errorMessageToDisplay", errorMessageReceived);
		model.addAttribute("infoMessageToDisplay", infoMessageReceived);
		HttpSession session = request.getSession(false);

		Test selectedTest = (Test) session.getAttribute("sessionTest");
		logger.info(" ### selectedTest get from session: " + selectedTest.getTestName());

		Integer currentQuestion = (Integer) session.getAttribute("currentQuestion") ;
		
//		conditional initiation of question number & session attribute
		if (currentQuestion == null) {
			session.setAttribute("currentQuestion", 0);
			currentQuestion = (Integer) session.getAttribute("currentQuestion") ;
		}
		
		logger.info(" $$$ currentQuestion: " + currentQuestion);

		if (selectedTest != null) {

			List<QuestionSet> thisTestQestionSetsList = questionSetRepository.findQuestionSetsByTest(selectedTest);
			logger.info(" $$$ resultsThatMeetSelectedCriteria enumeration: " + thisTestQestionSetsList.toString());
			session.setAttribute("thisTestQuestionSetsList", thisTestQestionSetsList);

			if (thisTestQestionSetsList.size() > 0) {
				int totalNumberOfQuestions = thisTestQestionSetsList.size();
				logger.info(" $$$ totalNumberOfQuestions: " + totalNumberOfQuestions);
				session.setAttribute("thisTestTotalNumberOfQuestions", totalNumberOfQuestions);
				
				Map<Integer, String> mapOfUserAnswers = (Map<Integer, String>) session
						.getAttribute("thisTestSelectionsMap");
				logger.info(" $$$ thisTestSelectionsMap: " + mapOfUserAnswers);

				model.addAttribute("questionNumber", currentQuestion);
				model.addAttribute("questionSet", thisTestQestionSetsList.get(currentQuestion));
				model.addAttribute("results", mapOfUserAnswers);
				model.addAttribute("resultsName", resultsName);
				model.addAttribute("resultName", resultName);
				model.addAttribute("thisTest", selectedTest);
				return "/quiz/exam";
			}
		}
		return "redirect:/quiz/stepthird";

	}
	
}

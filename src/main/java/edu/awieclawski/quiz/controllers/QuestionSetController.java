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
import edu.awieclawski.quiz.services.ExamServices;
import edu.awieclawski.quiz.services.QuestionSetProxy;

@Controller
@RequestMapping(path = "/questionset")
public class QuestionSetController {

	private static final Logger logger = LogManager.getLogger(QuestionSetController.class.getName());
	private String infoMessageInit = "OK. Selected: ";
	private String errorMessageInit = "Must select ";
	private String resultsName = "question sets";
	private String resultName = "guestion set";

	private ExamServices examServices;

	@Autowired
	public void setExamServices(ExamServices examServices) {
		this.examServices = examServices;
	}

	@GetMapping(path = "/exam")
	public String presentQuestionSets(
			@ModelAttribute("errorMessage") String errorMessageReceived,
			@ModelAttribute("infoMessage") String infoMessageReceived, 
			HttpServletRequest request, 
			ModelMap model) {
		model.addAttribute("errorMessageToDisplay", errorMessageReceived);
		model.addAttribute("infoMessageToDisplay", infoMessageReceived);
		HttpSession session = request.getSession(false);

		Test selectedTest = (Test) session.getAttribute("sessionTest");
		logger.info(" ### selectedTest get from session: " + selectedTest.getTestName());

		Integer currentQuestion = (Integer) session.getAttribute("currentQuestion");

//		conditional initiation of question number & session attribute
		if (currentQuestion == null) {
			currentQuestion = 1;
			session.setAttribute("currentQuestion", currentQuestion);
		}

		logger.info(" $$$ currentQuestion: " + currentQuestion);

		if (selectedTest != null) {

			List<QuestionSetProxy> thisTestQuestionSetsProxyList = 
					examServices.questionSetProxyListSetup(selectedTest);

			if (thisTestQuestionSetsProxyList.size() > 0) {
				int totalNumberOfQuestions = thisTestQuestionSetsProxyList.size();
				logger.info(" $$$ totalNumberOfQuestions: " + totalNumberOfQuestions);
				session.setAttribute("thisTestTotalNumberOfQuestions", totalNumberOfQuestions);
				
				QuestionSetProxy thisTestQestionSetProxy = thisTestQuestionSetsProxyList.get(currentQuestion-1);
				logger.info(" $$$ thisTestQestionSetProxy: " + thisTestQestionSetProxy
						+ ", $$$ currentQuestion: "+currentQuestion);
				session.setAttribute("thisTestQuestionSet", thisTestQestionSetProxy);
				
				@SuppressWarnings("unchecked")
				Map<Integer, String> mapOfUserAnswers = (Map<Integer, String>) session
						.getAttribute("thisTestSelectionsMap");
				logger.info(" $$$ thisTestSelectionsMap: " + mapOfUserAnswers);

				// Initialization of 'nothing selected' user's answers map
				if (mapOfUserAnswers == null) {
					mapOfUserAnswers = examServices.userSelectionsMapInitialization(totalNumberOfQuestions);
					logger.info(" $$$ userSelectionsMapInit: " + mapOfUserAnswers);
					session.setAttribute("thisTestSelectionsMap", mapOfUserAnswers);
				}

				model.addAttribute("currentQuestionNumber", currentQuestion);
				model.addAttribute("currentQuestionSet", thisTestQestionSetProxy);
				model.addAttribute("thisTest", selectedTest);
				model.addAttribute("results", mapOfUserAnswers);
				model.addAttribute("resultsName", resultsName);
				model.addAttribute("resultName", resultName);
				return "/quiz/exam";
			}
		}
		return "redirect:/quiz/stepthird";

	}

}

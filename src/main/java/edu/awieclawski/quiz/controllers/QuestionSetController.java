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

import edu.awieclawski.quiz.models.Test;
import edu.awieclawski.quiz.services.ExamServices;
import edu.awieclawski.quiz.services.QuestionSetProxy;

/*
 * Controller works only on striped down proxy of QuestionSet.
 * Correct answers, test type, difficulty level are NOT available nor in session, neither here.  
*/
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
//			@ModelAttribute("errorMessage") String errorMessageReceived,
//			@ModelAttribute("infoMessage") String infoMessageReceived, 
			HttpServletRequest request, 
			ModelMap model) {
		
//		model.addAttribute("errorMessageToDisplay", errorMessageReceived);
//		model.addAttribute("infoMessageToDisplay", infoMessageReceived);
		HttpSession session = request.getSession(false);

		Test selectedTest = (Test) session.getAttribute("sessionTest");
		logger.info(" !!! selectedTest get from session: " + selectedTest);

		Integer currentQuestion = (Integer) session.getAttribute("currentQuestion");
		logger.info(" !!! currentQuestion  get from session: " + currentQuestion );
		
		QuestionSetProxy thisTestQuestionSetProxy = 
				(QuestionSetProxy) session.getAttribute("thisTestQuestionSet");
		logger.info(" !!! thisTestQestionSetProxy get from session: " + thisTestQuestionSetProxy
				+ ", !!! currentQuestion  get from session: "+currentQuestion);

		@SuppressWarnings("unchecked")
		Map<Integer, String> mapOfUserAnswers = (Map<Integer, String>) session
		.getAttribute("thisTestSelectionsMap");
		logger.info(" !!! thisTestSelectionsMap get from session: " + mapOfUserAnswers);
		
		Integer totalNumberOfQuestions = (Integer) session.getAttribute("thisTestTotalNumberOfQuestions");
		logger.info(" !!! totalNumberOfQuestions get from session: " + totalNumberOfQuestions);

//		conditional initiation of question number & session attribute
		if (currentQuestion == null) {
			currentQuestion = 1;
			session.setAttribute("currentQuestion", currentQuestion);
			logger.info(" ### currentQuestion: " + currentQuestion);
		}


		if (selectedTest != null) {

			List<QuestionSetProxy> thisTestQuestionSetsProxyList = 
					examServices.questionSetProxyListSetup(selectedTest);
			logger.info(" @@@ totalNumberOfQuestions: " + thisTestQuestionSetsProxyList);

			if (thisTestQuestionSetsProxyList.size() > 0) {
				
				if(totalNumberOfQuestions == null) {
				totalNumberOfQuestions = thisTestQuestionSetsProxyList.size();
				logger.info(" ### totalNumberOfQuestions setAttribute: " + totalNumberOfQuestions);
				session.setAttribute("thisTestTotalNumberOfQuestions", totalNumberOfQuestions);
				}
				
				// initialization of  thisTestQuestionSetProxy on start
				if(thisTestQuestionSetProxy == null) {
					thisTestQuestionSetProxy = thisTestQuestionSetsProxyList.get(currentQuestion-1);
				logger.info(" $$$ thisTestQestionSetProxy setAttribute: " + thisTestQuestionSetProxy
						+ ", $$$ currentQuestion setAttribute: "+currentQuestion);
				session.setAttribute("thisTestQuestionSet", thisTestQuestionSetProxy);
				}
				
				// Initialization of 'nothing selected' user's answers map
				if (mapOfUserAnswers == null) {
					mapOfUserAnswers = examServices.userSelectionsMapInitialization(totalNumberOfQuestions);
					logger.info(" $$$ userSelectionsMapInit setAttribute: " + mapOfUserAnswers);
					session.setAttribute("thisTestSelectionsMap", mapOfUserAnswers);
				}

			} // end of the block, when list of questions is not empty

			model.addAttribute("currentQuestionNumber", currentQuestion);
			logger.info(" *** currentQuestionNumber model: " + currentQuestion);
			
			model.addAttribute("currentQuestionSet", thisTestQuestionSetProxy);
			logger.info(" *** currentQuestionSet model: " + thisTestQuestionSetProxy);
			
			model.addAttribute("thisTest", selectedTest);
			logger.info(" *** thisTest model: " + selectedTest);
			
			model.addAttribute("results", mapOfUserAnswers);
			logger.info(" *** results model: " + mapOfUserAnswers);
			
			model.addAttribute("resultsName", resultsName);
			logger.info(" *** resultsName model: " + resultsName);
			
			model.addAttribute("resultName", resultName);
			logger.info(" *** resultName model: " + resultName);
			
		}
		
		return "/quiz/exam";
	}

}

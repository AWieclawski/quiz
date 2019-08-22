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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	private String errorMessageInit = "Select ";
	private String resultsName = "question sets";
	private String resultName = "guestion set";

	private ExamServices examServices;

	@Autowired
	public void setExamServices(ExamServices examServices) {
		this.examServices = examServices;
	}

	@GetMapping(path = "/exam")
	public String presentQuestionSets(@ModelAttribute("errorMessage") String errorMessageReceived,
			@ModelAttribute("infoMessage") String infoMessageReceived, HttpServletRequest request, ModelMap model) {

		model.addAttribute("errorMessageToDisplay", errorMessageReceived);
		model.addAttribute("infoMessageToDisplay", infoMessageReceived);
		HttpSession session = request.getSession(false);

		Test selectedTest = (Test) session.getAttribute("sessionTest");
		Integer currentQuestion = (Integer) session.getAttribute("currentQuestion");
		QuestionSetProxy thisTestQuestionSetProxy = (QuestionSetProxy) session.getAttribute("thisTestQuestionSet");

		@SuppressWarnings("unchecked")
		Map<Integer, String> mapOfUserAnswers = (Map<Integer, String>) session.getAttribute("thisTestSelectionsMap");
		Integer totalNumberOfQuestions = (Integer) session.getAttribute("thisTestTotalNumberOfQuestions");

//		conditional initiation of question number & session attribute
		if (currentQuestion == null) {
			currentQuestion = 1;
			session.setAttribute("currentQuestion", currentQuestion);
		}

		if (selectedTest != null) {

			List<QuestionSetProxy> thisTestQuestionSetsProxyList = examServices.questionSetProxyListSetup(selectedTest);

			if (thisTestQuestionSetsProxyList.size() > 0) {

				if (totalNumberOfQuestions == null) {
					totalNumberOfQuestions = thisTestQuestionSetsProxyList.size();
					session.setAttribute("thisTestTotalNumberOfQuestions", totalNumberOfQuestions);
					session.setAttribute("thisTestQuestionSetList", thisTestQuestionSetsProxyList);
				}

				// initialization of thisTestQuestionSetProxy on start
				if (thisTestQuestionSetProxy == null) {
					thisTestQuestionSetProxy = thisTestQuestionSetsProxyList.get(currentQuestion - 1);
					session.setAttribute("thisTestQuestionSet", thisTestQuestionSetProxy);
				}

				// Initialization of 'nothing selected' user's answers map
				if (mapOfUserAnswers == null) {
					mapOfUserAnswers = examServices.userSelectionsMapInitialization(totalNumberOfQuestions);
					session.setAttribute("thisTestSelectionsMap", mapOfUserAnswers);
				}

			} // end of the block, when list of questions is not empty

			model.addAttribute("currentQuestionNumber", currentQuestion);
			model.addAttribute("currentQuestionSet", thisTestQuestionSetProxy);
			model.addAttribute("currentTestTotalNumberOfQuestions", totalNumberOfQuestions);
			model.addAttribute("thisTest", selectedTest);
			model.addAttribute("results", mapOfUserAnswers);
			model.addAttribute("resultsName", resultsName);
			model.addAttribute("resultName", resultName);
		}

		return "/quiz/exampresentation";
	}

	@PostMapping(path = "/exam")
	public String selectQuestionSets(@ModelAttribute("errorMessage") String errorMessage,
			@ModelAttribute("infoMessage") String infoMessage, ModelMap model, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		HttpSession session = request.getSession(false);

		Test selectedTest = (Test) session.getAttribute("sessionTest");
		Integer currentQuestion = (Integer) session.getAttribute("currentQuestion");
		QuestionSetProxy thisTestQuestionSetProxy = (QuestionSetProxy) session.getAttribute("thisTestQuestionSet");

		@SuppressWarnings("unchecked")
		List<QuestionSetProxy> thisTestQuestionSetsProxyList = (List<QuestionSetProxy>) session
				.getAttribute("thisTestQuestionSetList");

		@SuppressWarnings("unchecked")
		Map<Integer, String> mapOfUserAnswers = (Map<Integer, String>) session.getAttribute("thisTestSelectionsMap");

		if (selectedTest != null) {

			String action = request.getParameter("action");
			String radio = request.getParameter("submittedAnswer_Id");
			String selectedRadio = "N/S";
			// TODO replace quizUserId value by session attribute after login functionality
			// added
			String quizUserId = "testUserId";

			int numberOfRadioSamples = thisTestQuestionSetProxy.getArrayOfAnswers().length;

			if (radio != null) {
				selectedRadio = radioTest(radio, numberOfRadioSamples);
				mapOfUserAnswers.replace(currentQuestion.intValue() - 1, selectedRadio);
				logger.info(" === User id: " + quizUserId + " selected answer: " + selectedRadio + " in question no: "
						+ currentQuestion);
			}

			if ("next".equals(action)) {
				currentQuestion++;
			} else if ("previous".equals(action)) {
				currentQuestion--;
			} else if ("finishExam".equals(action)) {
				return "redirect:/results/finish";

			}

			session.setAttribute("currentQuestion", currentQuestion);

			thisTestQuestionSetProxy = thisTestQuestionSetsProxyList.get(currentQuestion - 1);
			session.setAttribute("thisTestQuestionSet", thisTestQuestionSetProxy);
			session.setAttribute("thisTestSelectionsMap", mapOfUserAnswers);

			infoMessage = infoMessageInit.concat(selectedRadio).concat(" answer of question ")
					.concat(currentQuestion.toString());
			redirectAttributes.addFlashAttribute("infoMessage", infoMessage);
			return "redirect:/quiz/exam";

		} else {
			errorMessage = errorMessageInit.concat(resultName).concat("!");
			logger.info(" ^^^ errorMessage flash redirect: " + errorMessage);
			redirectAttributes.addFlashAttribute("errorMessage", errorMessage);
			return "redirect:/quiz/exam";
		}
	}

	private String radioTest(String testedRadio, int numberOfRadioSamples) {

		for (int i = 0; i < numberOfRadioSamples; i++) {
			// Establish value basing on order number in array +1
			String sample = Integer.toString(i + 1);
			if (sample.equals(testedRadio)) {
				testedRadio = sample;
				logger.info(" === selected answer: " + testedRadio);
				break;
			}
		}
		return testedRadio;
	}

}

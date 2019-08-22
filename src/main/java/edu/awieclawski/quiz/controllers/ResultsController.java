package edu.awieclawski.quiz.controllers;

import java.util.ArrayList;
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
//import edu.awieclawski.quiz.services.QuestionSetProxy;
import edu.awieclawski.quiz.services.TestRecapitulation;

@Controller
@RequestMapping("/results")
public class ResultsController {

	private static final Logger logger = LogManager.getLogger(ResultsController.class.getName());

	private ExamServices examServices;

//	private String infoMessageInit = "OK. Selected: ";
//	private String errorMessageInit = "Select ";
	private String resultsName = "results";
	private String resultName = "result";

	@Autowired
	public void setExamServices(ExamServices examServices) {
		this.examServices = examServices;
	}

	@GetMapping(path = "/finish")
	public String presentQuestionSets(@ModelAttribute("errorMessage") String errorMessageReceived,
			@ModelAttribute("infoMessage") String infoMessageReceived, HttpServletRequest request, ModelMap model) {

		model.addAttribute("errorMessageToDisplay", errorMessageReceived);
		model.addAttribute("infoMessageToDisplay", infoMessageReceived);
		HttpSession session = request.getSession(false);

		Test selectedTest = (Test) session.getAttribute("sessionTest");

		@SuppressWarnings("unchecked")
		Map<Integer, String> mapOfUserAnswers = (Map<Integer, String>) session.getAttribute("thisTestSelectionsMap");
		Integer totalNumberOfQuestions = (Integer) session.getAttribute("thisTestTotalNumberOfQuestions");
		List<TestRecapitulation> userSelectionsMapVerificationResults = new ArrayList<TestRecapitulation>();

		if (selectedTest != null && mapOfUserAnswers != null) {

			userSelectionsMapVerificationResults = examServices.userSelectionsMapVerification(selectedTest,
					mapOfUserAnswers);
			logger.info(" @@@ userSelectionsMapVerificationResults get from examServices: "
					+ userSelectionsMapVerificationResults);
		}

		model.addAttribute("currentTestTotalNumberOfQuestions", totalNumberOfQuestions);
		model.addAttribute("thisTest", selectedTest);
		model.addAttribute("selections", mapOfUserAnswers);
		model.addAttribute("results", userSelectionsMapVerificationResults);
		model.addAttribute("resultsName", resultsName);
		model.addAttribute("resultName", resultName);
		return "/quiz/finish";
	}
}

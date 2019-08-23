package edu.awieclawski.quiz.controllers;

import java.time.LocalDate;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.awieclawski.quiz.models.Result;
import edu.awieclawski.quiz.models.Test;
import edu.awieclawski.quiz.models.User;
import edu.awieclawski.quiz.models.enums.Privileges;
import edu.awieclawski.quiz.models.enums.Statuses;
import edu.awieclawski.quiz.repositories.ResultRepository;
import edu.awieclawski.quiz.services.ExamServices;
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

	@Autowired
	private ResultRepository resultRepository;

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
			int userSelectionsCorrectAnswersCount = examServices
					.userSelectionsCorrectAnswersCount(userSelectionsMapVerificationResults);
			logger.info(" @@@ userSelectionsCorrectAnswersCount get from examServices: "
					+ userSelectionsCorrectAnswersCount);
			session.setAttribute("sessionUserSelectionsCorrectAnswersCount", userSelectionsCorrectAnswersCount);
		}

		// TODO replace quizUserId value by session attribute after login functionality
		// added
		User quizUser = new User(1L, "testUserName", "testUserLogin", "testUserPassword", Privileges.EXPLOITER,
				Statuses.ACTIVATED);
		session.setAttribute("sessionUser", quizUser);

		model.addAttribute("currentTestTotalNumberOfQuestions", totalNumberOfQuestions);
		model.addAttribute("thisTest", selectedTest);
		model.addAttribute("results", userSelectionsMapVerificationResults);
		model.addAttribute("resultsName", resultsName);
		model.addAttribute("resultName", resultName);
		return "/quiz/finish";
	}

	@PostMapping(path = "/finish")
	public String saveExitTest(@ModelAttribute("errorMessage") String errorMessage,
			@ModelAttribute("infoMessage") String infoMessage, ModelMap model, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		HttpSession session = request.getSession(false);
		Test selectedTest = (Test) session.getAttribute("sessionTest");
		User quizUser = (User) session.getAttribute("sessionUser");
		Integer goodAnswers = (Integer) session.getAttribute("sessionUserSelectionsCorrectAnswersCount");
		LocalDate resultDate = LocalDate.now();

		String action = request.getParameter("action");
		if ("save".equals(action)) {
			Result newResultToAdd = new Result();
			newResultToAdd.setGoodAnswers(goodAnswers);
			newResultToAdd.setResultDate(resultDate);
			newResultToAdd.setTest(selectedTest);
			newResultToAdd.setUser(quizUser);

			resultRepository.save(newResultToAdd);
			logger.info(" @@@ newResultToAdd saved in resultsController: " + newResultToAdd.toString());

			//Reset session attributes after finish the exam
			session.removeAttribute("sessionTestType");
			session.removeAttribute("sessionDifficultyLevel");
			session.removeAttribute("sessionTest");
			session.removeAttribute("thisTestQuestionSetsProxyList");
			session.removeAttribute("thisTestQuestionSet");
			session.removeAttribute("thisTestSelectionsMap");
			session.removeAttribute("sessionUserSelectionsCorrectAnswersCount");
			
			return "redirect:/home";
		}
		return "redirect:/results/finish";
	}

}

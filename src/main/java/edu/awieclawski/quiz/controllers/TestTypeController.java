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
import edu.awieclawski.quiz.repositories.TestTypeRepository;

@Controller
@RequestMapping(path = "/testtype")
public class TestTypeController {

	private static final Logger logger = LogManager.getLogger(TestTypeController.class.getName());
	private String infoMessageInit = "OK. Selected: ";
	private String errorMessageInit = "Select ";
	private String resultsName = "test types";
	private String resultName = "test type";

	@Autowired
	private TestTypeRepository testTypeRepository;

	@GetMapping(path = "/firststep")
	public String presentTestTypes(
			@ModelAttribute("errorMessage") String errorMessageReceived,
			@ModelAttribute("infoMessage") String infoMessageReceived,
			HttpServletRequest request,
			ModelMap model) {
		model.addAttribute("errorMessageToDisplay", errorMessageReceived);
		model.addAttribute("infoMessageToDisplay", infoMessageReceived);
		model.addAttribute("results", testTypeRepository.findAll());
		model.addAttribute("resultsName", resultsName);
		model.addAttribute("resultName", resultName);
		logger.info(" $$$ testTypeRepository count: " + testTypeRepository.count());
		return "/quiz/stepfirst";
	}

	@PostMapping(path = "/firststep")
	public String selectTestType(
			@ModelAttribute("errorMessage") String errorMessage,
			@ModelAttribute("infoMessage") String infoMessage,
			HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		HttpSession session = request.getSession(false);
		//Reset session attributes after restart quiz from menu
		session.removeAttribute("thisTestQuestionSetsProxyList");
		session.removeAttribute("thisTestQuestionSet");
		session.removeAttribute("thisTestSelectionsMap");
		
		if (session.equals(null)) {
			session = request.getSession();
		}
        String selectedTestTypeIdToString = null;
        selectedTestTypeIdToString = request.getParameter("submittedTestType_Id");
		
		if (selectedTestTypeIdToString != null) {
			Long selectedTestTypeId = Long.valueOf(selectedTestTypeIdToString);
			TestType selectedTestType = testTypeRepository.findById(selectedTestTypeId).get();
			session.setAttribute("sessionTestType", selectedTestType);
			logger.info(" *** sessionTestType set to session: " + selectedTestType.toString());
			infoMessage = infoMessageInit.concat(selectedTestType.getTestTypeName());
			redirectAttributes.addFlashAttribute("infoMessage", infoMessage);
			return "redirect:/quiz/secondstep";
		} else {
			errorMessage = errorMessageInit.concat(resultName).concat("!");
			logger.info(" ^^^ errorMessage flash redirect: " + errorMessage);
			redirectAttributes.addFlashAttribute("errorMessage", errorMessage);
			return "redirect:/quiz/firststep";
		}
	}

}

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

import edu.awieclawski.quiz.models.DifficultyLevel;
import edu.awieclawski.quiz.models.TestType;
import edu.awieclawski.quiz.repositories.DifficultyLevelRepository;

@Controller
@RequestMapping(path = "/difficultylevel")
public class DifficultyLevelController {

	private static final Logger logger = LogManager.getLogger(DifficultyLevelController.class.getName());
	private String infoMessageInit = "OK. Selected: ";
	private String errorMessageInit = "Must select ";
	private String resultsName = "difficulty levels";
	private String resultName = "difficulty level";

	@Autowired
	DifficultyLevelRepository difficultyLevelRepository;

	@GetMapping(path = "/secondstep")
	public String presentDifficultyLevels(
			@ModelAttribute("errorMessage") String errorMessageReceived,
			@ModelAttribute("infoMessage") String infoMessageReceived,
			HttpServletRequest request, 
			ModelMap model) {
		model.addAttribute("errorMessageToDisplay", errorMessageReceived);
		model.addAttribute("infoMessageToDisplay", infoMessageReceived);
		HttpSession session = request.getSession(false);
		TestType selectedTestType = (TestType) session.getAttribute("sessionTestType");
		model.addAttribute("selectedTestType", selectedTestType);
		logger.info(" ### selectedTestType get from session: " + selectedTestType);
		model.addAttribute("results", difficultyLevelRepository.findAll());
		model.addAttribute("resultsName", resultsName);
		model.addAttribute("resultName", resultName);
		return "/quiz/stepsecond";
	}

	@PostMapping(path = "/secondstep")
	public String selectDifficultyLevel(
			@ModelAttribute("errorMessage") String errorMessage,
			@ModelAttribute("infoMessage") String infoMessage,
			HttpServletRequest request, 
			ModelMap model,
			RedirectAttributes redirectAttributes) {
		HttpSession session = request.getSession(false);
		String selectedDifficultyLevelIdToString = null;
		selectedDifficultyLevelIdToString = request.getParameter("submittedDifficultyLevel_Id");

		if (selectedDifficultyLevelIdToString != null) {
			Long selectedDifficultyLevelId = Long.valueOf(selectedDifficultyLevelIdToString);
			DifficultyLevel selectedDifficultyLevel = difficultyLevelRepository.
					findById(selectedDifficultyLevelId)
					.get();
			session.setAttribute("sessionDifficultyLevel", selectedDifficultyLevel);
			logger.info(" *** sessionDifficultyLevel set to session: " + selectedDifficultyLevel.toString());
			infoMessage = infoMessageInit.concat(selectedDifficultyLevel.getDifficultyLevelName());
			logger.info(" ^^^ infoMessage flash redirect: " + infoMessage);
			redirectAttributes.addFlashAttribute("infoMessage", infoMessage);
			return "redirect:/quiz/thirdstep";
		} else {
			errorMessage = errorMessageInit.concat(resultName).concat("!");
			logger.info(" ^^^ errorMessage flash redirect: " + errorMessage);
			redirectAttributes.addFlashAttribute("errorMessage", errorMessage);
			return "redirect:/quiz/secondstep";
		}
	}

}

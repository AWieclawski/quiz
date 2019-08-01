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

	private static final Logger logger = LogManager
			.getLogger(DifficultyLevelController.class.getName());

	@Autowired
	DifficultyLevelRepository difficultyLevelRepository;


	@GetMapping(path = "/secondstep")
	public String presentDifficultyLevels(
			HttpServletRequest request,
			ModelMap model) {
		HttpSession session = request.getSession(false);
		TestType selectedTestType = (TestType) session.getAttribute("sessionTestType");
		model.addAttribute("selectedTestType", selectedTestType);
		logger.info(" ### selectedTestType get from session: " + selectedTestType);
		model.addAttribute("difficultyLevels", difficultyLevelRepository.findAll());
		logger.info(" $$$ testRepository count: " + difficultyLevelRepository.count());
		return "/quiz/stepsecond";
	}

	@PostMapping(path = "/secondstep")
	public String selectDifficultyLevel(
			@ModelAttribute("difficultyLevel_Id") Long selectedDifficultyLevelId,
			HttpServletRequest request,
			ModelMap model,
			RedirectAttributes redirectAttributes) {
		HttpSession session = request.getSession(false);
		DifficultyLevel selectedDifficultyLevel = difficultyLevelRepository
				.findById(selectedDifficultyLevelId).get();
		session.setAttribute("sessionDifficultyLevel", selectedDifficultyLevel);
		logger.info(" *** sessionDifficultyLevel set to session: " 
		+ selectedDifficultyLevel.toString());
		return "redirect:/quiz/thirdstep";
//		return "redirect:/test/thirdstep";
	}

}

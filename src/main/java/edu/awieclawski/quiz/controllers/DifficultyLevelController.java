package edu.awieclawski.quiz.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.awieclawski.quiz.models.DifficultyLevel;
import edu.awieclawski.quiz.models.TestType;
import edu.awieclawski.quiz.repositories.DifficultyLevelRepository;

@Controller
@RequestMapping(path = "/difficultylevel")
@SessionAttributes("sessionDifficultyLevel")
public class DifficultyLevelController {

	private static final Logger logger = LogManager
			.getLogger(DifficultyLevelController.class.getName());

	@Autowired
	DifficultyLevelRepository difficultyLevelRepository;

	@ModelAttribute("sessionDifficultyLevel")
	public DifficultyLevel setUpDifficultyLevel() {
		return new DifficultyLevel();
	}

	@GetMapping(path = "/secondstep")
	public String presentDifficultyLevels(
			@SessionAttribute("sessionTestType") TestType selectedTestType, 
			ModelMap model) {
		model.addAttribute("selectedTestType", selectedTestType);
		logger.info(" ### selectedTestType get from session: " + selectedTestType);
		model.addAttribute("difficultyLevels", difficultyLevelRepository.findAll());
		logger.info(" $$$ testRepository count: " + difficultyLevelRepository.count());
		return "/quiz/stepsecond";
	}

	@PostMapping(path = "/secondstep")
	public String selectDifficultyLevel(
			@ModelAttribute("difficultyLevel_Id") Long selectedDifficultyLevelId,
			@ModelAttribute("sessionDifficultyLevel") DifficultyLevel sessionDifficultyLevel, 
			ModelMap model,
			RedirectAttributes redirectAttributes) {
		sessionDifficultyLevel = difficultyLevelRepository
				.findById(selectedDifficultyLevelId).get();
		model.addAttribute("selectedDifficultyLevel",sessionDifficultyLevel);
		logger.info(" *** selectedDifficultyLevel set to session: " + sessionDifficultyLevel.toString());
		
//		return "redirect:/quiz/thirdstep";
		return "redirect:/test/thirdstep";
	}

}

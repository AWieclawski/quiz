package edu.awieclawski.quiz.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/*
 * Enables operating controllers keep hidden before session layer
*/
@Controller
@RequestMapping(path = "/quiz")
public class QuizController {
	
	private static final Logger logger = LogManager.getLogger(QuizController.class.getName());

	@GetMapping(path = "/firststep")
	public String redirectToFirstStep(Model model) {
		return "forward:/testtype/firststep";
	}

	@GetMapping(path = "/secondstep")
	public String redirectToSecondStep(
			@ModelAttribute("selectedTestTypeName") String selectedTestTypeName,
			ModelMap model, RedirectAttributes redirectAttributes) {
		redirectAttributes.addAttribute("selectedTestTypeName", selectedTestTypeName);
		return "forward:/difficultylevel/secondstep";
	}

	@GetMapping(path = "/thirdstep")
	public String redirectToThirdStep(
			@ModelAttribute("selectedTestTypeName") String selectedTestTypeName,
			@ModelAttribute("selectedDifficultyLevelName") String selectedDifficultyLevelName, 
			ModelMap model,
			RedirectAttributes redirectAttributes) {
		redirectAttributes.addAttribute("selectedTestTypeName", selectedTestTypeName);
		logger.info(" ### selectedTestTypeName: " + selectedTestTypeName);
		redirectAttributes.addAttribute("selectedDifficultyLevelName", selectedDifficultyLevelName);
		logger.info(" ### selectedDifficultyLevelName: " + selectedDifficultyLevelName);
		return "forward:/test/thirdstep";
	}

}

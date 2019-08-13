package edu.awieclawski.quiz.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/*
 * Enables direct controllers keep hidden before session layer
*/
@Controller
@RequestMapping(path = "/quiz")
public class QuizController {

	@GetMapping(path = "/firststep")
	public String redirectToFirstStep(
			@ModelAttribute("errorMessage") String errorMessage,
			@ModelAttribute("infoMessage") String infoMessage, 
			ModelMap model, 
			RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("errorMessage", errorMessage);
		redirectAttributes.addFlashAttribute("infoMessage", infoMessage);
		return "forward:/testtype/firststep";
	}

	@GetMapping(path = "/secondstep")
	public String redirectToSecondStep(
			@ModelAttribute("errorMessage") String errorMessage,
			@ModelAttribute("infoMessage") String infoMessage, 
			ModelMap model, 
			RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("errorMessage", errorMessage);
		redirectAttributes.addFlashAttribute("infoMessage", infoMessage);
		return "forward:/difficultylevel/secondstep";
	}

	@GetMapping(path = "/thirdstep")
	public String redirectToThirdStep(
			@ModelAttribute("errorMessage") String errorMessage,
			@ModelAttribute("infoMessage") String infoMessage, 
			ModelMap model, 
			RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("errorMessage", errorMessage);
		redirectAttributes.addFlashAttribute("infoMessage", infoMessage);
		return "forward:/test/thirdstep";
	}
	
	@GetMapping(path = "/exam")
	public String redirectToExam(
			@ModelAttribute("errorMessage") String errorMessage,
			@ModelAttribute("infoMessage") String infoMessage, 
			ModelMap model, 
			RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("errorMessage", errorMessage);
		redirectAttributes.addFlashAttribute("infoMessage", infoMessage);
		return "forward:/questionset/exam";
	}

}

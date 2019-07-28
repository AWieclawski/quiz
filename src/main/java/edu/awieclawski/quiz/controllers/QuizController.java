package edu.awieclawski.quiz.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(path = "/quiz")
public class QuizController {

	@GetMapping(path = "/firststep")
	public String redirectToFirstStep(Model model) {
		return "forward:/testtype/firststep";
	}

	@GetMapping(path = "/secondstep")
	public String redirectToSecondStep(@ModelAttribute("testTypeNameToPost") String testTypeNameToPost, Model model,
			RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("testTypeNameToPost", testTypeNameToPost);
		return "forward:/difficultylevel/secondstep";
	}

}

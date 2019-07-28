package edu.awieclawski.quiz.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.awieclawski.quiz.models.Test;
import edu.awieclawski.quiz.repositories.TestRepository;

@Controller
@RequestMapping(path = "/test")
@SessionAttributes("slectedTest")
public class TestController {

	private static final Logger logger = LogManager.getLogger(TestController.class.getName());

	@Autowired
	private TestRepository testRepository;

	@GetMapping(path = "/add")
	public @ResponseBody String addNewTestType(@RequestParam String testName) {
		Test t = new Test();
		t.setTestName(testName);
		testRepository.save(t);

		return testName.concat(" saved. OK\n");
	}

	@GetMapping(path = "/all")
	public @ResponseBody Iterable<Test> getAllTests() {
		return testRepository.findAll();
	}

	@GetMapping(path = "/thirdstep")
	public String presentTestTypes(
			@ModelAttribute("selectedDifficultyLevelName") String selectedDifficultyLevelName,
			@ModelAttribute("selectedTestTypeName") String selectedTestTypeName, 
			Model model
			) {
		model.addAttribute("selectedDifficultyLevelName", selectedDifficultyLevelName);
		logger.info(" ### selectedDifficultyLevelName: " + selectedDifficultyLevelName);
		model.addAttribute("tests", testRepository.findAll());
		logger.info(" $$$ testRepository count: " + testRepository.count());
		return "/quiz/stepthird";
	}
	
	@PostMapping(path = "/thirdstep")
	public String selectTest(
			@ModelAttribute("test_Id") Long selectedTestId,
			Model model,
			RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("selectedTestName",
				testRepository.findById(selectedTestId).get().getTestName());
		logger.info(" *** selectTest() test_Id: {}", selectedTestId);
		model.addAttribute("selectedTest",testRepository.findById(selectedTestId).get());
		return "redirect:/quiz/exam";
	}
}

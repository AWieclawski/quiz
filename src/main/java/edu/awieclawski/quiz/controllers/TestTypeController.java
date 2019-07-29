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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.awieclawski.quiz.models.TestType;
import edu.awieclawski.quiz.repositories.TestTypeRepository;

@Controller
@RequestMapping(path = "/testtype")
public class TestTypeController {

	private static final Logger logger = LogManager.getLogger(TestTypeController.class.getName());

	@Autowired
	private TestTypeRepository testTypeRepository;

	@GetMapping(path = "/add")
	public @ResponseBody String addNewTestType(@RequestParam String testTypeName) {
		TestType tt = new TestType();
		tt.setTestTypeName(testTypeName);
		testTypeRepository.save(tt);

		return testTypeName.concat(" saved. OK\n");
	}

	@GetMapping(path = "/all")
	public @ResponseBody Iterable<TestType> getAllTestTypes() {
		return testTypeRepository.findAll();
	}

	@GetMapping(path = "/firststep")
	public String presentTestTypes(Model model) {
		logger.debug("testTypeRepository count: " + testTypeRepository.count());
		model.addAttribute("testTypes", testTypeRepository.findAll());
		return "/quiz/stepfirst";
	}

	@PostMapping(path = "/firststep")
	public String selectTestType(
			@ModelAttribute("testType_Id") Long selectedTestTypeId, 
			ModelMap model,
			RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("selectedTestTypeName",
				testTypeRepository.findById(selectedTestTypeId).get().getTestTypeName());
		logger.debug("selectTestType() testType_Id: {}", selectedTestTypeId);
		return "redirect:/quiz/secondstep";
	}

}

package edu.awieclawski.quiz.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.awieclawski.quiz.models.TestType;
import edu.awieclawski.quiz.repositories.TestTypeRepository;

@Controller
@RequestMapping(path = "/testtype")
public class TestTypeController {

	private static final Logger logger = LogManager.getLogger(TestTypeController.class.getName());

	@Autowired
	private TestTypeRepository testTypeRepository;

	@GetMapping(path = "/firststep")
	public String presentTestTypes(ModelMap model) {
		model.addAttribute("results", testTypeRepository.findAll());
		model.addAttribute("resultsName", "test types");
		model.addAttribute("resultName", "test type");
		logger.info(" $$$ testTypeRepository count: " + testTypeRepository.count());
		return "/quiz/stepfirst";
	}

	@PostMapping(path = "/firststep")
	public String selectTestType(
			HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		HttpSession session = request.getSession(false);
		if (session == null) {
			session = request.getSession();
		}
        String selectedTestTypeIdToString = null;
        selectedTestTypeIdToString = request.getParameter("submittedTestType_Id");
		
        //check if submitted value is empty
		if (selectedTestTypeIdToString != null) {
			Long selectedTestTypeId = Long.valueOf(selectedTestTypeIdToString);
			TestType selectedTestType = testTypeRepository.findById(selectedTestTypeId).get();
			session.setAttribute("sessionTestType", selectedTestType);
			logger.info(" *** sessionTestType set to session: " + selectedTestType.toString());
			return "redirect:/quiz/secondstep";
//		return "redirect:/difficultylevel/secondstep";
		} else {
			return "redirect:/quiz/firststep";
		}
	}

}

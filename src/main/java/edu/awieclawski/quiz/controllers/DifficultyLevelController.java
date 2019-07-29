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

import edu.awieclawski.quiz.models.DifficultyLevel;
import edu.awieclawski.quiz.repositories.DifficultyLevelRepository;

@Controller
@RequestMapping(path = "/difficultylevel")
public class DifficultyLevelController {

	private static final Logger logger = LogManager.getLogger(DifficultyLevelController.class.getName());

	@Autowired
	DifficultyLevelRepository difficultyLevelRepository;

	@GetMapping(path = "/add")
	public @ResponseBody String addNewDifficultyLevel(@RequestParam String difficultyLevelName) {
		DifficultyLevel dl = new DifficultyLevel();
		dl.setDifficultyLevelName(difficultyLevelName);
		difficultyLevelRepository.save(dl);

		return difficultyLevelName.concat(" saved. OK\n");
	}

	@GetMapping(path = "/all")
	public @ResponseBody Iterable<DifficultyLevel> getAllDifficultyLevels() {
		return difficultyLevelRepository.findAll();
	}

	@GetMapping(path = "/secondstep")
	public String presentDifficultyLevels(
			@ModelAttribute("selectedTestTypeName") String selectedTestTypeName,
			Model model) {
		model.addAttribute("selectedTestTypeName", selectedTestTypeName);
		logger.info(" ### selectedTestTypeName: " + selectedTestTypeName);
		model.addAttribute("difficultyLevels", difficultyLevelRepository.findAll());
		logger.info(" $$$ testRepository count: " + difficultyLevelRepository.count());
		return "/quiz/stepsecond";
	}

	@PostMapping(path = "/secondstep")
	public String selectDifficultyLevel(
			@ModelAttribute("difficultyLevel_Id") Long selectedDifficultyLevelId,
			@ModelAttribute("selectedTestTypeName") String selectedTestTypeName, 
			ModelMap model,
			RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("selectedDifficultyLevelName",
				difficultyLevelRepository.findById(selectedDifficultyLevelId).get().getDifficultyLevelName());
		logger.info(" *** selectDifficultyLevel() difficultyLevel_Id: {}", selectedDifficultyLevelId);
		
		redirectAttributes.addFlashAttribute("selectedTestTypeName", selectedTestTypeName);
		logger.info( " &&& remind redirected selectedTestTypeName: "+selectedTestTypeName);
		return "redirect:/quiz/thirdstep";
	}

}

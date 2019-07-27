package edu.awieclawski.quiz.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
	public String presentDifficultyLevels(Model model) {
//		logger.debug("presentDifficultyLevels()");
		logger.info("difficultyLevelRepository count: " + difficultyLevelRepository.count());
		model.addAttribute("difficultyLevels", difficultyLevelRepository.findAll());
		return "/quiz/stepsecond";
	}

}

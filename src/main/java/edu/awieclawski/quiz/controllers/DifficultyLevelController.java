package edu.awieclawski.quiz.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.awieclawski.quiz.models.DifficultyLevel;
import edu.awieclawski.quiz.repositories.DifficultyLevelRepository;

@Controller
@RequestMapping(path = "/difficultylevel")
public class DifficultyLevelController {

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

}

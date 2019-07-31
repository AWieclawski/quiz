package edu.awieclawski.quiz.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.awieclawski.quiz.exceptions.DifficultyLevelNotFoundException;
import edu.awieclawski.quiz.models.DifficultyLevel;
import edu.awieclawski.quiz.repositories.DifficultyLevelRepository;

@RestController
@RequestMapping("/api")
public class DifficultyLevelApiController {
	
	@Autowired DifficultyLevelRepository difficultyLevelRepository;
	
	@GetMapping("/difflevels")
	Iterable<DifficultyLevel> getAllDifficultyLevels(){
		return difficultyLevelRepository.findAll();
	}

	@GetMapping("/difflevels/{difficultyLevelId}")
	DifficultyLevel getDifficultyLevelById(@PathVariable Long difficultyLevelId) {
		return difficultyLevelRepository.findById(difficultyLevelId)
				.orElseThrow(()->new DifficultyLevelNotFoundException(difficultyLevelId));
	}
	
	@PostMapping("/difflevels")
	DifficultyLevel addNewDifficultyLevel(@RequestBody DifficultyLevel newDifficultyLevel) {
		return difficultyLevelRepository.save(newDifficultyLevel);
	}
}

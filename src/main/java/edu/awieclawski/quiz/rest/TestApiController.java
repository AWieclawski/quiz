package edu.awieclawski.quiz.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.awieclawski.quiz.exceptions.TestNotFoundException;
import edu.awieclawski.quiz.models.Test;
import edu.awieclawski.quiz.repositories.TestRepository;

@RestController
@RequestMapping(path = "/api")
public class TestApiController {

	@Autowired
	TestRepository testRepository;

	@GetMapping("/tests")
	Iterable<Test> allTests() {
		return testRepository.findAll();
	}

	@GetMapping("/tests/{testId}")
	Test getTestById(@PathVariable Long testId) {
		return testRepository.findById(testId)
				.orElseThrow(() -> new TestNotFoundException(testId));
	}

	@PostMapping("/tests")
	Test addNewTest(@RequestBody Test newTest) {
		return testRepository.save(newTest);
	}

}

package edu.awieclawski.quiz.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.awieclawski.quiz.exceptions.TestTypeNotFoundException;
import edu.awieclawski.quiz.models.TestType;
import edu.awieclawski.quiz.repositories.TestTypeRepository;

@RestController
@RequestMapping("/api")
public class TestTypeApiController {
	
	@Autowired TestTypeRepository testTypeRepository;
	
	@GetMapping("/ttypes")
	Iterable<TestType> getAllTestTypes(){
		return testTypeRepository.findAll();
	}

	@GetMapping("/ttypes/{testTypeId}")
	TestType getTestTypeById(@PathVariable Long testTypeId) {
		return testTypeRepository.findById(testTypeId)
				.orElseThrow(()->new TestTypeNotFoundException(testTypeId));
	}
	
	@PostMapping("/ttypes")
	TestType addNewTestType(@RequestBody TestType newTestType) {
		return testTypeRepository.save(newTestType);
	}
}

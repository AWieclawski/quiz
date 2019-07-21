package edu.awieclawski.quiz.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.awieclawski.quiz.models.TestType;
import edu.awieclawski.quiz.repositories.TestTypeRepository;

@Controller
@RequestMapping(path = "/testtype")
public class TestTypeController {

	@Autowired
	private TestTypeRepository testTypeRepository;

	@GetMapping(path = "/add")
	public @ResponseBody String addNewTestType(@RequestParam String testTypeName) {
		TestType tt = new TestType();
		tt.setTestTypeName(testTypeName);
		return testTypeName.concat(" saved. OK\n");
	}

	@GetMapping (path="/all")
	public @ResponseBody Iterable<TestType> getAllTestTypes(){
		return testTypeRepository.findAll();
		
	}
	
}

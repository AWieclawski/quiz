package edu.awieclawski.quiz.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.awieclawski.quiz.exceptions.QuestionSetNotFoundException;
import edu.awieclawski.quiz.models.QuestionSet;
import edu.awieclawski.quiz.repositories.QuestionSetRepository;

@RestController
@RequestMapping(path = "/api")
public class QestionSetApiController {

	@Autowired
	private QuestionSetRepository questionSetRepository;

	@GetMapping("/qset")
	Iterable<QuestionSet> allQuestionSets() {
		return questionSetRepository.findAll();
	}

	@GetMapping("/qset/{questionSetId}")
	QuestionSet getQuestionSetById(@PathVariable Long questionSetId) {
		return questionSetRepository.findById(questionSetId)
				.orElseThrow(() -> new QuestionSetNotFoundException(questionSetId));
	}

	@PostMapping("/qset")
	QuestionSet addNewQuestionSet(@RequestBody QuestionSet newQuestionSet) {
		return questionSetRepository.save(newQuestionSet);
	}
}
/*
 * Iterable<QuestionSet> all(): $ curl -v localhost:8080/api/qset
 *
 * addNewQuestionSet: $ curl -X POST localhost:8080/api/qset -H
 * 'Content-type:application/json'-d '{\ "questionSetId":1,\
 * "question":"Question_01_HTML_EASY",\
 * "firstAnswer":"firstAnswer_of_Question_01",\
 * "secondAnswer":"secondAnswer_of_Question_01",\
 * "thirdAnswer":"thirdAnswer_of_Question_01",\
 * "fourthAnswer":"fourthAnswer_of_Question_01",\ "test":\ {"testId":1,\
 * "testName":"HTML_EASY_01",\ "status":"active",\ "testType":\
 * {"testTypeId":1,\ "testTypeName":"HTML"},\ "difficultyLevel":\
 * {"difficultyLevelId":1,\ "difficultyLevelName":"Easy"}\ }\ }'
 */

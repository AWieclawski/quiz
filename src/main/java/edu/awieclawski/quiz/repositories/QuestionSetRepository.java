package edu.awieclawski.quiz.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import edu.awieclawski.quiz.models.QuestionSet;
import edu.awieclawski.quiz.models.Test;

public interface QuestionSetRepository extends CrudRepository<QuestionSet, Long> {
	
	List<QuestionSet> findQuestionSetsByTest(Test test);

}

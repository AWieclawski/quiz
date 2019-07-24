package edu.awieclawski.quiz.repositories;

import org.springframework.data.repository.CrudRepository;
import edu.awieclawski.quiz.models.QuestionSet;

public interface QuestionSetRepository extends CrudRepository<QuestionSet, Long> {

}

package edu.awieclawski.quiz.repositories;

import org.springframework.data.repository.CrudRepository;

import edu.awieclawski.quiz.models.DifficultyLevel;
import edu.awieclawski.quiz.models.Test;
import edu.awieclawski.quiz.models.TestType;

public interface TestRepository extends CrudRepository<Test, Long> {

	Test findByTestTypeAndDifficultyLevel(TestType testType, DifficultyLevel difficultyLevel);

}

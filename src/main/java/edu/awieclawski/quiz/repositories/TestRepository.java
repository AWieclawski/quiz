package edu.awieclawski.quiz.repositories;

import org.springframework.data.repository.CrudRepository;

import edu.awieclawski.quiz.models.DifficultyLevel;
import edu.awieclawski.quiz.models.Test;
import edu.awieclawski.quiz.models.TestType;

public interface TestRepository extends CrudRepository<Test, Long> {

	Iterable<Test> findTestsByTestTypeAndDifficultyLevel(TestType testType, DifficultyLevel difficultyLevel);

}

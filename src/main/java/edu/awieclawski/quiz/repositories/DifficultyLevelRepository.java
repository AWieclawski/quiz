package edu.awieclawski.quiz.repositories;

import org.springframework.data.repository.CrudRepository;
import edu.awieclawski.quiz.models.DifficultyLevel;

//This will be AUTO IMPLEMENTED by Spring into a Bean called difficultyLevelRepository
//CRUD refers Create, Read, Update, Delete

public interface DifficultyLevelRepository extends CrudRepository<DifficultyLevel,Long> {

}

package edu.awieclawski.quiz.repositories;

import org.springframework.data.repository.CrudRepository;

import edu.awieclawski.quiz.models.TestType;

//This will be AUTO IMPLEMENTED by Spring into a Bean called testTypeRepository
//CRUD refers Create, Read, Update, Delete

public interface TestTypeRepository extends CrudRepository<TestType, Long>{

}

package edu.awieclawski.quiz.repositories;

import org.springframework.data.repository.CrudRepository;

import edu.awieclawski.quiz.models.Result;


//This will be AUTO IMPLEMENTED by Spring into a Bean called ResultRepository
//CRUD refers Create, Read, Update, Delete

public interface ResultRepository extends CrudRepository<Result, Long> {

}

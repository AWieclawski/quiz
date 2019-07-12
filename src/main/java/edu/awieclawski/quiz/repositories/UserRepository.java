package edu.awieclawski.quiz.repositories;

import org.springframework.data.repository.CrudRepository;

import edu.awieclawski.quiz.models.User;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserRepository extends CrudRepository<User, Long> {

}

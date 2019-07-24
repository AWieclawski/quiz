package edu.awieclawski.quiz.repositories;

import org.springframework.data.repository.CrudRepository;
import edu.awieclawski.quiz.models.Test;

public interface TestRepository extends CrudRepository<Test, Long> {

}

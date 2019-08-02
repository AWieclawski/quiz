package edu.awieclawski.quiz.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.awieclawski.quiz.exceptions.UserNotFoundException;
import edu.awieclawski.quiz.models.User;
import edu.awieclawski.quiz.repositories.UserRepository;

@RestController
@RequestMapping(path = "/api")
public class UserApiController {

	@Autowired
	UserRepository userRepository;

	@GetMapping("/users")
	Iterable<User> allUsers() {
		return userRepository.findAll();
	}

	@GetMapping("/users/{userId}")
	User getUserById(@PathVariable Long userId) {
		return userRepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundException(userId));
	}

	@PostMapping("/users")
	User addNewUser(@RequestBody User newUser) {
		return userRepository.save(newUser);
	}

}

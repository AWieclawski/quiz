package edu.awieclawski.quiz.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.awieclawski.quiz.models.Privileges;
import edu.awieclawski.quiz.models.Statuses;
import edu.awieclawski.quiz.models.User;
import edu.awieclawski.quiz.repositories.UserRepository;

@Controller
@RequestMapping(path = "/user")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@GetMapping(path = "/add")
	public @ResponseBody String addNewUser(
			@RequestParam String userName, 
			@RequestParam String login,
			@RequestParam String password, 
			@RequestParam int privilege, 
			@RequestParam Statuses status
	) {

		User n = new User();
		n.setUserName(userName);
		n.setLogin(login);
		n.setPassword(password);
		n.setPrivilege(Privileges.valueOf(privilege));
		n.setStatus(status);
		userRepository.save(n);

		return userName.concat(" saved. OK\n");
	}

	@GetMapping(path = "/all")
	public @ResponseBody Iterable<User> getAllUsers() {
		return userRepository.findAll();
	}

}
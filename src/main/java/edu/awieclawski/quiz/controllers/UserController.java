package edu.awieclawski.quiz.controllers;

import org.hibernate.boot.archive.internal.ExplodedArchiveDescriptor;
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
	public @ResponseBody String addNewUser(@RequestParam String userName, @RequestParam String login,
			@RequestParam String password,
			// API doesn't know the hard coded enum constants in code & db,
			// only their keys in fields
			@RequestParam int privilege, @RequestParam int status) {
		privilege = Privileges.EXPLOITER.privilegeKey();// API always establishes not admin privilege
		User u = new User();
		u.setUserName(userName);
		u.setLogin(login);
		u.setPassword(password);
		u.setPrivilege(Privileges.valueOf(privilege));
		u.setStatus(Statuses.valueOf(status));
		userRepository.save(u);

		return userName.concat(" saved. OK\n");
	}

	@GetMapping(path = "/all")
	public @ResponseBody Iterable<User> getAllUsers() {
		return userRepository.findAll();
	}

}
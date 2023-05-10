package edu.training.user_management_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.training.user_management_system.entity.User;
import edu.training.user_management_system.service.UserService;



@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;

	@PostMapping
	public User saveUser(@RequestBody User user, @RequestParam int adminId) {
		return userService.saveUser(user,adminId);
	}
	
	@GetMapping
	public User findUserById(@RequestParam int userId) {
		return userService.findUserById(userId);
	}
	
	@PutMapping
	public User updateUserById(@RequestParam int userId,@RequestBody User user) {
		return userService.updateUserById(userId, user);
	}
	
	@DeleteMapping
	public User deleteUserById(@RequestParam int userId, @RequestParam int adminId) {
		return userService.deleteUserById(userId,adminId);
	}
	
	@GetMapping("/admin")
	public List<User> getUsers(@RequestParam int adminId){
		return userService.getUsers(adminId);
	}
}

package edu.training.user_management_system.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.training.user_management_system.entity.User;
import edu.training.user_management_system.repository.AdminRepo;
import edu.training.user_management_system.repository.UserRepo;

@Repository
public class UserDao {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private AdminRepo adminRepo;
	
	public User saveUser(User user) {
		return userRepo.save(user);
	}
	
	public User deleteUser(User user) {
		userRepo.delete(user);
		return user;
	}
	
	public User findUserById(int userId) {
		Optional<User> optional = userRepo.findById(userId);
		if(optional.isEmpty()) {
			return null;
		}else {
			User user = optional.get();
			return user;
		}
	}
	
	public User updateUserById(int userId,User user) {
		Optional<User> optional = userRepo.findById(userId);
		if(optional.isEmpty()) {
			return null;
		}else {
			user.setUserId(userId);
			return userRepo.save(user);
		}
	}
	
	public User deleteUserById(int userId) {
		 Optional<User> optional = userRepo.findById(userId);
		 if(optional.isEmpty()) {
			 return null;
		 }else {
			 userRepo.deleteById(userId);
			 return optional.get();
		 }
	}
	
	public List<User> getUsers(int adminId){
		Optional<List<User>> optional = adminRepo.getUsers(adminId);
		if(optional.isEmpty()) {
			return null;
		}else {
			return optional.get();
		}
	}
}

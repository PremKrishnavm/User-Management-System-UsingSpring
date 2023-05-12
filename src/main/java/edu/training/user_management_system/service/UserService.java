package edu.training.user_management_system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import edu.training.user_management_system.dao.AdminDao;
import edu.training.user_management_system.dao.UserDao;
import edu.training.user_management_system.dto.UserDto;
import edu.training.user_management_system.entity.Admin;
import edu.training.user_management_system.entity.User;
import edu.training.user_management_system.exception.AdminNotFoundByIdException;
import edu.training.user_management_system.exception.UsersNotFoundException;
import edu.training.user_management_system.util.ResponseStructure;


@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private AdminDao adminDao;
	
	@Autowired
	private UserDto userDto;
	
	public ResponseEntity<ResponseStructure<User>> saveUser(User user, int adminId) {

		Admin admin = adminDao.findAdminById(adminId);
		List<User> users = admin.getUsers();
		users.add(user);
		admin.setUsers(users);
		User user2 = userDao.saveUser(user);
		adminDao.saveAdmin(admin);
		
		userDto.setUserId(user2.getUserId());
		userDto.setUserName(user2.getUserName());
		userDto.setUserEmail(user2.getUserEmail());
		
		ResponseStructure<User> responseStructure = new ResponseStructure<>();
		responseStructure.setStatusCode(HttpStatus.OK.value());
		responseStructure.setMessage("User Saved Successfully..");
		responseStructure.setData(userDto);
		return new ResponseEntity<ResponseStructure<User>>
		(responseStructure,HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<User>> findUserById(int userId) {
		User user = userDao.findUserById(userId);
		if(user!=null) {
			userDto.setUserId(user.getUserId());
			userDto.setUserName(user.getUserName());
			userDto.setUserEmail(user.getUserEmail());
			
			ResponseStructure<User> responseStructure = new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.FOUND.value());
			responseStructure.setMessage("User found Successfully..");
			responseStructure.setData(userDto);
			return new ResponseEntity<ResponseStructure<User>>
			(responseStructure,HttpStatus.FOUND);
		}else {
			throw new UsersNotFoundException("Failed to find to Users!!");
		}
	}
	
	public ResponseEntity<ResponseStructure<User>> updateUserById(int userId,User user) {
		User user2 = userDao.updateUserById(userId, user);
		if(user2!=null) {
			userDto.setUserId(user2.getUserId());
			userDto.setUserName(user2.getUserName());
			userDto.setUserEmail(user2.getUserEmail());
			
			ResponseStructure<User> responseStructure = new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("User found Successfully..");
			responseStructure.setData(userDto);
			return new ResponseEntity<ResponseStructure<User>>
			(responseStructure,HttpStatus.OK);
	}else {
		throw new UsersNotFoundException("Failed to find to User!!");
	}
}
	
	public ResponseEntity<ResponseStructure<User>> deleteUserById(int userId, int adminId) {
		User user = userDao.findUserById(userId);
		if(user!=null) {
			 Admin admin = adminDao.findAdminById(adminId);
			 userDto.setUserId(user.getUserId());
				userDto.setUserName(user.getUserName());
				userDto.setUserEmail(user.getUserEmail());
				
				ResponseStructure<User> responseStructure = new ResponseStructure<>();
				responseStructure.setStatusCode(HttpStatus.OK.value());
				responseStructure.setMessage("User found Successfully..");
				responseStructure.setData(userDto);
			 if(admin!=null) {
				 List<User> users = admin.getUsers();
				 users.remove(user);
				 admin.setUsers(users);
				 adminDao.updateAdminById(adminId, admin);
				 userDao.deleteUserById(userId);
				 return new ResponseEntity<ResponseStructure<User>>
					(responseStructure,HttpStatus.OK);
			 }else {
				 throw new AdminNotFoundByIdException("Failed to find to Admin!!");
			 }
		}else {
			throw new UsersNotFoundException("Failed to find to User!!");
		}
	}
	
	public ResponseEntity<ResponseStructure<List<User>>> getUsers(int adminId){
		User user = userDao.findUserById(adminId);
		if(user!=null) {
			List<User> users = userDao.getUsers(adminId);
			if(users!=null) {
				ResponseStructure<List<User>> responseStructure = new ResponseStructure<>();
				responseStructure.setStatusCode(HttpStatus.FOUND.value());
				responseStructure.setMessage("Users Found");
				responseStructure.setData(users);
				return new ResponseEntity<ResponseStructure<List<User>>>(responseStructure,HttpStatus.FOUND);	
			}else {
				throw new UsersNotFoundException("Failed to find Users!!!");
			}
		}else {
			throw new AdminNotFoundByIdException("Failed to find Users!!!");
		}
	}
}

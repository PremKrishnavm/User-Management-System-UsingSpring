package edu.training.user_management_system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.training.user_management_system.dao.AdminDao;
import edu.training.user_management_system.dao.UserDao;
import edu.training.user_management_system.entity.Admin;
import edu.training.user_management_system.entity.User;


@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private AdminDao adminDao;
	
	public User saveUser(User user, int adminId) {

		Admin admin = adminDao.findAdminById(adminId);
		List<User> users = admin.getUsers();
		users.add(user);
		admin.setUsers(users);
		User user2 = userDao.saveUser(user);
		adminDao.saveAdmin(admin);
		
		return user2;
	}
	
	public User findUserById(int userId) {
		return userDao.findUserById(userId);
	}
	
	public User updateUserById(int userId,User user) {
		return userDao.updateUserById(userId, user);
	}
	
	public User deleteUserById(int userId, int adminId) {
		User user = userDao.findUserById(userId);
		if(user!=null) {
			 Admin admin = adminDao.findAdminById(adminId);
			 if(admin!=null) {
				 List<User> users = admin.getUsers();
				 users.remove(user);
				 admin.setUsers(users);
				 adminDao.updateAdminById(adminId, admin);
				 userDao.deleteUserById(userId);
				 return user;
			 }else {
				 return null;
			 }
		}else {
			return null;
		}
	}
	
	public List<User> getUsers(int adminId){
		return userDao.getUsers(adminId);
	}
}

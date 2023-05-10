package edu.training.user_management_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.training.user_management_system.dao.AdminDao;
import edu.training.user_management_system.entity.Admin;



@Service
public class AdminService {
	
	@Autowired
	private AdminDao adminDao;
	
	public Admin saveAdmin(Admin admin) {
		return adminDao.saveAdmin(admin);
	}
	
	public Admin findAdminById(int adminId) {
		return adminDao.findAdminById(adminId);
	}
	
	public Admin updateAdminById(int adminId,Admin admin) {
		return adminDao.updateAdminById(adminId, admin);
	}
	
	public Admin deleteAdminById(int adminId) {
		Admin admin = adminDao.findAdminById(adminId);
		if(admin!=null) {
			adminDao.deleteAdminBuId(adminId);
			return admin;
		}else {
			return null;
		}
	}
}

package edu.training.user_management_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import edu.training.user_management_system.dao.AdminDao;
import edu.training.user_management_system.dto.AdminDto;
import edu.training.user_management_system.entity.Admin;
import edu.training.user_management_system.exception.AdminNotFoundByIdException;
import edu.training.user_management_system.util.ResponseStructure;

@Service
public class AdminService {
	
	@Autowired
	private AdminDao adminDao;
	
	@Autowired
	private AdminDto adminDto;
	
	public ResponseEntity<ResponseStructure<Admin>> saveAdmin(Admin admin) {
		Admin admin2 = adminDao.saveAdmin(admin);
		adminDto.setAdminId(admin2.getAdminId());
		adminDto.setAdminName(admin2.getAdminName());
		adminDto.setAdminEmail(admin2.getAdminEmail());
		adminDto.setStudents(admin2.getUsers());
		
		ResponseStructure<Admin> responseStructure = new ResponseStructure<>();
		responseStructure.setStatusCode(HttpStatus.OK.value());
		responseStructure.setMessage("Admin Saved Succeessfully..");
		responseStructure.setData(adminDto);
		return new ResponseEntity<ResponseStructure<Admin>>
		(responseStructure,HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<Admin>> findAdminById(int adminId) {
		Admin admin2 = adminDao.findAdminById(adminId);
		if(admin2!=null) {
			adminDto.setAdminId(admin2.getAdminId());
			adminDto.setAdminName(admin2.getAdminName());
			adminDto.setAdminEmail(admin2.getAdminEmail());
			adminDto.setStudents(admin2.getUsers());
			
			ResponseStructure<Admin> responseStructure = new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.FOUND.value());
			responseStructure.setMessage("Admin Found..");
			responseStructure.setData(adminDto);
			return new ResponseEntity<ResponseStructure<Admin>>(responseStructure,HttpStatus.FOUND);
		}else {
			throw new AdminNotFoundByIdException("Failed to find to Admin!!");
		}
	}
	
	public ResponseEntity<ResponseStructure<Admin>> updateAdminById(int adminId,Admin admin) {
		Admin admin2 = adminDao.updateAdminById(adminId, admin);
		if(admin2!=null) {
			adminDto.setAdminId(admin2.getAdminId());
			adminDto.setAdminName(admin2.getAdminName());
			adminDto.setAdminEmail(admin2.getAdminEmail());
			adminDto.setStudents(admin2.getUsers());
			
			ResponseStructure<Admin> responseStructure = new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Admin Updated Succeessfully..");
			responseStructure.setData(adminDto);
			return new ResponseEntity<ResponseStructure<Admin>>
			(responseStructure,HttpStatus.OK);
		}else {
			throw new AdminNotFoundByIdException("Failed to Update Admin!!");
		}
	}
	
	public ResponseEntity<ResponseStructure<Admin>> deleteAdminById(int adminId) {
		Admin admin2 = adminDao.findAdminById(adminId);
		if(admin2!=null) {
			adminDao.deleteAdminBuId(adminId);
			adminDto.setAdminId(admin2.getAdminId());
			adminDto.setAdminName(admin2.getAdminName());
			adminDto.setAdminEmail(admin2.getAdminEmail());
			adminDto.setStudents(admin2.getUsers());
			
			ResponseStructure<Admin> responseStructure = new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Admin deleted Succeessfully..");
			responseStructure.setData(adminDto);
			return new ResponseEntity<ResponseStructure<Admin>>
			(responseStructure,HttpStatus.OK);
		}else {
			throw new AdminNotFoundByIdException("Failed to delete Admin!!");
		}
	}
}

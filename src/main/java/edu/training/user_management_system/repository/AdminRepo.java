package edu.training.user_management_system.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.training.user_management_system.entity.Admin;
import edu.training.user_management_system.entity.User;

public interface AdminRepo extends JpaRepository<Admin, Integer>{
	
	@Query(value = "select a.users from Admin a where a.adminId=?1")
	public Optional<List<User>> getUsers(int adminId);
}

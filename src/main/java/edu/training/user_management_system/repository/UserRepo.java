package edu.training.user_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.training.user_management_system.entity.User;

public interface UserRepo extends JpaRepository<User, Integer>{

}

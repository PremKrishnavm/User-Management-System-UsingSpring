package edu.training.user_management_system.dto;

import java.util.List;

import org.springframework.stereotype.Component;

import edu.training.user_management_system.entity.User;
import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class AdminDto {
	
	private int adminId;
	private String adminName;
	private String adminEmail;
	
	private List<User> students;

}

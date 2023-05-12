package edu.training.user_management_system.dto;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class UserDto {
	private int userId;
	private String userName;
	private String userEmail;
}

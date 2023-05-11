package edu.training.user_management_system.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@SuppressWarnings("serial")
public class UsersNotFoundException extends RuntimeException {
	private String message;
}

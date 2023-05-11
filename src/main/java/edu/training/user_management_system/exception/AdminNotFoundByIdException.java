package edu.training.user_management_system.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@SuppressWarnings("serial")
@Getter
@AllArgsConstructor
public class AdminNotFoundByIdException extends RuntimeException {
	
	private String message;
}

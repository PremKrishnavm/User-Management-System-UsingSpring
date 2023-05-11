package edu.training.user_management_system.util;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class ResponseStructure<T> {
	
	private int statusCode;
	private String message;
	private Object data;
}

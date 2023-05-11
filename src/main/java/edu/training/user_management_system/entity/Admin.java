package edu.training.user_management_system.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Admin {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int adminId;
	@NotNull(message="Admin name cannot be null")
	private String adminName;
	@NotNull(message="Admin Email cannot be null")
	private String adminEmail;
	@NotNull(message="Admin Password cannot be null")
	private String adminPassword;

	@OneToMany(cascade = CascadeType.ALL)
	private List<User> users;
}

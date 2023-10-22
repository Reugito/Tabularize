package com.tabularize.app.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UserRegistrationDTO {
	
	@NotBlank
	@NotNull
    private String name;
    

	@Email
	@NotBlank
	@NotNull
    private String email;

	@NotBlank
	@NotNull
    private String password;


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
	
	

}

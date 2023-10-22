package com.tabularize.app.model;



import com.tabularize.app.dto.UserRegistrationDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "password")
    private String password;

	public User(UserRegistrationDTO registerDto) {
		super();
		this.email = registerDto.getEmail();
		this.name = registerDto.getName();
		this.password = registerDto.getPassword();
	}

	public User() {
		super();
	}



	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	
    
    
    
    
}
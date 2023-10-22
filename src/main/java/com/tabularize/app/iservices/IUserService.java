package com.tabularize.app.iservices;

import org.springframework.stereotype.Service;

import com.tabularize.app.dto.LoginDTO;
import com.tabularize.app.dto.UserRegistrationDTO;

import jakarta.validation.Valid;

@Service
public interface IUserService {
	
	void registerUser(@Valid UserRegistrationDTO registrationDTO);

	String loginUser(@Valid LoginDTO loginDTO);

}

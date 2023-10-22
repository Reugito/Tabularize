package com.tabularize.app.contoller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tabularize.app.dto.LoginDTO;
import com.tabularize.app.dto.UserRegistrationDTO;
import com.tabularize.app.iservices.IUserService;
import com.tabularize.app.wrapper.ResponseWrapper;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/user")
public class UserController {

	@Autowired
	private IUserService userService;

	@PostMapping("/register")
	public ResponseWrapper<Void> registerUser(@Valid @RequestBody UserRegistrationDTO registrationDTO) {
		try {
			userService.registerUser(registrationDTO);
			return new ResponseWrapper<Void>(HttpStatus.OK, "User Registered Successfully", null);
		} catch (Exception e) {
			return new ResponseWrapper<Void>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null); 
		}
	}

	@PostMapping("/login")
	public ResponseWrapper<String> loginUser(@Valid @RequestBody LoginDTO loginDTO) {
		try {
			String token = userService.loginUser(loginDTO);
			if (token != null) {
				return new ResponseWrapper<String>(HttpStatus.OK, "User Login Successfull", token);
			} else {
				return new ResponseWrapper<String>(HttpStatus.NOT_FOUND, "Invalid credentials", null); 
			}
		} catch (Exception e) {
			return new ResponseWrapper<String>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null); 
		}
	}
}

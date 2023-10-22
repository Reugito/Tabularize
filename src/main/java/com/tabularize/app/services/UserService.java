package com.tabularize.app.services;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tabularize.app.dto.LoginDTO;
import com.tabularize.app.dto.UserRegistrationDTO;
import com.tabularize.app.exception.CustomException;
import com.tabularize.app.iservices.IUserService;
import com.tabularize.app.model.User;
import com.tabularize.app.repositories.UserRepository;

import jakarta.validation.Valid;

@Service
public class UserService implements IUserService {
	
	private static final Log logger = LogFactory.getLog(UserService.class);
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	Token myToken;
	
	@Autowired
	PasswordService passwordService;

	@Override
	public void registerUser(@Valid UserRegistrationDTO registrationDTO) {
        logger.info("registerUser starts");
        try {
            boolean emailExists = userRepo.existsByEmail(registrationDTO.getEmail()) ;

            if (emailExists) {
                logger.warn("EmailId Already Exists: "+ registrationDTO.getEmail());
                throw new CustomException("EmailId Already Exists");
            }

            String encryptedPassword = passwordService.encryptPassword(registrationDTO.getPassword());

            User newUser = new User(registrationDTO);
            newUser.setPassword(encryptedPassword);

            userRepo.save(newUser);

            logger.info("User registered successfully");

        }catch (CustomException e) {
        	throw e;
        }catch (Exception e) {
            logger.error("Error during user registration", e);
            throw new CustomException("Internal Server Error", e);
        }
    }
	
	public String loginUser(@Valid LoginDTO loginDTO) {
	    logger.info("loginUser starts");
	    try {
	        User user = userRepo.findByEmail(loginDTO.getUsername());

	        if (user == null) {
	            logger.warn("User with email not found: " + loginDTO.getUsername());
	            throw new CustomException("User not found");
	        }

	        if (passwordService.verifyPassword(loginDTO.getPassword(), user.getPassword())) {
	            logger.info("User logged in successfully");
	            
	            String token = myToken.createToken(user.getEmail());
	            
	            return token;
	        } else {
	            logger.warn("Invalid password for user: " + user.getEmail());
	            throw new CustomException("Invalid password");
	        }
	    } catch (CustomException e) {
	        throw e;
	    } catch (Exception e) {
	        logger.error("Error during user login", e);
	        throw new CustomException("Internal Server Error", e);
	    }
	}



}

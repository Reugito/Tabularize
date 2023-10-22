package com.tabularize.app.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.tabularize.app.model.User;


public interface UserRepository extends JpaRepository<User, String>{
	
	User findByEmail(String emailId);
    
    boolean existsByEmail(String emailId);
}


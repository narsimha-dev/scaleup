package com.scaleup.user.service;

import java.util.List;

import com.scaleup.user.exception.RecordNotFoundException;
import com.scaleup.user.model.User;
 
public interface UserService {

	User createOrUpdateUser(User user) ; //save/ update
	void deleteUserByEmail(String email) throws RecordNotFoundException;//
	//User findByEmail(String email); // get user email details
	User getUserById(Long id) throws RecordNotFoundException; // get user by id
	List<User> getFirstName(String name); // search
	List<User> getAllUsers();
     }